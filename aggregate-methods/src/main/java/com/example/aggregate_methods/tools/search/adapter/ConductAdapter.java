package com.example.aggregate_methods.tools.search.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aggregate_methods.R;
import com.example.aggregate_methods.base.BaseViewHolder;
import com.example.aggregate_methods.tools.Methods;

import java.util.ArrayList;
import java.util.List;

/**
 * CREATE BY LiYang
 * ON 2021-03-06
 * SUPPLY : Thanks for watching
 */
public class ConductAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<String> list = new ArrayList<>();
    private String keywords;

    public ConductAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<String> list, String keywords) {
        this.list = list;
        this.keywords = keywords;
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
            convertView = inflater.inflate(R.layout.activity_search_conduct_item, parent, false);
        }

        TextView itemTxt = BaseViewHolder.get(convertView, R.id.itemTxt);

        itemTxt.setText(Html.fromHtml(Methods.matcherSearchTitle(list.get(position), keywords)));

        return convertView;
    }
}
