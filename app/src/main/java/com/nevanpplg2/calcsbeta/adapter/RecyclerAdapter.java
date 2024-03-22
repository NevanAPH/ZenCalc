package com.nevanpplg2.calcsbeta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.nevanpplg2.calcsbeta.Animator;
import com.nevanpplg2.calcsbeta.R;
import com.nevanpplg2.calcsbeta.item.MenuItem;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private final List<MenuItem> items;
    private int color;
    private Context context;

    public RecyclerAdapter(Context context, List<MenuItem> items, int color) {
        this.context = context;
        this.items = items;
        this.color = color;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MenuItem item = items.get(position);
        holder.card.setStrokeColor(color);
        holder.view.setOnClickListener(item.getOnClickListener());
        holder.name.setText(item.getName());
        holder.image.setImageResource(item.getImage());
        Animator.bounceInRecycler(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    MenuItem getItem(int id) {
        return items.get(id);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;
        public MaterialCardView card;
        public View view;

        public ViewHolder(View v) {
            super(v);
            this.view = v;

            this.card = v.findViewById(R.id.card_view);
            this.name = v.findViewById(R.id.title);
            this.image = v.findViewById(R.id.image);
        }
    }
}