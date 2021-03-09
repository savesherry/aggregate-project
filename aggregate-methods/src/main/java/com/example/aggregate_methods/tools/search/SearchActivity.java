package com.example.aggregate_methods.tools.search;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aggregate_methods.R;
import com.example.aggregate_methods.base.BaseActivity;
import com.example.aggregate_methods.tools.FileUtils;
import com.example.aggregate_methods.tools.Methods;
import com.example.aggregate_methods.tools.search.adapter.ConductAdapter;
import com.example.aggregate_methods.tools.search.adapter.HistoryAdapter;
import com.example.aggregate_methods.tools.storage.FileStorage;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import static com.example.aggregate_methods.tools.search.adapter.HistoryAdapter.OnHistoryListener;

public class SearchActivity extends BaseActivity implements View.OnClickListener, TextWatcher, OnHistoryListener,
        View.OnKeyListener {

    private RecyclerView recyclerView;
    private ListView listView;
    private RelativeLayout deleteIcon, conductLayout;
    private LinearLayout historyLayout;
    private TextView cancelBtn;
    private EditText searchEdit;
    private List<String> conductList = new ArrayList<>();//搜索结果
    private List<String> historyList = new ArrayList<>();//历史记录
    private HistoryAdapter historyAdapter;
    private ConductAdapter conductAdapter;
    private FileStorage<String> storage;

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        listView = findViewById(R.id.listView);
        searchEdit = findViewById(R.id.searchEdit);
        deleteIcon = findViewById(R.id.deleteIcon);
        cancelBtn = findViewById(R.id.cancelBtn);

        historyLayout = findViewById(R.id.historyLayout);
        conductLayout = findViewById(R.id.conductLayout);
    }

    @Override
    protected void initData() {
        conductList = (List<String>) getIntent().getSerializableExtra("conductList");

        storage = new FileStorage<>(FileUtils.getCacheDirectory(this, "") + "searchHistory");

        historyAdapter = new HistoryAdapter(this);
        recyclerView.setAdapter(historyAdapter);

        conductAdapter = new ConductAdapter(this);
        listView.setAdapter(conductAdapter);

        FlexboxLayoutManager manager = new FlexboxLayoutManager(this);
        manager.setFlexWrap(FlexWrap.WRAP);
        manager.setFlexDirection(FlexDirection.ROW);
        manager.setAlignItems(AlignItems.STRETCH);
        manager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    protected void setListener() {
        cancelBtn.setOnClickListener(this);
        deleteIcon.setOnClickListener(this);
        searchEdit.addTextChangedListener(this);
        searchEdit.setOnKeyListener(this);
        historyAdapter.setHistoryListener(this);
    }

    @Override
    protected void setNerWork() {
        historyList = storage.read();
        historyAdapter.setList(historyList);
    }

    /**
     * android项目的library module里不能使用资源ID作为switch语句的case值。
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancelBtn) {
            finish();
        } else if (v.getId() == R.id.deleteIcon) {
            searchEdit.setText("");
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.length() > 0) {
            deleteIcon.setVisibility(View.VISIBLE);
            conductLayout.setVisibility(View.VISIBLE);

            conductAdapter.setList(conductList, editable.toString());

        } else {
            deleteIcon.setVisibility(View.GONE);
            conductLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onHistory(int position) {
        if (position != 0)
            historyList.add(0, historyList.remove(position));
        historyAdapter.setList(historyList);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            //修改回车键功能
            Methods.isKeyboardBack(this);
            displaceList();
        }
        return false;
    }

    private void displaceList() {
        if (historyList.contains(searchEdit.getText().toString().trim())) {
            for (int i = 0; i < historyList.size(); i++) {
                if (TextUtils.equals(historyList.get(i), searchEdit.getText().toString())) {
                    historyList.add(0, historyList.remove(i));
                }
            }
        } else {
            if (historyList.size() >= 10) {
                historyList.remove(historyList.size() - 1);
            }
            historyList.add(0, searchEdit.getText().toString().trim());
        }
        historyAdapter.setList(historyList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        storage.write(historyList);
    }
}
