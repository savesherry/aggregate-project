package com.example.aggregateproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.aggregate_methods.dialog.SingleChoiceDialog;
import com.example.aggregate_methods.tools.permissions.ConstantPermission;
import com.example.aggregate_methods.tools.permissions.PermissionCallback;
import com.example.aggregate_methods.tools.permissions.RequestPermissions;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SingleChoiceDialog dialog;
    private LinearLayout parentLayout;

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

        Button button = findViewById(R.id.button);
        parentLayout = findViewById(R.id.parentLayout);
        button.setOnClickListener(v -> {
            List<ShowModel> list = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                ShowModel model = new ShowModel();
                model.setId(i + "");
                model.setName(i + "这是对应的name");
                list.add(model);
            }

            dialog = new SingleChoiceDialog(this, list, "标题位置");
            dialog.setListener(position -> {
                Toast.makeText(MainActivity.this, "点击事件" + position, Toast.LENGTH_SHORT).show();
            });
            dialog.show(parentLayout);
        });
    }
}
