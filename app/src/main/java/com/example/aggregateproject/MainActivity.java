package com.example.aggregateproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.aggregate_methods.tools.loading.ProgressHelper;
import com.example.aggregate_methods.tools.loading.ProgressWheel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressHelper.show(this);
    }
}
