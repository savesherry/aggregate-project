package com.example.aggregateproject.expandable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aggregate_methods.base.BaseViewHolder;
import com.example.aggregateproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * CREATE BY LiYang
 * ON 2021-03-20
 * SUPPLY : Thanks for watching
 */
public class ExpandableAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<ExpandableModel> list = new ArrayList<>();

    public ExpandableAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<ExpandableModel> list) {
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
            convertView = inflater.inflate(R.layout.activity_expandable_list_item, parent, false);
        }

        TextView itemTitle = BaseViewHolder.get(convertView, R.id.itemTitle);
        TextView itemContent = BaseViewHolder.get(convertView, R.id.itemContent);

        itemTitle.setText(list.get(position).getName());
        itemContent.setText(list.get(position).getContent());

        return convertView;
    }
}
