package com.example.tgs.demodam.sqlitedao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tgs.demodam.database.DatabaseHelper;
import com.example.tgs.demodam.model.Bill;
import com.example.tgs.demodam.model.HoaDon;
import com.example.tgs.demodam.model.HoaDonChiTiet;
import com.example.tgs.demodam.model.Sach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDAO {
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    public static final String TABLE_NAME = "HoaDonChiTiet";
    public static final String SQL_HOA_DON_CHI_TIET = "CREATE TABLE HoaDonChiTiet (maHDCT INTEGER PRIMARY KEY AUTOINCREMENT," +
            " maHoaDon text NOT NULL, maSach text NOT NULL , soLuong INTEGER);";
    public static final String TAG = "HoaDonChiTiet";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public HoaDonChiTietDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    //insert
    public int inserHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        ContentValues values = new ContentValues();
        values.put("mahoadon", hoaDonChiTiet.getHoaDon().getMaHoaDon());
        values.put("maSach", hoaDonChiTiet.getSach().getMaSach());
        values.put("soLuong", hoaDonChiTiet.getSoLuongMua());
        try {
            if (database.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    //getAll
    public List<HoaDonChiTiet> getAllHoaDonChiTiet() {
        List<HoaDonChiTiet> dsHoaDonChiTiet = new ArrayList<>();
        String sSQL = " SELECT maHDCT, HoaDon.maHoaDon,HoaDon.ngayMua, " +
                "Sach.maSach, Sach.maTheLoai, Sach.tenSach, Sach.tacGia, Sach.NXB,Sach.giaBia, " +
                "Sach.soLuong,HoaDonChiTiet.soLuong FROM HoaDonChiTiet INNER JOIN HoaDon " +
                "on HoaDonChiTiet.maHoaDon = HoaDon.maHoaDon INNER JOIN Sach on Sach.maSach = HoaDonChiTiet.maSach";
        Cursor cursor = database.rawQuery(sSQL, null);
        cursor.moveToFirst();
        try {
            while (cursor.isAfterLast() == false) {
                HoaDonChiTiet ee = new HoaDonChiTiet();
                ee.setMaHDCT(cursor.getInt(0));
                ee.setHoaDon(new HoaDon(cursor.getString(1), simpleDateFormat.parse(cursor.getString(2))));
                ee.setSach(new Sach(cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getInt(8), cursor.getInt(9)));
                ee.setSoLuongMua(cursor.getInt(10));
                dsHoaDonChiTiet.add(ee);
                Log.d("//=====", ee.toString());
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return dsHoaDonChiTiet;
    }

    public List<HoaDonChiTiet> getAllHoaDonChiTietByID(String maHoaDon) {
        List<HoaDonChiTiet> dsHoaDonChiTiet = new ArrayList<>();
        String sSQL = "SELECT maHDCT, HoaDon.maHoaDon,HoaDon.ngayMua, " +
                "Sach.maSach, Sach.maTheLoai, Sach.tenSach, Sach.tacGia, Sach.NXB,Sach.giaBia, " +
                "Sach.soLuong,HoaDonChiTiet.soLuong FROM HoaDonChiTiet INNER JOIN HoaDon " +
                "on HoaDonChiTiet.maHoaDon = HoaDon.maHoaDon INNER JOIN Sach on Sach.maSach = HoaDonChiTiet.maSach where HoaDonChiTiet.maHoaDon='" + maHoaDon + "'";
        Cursor cursor = database.rawQuery(sSQL, null);
        cursor.moveToFirst();
        try {
            while (cursor.isAfterLast() == false) {
                HoaDonChiTiet ee = new HoaDonChiTiet();
                ee.setMaHDCT(cursor.getInt(0));
                ee.setHoaDon(new HoaDon(cursor.getString(1), simpleDateFormat.parse(cursor.getString(2))));
                ee.setSach(new
                        Sach(cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getInt(8), cursor.getInt(9)));
                ee.setSoLuongMua(cursor.getInt(10));
                dsHoaDonChiTiet.add(ee);
                Log.d("//=====", ee.toString());
                cursor.moveToNext();
            }
            cursor.close();

        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return dsHoaDonChiTiet;
    }
    //update
    public int updateHoaDonChiTiet(HoaDonChiTiet hd) {
        ContentValues values = new ContentValues();
        values.put("maHDCT", hd.getMaHDCT());
        values.put("mahoadon", hd.getHoaDon().getMaHoaDon());
        values.put("maSach", hd.getSach().getMaSach());
        values.put("soLuong", hd.getSoLuongMua());
        int result = database.update(TABLE_NAME, values, "maHDCT=?", new
                String[]{String.valueOf(hd.getMaHDCT())});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteHoaDonChiTietByID(String maHDCT){
        int result = database.delete(TABLE_NAME,"maHDCT=?",new String[]{maHDCT});
        if (result == 0)
            return -1;
        return 1;
    }

    //check
    public boolean checkHoaDon(String maHoaDon){
//SELECT
        String[] columns = {"maHoaDon"};
//WHERE clause
        String selection = "maHoaDon=?";
//WHERE clause arguments
        String[] selectionArgs = {maHoaDon};
        Cursor c = null;
        try{
            c = database.query(TABLE_NAME, columns, selection, selectionArgs, null, null,
                    null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if(i <= 0){
                return false;
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public double getDoanhThuTheoNgay(){
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " +
                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
                "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach where HoaDon.ngayMua = date('now') GROUP BY HoaDonChiTiet.maSach)tmp";
        Cursor cursor = database.rawQuery(sSQL, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }

    public double getDoanhThuTheoThang(){
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " +
                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
                "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach where strftime('%m',HoaDon.ngayMua) = strftime('%m','now') GROUP BY HoaDonChiTiet.maSach)tmp";
        Cursor cursor = database.rawQuery(sSQL, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }
    public double getDoanhThuTheoNam(){
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " +
                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
                "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach where strftime('%Y',HoaDon.ngayMua) = strftime('%Y','now') GROUP BY HoaDonChiTiet.maSach)tmp";
        Cursor cursor = database.rawQuery(sSQL, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }



}
