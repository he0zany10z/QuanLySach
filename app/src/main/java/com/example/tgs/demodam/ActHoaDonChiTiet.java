package com.example.tgs.demodam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tgs.demodam.adapter.CartAdapter;
import com.example.tgs.demodam.model.Bill;
import com.example.tgs.demodam.model.HoaDon;
import com.example.tgs.demodam.model.HoaDonChiTiet;
import com.example.tgs.demodam.model.Sach;
import com.example.tgs.demodam.sqlitedao.BookDAO;
import com.example.tgs.demodam.sqlitedao.HoaDonChiTietDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActHoaDonChiTiet extends AppCompatActivity {
    EditText edMaSach, edMaHoaDon, edSoLuong;
    TextView tvThanhTien;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    BookDAO bookDAO;
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    double thanhTien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CHI TIẾT HOÁ ĐƠN");
        setContentView(R.layout.act_hoa_don_chi_tiet);
        edMaSach = (EditText) findViewById(R.id.edMaSach);
        edMaHoaDon = (EditText) findViewById(R.id.edMaHoaDon);
        edSoLuong = (EditText) findViewById(R.id.edSoLuongMua);
        lvCart = (ListView) findViewById(R.id.lvCart);
        tvThanhTien = (TextView) findViewById(R.id.tvThanhTien);
        adapter = new CartAdapter(dsHDCT, this);
        lvCart.setAdapter(adapter);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaHoaDon.setText(b.getString("MAHOADON"));
        }
    }

    public void ADDHoaDonCHITIET(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(ActHoaDonChiTiet.this);
        bookDAO = new BookDAO(ActHoaDonChiTiet.this);
        try {
            if (validation() <0){
                Toast.makeText(getApplicationContext(), "Vui Lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }else {
                Sach sach = bookDAO.getSachByID(edMaSach.getText().toString());
                if (sach != null){
                    int pos = checkMaSach(dsHDCT,edMaSach.getText().toString());
                    HoaDon hoaDon = new HoaDon(edMaHoaDon.getText().toString(),new Date());
                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(1,hoaDon,sach,Integer.parseInt(edSoLuong.getText().toString()));
                    if (pos >= 0){
                        int soluong = dsHDCT.get(pos).getSoLuongMua();
                        hoaDonChiTiet.setSoLuongMua(soluong + Integer.parseInt(edSoLuong.getText().toString()));
                        dsHDCT.set(pos,hoaDonChiTiet);
                    }else {
                        Toast.makeText(getApplicationContext(), "Mã Sách không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }catch (Exception ex){
            Log.e("Error", ex.toString());
        }

    }

    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(ActHoaDonChiTiet.this);
//tinh tien
        thanhTien = 0;
        try {
            for (HoaDonChiTiet hd: dsHDCT) {
                hoaDonChiTietDAO.inserHoaDonChiTiet(hd);
                thanhTien = thanhTien + hd.getSoLuongMua() *
                        hd.getSach().getGiaBia();
            }
            tvThanhTien.setText("Tổng tiền: " +thanhTien);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int checkMaSach(List<HoaDonChiTiet> lsHD, String maSach){
        int pos = -1;
        for (int i = 0; i < lsHD.size(); i++){
            HoaDonChiTiet hd = lsHD.get(i);
            if (hd.getSach().getMaSach().equalsIgnoreCase(maSach)){
                pos = i;
                break;
            }
        }
        return pos;
    }

    public int validation(){
        if (edMaSach.getText().toString().isEmpty()||edSoLuong.getText().toString().isEmpty()||edMaHoaDon.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
    }




}
