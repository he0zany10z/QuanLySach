package com.example.tgs.demodam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.tgs.demodam.adapter.CartAdapter;
import com.example.tgs.demodam.model.HoaDonChiTiet;
import com.example.tgs.demodam.sqlitedao.HoaDonChiTietDAO;

import java.util.ArrayList;
import java.util.List;

public class ActListHoaDonChiTietByID extends AppCompatActivity {
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("HOÁ ĐƠN CHI TIẾT");
        setContentView(R.layout.act_list_hoa_don_chi_tiet_by_id);
        lvCart = (ListView) findViewById(R.id.lvHoaDonChiTiet);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(ActListHoaDonChiTietByID.this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            dsHDCT = hoaDonChiTietDAO.getAllHoaDonChiTietByID(b.getString("MAHOADON"));
        }
        adapter = new CartAdapter(dsHDCT, this);
        lvCart.setAdapter(adapter);
    }
}
