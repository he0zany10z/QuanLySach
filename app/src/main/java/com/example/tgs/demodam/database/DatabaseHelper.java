package com.example.tgs.demodam.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tgs.demodam.Constant;
import com.example.tgs.demodam.common.Constants;
import com.example.tgs.demodam.model.User;
import com.example.tgs.demodam.sqlitedao.BookDAO;
import com.example.tgs.demodam.sqlitedao.HoaDonChiTietDAO;
import com.example.tgs.demodam.sqlitedao.HoaDonDAO;
import com.example.tgs.demodam.sqlitedao.TheLoaiDAO;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper implements Constant {
    private SQLiteDatabase db;




    public DatabaseHelper(Context context) {
        super(context, "BookManager", null, 1);
    }






    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_USER);

        sqLiteDatabase.execSQL(CREATE_TABLE_TYPE_BOOK);

        sqLiteDatabase.execSQL(CREATE_TABLE_BILL);

        sqLiteDatabase.execSQL(BookDAO.SQL_SACH);

        sqLiteDatabase.execSQL(HoaDonDAO.SQL_HOA_DON);

        sqLiteDatabase.execSQL(HoaDonChiTietDAO.SQL_HOA_DON_CHI_TIET);

        sqLiteDatabase.execSQL(TheLoaiDAO.SQL_THE_LOAI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_TYPE_BOOK);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_BILL);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + BookDAO.TABLE_NAME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + HoaDonDAO.TABLE_NAME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + HoaDonChiTietDAO.TABLE_NAME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TheLoaiDAO.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
