package com.example.aggregate_methods.dialog.progress;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.aggregate_methods.R;

import androidx.annotation.NonNull;

/**
 * CREATE BY LiYang
 * ON 2021-03-19
 * SUPPLY : Thanks for watching
 */
public class DownloadDialog extends Dialog {

    private ProgressBar progressBar;
    private TextView speedTxt;

    public DownloadDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_download);
        setCanceledOnTouchOutside(false);

        speedTxt = findViewById(R.id.speedTxt);
        progressBar = findViewById(R.id.progressBar);
    }

    public void setProgressBar(int percentage, String speed) {
        progressBar.setProgress(percentage);
        speedTxt.setText(speed);
    }


    public void setHeight(Activity activity) {
        show();
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = (int) (metric.widthPixels * 0.8);
        layoutParams.height = (int) (metric.heightPixels * 0.15);
        getWindow().setAttributes(layoutParams);
    }
}
