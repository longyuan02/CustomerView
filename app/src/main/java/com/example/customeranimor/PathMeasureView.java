package com.example.customeranimor;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * author：chs
 * date：2019/3/30
 * des：
 */
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MQ on 2017/2/10.
 */

public class PathMeasureView extends View {
    private Paint mPaint;
    private Path mPath;
    private PathMeasure pathMeasure;
    private int mCenterX, mCenterY;
    private RectF mRectF;
    private int halfWidth = 300;
    private float[] pos = new float[2];
    private float[] tan = new float[2];
    private float[] pos1 = new float[2];
    private float[] pos2 = new float[2];
    private float[] pos3 = new float[2];
    private float[] pos4 = new float[2];
    private float[] pos5 = new float[2];
    private float[] pos6 = new float[2];
    private float[] pos7 = new float[2];
    private float[] pos8 = new float[2];
    private float[] pos9 = new float[2];
    private float[] pos10 = new float[2];
    private float[] pos11 = new float[2];
    private float[] pos12 = new float[2];
    private float[] pos13 = new float[2];
    private float[] pos14 = new float[2];

    public PathMeasureView(Context context) {
        this(context, null);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //初始化Paint
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);
        mPaint.setColor(Color.BLACK);
        //初始化Path
        mPath = new Path();
        //初始化PathMeasure
        pathMeasure = new PathMeasure();
        //初始化RectF
        mRectF = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenterX = w / 2;
        mCenterY = h / 2;
        mRectF.set(mCenterX - halfWidth, mCenterY - halfWidth, mCenterX + halfWidth, mCenterY + halfWidth);
        pos[0] = mCenterX - halfWidth;
        pos[1] = mCenterY - halfWidth;

        pos1[0] = pos2[0] = pos3[0] = pos4[0] = pos5[0] = pos6[0] = pos7[0] = pos8[0] = pos9[0] = pos10[0] = pos11[0] = pos12[0] = pos13[0] = pos14[0] = mCenterX - halfWidth;
        pos1[1] = pos2[1] = pos3[1] = pos4[1] = pos5[1] = pos6[1] = pos7[1] = pos8[1] = pos9[1] = pos10[1] = pos11[1] = pos12[1] = pos13[1] = pos14[1] = mCenterY - halfWidth;
    }

    float[] radii = new float[30];
    private int radius = 10;
    private int space = 12;
    private int Duration = 6;

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制矩形
        mPath.reset();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.addRoundRect(mRectF, 30, 30, Path.Direction.CW);
        pathMeasure.setPath(mPath, false);
        canvas.drawPath(mPath, mPaint);
        //绘制圆
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(pos[0], pos[1], radius, mPaint);
        canvas.drawCircle(pos1[0], pos1[1], radius - 2, mPaint);
        canvas.drawCircle(pos2[0], pos2[1], radius - 3, mPaint);
        canvas.drawCircle(pos3[0], pos3[1], radius - 4, mPaint);
        canvas.drawCircle(pos4[0], pos4[1], radius - 5, mPaint);
        canvas.drawCircle(pos5[0], pos5[1], radius - 6, mPaint);
        canvas.drawCircle(pos6[0], pos6[1], radius - 7, mPaint);


