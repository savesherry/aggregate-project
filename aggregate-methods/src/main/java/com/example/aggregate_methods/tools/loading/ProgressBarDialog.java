package com.example.aggregate_methods.tools.loading;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.example.aggregate_methods.R;

public class ProgressBarDialog extends ProgressDialog {

    private Context context;
    private ProgressWheel progressWheel;

    public ProgressBarDialog(Context context) {
        super(context, R.style.progress_bar_dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.dialog_progress_bar);
        progressWheel = findViewById(R.id.progressWheel);
    }
}
