package com.example.customeranimor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.customeranimor.views.CameraAngleDialog;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private PathMeasureViewCopy pathMeasureView;
    private MatrixView idMatrix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       //添加布局
        
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + this.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(intent);
            } else {
            }
        }
        getWindow().setUiOptions(0);
        verifyStoragePermissions(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        idMatrix = findViewById(R.id.idMatrix);
        idMatrix.invalidate();
//        pathMeasureView = findViewById(R.id.pathMeasure);
//        CanvasLightLine cv = findViewById(R.id.cv);
//        ConstraintLayout cl = findViewById(R.id.cl);
//        CameraAngleDialog cameraAngleDialog = new CameraAngleDialog(this);
//        cameraAngleDialog.show();
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(dipToPx(this, 118), dipToPx(this, 44));
//        CanvasLightLine canvasLightLine = CanvasLightLine.create(this).setInputLayoutParams(params).setAction(true);
//        //    cl.addView(canvasLightLine);
//        Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 21; i++) {
//                    pathMeasureView.animatorList1.get(i).start();
//                    pathMeasureView.animatorList2.get(i).start();
//                    pathMeasureView.animatorList3.get(i).start();
//                }
////                pathMeasureView.startMove();
////                pathMeasureView.startMove1();
////                pathMeasureView.startMove2();
////                pathMeasureView.startMove3();
////                pathMeasureView.startMove4();
////                pathMeasureView.startMove5();
////                pathMeasureView.startMove6();
//            }
//        }, 1000);
    }

    //先定义
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    //然后通过一个函数来申请
    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void next(View view) {
        start();
    }

    private void start() {
        Intent intent = new Intent("com.jrmf360.action.ENTER2");
        intent.addCategory("com.jrmf360.action.ENTER2.category");
        intent.setData(Uri.parse("jrmf://jrmf360.com:8888/first?message=Hello SecondActivity"));
        ComponentName componentName = intent.resolveActivity(MainActivity.this.getPackageManager());
        startActivity(intent);
        /*Intent intent = new Intent("com.ryg.charpter_1.c");
//        intent.addCategory("com.ryg.charpter.c");

        startActivity(intent);*/
//        Intent intent = new Intent(this, SecondActivity.class);
//        startActivity(intent);
    }

    private void parseData() {
        Uri data = getIntent().getData();
        if (data != null) {
            String scheme = data.getScheme();
            String host = data.getHost();
            int port = data.getPort();
            String path = data.getPath();
            String query = data.getQuery();
            String message = data.getQueryParameter("message");
            Log.e(getClass().getSimpleName(), "scheme:" + scheme);
            Log.e(getClass().getSimpleName(), "host:" + host);
            Log.e(getClass().getSimpleName(), "port:" + port);
            Log.e(getClass().getSimpleName(), "path:" + path);
            Log.e(getClass().getSimpleName(), "query:" + query);

//            if ("/first".equals(path)){
//                FirstActivity.intent(this,message);
//                finish();
//            }else if ("/second".equals(path)){
//                SecondActivity.intent(this,message);
//                finish();
//            }
        }
    }

    /**
     * dp转px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dipToPx(Context context, float dp) {
//        获取手机屏幕像素密度
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param context
     * @param px
     * @return
     */
    public static int pxToDip(Context context, float px) {
//        获取手机屏幕像素密度
        final float scale = context.getResources().getDisplayMetrics().density;
        final float scaless = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