//        canvas.drawCircle(pos4[0], pos4[1], radius - 2, mPaint);
//        canvas.drawCircle(pos5[0], pos5[1], radius - 4, mPaint);
//        canvas.drawCircle(pos6[0], pos6[1], radius - 6, mPaint);
//        canvas.drawCircle(pos7[0], pos6[1], radius - 6, mPaint);
//        canvas.drawCircle(pos8[0], pos6[1], radius - 6, mPaint);
//        canvas.drawCircle(pos9[0], pos6[1], radius - 6, mPaint);
//        canvas.drawCircle(pos10[0], pos6[1], radius - 6, mPaint);
//        canvas.drawCircle(pos11[0], pos6[1], radius - 6, mPaint);
//        canvas.drawCircle(pos12[0], pos6[1], radius - 6, mPaint);
//        canvas.drawCircle(pos13[0], pos6[1], radius - 6, mPaint);
//        canvas.drawCircle(pos14[0], pos6[1], radius - 6, mPaint);
    }

    public List<ValueAnimator> animatorList = new ArrayList<>();

    public void startMove() {
        mPath.reset();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.addRoundRect(mRectF, 30, 30, Path.Direction.CW);
        pathMeasure.setPath(mPath, false);
        ValueAnimator animator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
        animator.setDuration(Duration * 1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float distance = (float) animation.getAnimatedValue();
                //tan[0]是邻边 tan[1]是对边
                pathMeasure.getPosTan(distance, pos, tan);
                postInvalidate();
            }

        });
        animator.addListener(animatorListener);
        animator.start();
    }

    public void startMove1() {
        mPath.reset();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.addRoundRect(mRectF, 30, 30, Path.Direction.CW);
        pathMeasure.setPath(mPath, false);
        ValueAnimator animator = ValueAnimator.ofFloat(space, pathMeasure.getLength() + space);
        animator.setDuration(Duration * 1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float distance = (float) animation.getAnimatedValue();
                //tan[0]是邻边 tan[1]是对边
                pathMeasure.getPosTan(distance, pos1, tan);
                postInvalidate();
            }

        });
        animator.addListener(animatorListener);
        animator.start();
    }

    public void startMove2() {
        mPath.reset();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.addRoundRect(mRectF, 30, 30, Path.Direction.CW);
        pathMeasure.setPath(mPath, false);
        ValueAnimator animator = ValueAnimator.ofFloat(2 * space, pathMeasure.getLength() + 2 * space);
        animator.setDuration(Duration * 1000);
        animator.setInterpolator(new LinearInterpolator());
//        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float distance = (float) animation.getAnimatedValue();
                //tan[0]是邻边 tan[1]是对边
                pathMeasure.getPosTan(distance, pos2, tan);
                postInvalidate();
            }

        });
        animator.addListener(animatorListener);
        animator.start();
    }

    public void startMove3() {
        mPath.reset();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.addRoundRect(mRectF, 30, 30, Path.Direction.CW);
        pathMeasure.setPath(mPath, false);
        ValueAnimator animator = ValueAnimator.ofFloat(3 * space, pathMeasure.getLength() + 3 * space);
        animator.setDuration(Duration * 1000);
        animator.setInterpolator(new LinearInterpolator());
//        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float distance = (float) animation.getAnimatedValue();
                //tan[0]是邻边 tan[1]是对边
                pathMeasure.getPosTan(distance, pos3, tan);
                postInvalidate();
            }

        });
        animator.addListener(animatorListener);
        animator.start();
    }

    public void startMove4() {
        ValueAnimator animator = ValueAnimator.ofFloat(-space, pathMeasure.getLength() - space);
        animator.setDuration(Duration * 1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float distance = (float) animation.getAnimatedValue();
                //tan[0]是邻边 tan[1]是对边
                pathMeasure.getPosTan(distance, pos4, tan);
                postInvalidate();
            }

        });
        animator.addListener(animatorListener);
        animator.start();
    }

    public void startMove5() {
        ValueAnimator animator = ValueAnimator.ofFloat(-2 * space, pathMeasure.getLength() - 2 * space);
        animator.setDuration(Duration * 1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float distance = (float) animation.getAnimatedValue();
                //tan[0]是邻边 tan[1]是对边
                pathMeasure.getPosTan(distance, pos5, tan);
                postInvalidate();
            }

        });
        animator.addListener(animatorListener);
        animator.start();
    }

    public void startMove6() {
        ValueAnimator animator = ValueAnimator.ofFloat(-3 * space, pathMeasure.getLength() - 3 * space);
        animator.setDuration(Duration * 1000);
        animator.setInterpolator(new LinearInterpolator());
//        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float distance = (float) animation.getAnimatedValue();
                //tan[0]是邻边 tan[1]是对边
                pathMeasure.getPosTan(distance, pos6, tan);
                postInvalidate();
            }

        });
        animator.addListener(animatorListener);
        animator.start();
    }

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            animation.start();
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

}