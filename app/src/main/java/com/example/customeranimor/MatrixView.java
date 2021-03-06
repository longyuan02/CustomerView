package com.example.customeranimor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * @Description
 * @Author zjl
 * @Time 2021/7/8 4:17 PM
 */
public class MatrixView extends View {
    private Context mContext;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int girlBitWidth, girlBitHeight;
    private Bitmap girlBitmap;
    private Rect girlSrcRect, girlDesRect;
    private int mTotalWidth, mTotalHeight;

    public MatrixView(Context context) {
        super(context);
        init(context);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
//        showLoadView(0, 0);
    }

   /* public void showLoadView(int x, int y) {
        ImageView imageView = new ImageView(mContext);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(params);
        params.leftMargin = -0;
        params.topMargin = 0;
        imageView.setImageResource(R.mipmap.bomb_finish);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Animation rotateAnimation = new RotateAnimation(0f, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(9000);
        rotateAnimation.setRepeatCount(0);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDetachWallpaper(true);
        imageView.startAnimation(rotateAnimation);
*//*        girlBitmap = drawableToBitamps(ContextCompat.getDrawable(mContext, R.mipmap.bomb_finish));
        Matrix matrix = new Matrix(); //???????????? ??????
        matrix.setRotate(20);//????????????
        matrix.setScale(1, 1, girlBitmap.getWidth() / 2, girlBitmap.getHeight() / 2);
//        width = bitmap.getWidth();
//        height = bitmap.getHeight(); // ??????????????????
        Bitmap resizedBitmap = Bitmap.createBitmap(girlBitmap, 0, 0, girlBitmap.getWidth(), girlBitmap.getHeight(), matrix, true);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(resizedBitmap);
        imageView.setImageDrawable(bitmapDrawable);*//*
        this.addView(imageView);
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        MatrixImage(canvas);
    }


    /**
     * ????????????
     *
     * @param canvas
     */
    float mFloat = 0;
    PathMeasure pathMeasure = new PathMeasure();
    Path mPath = new Path();
    Matrix mMatrix = new Matrix();
    Bitmap mBitmap = null;

    private void MatrixImage(Canvas canvas) {//????????????????????????
        mBitmap = drawableToBitamps(ContextCompat.getDrawable(mContext, R.mipmap.bomb_finish));
        mPath.reset();
        mFloat += 0.01;
        if (mFloat >= 1) {
            mFloat = 0;
        }
        mPath.addCircle(getWidth() / 4, getHeight() / 4, 1, Path.Direction.CW);
        pathMeasure.setPath(mPath, false); //??????????????????
        float[] pos = new float[2]; //???????????????????????????
        float[] tan = new float[2];
        float distance = pathMeasure.getLength() * mFloat;
        pathMeasure.getPosTan(distance, pos, tan); //???????????????????????????????????????
        float degree = (float) (Math.atan2(tan[1], tan[0]) * 180 / Math.PI);
        mMatrix.reset(); //?????????????????????????????????
        mMatrix.postRotate(degree, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2); //??????????????????????????????????????????????????????
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);
        canvas.drawPath(mPath, paint);
        canvas.drawBitmap(mBitmap, mMatrix, paint);
        invalidate();
    }




   /* private void dd(Canvas canvas) {//????????????????????????
        mBitmap = drawableToBitamps(ContextCompat.getDrawable(mContext, R.mipmap.bomb_finish));

//        mPath.reset();
        mFloat += 0.01;
        if (mFloat >= 1) {
            mFloat = 0;
        }
        mPath.addCircle(getWidth() / 2, getHeight() / 2, 200, Path.Direction.CW);
        pathMeasure.setPath(mPath, false); //??????????????????
        float[] pos = new float[2]; //???????????????????????????
        float[] tan = new float[2];
        float distance = pathMeasure.getLength() * mFloat;
        pathMeasure.getPosTan(distance, pos, tan); //???????????????????????????????????????
        float degree = (float) (Math.atan2(tan[1], tan[0]) * 180 / Math.PI);
        mMatrix.reset(); //?????????????????????????????????
        mMatrix.postRotate(degree, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2); //??????????????????????????????????????????????????????
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);
        canvas.drawPath(mPath, paint);
        canvas.drawBitmap(mBitmap, mMatrix, paint);
        invalidate();
    }*/



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        Log.d("xxxxxx", "onSizeChanged , w = "+w+" , h = "+h+" , mBitWidth = "+mBitWidth+" , mBitHeight = "+mBitHeight);
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;

//        girlSrcRect = new Rect(0, 0, girlBitWidth, girlBitHeight);
//        girlDesRect = new Rect(0, 0, girlBitWidth, girlBitHeight);

    }

    private Bitmap drawableToBitamps(Drawable drawable) {
        Bitmap bitmap = null;
        int w = drawable.getIntrinsicHeight();
        int h = drawable.getIntrinsicHeight();
        System.out.println("Drawable???Bitmap");
        Bitmap.Config config = drawable.getOpacity()
                != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
//        bitmap = Bitmap.createBitmap(w, h, config);
        bitmap = adjustPhotoRotation(Bitmap.createBitmap(w, h, config), 180);
        //????????????????????????????????????????????????View??????surfaceview??????canvas.drawBitmap???????????????
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
}
