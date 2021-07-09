package com.example.customeranimor;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.Objects;

/**
 * 追光效果
 */
public class CanvasViews extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int angle = 0;
    private Context cContext;
    private Handler handler = new Handler(Looper.myLooper());
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 5000);
            Log.e("run======", "run");
            postInvalidate();
        }
    };

    public CanvasViews(Context context) {
        super(context);
        this.cContext = context;
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        handler.postDelayed(runnable, 1000);
    }

    public CanvasViews(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.cContext = context;
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        handler.postDelayed(runnable, 1000);
    }

    public CanvasViews(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.cContext = context;
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        part1(canvas);
//        part2(canvas);
//        part5(canvas);
//        part6(canvas);
//        part7(canvas);
//        canvasTriangle(canvas, drawableToBitamps(ContextCompat.getDrawable(cContext, R.mipmap.color)), 100);
//        drawPathMeasure(canvas);
//        drawPathMeasureCopy1(canvas);
        /*drawPathMeasureCopy2(canvas);
        drawPathMeasureCopy21(canvas);*/
//        drawPathMeasureTest(canvas);
//        drawPathMeasureTest1(canvas);
        drawPathMeasureCopy22(canvas);
    }

    /**
     * drawPathMeasureCopy2(canvas);
     * drawPathMeasureCopy21(canvas);
     */
    int[] colors = {Color.parseColor("#20FFFFFF"), Color.parseColor("#80FFFFFF"), Color.WHITE, Color.parseColor("#80FFFFFF"), Color.parseColor("#20FFFFFF")};

    float[] position = {0f, 0.2f, 0.5f, 0.7f, 1f};

    private void part7(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        Path path = new Path();
        canvas.drawBitmap(drawableToBitamps(ContextCompat.getDrawable(cContext, R.mipmap.color)), width - 100, 0, paint);

        /*path.addRoundRect(0, 0, width - 50, height - 50, 30, 30, Path.Direction.CW);
//        path.moveTo(0, 0);
//        path.lineTo(0,-10);
//        path.lineTo(10,-10);
//        path.lineTo(width+10,height+10);
//        path.lineTo(width-10,height-10);
//        path.lineTo(-10,0);
//        path.lineTo(0,0);
//
//        path.lineTo(width, height);
//        paint.setColor(Color.RED);
//        paint.setStrokeWidth(60);
//        canvas.drawLine(0, 0, width - 50, height - 50,paint);
        canvas.drawPath(path, paint);
        paint.reset();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        paint.setStrokeWidth(10);
//        LinearGradient linearGradient = new LinearGradient(0, 0, (int) (width * 1.5), (int) (height * 1.5), colors, position, Shader.TileMode.CLAMP);
//        paint.setShader(linearGradient);
//        canvas.drawLine(0, 0, (int) (width * 1.5), (int) (height * 1.5), paint);
//        canvas.drawRoundRect(0, 0, width - 50, height - 50, 30, 30, paint);
        canvas.drawBitmap(drawableToBitamps(ContextCompat.getDrawable(cContext, R.mipmap.ic_launcher)), width - 100, 0, paint);
        paint.setXfermode(null);*/
    }

    private void part5(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        Bitmap output = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_4444);
        Canvas canvas1 = new Canvas(output);
        final Rect rect = new Rect(0, 0, width,
                height);

        Path path = new Path();
