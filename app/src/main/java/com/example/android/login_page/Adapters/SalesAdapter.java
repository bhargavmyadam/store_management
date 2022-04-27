package com.example.android.login_page.Adapters;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.login_page.DAO.ItemDao;
import com.example.android.login_page.Entity.SalesItem;
import com.example.android.login_page.R;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.SalesItemViewHolder> {
    public SalesItem[] salesItems;
    public static SQLiteDatabase db;
    public SalesAdapter(SalesItem[] salesItems, SQLiteDatabase db){
        this.salesItems = salesItems;
        SalesAdapter.db = db;
    }

    @NonNull
    @Override
    public SalesItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_item_card,parent,false);
        return new SalesItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesItemViewHolder holder, int position) {
        holder.bindData(salesItems[position]);
    }

    @Override
    public int getItemCount() {
        if(salesItems == null){
            return 0;
        }
        else{
            return salesItems.length;
        }
    }

    public static class SalesItemViewHolder extends RecyclerView.ViewHolder{
        private final TextView mItemName;
        private final TextView mQuantity;
        private SalesItem salesItem;
        public SalesItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemName = itemView.findViewById(R.id.tv_item_name);
            mQuantity= itemView.findViewById(R.id.tv_quantity);
        }

        public void bindData(SalesItem salesItem) {
            this.salesItem = salesItem;
            mItemName.setText(ItemDao.getItemById(db,salesItem.getItemId()).getItemName());
            mQuantity.setText(String.valueOf(salesItem.getQuantity()));
        }
    }
}
