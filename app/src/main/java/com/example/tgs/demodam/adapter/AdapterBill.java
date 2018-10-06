package com.example.tgs.demodam.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tgs.demodam.R;
import com.example.tgs.demodam.holder.HolderBill;
import com.example.tgs.demodam.model.Bill;

import java.util.List;

public class AdapterBill extends RecyclerView.Adapter<HolderBill> {
    public Context context;
    public List<Bill> bills;

    public AdapterBill(Context context,List<Bill> bills){
        this.context = context;
        this.bills = bills;
    }


    @NonNull
    @Override
    public HolderBill onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_bill, parent,false);


        return new HolderBill(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderBill holder, int position) {

        Bill bill = bills.get(position);

        holder.tvInfo.setText(bill.toString());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        if (bills == null) return 0;
        return bills.size();
    }
}