//        path.moveTo(0, 0);
//        path.lineTo(width - 15, 0);
//        path.lineTo(width - 15, 10);
//        path.lineTo(width, 20);
//        path.lineTo(width - 15, 30);
//        path.lineTo(width - 15, height);
//        path.lineTo(0, height);
        path.addRoundRect(0, 0, width, height, 50, 50, Path.Direction.CW);
        path.close();
        canvas1.drawPath(path, paint);//（DES）
        paint.setColor(Color.RED);
        //两层绘制交集。显示上层
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        paint.setColor(Color.BLUE);
        canvas1.drawBitmap(output, rect, rect, paint);//（SRC）
    }

    private void part6(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        angle = 55;
        paint.setColor(Color.parseColor("#20FFFFFF"));
        paint.setStrokeWidth(40);
        float r = height * 1.5f;
        float ox = height / 2;
        float oy = width / 2;
        float y1 = (float) (ox + r * Math.sin(Math.toRadians(angle)));//45
        float x1 = (float) (oy + r * Math.cos(Math.toRadians(angle)));
        float y2 = (float) (ox + r * Math.sin(Math.toRadians(180 + angle)));
        float x2 = (float) (oy + r * Math.cos(Math.toRadians(180 + angle)));
        canvas.drawRoundRect(20, 20, width - 20, height - 20, 50, 50, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
        paint.setColor(Color.BLUE);
        canvas.drawLine(x1, y1, x2, y2, paint);
        paint.setXfermode(null);
    }

    private void part1(Canvas canvas) {
        //设置背景色
        canvas.drawARGB(255, 139, 197, 186);

        int canvasWidth = canvas.getWidth();
        int r = canvasWidth / 3;
        //绘制黄色的圆形
        paint.setColor(0xFFFFCC44);
        canvas.drawCircle(r, r, r, paint);
        //绘制蓝色的矩形
        paint.setColor(0xFF66AAFF);
        canvas.drawRect(r, r, r * 2.7f, r * 2.7f, paint);
        canvas.rotate(-90);
    }

    private void part2(Canvas canvas) {
        angle++;
//        canvas.rotate(angle);
//        canvas.translate(-getHeight() / 2, -getWidth() / 2);
        //设置背景色
//        canvas.drawARGB(255, 139, 197, 186);
        int canvasWidth = canvas.getWidth();
        int r = canvasWidth / 3;
        //绘制黄色的圆形
        paint.setColor(Color.BLUE);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        canvas.drawCircle(r, r, r, paint);
//        paint.setXfermode(null);
//        //绘制蓝色的矩形
//        paint.setColor(0xFF66AAFF);
//        canvas.drawRect(r, r, r * 2.7f, r * 2.7f, paint);
        //使用CLEAR作为PorterDuffXfermode绘制蓝色的矩形
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        paint.setColor(Color.RED);
        canvas.drawRect(r / 2, r / 2, r * 1.8f, r * 1.8f, paint);
        //最后将画笔去除Xfermode
        paint.setXfermode(null);
    }

    private void part3(Canvas canvas) {
        //设置背景色
        canvas.drawARGB(255, 139, 197, 186);

        int canvasWidth = canvas.getWidth();//屏幕像素点 dp* dpi
        int canvasHeight = canvas.getHeight();
        int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, paint, Canvas.ALL_SAVE_FLAG);
        int r = canvasWidth / 3;
        Log.e("circle=====", canvasWidth + ":circle:" + r);
        //正常绘制黄色的圆形
        paint.setColor(0xFFFFCC44);
        canvas.drawCircle(r, r, r, paint);
        //使用CLEAR作为PorterDuffXfermode绘制蓝色的矩形
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        paint.setColor(0xFF66AAFF);
        canvas.drawRect(r, r, r * 2.7f, r * 2.7f, paint);
        //最后将画笔去除Xfermode
        paint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }

    private void part4(Canvas canvas) {
        //设置背景色
        canvas.drawARGB(255, 139, 197, 186);

        int canvasWidth = canvas.getWidth();
        int r = canvasWidth / 3;
        //绘制黄色的圆形
        paint.setColor(0xFFFFCC44);
        canvas.drawCircle(r, r, r, paint);
//        //绘制蓝色的矩形
//        paint.setColor(0xFF66AAFF);
//        canvas.drawRect(r, r, r * 2.7f, r * 2.7f, paint);
        //使用CLEAR作为PorterDuffXfermode绘制蓝色的矩形
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
        paint.setColor(0xFF66AAFF);
        canvas.drawRect(r, r, r * 2.7f, r * 2.7f, paint);
        //最后将画笔去除Xfermode
        paint.setXfermode(null);
    }

    private Bitmap drawableToBitamp(Drawable drawable) {
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bitmap = bd.getBitmap();
        return bitmap;
    }

    public Bitmap canvasTriangle(Canvas canvas, Bitmap bitmapimg, int direct) {
//        bitmapimg = adjustPhotoRotation(bitmapimg, 30);
        Bitmap output = Bitmap.createBitmap(200,
                300, Bitmap.Config.ARGB_4444);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, 200,
                300);

