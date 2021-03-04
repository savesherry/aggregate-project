package com.example.aggregate_methods.tools.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aggregate_methods.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * CREATE BY LiYang
 * ON 2021-03-04
 * SUPPLY : Thanks for watching
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private List<String> list = new ArrayList<>();
    private OnHistoryListener listener;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setHistoryListener(OnHistoryListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_search_history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemTxt.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView itemTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTxt = itemView.findViewById(R.id.itemTxt);

            itemTxt.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onHistory(getAdapterPosition());
        }
    }

    public interface OnHistoryListener {
        void onHistory(int position);
    }
}
