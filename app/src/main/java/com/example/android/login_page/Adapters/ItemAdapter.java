package com.example.android.login_page.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.login_page.Entity.Customer;
import com.example.android.login_page.Entity.Item;
import com.example.android.login_page.R;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    Item[] items;
    public static ItemAdapter.OnClickViewHolder clickHandler;
    public interface OnClickViewHolder{
        void onClick(Item item);
    }

    public ItemAdapter(Item[] items, ItemAdapter.OnClickViewHolder clickHandler){
        this.items = items;
        ItemAdapter.clickHandler = clickHandler;
    }
    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        holder.bindData(items[position]);
    }


    @Override
    public int getItemCount() {
        if(items == null){
            return 0;
        }
        else{
            return items.length;
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView mName;
        private final TextView mPrice;
        private final TextView mQuantity;
        private Item item;
        public ItemViewHolder(View v){
            super(v);
            mName = v.findViewById(R.id.tv_item_name);
            mPrice = v.findViewById(R.id.tv_price);
            mQuantity = v.findViewById(R.id.tv_quantity);
            v.findViewById(R.id.ll_item).setOnClickListener(this);
        }
        public void bindData(Item item){
            mName.setText(item.getItemName());
            mQuantity.setText(String.valueOf(item.getQuantity()));
            mPrice.setText(String.valueOf(item.getPrice()));
            this.item = item;
        }
        @Override
        public void onClick(View view) {
            clickHandler.onClick(item);
        }
    }

}
