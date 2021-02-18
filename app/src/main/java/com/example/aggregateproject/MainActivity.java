package com.example.aggregateproject;

import android.os.Bundle;
import android.widget.Toast;

import com.example.aggregate_methods.tools.permissions.ConstantPermission;
import com.example.aggregate_methods.tools.permissions.PermissionCallback;
import com.example.aggregate_methods.tools.permissions.RequestPermissions;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestPermissions.obtainPermission(this, ConstantPermission.ACCESS_FINE_LOCATION, new PermissionCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "同意", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "不同意", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
