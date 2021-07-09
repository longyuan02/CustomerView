package com.example.customeranimor;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class PathMeasureViewCopy extends View {
    private Paint mPaint;
    private Path mPath;
    private PathMeasure pathMeasure;
    private int mCenterX, mCenterY;
    private RectF mRectF;
    private int halfWidth = 300;
    private float[] tan = new float[2];
    private List<float[]> pointlsit = new ArrayList<>();
    private List<float[]> pointlsit1 = new ArrayList<>();
    int count = 21;

    public PathMeasureViewCopy(Context context) {
        this(context, null);
    }

    public PathMeasureViewCopy(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureViewCopy(Context context, AttributeSet attrs, int defStyleAttr) {
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
        for (int i = 0; i < count; i++) {
            float[] floats = new float[2];
            floats[0] = mCenterX - halfWidth;
            floats[1] = mCenterY - halfWidth;
            pointlsit.add(floats);
        }
        for (int i = 0; i < count; i++) {
            float[] floats = new float[2];
            floats[0] = mCenterX - halfWidth;
            floats[1] = mCenterY - halfWidth;
            pointlsit1.add(floats);
        }
    }

    private int radius = 6;
    private int space = 10;
    private int Duration = 3;
    private int rate = 1;
    public List<ValueAnimator> animatorList1 = new ArrayList<>();
    public List<ValueAnimator> animatorList2 = new ArrayList<>();
    public List<ValueAnimator> animatorList3 = new ArrayList<>();

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
        animatorList1.clear();
        animatorList2.clear();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < count; i++) {
            if (i < (count - 1) / 2) {
                mPaint.setAlpha(255 * (int) (count - 1.5 * i) / count);
                canvas.drawCircle(pointlsit.get(i)[0], pointlsit.get(i)[1], radius + i * rate, mPaint);
                Log.e("up========", ":" + i * rate);
            } else if (i == (count - 1) / 2) {
                mPaint.setAlpha(255);
                canvas.drawCircle(pointlsit.get(i)[0], pointlsit.get(i)[1], radius + i * rate, mPaint);
            } else {
                mPaint.setAlpha((255 * (int) (count - 1.5 * (i / 2)) / count));
                Log.e("up===1=====", ":" + (255 * (count - i / 2) / count));
                canvas.drawCircle(pointlsit.get(i)[0], pointlsit.get(i)[1], radius + (count - 1 - i) * rate, mPaint);
            }
            animatorList1.add(startMove1(pointlsit.get(i), i));
        }

        for (int i = 0; i < count; i++) {
            if (i < (count - 1) / 2) {
                mPaint.setAlpha(255 * (int) (count - 1.5 * i) / count);
                canvas.drawCircle(pointlsit1.get(i)[0], pointlsit1.get(i)[1], radius + i * rate, mPaint);
                Log.e("up========", ":" + i * rate);
            } else if (i == (count - 1) / 2) {
                mPaint.setAlpha(255);
                canvas.drawCircle(pointlsit1.get(i)[0], pointlsit1.get(i)[1], radius + i * rate, mPaint);
            } else {
                mPaint.setAlpha((255 * (int) (count - 1.5 * (i / 2)) / count));
                Log.e("up===1=====", ":" + (255 * (count - i / 2) / count));
                canvas.drawCircle(pointlsit1.get(i)[0], pointlsit1.get(i)[1], radius + (count - 1 - i) * rate, mPaint);
            }
            animatorList2.add(startMove5(pointlsit1.get(i), i));
            animatorList3.add(startMove6(pointlsit1.get(i), i));
        }
    }

    public ValueAnimator startMove(float[] pos) {
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
//        animator.start();
        return animator;
    }

    public ValueAnimator startMove1(float[] pos, int rate) {
        mPath.reset();
        mPath.addRoundRect(mRectF, 30, 30, Path.Direction.CW);
        pathMeasure.setPath(mPath, false);
        ValueAnimator animator = ValueAnimator.ofFloat(rate * space, pathMeasure.getLength() + rate * space);
        Log.e("pathMeasure======", "::" + pathMeasure.getLength());
        animator.setDuration(Duration * 1000);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(Animation.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float distance = (float) animation.getAnimatedValue();
//                Log.e("pathMeasure======", "::" + pathMeasure.getLength());
                //tan[0]是邻边 tan[1]是对边
                pathMeasure.getPosTan(distance, pos, tan);
                postInvalidate();
            }

        });
        animator.addListener(animatorListener);
        return animator;
    }

    public ValueAnimator startMove5(float[] pos, int rate) {
        mPath.reset();
        mPath.addRoundRect(mRectF, 30, 30, Path.Direction.CW);
        pathMeasure.setPath(mPath, false);
        ValueAnimator animator = ValueAnimator.ofFloat(pathMeasure.getLength() / 2 + rate * space, pathMeasure.getLength() + rate * space);
        animator.setDuration(Duration * 1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float distance = (float) animation.getAnimatedValue();
//                animation.setObjectValues(ValueAnimator.ofFloat(rate * space, pathMeasure.getLength() / 2 + rate * space));
                //tan[0]是邻边 tan[1]是对边
                pathMeasure.getPosTan(distance, pos, tan);
                postInvalidate();
            }

        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.cancel();
                if (!isfirst) {
                    for (int i = 0; i < animatorList3.size(); i++) {
                        animatorList3.get(i).start();
                    }
                    isfirst = !isfirst;
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return animator;
    }

    private boolean isfirst = false;

    public ValueAnimator startMove6(float[] pos, int rate) {
        mPath.reset();
        mPath.addRoundRect(mRectF, 30, 30, Path.Direction.CW);
        pathMeasure.setPath(mPath, false);
        ValueAnimator animator = ValueAnimator.ofFloat(rate * space, (int) (pathMeasure.getLength()) / 2 + rate * space);
        animator.setDuration(Duration * 1000);
//        animator.setRepeatMode(ValueAnimator.RESTART);
//        animator.setRepeatCount(Animation.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float distance = (float) animation.getAnimatedValue();
//                animation.setObjectValues(ValueAnimator.ofFloat(rate * space, pathMeasure.getLength() / 2 + rate * space));
                //tan[0]是邻边 tan[1]是对边
                pathMeasure.getPosTan(distance, pos, tan);
                postInvalidate();
            }

        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.cancel();
                if (isfirst) {
                    for (int i = 0; i < animatorList2.size(); i++) {
                        animatorList2.get(i).start();
                    }
                    isfirst = !isfirst;
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return animator;
    }

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            animation.start();
            if (!isfirst) {
                Log.e("distance===", ":" + "distance");
                for (int i = 0; i < 21; i++) {
//                    pathMeasureView.animatorList2.get(i).start();
                    animatorList3.get(i).start();
                }
                isfirst = false;
            }

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

}