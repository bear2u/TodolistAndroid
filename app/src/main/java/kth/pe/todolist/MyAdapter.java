package kth.pe.todolist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kth.pe.todolist.db.entity.Items;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Items> mDataset;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    public MyAdapter(List<Items> items) {
        this.mDataset = items;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv.setText(mDataset.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;
        public MyViewHolder(TextView itemView) {
            super(itemView);
            tv = itemView;
        }
    }

    public void setItems(List<Items> items) {
        this.mDataset = items;
        notifyDataSetChanged();
    }


}
