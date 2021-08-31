/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.hjc;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;

public class CalculatorExpressionBuilder extends SpannableStringBuilder {

    private final CalculatorExpressionTokenizer mTokenizer;
    private boolean mIsEdited;
    String builder="Builder";

    public CalculatorExpressionBuilder(
            CharSequence text, CalculatorExpressionTokenizer tokenizer, boolean isEdited) {
        super(text);

        mTokenizer = tokenizer;
        mIsEdited = isEdited;
    }

    @Override
    public SpannableStringBuilder replace(int start, int end, CharSequence tb, int tbstart,
            int tbend) {
        Log.d(builder,"进入replace函数，start="+start+",end="+end+",tb="+tb.toString()+",tbstart="+tbstart+",tbend="+tbend);
        Log.d(builder,"length="+length());
        if (start != length() || end != length()) {
            mIsEdited = true;
            Log.d(builder,"进入replace函数isEdited=true");
            return super.replace(start, end, tb, tbstart, tbend);
        }

        //最后添加的字符，tbstart=0, tbend=1,并转化为通用字符
        String appendExpr =
                mTokenizer.getNormalizedExpression(tb.subSequence(tbstart, tbend).toString());
        if (appendExpr.length() == 1) {
            //将整个表达式也转为通用字符
            final String expr = mTokenizer.getNormalizedExpression(toString());
            // SPRD 515934 enter all numbers and operators, calculator crash
            final int len = expr.length();
            switch (appendExpr.charAt(0)) {
                case '.':
                    Log.d(builder,"小数点");
                    // don't allow two decimals in the same number
                    //排除两个小数点的情况，先找出原表达式中最后一个小数点的位置
                    final int index = expr.lastIndexOf('.');
                    // SPRD: 544823 modify for StringIndexOutOfBoundsException
                    //若原表达式中存在小数点且两个小数点之间只有数字或为空时，则重赋为空
                    if (index != -1 && index < start && TextUtils.isDigitsOnly(expr.substring(index + 1, start))) {
                        appendExpr = "";
                    }
                    break;
                case '+':
                case '*':
                case '/':
                    // don't allow leading operator-不允许+*/出现在首位
                    if (start == 0) {
                        appendExpr = "";
                        break;
                    }
                    /*SPRD: Bug 487833 don't allow leading operator change from - to * or / @{ */
                    //不允许跟在-号后面
                    if (start == 1 && expr.equals("-")) {
                        appendExpr = "";
                        break;
                    }
                    /* @} */
                    /* SPRD 515934 enter all numbers and operators, calculator crash @{ */
                    Log.d("Calculator", "len: " + len + " start: " + start+" expr: "+expr);
                    if (start > len) {
                        break;
                    }
                    /* @} */
                    // don't allow multiple successive operators 不许*+/跟在*+-/后，注意这里8*-6是可以的
                    while (start > 0 && "+-*/".indexOf(expr.charAt(start - 1)) != -1) {
                        --start;
                    }
                    // fall through
                case '-':
                    // don't allow -- or +- 不允许--或+-,8/-9或7*-9这种是合法的
                    // SPRD 515934 enter all numbers and operators,calculator crash
                    if ((start > 0) && (start <= len) && "+-".indexOf(expr.charAt(start - 1)) != -1) {
                        --start;
                    }

                    // mark as edited since operators can always be appended
                    mIsEdited = true;
                    break;
                default:
                    break;
            }
        }

        // since this is the first edit replace the entire string
        if (!mIsEdited && appendExpr.length() > 0) {
            start = 0;
            mIsEdited = true;
        }

        appendExpr = mTokenizer.getLocalizedExpression(appendExpr);
        return super.replace(start, end, appendExpr, 0, appendExpr.length());
    }
}
