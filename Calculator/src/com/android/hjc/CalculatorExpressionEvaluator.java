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

import android.util.Log;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;
import org.javia.arity.Util;

public class CalculatorExpressionEvaluator {

    String eva="Evaluator:";

    /**
     * The maximum number of significant digits to display.
     */
    private static final int MAX_DIGITS = 12;

    /**
     * A {@link Double} has at least 17 significant digits, we show the first {@link #MAX_DIGITS}
     * and use the remaining digits as guard digits to hide floating point precision errors.
     */
    private static final int ROUNDING_DIGITS = Math.max(17 - MAX_DIGITS, 0);

    private final Symbols mSymbols;
    private final CalculatorExpressionTokenizer mTokenizer;

    public CalculatorExpressionEvaluator(CalculatorExpressionTokenizer tokenizer) {
        mSymbols = new Symbols();
        mTokenizer = tokenizer;
    }

    public void evaluate(CharSequence expr, EvaluateCallback callback) {
        evaluate(expr.toString(), callback);
    }

    //具体执行计算的方法
    public void evaluate(String expr, EvaluateCallback callback) {
        //首先替换表达式中字符为通用字符
        Log.d(eva,"进入evaluate"+expr);
        expr = mTokenizer.getNormalizedExpression(expr);

        // remove any trailing operators
        //去除尾部的运算符
        while (expr.length() > 0 && "+-/*".indexOf(expr.charAt(expr.length() - 1)) != -1) {
            expr = expr.substring(0, expr.length() - 1);
        }

        //若此时表达式为空或为纯数字，则调用onEvaluate参数为Calculator.INVALID_RES_ID
        try {
            if (expr.length() == 0 || Double.valueOf(expr) != null) {
                Log.d(eva,"expr空或为纯数字");
                callback.onEvaluate(expr, null, Calculator.INVALID_RES_ID);
                return;
            }
        } catch (NumberFormatException e) {
            // expr is not a simple number
        }

        //过滤完成，这时的expr是正常的表达式
        try {
            String test=new String(".");
            Log.d(eva,String.valueOf(mSymbols.eval(test)));
            Log.d(eva,"过滤后表达式："+expr);
            //通过Arity的eval方法，得出该表达式的结果
            double result = mSymbols.eval(expr);
            Log.d("Evaluator:",String.valueOf(result));
            //当结果超出定义（0/0或根号下负数），实际在结果区输出不是数字
            if (Double.isNaN(result)) {
                Log.d("Evaluator:","isNaN");
                callback.onEvaluate(expr, null, R.string.error_nan);
            } else {
                // The arity library uses floating point arithmetic when evaluating the expression
                // leading to precision errors in the result. The method doubleToString hides these
                // errors; rounding the result by dropping N digits of precision.
                //当result合法时，先把double型四舍五入转为string
                final String resultString = mTokenizer.getLocalizedExpression(
                        Util.doubleToString(result, MAX_DIGITS, ROUNDING_DIGITS));
                Log.d(eva,resultString);
                callback.onEvaluate(expr, resultString, Calculator.INVALID_RES_ID);
            }
        } catch (SyntaxException e) {
            //转化过程中有语法错误，输出语法错误
            callback.onEvaluate(expr, null, R.string.error_syntax);
        }
    }

    public interface EvaluateCallback {
        public void onEvaluate(String expr, String result, int errorResourceId);
    }
}
