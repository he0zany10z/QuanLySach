package com.example.tgs.demodam.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tgs.demodam.R;

public class HolderTypeBook extends RecyclerView.ViewHolder {
    public TextView tvId;
    public TextView tvName;
    public ImageView btnEdit;
    public ImageView btnDel;


    public HolderTypeBook(View itemView) {
        super(itemView);

        tvId = itemView.findViewById(R.id.tvId);
        tvName = itemView.findViewById(R.id.tvName);
        btnEdit = itemView.findViewById(R.id.btnEdit);
        btnDel = itemView.findViewById(R.id.btnDel);
    }
}
