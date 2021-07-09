//package com.example.customeranimor;
//
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.graphics.Typeface;
//import android.text.SpannableStringBuilder;
//import android.text.Spanned;
//import android.text.TextUtils;
//import android.text.style.StyleSpan;
//import android.view.View;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class TestActivity extends AppCompatActivity {
//    private TextView tv_index1_des, tv_index2_des, tv_index4_des, tv_index3_des, devicesName;
//    private RelativeLayout rl_part4;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view);
//
//        TextView df = findViewById(R.id.tv_operation);
//        tv_index1_des = findViewById(R.id.tv_index1_des);
//        tv_index2_des = findViewById(R.id.tv_index2_des);
//        tv_index4_des = findViewById(R.id.tv_index4_des);
//        rl_part4 = findViewById(R.id.rl_part4);
//
//        devicesName.setText("设备");
//        tv_index1_des.setText(getVerName(this));
//
//
////        setWorldStyle(getResources().getString(R.string.index_one_des),14,21,df);
////        setWorldStyle(getResources().getString(R.string.index_two_des),17,34,df);
//        setWorldStyle(getResources().getString(R.string.index_four_des), 5, 8, df, "123");
//    }
//
//    boolean booleanDbdcState;
//
//    public void setShowMessage(String append) {
//        if (booleanDbdcState) {
//            tv_index3_des.setText(getResources().getString(R.string.index_three_des_dbdc));
//            setWorldStyle(getResources().getString(R.string.index_one_des), 14, 21, tv_index1_des, append);
//            setWorldStyle(getResources().getString(R.string.index_two_des), 17, 34, tv_index2_des, append);
//            setWorldStyle(getResources().getString(R.string.index_four_des), 5, 8, tv_index4_des, "");
//        } else {
//            setWorldStyle(getResources().getString(R.string.index_one_des), 14, 21, tv_index1_des, append);
//            setWorldStyle(getResources().getString(R.string.index_two_des), 17, 34, tv_index2_des, append);
//            setWorldStyle(getResources().getString(R.string.index_four_des), 5, 8, tv_index4_des, "");
//        }
//    }
//
//    /**
//     * 获取版本号名称
//     *
//     * @param context 上下文
//     * @return
//     */
//    public static String getVerName(Context context) {
//        String verName = "";
//        try {
//            String dfdf = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
//            verName = context.getPackageManager().
//                    getPackageInfo(context.getPackageName(), 0).versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return verName;
//    }
//
//    // set worlds bold
//    private void setWorldStyle(String str, int start, int end, TextView targetView, String append) {
//        SpannableStringBuilder builder = new SpannableStringBuilder(str);
//        builder.append(append);
//        builder.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        targetView.setText(builder);
//    }
//}
