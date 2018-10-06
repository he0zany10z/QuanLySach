package com.example.tgs.demodam.adapter.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tgs.demodam.Constant;
import com.example.tgs.demodam.R;
import com.example.tgs.demodam.adapter.AdapterBill;
import com.example.tgs.demodam.adapter.AdapterUser;
import com.example.tgs.demodam.database.DatabaseHelper;
import com.example.tgs.demodam.model.Bill;
import com.example.tgs.demodam.model.User;
import com.example.tgs.demodam.sqlitedao.BillDAO;
import com.example.tgs.demodam.sqlitedao.UserDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class SettingFragment extends Fragment implements Constant {

    private RecyclerView lvListBill;
    private AdapterBill adapterBill;
    private LinearLayoutManager linearLayoutManager;
    private List<Bill> bills;

    private BillDAO billDAO;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cai_dat,container,false);

        billDAO = new BillDAO(new DatabaseHelper(getActivity()));
        lvListBill = view.findViewById(R.id.lvListBill);

        bills = billDAO.getAllBills();

        linearLayoutManager = new LinearLayoutManager(getActivity());

        adapterBill = new AdapterBill(getActivity(), bills);

        lvListBill.setAdapter(adapterBill);
        lvListBill.setLayoutManager(linearLayoutManager);

        view.findViewById(R.id.btnAddBill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialogShowAddBill = new Dialog(getActivity());
                dialogShowAddBill.setTitle(getString(R.string.title_add_bills));
                dialogShowAddBill.setContentView(R.layout.dialog_add_bill);

                EditText edtBillID;
                final TextView tvDatePicker;
                Button btnOpenDatePicker;

                edtBillID = dialogShowAddBill.findViewById(R.id.edtBillID);
                tvDatePicker = dialogShowAddBill.findViewById(R.id.tvDatePicker);
                btnOpenDatePicker = dialogShowAddBill.findViewById(R.id.btnOpenDatePicker);

                btnOpenDatePicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDatePicker(tvDatePicker);
                    }
                });

                dialogShowAddBill.show();
            }
        });

        return view;




    }

    private void showDatePicker(final TextView tvDatePicker) {

        // lay ngay thang hien tai

        Calendar currentDate = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                long datePicked = 0;
                Calendar calendar = Calendar.getInstance();
                calendar.set(i, i1, i2);
                datePicked = calendar.getTimeInMillis();
                String datePicked_ = new Date(datePicked).toString();
                tvDatePicker.setText(datePicked_);


            }
        }, currentDate.get(Calendar.YEAR)
                , currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }
}
