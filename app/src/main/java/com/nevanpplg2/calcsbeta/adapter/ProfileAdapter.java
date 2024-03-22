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

public class ProfileAdapter extends RecyclerView.Adapter {
    private final List<MenuItem> items;
    private int color;
    private Context context;

    public ProfileAdapter(Context context, List<MenuItem> items, int color) {
        this.context = context;
        this.items = items;
        this.color = color;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(this.context).inflate(R.layout.profile_item, parent, false);
            return new ViewHolderProfile(view);
        }
        View view2 = LayoutInflater.from(this.context).inflate(R.layout.list_item, parent, false);
        return new ViewHolderNormal(view2);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MenuItem item = items.get(position);
        if (holder.getItemViewType() == 0) {
            ViewHolderProfile holderProfile = (ViewHolderProfile) holder;
            holderProfile.name.setText(item.getName());
            holderProfile.email.setText(item.getEmail());
            holderProfile.card.setStrokeColor(color);
        } else {
            ViewHolderNormal holderNormal = (ViewHolderNormal) holder;
            holderNormal.card.setStrokeColor(color);
            holderNormal.view.setOnClickListener(item.getOnClickListener());
            holderNormal.name.setText(item.getName());
            holderNormal.image.setImageResource(item.getImage());
        }
        Animator.bounceInRecycler(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    MenuItem getItem(int id) {
        return items.get(id);
    }

    public static class ViewHolderProfile extends RecyclerView.ViewHolder {
        public TextView name, email;
        public MaterialCardView card;
        public View view;

        public ViewHolderProfile(View v) {
            super(v);
            this.view = v;

            this.name = v.findViewById(R.id.title);
            this.email = v.findViewById(R.id.desc);
            this.card = v.findViewById(R.id.card_view);
        }
    }

    public static class ViewHolderNormal extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;
        public MaterialCardView card;
        public View view;

        public ViewHolderNormal(View v) {
            super(v);
            this.view = v;

            this.card = v.findViewById(R.id.card_view);
            this.name = v.findViewById(R.id.title);
            this.image = v.findViewById(R.id.image);
        }
    }
}