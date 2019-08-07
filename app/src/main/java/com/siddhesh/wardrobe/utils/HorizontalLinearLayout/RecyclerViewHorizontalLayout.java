package com.siddhesh.wardrobe.utils.HorizontalLinearLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class RecyclerViewHorizontalLayout extends LinearLayout {
    private float scale = PagerAdapter.BIG_SCALE;

    public RecyclerViewHorizontalLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewHorizontalLayout(Context context) {
        super(context);
    }

    public void setScaleBoth(float scale) {
        this.scale = scale;
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // The main mechanism to display scale animation, you can customize it as your needs
        int w = this.getWidth();
        int h = this.getHeight();
        canvas.scale(scale, scale, w / 2, h / 2);
    }
}