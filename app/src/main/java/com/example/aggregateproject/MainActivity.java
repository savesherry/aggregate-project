package com.example.aggregateproject;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.aggregate_methods.base.BaseActivity;
import com.example.aggregate_methods.dialog.single.bottom.SingleChoiceDialog;
import com.example.aggregate_methods.tools.loading.ProgressHelper;
import com.example.aggregateproject.thumbnail.ThumbnailActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private RelativeLayout parentLayout;
    private ListView listView;
    private List<String> list = new ArrayList<>();
    private ItemAdapter adapter;

    private SingleChoiceDialog singleChoiceDialog;
    private List<SingleModel> singleList = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        parentLayout = findViewById(R.id.parentLayout);
        listView = findViewById(R.id.listView);
    }

    @Override
    protected void initData() {
        adapter = new ItemAdapter(this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void setListener() {
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void setNerWork() {
        for (String item : CustomConstant.itemName) {
            list.add(item);
        }
        adapter.setList(list);

        for (int i = 0; i < 5; i++) {
            SingleModel model = new SingleModel();
            model.setName("展示选项卡" + i);
            singleList.add(model);

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                ProgressHelper.show(this);
                break;
            case 1:
                break;
            case 2:
                singleChoiceDialog = new SingleChoiceDialog(MainActivity.this, singleList, "标题位置");
                singleChoiceDialog.setListener(v -> {
                });
                singleChoiceDialog.show(parentLayout);
                break;
            case 3:
                startActivity(new Intent(this, ThumbnailActivity.class));
                break;
        }
    }
}
