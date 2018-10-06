package com.example.tgs.demodam.sqlitedao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tgs.demodam.database.DatabaseHelper;
import com.example.tgs.demodam.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    public BookDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public static final String TABLE_NAME ="Sach";
    public static final String SQL_SACH = "CREATE TABLE sach (maSach text PRIMARY KEY, maTheLoai text, tensach text," +
            "tacGia text, NXB text, giaBia double, soluong number);";
    public static final String TAG = "SachDAO";

    public BookDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }
    //inserBook
    public int inserBook(Sach sach){
        ContentValues values = new ContentValues();
        values.put("maSach",sach.getMaSach());
        values.put("maTheLoai",sach.getMaTheLoai());
        values.put("tensach",sach.getTenSach());
        values.put("tacGia",sach.getTacGia());
        values.put("NXB",sach.getNXB());
        values.put("giaBia",sach.getGiaBia());
        values.put("soLuong",sach.getSoLuong());
        if (checkPrimaryKey(sach.getMaSach())){
            int result = database.update(TABLE_NAME,values,"masach=?", new
                    String[]{sach.getMaSach()});
            if (result == 0){
                return -1;
            }
        }else {
            try {
                if (database.insert(TABLE_NAME, null, values) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }

    //getAll
    public List<Sach> getAllSach(){
        List<Sach> dsSach = new ArrayList<>();
        Cursor c = database.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Sach s = new Sach();
            s.setMaSach(c.getString(0));
            s.setMaTheLoai(c.getString(1));
            s.setTenSach(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBia(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            dsSach.add(s);
            Log.d("//=====",s.toString());
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }

    //update
    public int updateSach(Sach sach){
        ContentValues values = new ContentValues();
        values.put("maSach",sach.getMaSach());
        values.put("maTheLoai",sach.getMaTheLoai());
        values.put("tensach",sach.getTenSach());
        values.put("tacGia",sach.getTacGia());
        values.put("NXB",sach.getNXB());
        values.put("giaBia",sach.getGiaBia());
        values.put("soLuong",sach.getSoLuong());
        int result = database.update(TABLE_NAME,values,"maSach=?", new
                String[]{sach.getMaSach()});
        if (result == 0){
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteSachByID(String maSach){
        int result = database.delete(TABLE_NAME,"maSach=?",new String[]{maSach});
        if (result == 0)
            return -1;
        return 1;
    }

    private boolean checkPrimaryKey(String strPrimaryKey) {
        //SELECT
        String[] columns = {"masach"};
//WHERE clause
        String selection = "masach=?";
//WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
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

    //check
    public Sach checkBook(String strPrimaryKey) {
        Sach sach = new Sach();
//SELECT
        String[] columns = {"masach"};
//WHERE clause
        String selection = "masach=?";
//WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor cursor = null;

        try {
            cursor = database.query(TABLE_NAME, columns, selection, selectionArgs, null, null,
                    null);
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                sach.setMaSach(cursor.getString(0));
                sach.setMaTheLoai(cursor.getString(1));
                sach.setTenSach(cursor.getString(2));
                sach.setTacGia(cursor.getString(3));
                sach.setNXB(cursor.getString(4));
                sach.setGiaBia(cursor.getDouble(5));
                sach.setSoLuong(cursor.getInt(6));
                Log.d("//=====",sach.toString());
                break;
        }cursor.close();
            return sach;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //getAll
    public Sach getSachByID(String maSach){
        Sach s = null;
//WHERE clause
        String selection = "masach=?";
//WHERE clause arguments
        String[] selectionArgs = {maSach};
        Cursor c = database.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        Log.d("getSachByID","===>"+ c.getCount());
        c.moveToFirst();
        while (c.isAfterLast()==false){
            s = new Sach();
            s.setMaSach(c.getString(0));
            s.setMaTheLoai(c.getString(1));
            s.setTenSach(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBia(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            break;
        }
        c.close();
        return s;
    }
    //getAll
    public List<Sach> getSachTop10(String month){
        List<Sach> dsSach = new ArrayList<>();
        if (Integer.parseInt(month)<10){
            month = "0"+month;
        }
        String sSQL = " SELECT maSach, SUM(soLuong) as soLuong FROM HoaDonChiTiet INNER JOIN HoaDon " +
                "ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon WHERE\n" +
                "strftime('%m',HoaDon.ngayMua) = '" +month+"' " + "GROUP BY maSach ORDER BY soluong DESC LIMIT 10";
        Cursor cursor = database.rawQuery(sSQL, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Log.d("//=====",cursor.getString(0));
            Sach sach = new Sach();
            sach.setMaSach(cursor.getString(0));
            sach.setSoLuong(cursor.getInt(1));
            sach.setGiaBia(0);
            sach.setMaTheLoai("");
            sach.setTenSach("");
            sach.setTacGia("");
            sach.setNXB("");
            dsSach.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        return dsSach;

    }
}


