package com.example.customeranimor.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.customeranimor.R;

public class CameraAngleDialog extends Dialog {
    private ChangeCameraAngleView angleView;

    public CameraAngleDialog(@NonNull Context context) {
        super(context);
    }

    public CameraAngleDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CameraAngleDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_angle_dialog);
        getWindow().setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        angleView = findViewById(R.id.angleView);
        angleView.invalidate();
    }

}
