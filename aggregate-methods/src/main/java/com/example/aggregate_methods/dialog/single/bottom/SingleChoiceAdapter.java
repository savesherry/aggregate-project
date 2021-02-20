package com.example.aggregate_methods.dialog.single.bottom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aggregate_methods.R;
import com.example.aggregate_methods.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * CREATE BY LiYang
 * ON 2021-02-19
 * SUPPLY : Thanks for watching
 */
public class SingleChoiceAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<? extends SingleChoiceModel> list = new ArrayList<>();

    public SingleChoiceAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<? extends SingleChoiceModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.dialog_bottom_single_choice_item, parent, false);
        }

        TextView itemTxt = BaseViewHolder.get(convertView, R.id.itemTxt);
        ImageView itemChoice = BaseViewHolder.get(convertView, R.id.itemChoice);
        View itemLine = BaseViewHolder.get(convertView, R.id.itemLine);

        if (position == list.size() - 1)
            itemLine.setVisibility(View.GONE);
        else
            itemLine.setVisibility(View.VISIBLE);

        if (list.get(position).isChoice())
            itemChoice.setVisibility(View.VISIBLE);
        else
            itemChoice.setVisibility(View.GONE);

        itemTxt.setText(list.get(position).getName());

        return convertView;
    }
}
