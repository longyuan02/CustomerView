package com.example.customeranimor;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


public class CanvasLightLine extends View {
    private Paint paint = new Paint();
    private Paint rectPaint;
    private Paint rectPaintMiddle;
    private Paint roundPaint;
    private long speed = 100;
    private int sideWidth = 500;//边缘光宽度
    private int radius = 50;//弧度
    private int padding = 8;//边缘偏移默认是像素
    public boolean action = false;
    private int drawColor = Color.parseColor("#FF0000");
    private Path clipPath;
    private int width;
    private int height;
    private Handler handler = new Handler(Looper.myLooper());
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, speed);
            angle = angle + 6;
            postInvalidate();
        }
    };
    private int angle = 45;//初始角度

    public CanvasLightLine(Context context) {
        super(context);
        initPaint();
        postInvalidate();
    }

    public CanvasLightLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取布局 设置属性
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CanvasLightLine);
//        drawColor = a.getColor(R.styleable.CanvasLightLine_sideColor, Color.BLUE);
//        radius = a.getInteger(R.styleable.CanvasLightLine_setRadius, 20);
//        padding = a.getInteger(R.styleable.CanvasLightLine_setSidePadding, 10);
        initPaint();
        handler.postDelayed(runnable, 1 * 200);
    }

    public CanvasLightLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public static CanvasLightLine create(Context context) {
        return new CanvasLightLine(context);
    }

    public boolean isAction() {
        return action;
    }

    public CanvasLightLine setInputLayoutParams(ViewGroup.LayoutParams layoutParams) {
        setLayoutParams(layoutParams);
        return this;
    }

    public CanvasLightLine setAction(boolean action) {
        this.action = action;
        if (action) {
            handler.postDelayed(runnable, 1 * 2000);
        } else {
            handler.removeCallbacksAndMessages(null);
        }
        return this;
    }

    public CanvasLightLine setSpeed(long inputSpeed) {
        this.speed = inputSpeed;
        return this;
    }

    public CanvasLightLine setSideWidth(int setWidth) {
        this.sideWidth = setWidth;
        return this;
    }

    private void initPaint() {
        //paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(sideWidth);
        paint.setAntiAlias(true);
//描边
        rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setColor(Color.WHITE);
        rectPaint.setAlpha(80);
        rectPaint.setAntiAlias(true);
        rectPaint.setStrokeWidth(2);


//        rectPaintMiddle = new Paint();
//        rectPaintMiddle.setStyle(Paint.Style.STROKE);
//        rectPaintMiddle.setColor(Color.RED);
//        rectPaintMiddle.setAntiAlias(true);
//        rectPaintMiddle.setStrokeWidth(3);

//内容区
        roundPaint = new Paint();
        roundPaint.setStrokeWidth(1);
        roundPaint.setColor(Color.RED);
        roundPaint.setStyle(Paint.Style.FILL);
        roundPaint.setAntiAlias(true);
        clipPath = new Path();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        clipPath.reset();
        clipPath.addRoundRect(new RectF(0f, 0f, width, height), radius, radius, Path.Direction.CW);
        //        //消除锯齿
        canvas.setDrawFilter(
                new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        canvas.clipPath(clipPath, Region.Op.INTERSECT);

        DrawLine(canvas);
        initRoundRect(canvas);
        drawRectStork(canvas);
    }

    //    int[] colors = {Color.RED, Color.GREEN, Color.BLUE};
    int[] colors = {Color.parseColor("#20FFFFFF"), Color.parseColor("#80FFFFFF"), Color.WHITE, Color.parseColor("#80FFFFFF"), Color.parseColor("#20FFFFFF")};

    float[] position = {0f, 0.2f, 0.5f, 0.7f, 1f};

    private void DrawLine(Canvas canvas) {
        //设置背景色
        //canvas.drawARGB(255, 139, 197, 186);
        canvas.drawARGB(10, 255, 255, 255);
        //最后将画笔去除Xfermode
        paint.setXfermode(null);
        float r = height * 0.48f;
        float ox = height / 2;
        float oy = width / 2;
        float y1 = (float) (ox + r * Math.sin(Math.toRadians(angle)));//45
        float x1 = (float) (oy + r * Math.cos(Math.toRadians(angle)));
        float y2 = (float) (ox + r * Math.sin(Math.toRadians(180 + angle)));
        float x2 = (float) (oy + r * Math.cos(Math.toRadians(180 + angle)));
        //设置渐变 这里没有设置
        //LinearGradient shaderGradient = new LinearGradient(x1, y1, x2, y2, Color.parseColor("#409FFF"), Color.parseColor("#1AFFFFFF"), Shader.TileMode.CLAMP);
//        SweepGradient sweepGradient = new SweepGradient(ox, oy, colors, null);
        LinearGradient linearGradient = new LinearGradient(x1, y1, x2, y2, colors, position, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        canvas.drawLine(x1, y1, x2, y2, paint);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawRectStork(Canvas canvas) {
        canvas.drawRoundRect(padding / 2, padding / 2, width - padding / 2, height - padding / 2, radius, radius, rectPaint);

//        canvas.drawRoundRect(0 + 4, 0 + 4, width - 4, height - 4, radius, radius, rectPaintMiddle);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initRoundRect(Canvas canvas) {
        LinearGradient linearGradient = new LinearGradient(0, 0, width, height, colors, position, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        canvas.drawRoundRect(padding, padding, width - padding, height - padding, radius, radius, roundPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = MeasureSpec.getSize(widthMeasureSpec);
        this.height = MeasureSpec.getSize(heightMeasureSpec);
        sideWidth = (int) (width * 1.5);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMeasureSpec == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, height);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, heightMeasureSpec);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthMeasureSpec, height);
        }
    }

}