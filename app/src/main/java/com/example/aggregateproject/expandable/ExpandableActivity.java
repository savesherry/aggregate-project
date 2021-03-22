package com.example.aggregateproject.expandable;

import android.widget.ListView;

import com.example.aggregate_methods.base.BaseActivity;
import com.example.aggregateproject.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandableActivity extends BaseActivity {

    private ListView listView;
    private ExpandableAdapter adapter;
    private List<ExpandableModel> list = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_expandable;
    }

    @Override
    protected void initView() {
        listView = findViewById(R.id.listView);
    }

    @Override
    protected void initData() {
        adapter = new ExpandableAdapter(this);
        listView.setAdapter(adapter);

        for (int i = 0; i < 6; i++) {
            ExpandableModel model = new ExpandableModel();
            model.setName("当前第一个名头" + i);
            model.setContent("内容部啥时候都好低洒家iOS啊就is挨浇哦大姐死傲娇撒娇啊iOS阿记得手机我哦啊接地搜啊脚手架爱打架撒娇我我就打死哦啊接分 " + i + i + i + i + i + i + i + i + i + i);
            list.add(model);
        }
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void setNerWork() {
        adapter.setList(list);
    }
}
