package com.example.android.login_page.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.login_page.CustomersActivity;
import com.example.android.login_page.Entity.Customer;
import com.example.android.login_page.Entity.Worker;
import com.example.android.login_page.R;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    Customer[] customers;
    public static CustomerAdapter.OnClickViewHolder clickHandler;
    public interface OnClickViewHolder{
        void onClick(Customer customer);
    }

    public CustomerAdapter(Customer[] customers, CustomerAdapter.OnClickViewHolder clickHandler){
        this.customers = customers;
        CustomerAdapter.clickHandler = clickHandler;
    }
    @NonNull
    @Override
    public CustomerAdapter.CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_card,parent,false);
        return new CustomerAdapter.CustomerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        holder.bindData(customers[position]);
    }


    @Override
    public int getItemCount() {
        if(customers == null){
            return 0;
        }
        else{
            return customers.length;
        }
    }

    public static class CustomerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView mName;
        private final TextView mPhone;
        private Customer customer;
        public CustomerViewHolder(View v){
            super(v);
            mName = v.findViewById(R.id.tv_customer_name);
            mPhone = v.findViewById(R.id.tv_phone);
            v.findViewById(R.id.ll_customer).setOnClickListener(this);
        }
        public void bindData(Customer customer){
            mName.setText(customer.getCustomerName());
            mPhone.setText(customer.getPhoneNumbers().get(0));
            this.customer = customer;
        }
        @Override
        public void onClick(View view) {
            clickHandler.onClick(customer);
        }
    }
}
