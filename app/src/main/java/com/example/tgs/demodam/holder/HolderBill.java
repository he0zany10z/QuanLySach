package com.example.tgs.demodam.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tgs.demodam.R;

public class HolderBill extends RecyclerView.ViewHolder {

    public TextView tvInfo;
    public ImageView btnDelete;

    public HolderBill(View itemView) {
        super(itemView);

        tvInfo = itemView.findViewById(R.id.tvInfo);
        btnDelete = itemView.findViewById(R.id.btnDelete);

    }
}
