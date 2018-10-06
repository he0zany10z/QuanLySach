package com.example.tgs.demodam.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tgs.demodam.R;


public class UserHolder extends RecyclerView.ViewHolder {

    public final TextView tvName;
    public final TextView tvPhone;
    public final ImageView btnEditUser;
    public final ImageView btnDeleteUser;



    public UserHolder(View convertView) {
        super(convertView);

        tvName = convertView.findViewById(R.id.tvName);
        tvPhone = convertView.findViewById(R.id.tvPhone);
        btnEditUser = convertView.findViewById(R.id.btnEditUser);
        btnDeleteUser = convertView.findViewById(R.id.btnDeleteUser);

    }
}
