package com.example.tgs.demodam.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.tgs.demodam.model.NguoiDung;

import java.util.List;

public class CustomThemNguoiDungAdapter extends ArrayAdapter<NguoiDung> {
    Context context;
    int resource;
    List<NguoiDung> nguoiDungs;



    public CustomThemNguoiDungAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public CustomThemNguoiDungAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public CustomThemNguoiDungAdapter(@NonNull Context context, int resource, @NonNull NguoiDung[] objects) {
        super(context, resource, objects);
    }

    public CustomThemNguoiDungAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull NguoiDung[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public CustomThemNguoiDungAdapter(@NonNull Context context, int resource, @NonNull List<NguoiDung> objects) {
        super(context, resource, objects);
    }

    public CustomThemNguoiDungAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<NguoiDung> objects) {
        super(context, resource, textViewResourceId, objects);
    }


}
