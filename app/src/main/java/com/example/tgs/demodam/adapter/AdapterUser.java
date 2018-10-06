package com.example.tgs.demodam.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tgs.demodam.Constant;
import com.example.tgs.demodam.NguoiDungActivity;
import com.example.tgs.demodam.R;
import com.example.tgs.demodam.holder.UserHolder;
import com.example.tgs.demodam.model.User;
import com.example.tgs.demodam.sqlitedao.UserDAO;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<UserHolder> implements Constant {
    private Context context;
    private List<User> users;
    private UserDAO userDAO;

    public AdapterUser(Context context, List<User> users, UserDAO userDAO){
        this.context = context;
        this.users = users;
        this.userDAO = userDAO;
    }




    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.settingfragment, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder,final int position) {
        holder.tvName.setText(users.get(position).getName());
        holder.tvPhone.setText(users.get(position).getPhone());

        holder.btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, NguoiDungActivity.class);
                intent.putExtra(DATA, users.get(position));
                intent.putExtra(POSITION,position);
                context.startActivity(intent);

            }
        });

        holder.btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDAO.deleteUser(users.get(position).getUsername());
                notifyItemRemoved(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (users == null) return 0;
        return users.size();
    }
}
