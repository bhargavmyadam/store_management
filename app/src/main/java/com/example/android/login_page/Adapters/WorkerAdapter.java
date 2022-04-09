package com.example.android.login_page.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.login_page.Entity.Worker;
import com.example.android.login_page.R;
import com.example.android.login_page.WorkerActivity;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder> {
    Worker[] workers;
    public static OnClickViewHolder clickHandler;
    public interface OnClickViewHolder{
        void onClick(Worker worker);
    }

    public WorkerAdapter(Worker[] workers,OnClickViewHolder clickHandler){
        this.workers = workers;
        WorkerAdapter.clickHandler = clickHandler;
    }
    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.worker_item,parent,false);
        return new WorkerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, int position) {
        holder.bindData(workers[position]);
    }

    @Override
    public int getItemCount() {
        if(workers == null){
            return 0;
        }
        else{
            return workers.length;
        }
    }

    public static class WorkerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView mName;
        private final TextView mSalary;
        private final TextView mPhone;
        private Worker worker;
        public WorkerViewHolder(View v){
            super(v);
            mName = v.findViewById(R.id.tv_worker_name);
            mSalary = v.findViewById(R.id.tv_salary);
            mPhone = v.findViewById(R.id.tv_phone);
            v.findViewById(R.id.ll_worker).setOnClickListener(this);
        }
        public void bindData(Worker worker){
            mName.setText(worker.getWorkerName());
            mSalary.setText(String.valueOf(worker.getWorkerSalary()));
            mPhone.setText(worker.getPhoneNumbers().get(0));
            this.worker = worker;
        }
        @Override
        public void onClick(View view) {
            clickHandler.onClick(worker);
        }
    }
}
