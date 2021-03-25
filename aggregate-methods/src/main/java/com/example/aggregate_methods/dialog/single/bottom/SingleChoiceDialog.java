package com.example.aggregate_methods.dialog.single.bottom;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.aggregate_methods.R;
import com.example.aggregate_methods.tools.views.MaxListView;

import java.util.List;

/**
 * CREATE BY LiYang
 * ON 2021-02-19
 * SUPPLY : Thanks for watching
 */
public class SingleChoiceDialog extends PopupWindow implements AdapterView.OnItemClickListener {

    private View mFilterView;
    private MaxListView listView;
    private TextView filterTitle;
    private SingleChoiceAdapter adapter;
    private List<? extends SingleChoiceModel> list;
    private onChoiceListener listener;

    public SingleChoiceDialog(Context context, List<? extends SingleChoiceModel> list, String title) {
        this.list = list;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 主界面
        mFilterView = inflater.inflate(R.layout.dialog_bottom_single_choice, null);

        listView = mFilterView.findViewById(R.id.listView);
        filterTitle = mFilterView.findViewById(R.id.itemTitle);

        adapter = new SingleChoiceAdapter(context);
        listView.setAdapter(adapter);

        filterTitle.setText(title);
        adapter.setList(list);
        listView.setListViewHeight(800);

        listView.setOnItemClickListener(this);

        // 设置的View
        this.setContentView(mFilterView);
        // 设置弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        // 设置弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mFilterView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mFilterView.setOnTouchListener((v, event) -> {
            int height = mFilterView.findViewById(R.id.popLayout).getTop();
            int y = (int) event.getY();// 返回此事件的Y坐标为给定的指针索引
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (y < height) {
                    SingleChoiceDialog.this.dismiss();
                }
            }
            return true;
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        for (SingleChoiceModel model : list) {
            model.setChoice(false);
        }
        list.get(position).setChoice(true);
        adapter.setList(list);
        listener.onChoice(position);
    }

    public interface onChoiceListener {
        void onChoice(int position);
    }

    public void setListener(onChoiceListener listener) {
        this.listener = listener;
    }

    public void show(ViewGroup viewGroup) {
        showAtLocation(viewGroup, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 250);
    }
}
