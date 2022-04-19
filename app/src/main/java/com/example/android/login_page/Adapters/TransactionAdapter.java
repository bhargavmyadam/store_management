package com.example.android.login_page.Adapters;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.login_page.DAO.TransactionUpdateItemDao;
import com.example.android.login_page.Entity.Transaction;
import com.example.android.login_page.Entity.Worker;
import com.example.android.login_page.R;
import com.example.android.login_page.TransactionActivity;
import com.example.android.login_page.TransactionsActivity;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    Transaction[] transactions;
    public static SQLiteDatabase db;
    public static TransactionAdapter.OnClickViewHolder clickHandler;
    public interface OnClickViewHolder{
        void onClick(Transaction transaction);
    }
    public TransactionAdapter(Transaction[] transactions, TransactionAdapter.OnClickViewHolder clickHandler,SQLiteDatabase db){
        this.transactions = transactions;
        TransactionAdapter.clickHandler = clickHandler;
        TransactionAdapter.db = db;
    }
    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_card,parent,false);
        return new TransactionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        holder.bindData(transactions[position]);
    }

    @Override
    public int getItemCount() {
        if(transactions == null){
            return 0;
        }
        else{
            return transactions.length;
        }
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView mTid;
        private final TextView mDate;
        private final TextView mAmount;
        private Transaction transaction;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            mTid = itemView.findViewById(R.id.tv_tid);
            mDate = itemView.findViewById(R.id.tv_date);
            mAmount = itemView.findViewById(R.id.tv_amount);
            itemView.findViewById(R.id.ll_transaction).setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickHandler.onClick(transaction);
        }

        public void bindData(Transaction transaction) {
            mTid.setText(String.valueOf(transaction.getTransactionId()));
            mDate.setText(String.valueOf(transaction.getDate()));
            mAmount.setText(String.valueOf(TransactionUpdateItemDao.getAmount(db,transaction.getTransactionId())));
            this.transaction = transaction;
        }
    }
}
