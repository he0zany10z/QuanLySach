package com.example.tgs.demodam.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.example.tgs.demodam.R;
import com.example.tgs.demodam.holder.HolderTypeBook;
import com.example.tgs.demodam.model.TypeBook;
import com.example.tgs.demodam.sqlitedao.TypeBookDAO;

import java.util.List;

public class AdapterTypeBook extends RecyclerView.Adapter<HolderTypeBook> {

    private Context context;
    private List<TypeBook> typeBooks;
    private TypeBookDAO typeBookDAO;

    public AdapterTypeBook(Context context, List<TypeBook> typeBooks, TypeBookDAO typeBookDAO) {
        this.context = context;
        this.typeBooks = typeBooks;
        this.typeBookDAO = typeBookDAO;
    }

    @NonNull
    @Override
    public HolderTypeBook onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_type_book, parent, false);
        return new HolderTypeBook(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderTypeBook holder, final int position) {

        final TypeBook typeBook = typeBooks.get(position);
        holder.tvId.setText(typeBook.id);
        holder.tvName.setText(typeBook.name);
        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long result = typeBookDAO.deleteTypeBook(typeBook.id);

                if (result > 0) {
                    typeBooks.remove(position);
                    notifyDataSetChanged();
                } else {
                    // ko thanh cong
                }
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_themsach);
                final EditText tvId;
                final EditText tvName;
                final EditText tvPos;
                final EditText tvDes;
                Button btnSave;
                Button btnCancel;

                tvId = dialog.findViewById(R.id.tvId);
                tvId.setVisibility(View.GONE);
                tvName = dialog.findViewById(R.id.tvName);
                tvPos = dialog.findViewById(R.id.tvPos);
                tvDes = dialog.findViewById(R.id.tvDes);
                btnSave = dialog.findViewById(R.id.btnSave);
                btnCancel = dialog.findViewById(R.id.btnCancel);
                btnSave.setText("Update");


                tvName.setText(typeBook.name);

                tvDes.setText(typeBook.description);

                tvPos.setText(typeBook.position);

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TypeBook typeBook_ = new TypeBook();
                        String name = tvName.getText().toString().trim();
                        String des = tvDes.getText().toString().trim();
                        String pos = tvPos.getText().toString().trim();
                        typeBook_.id = typeBook.id;
                        typeBook_.name = name;
                        typeBook_.description = des;
                        typeBook_.position = pos;
                        long result = typeBookDAO.updateTypeBook(typeBook);
                        if (result > 0) {

                            typeBooks.get(position).id = typeBook.id;

                            typeBooks.get(position).name = name;

                            typeBooks.get(position).description = des;

                            typeBooks.get(position).position = pos;

                            notifyDataSetChanged();

                            dialog.cancel();

                        } else {
                            // update ko thanh cong
                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return typeBooks.size();
    }
}
