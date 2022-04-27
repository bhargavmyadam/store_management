package com.example.android.login_page.Adapters;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.login_page.DAO.AdminDao;
import com.example.android.login_page.DAO.ItemDao;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.AdminItem;
import com.example.android.login_page.Entity.Customer;
import com.example.android.login_page.R;

public class AdminItemAdapter extends RecyclerView.Adapter<AdminItemAdapter.AdminItemViewHolder>{
    public AdminItem[] itemLog;
    public static SQLiteDatabase db;

    public AdminItemAdapter(AdminItem[] itemLog,SQLiteDatabase db){
        this.itemLog = itemLog;
        AdminItemAdapter.db = db;
    }
    @NonNull
    @Override
    public AdminItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_item_card,parent,false);
        return new AdminItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminItemViewHolder holder, int position) {
        holder.bindData(itemLog[position]);
    }

    @Override
    public int getItemCount() {
        if(itemLog == null){
            return 0;
        }
        else{
            return itemLog.length;
        }
    }

    public static class AdminItemViewHolder extends RecyclerView.ViewHolder{
        private final TextView mAdminName;
        private final TextView mAdminEmail;
        private final TextView mItemName;
        private final TextView mQuantity;
        private final TextView mDate;
        private AdminItem adminItem;
        public AdminItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mAdminName = itemView.findViewById(R.id.tv_admin_name);
            mAdminEmail = itemView.findViewById(R.id.tv_admin_email);
            mItemName = itemView.findViewById(R.id.tv_item_name);
            mQuantity= itemView.findViewById(R.id.tv_qty);
            mDate = itemView.findViewById(R.id.tv_date);
        }

        public void bindData(AdminItem adminItem) {
            this.adminItem = adminItem;
            mDate.setText(adminItem.getDate());
            mQuantity.setText(String.valueOf(adminItem.getQuantity()));
            mAdminName.setText(AdminDao.getAdminName(db,adminItem.getAdminId()));
            mAdminEmail.setText(AdminDao.getAdminEmail(db,adminItem.getAdminId()));
            mItemName.setText(ItemDao.getItemById(db,adminItem.getItemId()).getItemName());
        }
    }
}
