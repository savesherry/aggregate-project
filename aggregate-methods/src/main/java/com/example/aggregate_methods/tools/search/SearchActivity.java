package com.example.aggregate_methods.tools.search;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aggregate_methods.R;
import com.example.aggregate_methods.base.BaseActivity;

import androidx.recyclerview.widget.RecyclerView;

public class SearchActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private RecyclerView recyclerView;
    private RelativeLayout deleteIcon;
    private TextView cancelBtn;
    private EditText searchEdit;

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        searchEdit = findViewById(R.id.searchEdit);
        deleteIcon = findViewById(R.id.deleteIcon);
        cancelBtn = findViewById(R.id.cancelBtn);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setNerWork() {
        cancelBtn.setOnClickListener(this);
        deleteIcon.setOnClickListener(this);
        searchEdit.addTextChangedListener(this);
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
}
