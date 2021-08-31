package com.example.cy.musicplayer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author cy
 * 实现跑马灯效果的TextView
 */

public class MarqueeTextView extends TextView {

    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
/*    返回textview是否处在选中的状态
    而只有选中的textview才能够实现跑马灯效果*/
    @Override
    public boolean isFocused() {
        return true;
    }

}
