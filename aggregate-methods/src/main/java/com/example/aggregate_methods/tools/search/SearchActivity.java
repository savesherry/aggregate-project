package com.example.aggregate_methods.tools.search;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aggregate_methods.R;
import com.example.aggregate_methods.base.BaseActivity;
import com.example.aggregate_methods.tools.FileUtils;
import com.example.aggregate_methods.tools.Methods;
import com.example.aggregate_methods.tools.search.adapter.SearchAdapter;
import com.example.aggregate_methods.tools.storage.FileStorage;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import static com.example.aggregate_methods.tools.search.adapter.SearchAdapter.OnHistoryListener;

public class SearchActivity extends BaseActivity implements View.OnClickListener, TextWatcher, OnHistoryListener,
        View.OnKeyListener {

    private RecyclerView recyclerView;
    private RelativeLayout deleteIcon;
    private TextView cancelBtn;
    private EditText searchEdit;
    private List<String> list = new ArrayList<>();
    private SearchAdapter adapter;
    private FileStorage<String> storage;

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        searchEdit = findViewById(R.id.searchEdit);
        deleteIcon = findViewById(R.id.deleteIcon);
        cancelBtn = findViewById(R.id.cancelBtn);
    }

    @Override
    protected void initData() {
        storage = new FileStorage<>(FileUtils.getCacheDirectory(this, "") + "searchHistory");

        adapter = new SearchAdapter(this);
        recyclerView.setAdapter(adapter);

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
        adapter.setHistoryListener(this);
    }

    @Override
    protected void setNerWork() {
        list = storage.read();
        adapter.setList(list);
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
    public void afterTextChanged(Editable s) {
        if (s.length() > 0)
            deleteIcon.setVisibility(View.VISIBLE);
        else
            deleteIcon.setVisibility(View.GONE);
    }

    @Override
    public void onHistory(int position) {
        if (position != 0)
            list.add(0, list.remove(position));
        adapter.setList(list);
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
        if (list.contains(searchEdit.getText().toString().trim())) {
            for (int i = 0; i < list.size(); i++) {
                if (TextUtils.equals(list.get(i), searchEdit.getText().toString())) {
                    list.add(0, list.remove(i));
                }
            }
        } else {
            if (list.size() >= 10) {
                list.remove(list.size() - 1);
            }
            list.add(0, searchEdit.getText().toString().trim());
        }
        adapter.setList(list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        storage.write(list);
    }
}