//      paint.setStrokeWidth(30);
//      paint.setStyle(Paint.Style.STROKE);

        paint.setAntiAlias(true);
//        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(0x66000000);

        //可以使用canvas.drawRect() + canvas.drawPath()等方式多个区域合并后进行绘制
        //path只是用来绘制像素的透明度的。如果paint的透明度为0，那么相当于没有画任何东西，如果paint的透明度为0.3，那么最后的图片的透明度为0.3
        Path path = new Path();
        path.addRoundRect(0, 0, 100, 300, 50, 50, Path.Direction.CW);
        path.close();
        canvas.drawPath(path, paint);//（DES）
        //两层绘制交集。显示上层
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapimg, rect, rect, paint);//（SRC）
        return output;
    }

    private Bitmap drawableToBitamps(Drawable drawable) {
        Bitmap bitmap = null;
        int w = drawable.getIntrinsicHeight() / 10;
        int h = drawable.getIntrinsicHeight();
        System.out.println("Drawable转Bitmap");
        Bitmap.Config config = drawable.getOpacity()
                != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
//        bitmap = Bitmap.createBitmap(w, h, config);
        bitmap = adjustPhotoRotation(Bitmap.createBitmap(w, h, config), 180);
        //注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    Bitmap adjustPhotoRotation(Bitmap bm, final int orientationDegree) {

        Matrix m = new Matrix();
        m.setRotate(orientationDegree, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);

        try {
            Bitmap bm1 = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), m, true);

            return bm1;

        } catch (OutOfMemoryError ex) {
        }
        return null;

    }

    float mFloat = 0;
    Path dst1 = new Path();
    PathMeasure pathMeasure3 = new PathMeasure();

    private void drawPathMeasure(Canvas canvas) {
        mFloat += 0.001;
        if (mFloat >= 1) {
            mFloat = 0;
        }
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
//        dst1.addCircle(getWidth() / 2, getHeight() / 2, 200, Path.Direction.CW);
        dst1.addRoundRect(0, 0, 300, 300, 30, 30, Path.Direction.CW);

        pathMeasure3.setPath(dst1, false);
        float changeD = pathMeasure3.getLength() * mFloat;
        Path dst2 = new Path();
        dst2.addRoundRect(0, 0, 300, 300, 30, 30, Path.Direction.CW);
//        dst2.addRoundRect(0, 0, 200, 200, 30, 30, Path.Direction.CW);
//        pathMeasure3.getSegment(changeD, pathMeasure3.getLength(), dst2, true);
        pathMeasure3.getSegment(changeD, pathMeasure3.getLength(), dst2, true);
        canvas.drawPath(dst2, paint);
        invalidate();
    }

    private void drawPathMeasureCopy(Canvas canvas) {
        mFloat += 0.005;
        if (mFloat >= 1) {
            mFloat = 0;
        }
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
//        dst1.addCircle(getWidth() / 2, getHeight() / 2, 200, Path.Direction.CW);
        dst1.addRoundRect(50, 0, 250, 300, 30, 30, Path.Direction.CW);

        pathMeasure3.setPath(dst1, false);
        float changeD = pathMeasure3.getLength() * mFloat;
        Path dst2 = new Path();
        dst2.addRoundRect(0, 0, 300, 300, 30, 30, Path.Direction.CW);
//        dst2.addRoundRect(0, 0, 200, 200, 30, 30, Path.Direction.CW);
//        pathMeasure3.getSegment(changeD, pathMeasure3.getLength(), dst2, true);
        pathMeasure3.getSegment(changeD, 2 * pathMeasure3.getLength(), dst2, true);
        paint.setColor(Color.YELLOW);
        canvas.drawPath(dst2, paint);
        invalidate();
    }

    private void drawPathMeasureCopy1(Canvas canvas) {
        mFloat += 0.005;
        if (mFloat >= 1) {
            mFloat = 0;
        }
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        if (mFloat < 0.5) {
            dst1.reset();
            dst1.addRoundRect(50, 50, 250, 300, 30, 30, Path.Direction.CW);
            pathMeasure3.setPath(dst1, false);
            float changeD = pathMeasure3.getLength() * mFloat;
            Path dst2 = new Path();
            pathMeasure3.getSegment(pathMeasure3.getLength() / 2 + changeD, pathMeasure3.getLength() / 2 + changeD + 50, dst2, true);
            Log.e("draw~~~~~~~0.5", ":" + mFloat);
            canvas.drawPath(dst2, paint);
        } else {
            dst1.reset();
            dst1.addRoundRect(50, 50, 250, 300, 30, 30, Path.Direction.CW);
            pathMeasure3.setPath(dst1, false);
            float changeD = pathMeasure3.getLength() * mFloat;
            Path dst2 = new Path();
//            pathMeasure3.getSegment(pathMeasure3.getLength() / 2 + changeD, pathMeasure3.getLength() / 2 + changeD + 50, dst2, false);
            pathMeasure3.getSegment(changeD - pathMeasure3.getLength() / 2, changeD - pathMeasure3.getLength() / 2 + 50, dst2, true);
            Log.e("draw~~~~~~~1", ":" + changeD);
            canvas.drawPath(dst2, paint);
        }
//        int[] radialColors = {Color.RED,Color.YELLOW};
//        RadialGradient radialGradient = new RadialGradient(300,300,200,radialColors,null, Shader.TileMode.MIRROR);
//        paint.setShader(radialGradient);
//        canvas.drawCircle(300,300,200,paint);
        invalidate();
    }


    private void drawPathMeasureCopy21(Canvas canvas) {
        mFloat += 0.005;
        if (mFloat >= 1) {
            mFloat = 0;
        }
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
//        dst1.addCircle(getWidth() / 2, getHeight() / 2, 200, Path.Direction.CW);

//        dst2.addRoundRect(0, 0, 300, 300, 30, 30, Path.Direction.CW);
////        dst2.addRoundRect(0, 0, 200, 200, 30, 30, Path.Direction.CW);
//        pathMeasure3.getSegment(changeD, pathMeasure3.getLength(), dst2, true);
        paint.setStrokeWidth(20);
        if (mFloat < 0.5) {
            dst1.reset();
            dst1.addRoundRect(50, 50, 250, 300, 30, 30, Path.Direction.CW);
            pathMeasure3.setPath(dst1, false);
            float changeD = pathMeasure3.getLength() * mFloat;
            Path dst2 = new Path();
            pathMeasure3.getSegment(pathMeasure3.getLength() / 2 + changeD, pathMeasure3.getLength() / 2 + changeD + 50, dst2, true);
            Log.e("draw~~~~~~~0.5", ":" + mFloat);
            canvas.drawPath(dst2, paint);
        } else {
            dst1.reset();
            dst1.addRoundRect(50, 50, 250, 300, 30, 30, Path.Direction.CW);
            pathMeasure3.setPath(dst1, false);
            float changeD = pathMeasure3.getLength() * mFloat;
            Path dst2 = new Path();
//            pathMeasure3.getSegment(pathMeasure3.getLength() / 2 + changeD, pathMeasure3.getLength() / 2 + changeD + 50, dst2, false);
            pathMeasure3.getSegment(changeD - pathMeasure3.getLength() / 2, changeD - pathMeasure3.getLength() / 2 + 50, dst2, true);
            Log.e("draw~~~~~~~1", ":" + changeD);
            canvas.drawPath(dst2, paint);
        }
//        int[] radialColors = {Color.RED,Color.YELLOW};
//        RadialGradient radialGradient = new RadialGradient(300,300,200,radialColors,null, Shader.TileMode.MIRROR);
//        paint.setShader(radialGradient);
//        canvas.drawCircle(300,300,200,paint);
        invalidate();
    }


    private void drawPathMeasureCopy2(Canvas canvas) {
        mFloat += 0.005;
        if (mFloat >= 1) {
            mFloat = 0;
        }
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
//        dst1.addCircle(getWidth() / 2, getHeight() / 2, 200, Path.Direction.CW);

//        dst2.addRoundRect(0, 0, 300, 300, 30, 30, Path.Direction.CW);
////        dst2.addRoundRect(0, 0, 200, 200, 30, 30, Path.Direction.CW);
//        pathMeasure3.getSegment(changeD, pathMeasure3.getLength(), dst2, true);
        paint.setStrokeWidth(20);
        if (mFloat < 0.5) {
            paint.setColor(Color.BLUE);
            dst1.reset();
            dst1.addRoundRect(50, 50, 250, 300, 30, 30, Path.Direction.CW);
            pathMeasure3.setPath(dst1, false);
            float changeD = pathMeasure3.getLength() * mFloat;
            Path dst2 = new Path();
            pathMeasure3.getSegment(changeD, changeD + 50, dst2, true);
            Log.e("draw~~~~~~~0.5", ":" + mFloat);
            canvas.drawPath(dst2, paint);
        } else {
            paint.setColor(Color.YELLOW);
            dst1.reset();
            dst1.addRoundRect(50, 50, 250, 300, 30, 30, Path.Direction.CW);
            pathMeasure3.setPath(dst1, false);
            float changeD = pathMeasure3.getLength() * mFloat;
            Path dst2 = new Path();
//            pathMeasure3.getSegment(pathMeasure3.getLength() / 2 + changeD, pathMeasure3.getLength() / 2 + changeD + 50, dst2, false);
            pathMeasure3.getSegment(changeD, changeD + 50, dst2, true);
            Log.e("draw~~~~~~~1", ":" + mFloat);
            canvas.drawPath(dst2, paint);
        }
//        int[] radialColors = {Color.RED,Color.YELLOW};
//        RadialGradient radialGradient = new RadialGradient(300,300,200,radialColors,null, Shader.TileMode.MIRROR);
//        paint.setShader(radialGradient);
//        canvas.drawCircle(300,300,200,paint);
        invalidate();
    }

    private void drawPathMeasureCopy22(Canvas canvas) {
        mFloat += 0.005;
        if (mFloat >= 1) {
            mFloat = 0;
        }
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        int[] radialColors = {Color.RED, Color.YELLOW, Color.WHITE};
        RadialGradient radialGradient = new RadialGradient(300, 300, 200, radialColors, null, Shader.TileMode.MIRROR);
        paint.setShader(radialGradient);
        if (mFloat < 0.5) {
            paint.setColor(Color.BLUE);
            dst1.reset();
            dst1.addRoundRect(50, 50, 250, 300, 30, 30, Path.Direction.CW);
            pathMeasure3.setPath(dst1, false);
            float changeD = pathMeasure3.getLength() * mFloat;
            Path dst2 = new Path();
            pathMeasure3.getSegment(changeD, changeD + 50, dst2, true);
            Log.e("draw~~~~~~~0.5", ":" + mFloat);
            canvas.drawPath(dst2, paint);
        } else {
            paint.setColor(Color.YELLOW);
            dst1.reset();
            dst1.addRoundRect(50, 50, 250, 300, 30, 30, Path.Direction.CW);
            pathMeasure3.setPath(dst1, false);
            float changeD = pathMeasure3.getLength() * mFloat;
            Path dst2 = new Path();
//            pathMeasure3.getSegment(pathMeasure3.getLength() / 2 + changeD, pathMeasure3.getLength() / 2 + changeD + 50, dst2, false);
            pathMeasure3.getSegment(changeD, changeD + 50, dst2, true);
            Log.e("draw~~~~~~~1", ":" + mFloat);
            canvas.drawPath(dst2, paint);
        }
//        canvas.drawCircle(300,300,200,paint);
        invalidate();
    }

    private void drawPathMeasureTest1(Canvas canvas) {
        mFloat += 0.01;
        if (mFloat >= 1) {
            mFloat = 0;
        }
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
//        dst1.addCircle(getWidth() / 2, getHeight() / 2, 200, Path.Direction.CW);
        dst1.addRoundRect(0, 0, 300, 300, 30, 30, Path.Direction.CW);
        pathMeasure3 = new PathMeasure();
        pathMeasure3.setPath(dst1, false);
        float start = pathMeasure3.getLength() * (mFloat - 0.01f);
        float changeD = pathMeasure3.getLength() * mFloat;
        Path dst2 = new Path();
        dst2.addRoundRect(0, 0, 300, 300, 30, 30, Path.Direction.CW);
//        pathMeasure3.getSegment(changeD, pathMeasure3.getLength(), dst2, true);
        pathMeasure3.getSegment(changeD / 10, changeD, dst2, false);
        canvas.drawPath(dst2, paint);
        invalidate();


//        Path dst1 = new Path();
//        pathMeasure3.getSegment(changeD, pathMeasure3.getLength(), dst1, true);
//        canvas.drawPath(dst1, paint);
//        Path dst2 = new Path();
//        pathMeasure3.getSegment(0, 100 - pathMeasure3.getLength() + changeD, dst2, true);
//        canvas.drawPath(dst2, paint);
    }

    private void drawPathMeasureTest(Canvas canvas) {
        mFloat += 0.01;
        if (mFloat >= 1) {
            mFloat = 0;
        }
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
//        dst1.addCircle(getWidth() / 2, getHeight() / 2, 200, Path.Direction.CW);
        dst1.addRoundRect(10, 10, 300, 300, 30, 30, Path.Direction.CW);
        PathMeasure pathMeasure3 = new PathMeasure();
        pathMeasure3.setPath(dst1, false);
        float start = pathMeasure3.getLength() * (mFloat - 0.01f);
        float changeD = pathMeasure3.getLength() * mFloat;
        Path dst2 = new Path();
        dst2.addRoundRect(0, 0, 310, 310, 30, 30, Path.Direction.CW);
//        pathMeasure3.getSegment(changeD, pathMeasure3.getLength(), dst2, true);
        pathMeasure3.getSegment(changeD, pathMeasure3.getLength(), dst2, false);
        canvas.drawPath(dst2, paint);
        invalidate();
    }


    private void ddd(Canvas canvas) {
        mFloat += 0.01;
        if (mFloat >= 1) {
            mFloat = 0;
        }
        float changeD = pathMeasure3.getLength() * mFloat;
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
//        dst1.addCircle(getWidth() / 2, getHeight() / 2, 200, Path.Direction.CW);
        dst1.addRoundRect(0, 0, 300, 300, 30, 30, Path.Direction.CW);


        pathMeasure3.getSegment(changeD, pathMeasure3.getLength(), dst1, true);
        canvas.drawPath(dst1, paint);
//        Path dst2 = new Path();
//        dst2.addRoundRect(0, 0, 300, 300, 30, 30, Path.Direction.CW);
//        pathMeasure3.getSegment(0, 100 - pathMeasure3.getLength() + changeD, dst2, true);
//        paint.setColor(Color.BLUE);
//        canvas.drawPath(dst2, paint);
        invalidate();
    }


    PathMeasure pathMeasure = new PathMeasure();
    Path mPath = new Path();
    Matrix mMatrix = new Matrix();
    Bitmap mBitmap = null;

    /**
     * 旋转图片
     *
     * @param canvas
     */
    private void dd(Canvas canvas) {
        mBitmap = drawableToBitamps(ContextCompat.getDrawable(cContext, R.mipmap.color));

//        mPath.reset();
        mFloat += 0.01;
        if (mFloat >= 1) {
            mFloat = 0;
        }
        mPath.addCircle(getWidth() / 2, getHeight() / 2, 200, Path.Direction.CW);
        pathMeasure.setPath(mPath, false); //用来记录位置
        float[] pos = new float[2]; //用来记录切点的位置
        float[] tan = new float[2];
        float distance = pathMeasure.getLength() * mFloat;
        pathMeasure.getPosTan(distance, pos, tan); //计算出当前图片要旋转的角度
        float degree = (float) (Math.atan2(tan[1], tan[0]) * 180 / Math.PI);
        mMatrix.reset(); //设置旋转角度和旋转中心
        mMatrix.postRotate(degree, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2); //设置绘制的中心点与当前图片中心点重合
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);
        canvas.drawPath(mPath, paint);
        canvas.drawBitmap(mBitmap, mMatrix, paint);
        invalidate();
    }

    public void MyPathView(Context context, AttributeSet attrs, int defStyleAttr) {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        Path dstPath = new Path();
        Path path = new Path();
        path.addCircle(500, 500, 200, Path.Direction.CW);

        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                ValueAnimator animatorValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(1300);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }
}
