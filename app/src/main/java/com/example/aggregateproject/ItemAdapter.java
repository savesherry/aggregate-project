package com.example.aggregateproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aggregate_methods.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * CREATE BY LiYang
 * ON 2021-02-24
 * SUPPLY : Thanks for watching
 */
public class ItemAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<String> list = new ArrayList<>();

    public ItemAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void clear() {
        this.list.clear();
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
            convertView = inflater.inflate(R.layout.activity_item, parent, false);
        }

        TextView itemTxt = BaseViewHolder.get(convertView, R.id.itemTxt);
        itemTxt.setText(list.get(position));

        return convertView;
    }
}
