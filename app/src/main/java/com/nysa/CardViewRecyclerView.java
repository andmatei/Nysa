package com.nysa;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CardViewRecyclerView extends RecyclerView.Adapter<CardViewRecyclerView.ViewHolder> {

    private List<ListItem> itemList;
    private Context context;

    public CardViewRecyclerView(List<ListItem> itemList, Context context) {
        this.context = context;
        this.itemList = new ArrayList<>(itemList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_cardview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ListItem listItem = itemList.get(position);
        holder.textViewTitle.setText(listItem.getTitle());
        String Description = listItem.getDesc();
        if (Description.length() > 33)
            Description = Description.substring(0, 30) + "...";
        holder.textViewDesc.setText(Description);
        holder.btnGoToArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Article.class);
                i.putExtra("article_data", listItem.getArt().toString());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public TextView textViewDesc;
        public Button btnGoToArt;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.title_cardview);
            textViewDesc = (TextView) itemView.findViewById(R.id.desc_cardview);
            btnGoToArt = (Button) itemView.findViewById(R.id.btn_cardview);
        }
    }

    public void animateTo(List<ListItem> itemList) {
        applyRemovals(itemList);
        applyAdditions(itemList);
        applyMovedItems(itemList);
    }

    private void applyRemovals(List<ListItem> newList) {
        for(int i = itemList.size()-1; i>=0; i--) {
            final ListItem item = itemList.get(i);
            if(!newList.contains(item)) {
                removeItem(i);
            }
        }
    }

    private void applyAdditions(List<ListItem> newList) {
        for(int i = 0; i < newList.size(); i++) {
            final ListItem item = newList.get(i);
            if(!itemList.contains(item)) {
                addItem(i, item);
            }
        }
    }

    private void applyMovedItems(List<ListItem> newList) {
        for(int i = newList.size()-1; i >= 0; i--) {
            final ListItem item = newList.get(i);
            final int j = itemList.indexOf(item);
            if(j >= 0 && j != i) {
                moveItem(j, i);
            }
        }
    }

    public ListItem removeItem(int position) {
        final ListItem item = itemList.remove(position);
        notifyItemRemoved(position);
        return item;
    }

    public void addItem(int position, ListItem item) {
        itemList.add(position, item);
        notifyItemInserted(position);
    }

    public void moveItem(int j, int i) {
        final ListItem item = itemList.remove(j);
        itemList.add(i, item);
        notifyItemMoved(j, i);
    }
}
