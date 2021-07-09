package com.example.customeranimor.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ChangeCameraAngleView extends View {
    private Context context;
    private int cWidth;
    private int cHeight;
    private Paint unSelectPaint;
    private Paint seletedPaint;
    private Paint backColorPaint;
    private int paddingHorizontal;
    private int paddingVertical;
    private int paddingTop = 24;
    private int itemHeight;
    private int AngleInde = 5;

    public ChangeCameraAngleView(Context context) {
        super(context);
        initPaint(context);
    }

    public ChangeCameraAngleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    public ChangeCameraAngleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    public void setAngleInde(int angleInde) {
        AngleInde = angleInde;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(0, 0, cWidth, cHeight);
        canvas.drawRoundRect(rectF, dp2px(context, 28), dp2px(context, 28), backColorPaint);
        for (int i = 0; i < 10; i++) {
            setDrawPosition(canvas, i);
        }
    }

    private void setDrawPosition(Canvas canvas, int idex) {
        RectF rectF = new RectF();
        rectF.left = paddingHorizontal;
        rectF.right = cWidth - paddingHorizontal;
        rectF.top = paddingVertical + paddingVertical * idex + itemHeight * 2 * idex + dp2px(context, paddingTop);
        rectF.bottom = itemHeight * 2 * (1 + idex) + paddingVertical * (1 + idex) + dp2px(context, paddingTop);

        if (idex >= AngleInde) {
            canvas.drawRect(rectF, unSelectPaint);
        } else {
            canvas.drawRect(rectF, seletedPaint);
        }
    }

    private void initPaint(Context context) {
        this.context = context;
        unSelectPaint = new Paint();
        unSelectPaint.setStyle(Paint.Style.FILL);
        unSelectPaint.setStrokeWidth(px2dp(context, 1));
        unSelectPaint.setColor(Color.RED);

        seletedPaint = new Paint();
        seletedPaint.setStrokeWidth(1);
        seletedPaint.setStyle(Paint.Style.STROKE);
        seletedPaint.setColor(Color.BLUE);

        backColorPaint = new Paint();
        backColorPaint.setColor(Color.parseColor("#8054CAFF"));
        backColorPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.cHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.cWidth = MeasureSpec.getSize(widthMeasureSpec);

        itemHeight = (cHeight - dp2px(context, paddingTop * 2)) / 30;
        paddingVertical = (cHeight - dp2px(context, paddingTop * 2)) / 30;
        paddingHorizontal = dp2px(context, 8);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMeasureSpec == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(cWidth, cHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(cWidth, heightMeasureSpec);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthMeasureSpec, cHeight);
        }
    }

    private int dp2px(Context context, int dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private int px2dp(Context context, int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
