package com.example.tgs.demodam.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tgs.demodam.Constant;
import com.example.tgs.demodam.common.Constants;
import com.example.tgs.demodam.database.DatabaseHelper;
import com.example.tgs.demodam.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Constant {

    private DatabaseHelper databaseHelper;

    public UserDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }




    public void insertUser(User user) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        //sử dụng ContenttValues để đưa dữ liệu vào DB

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, user.getUsername());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_NAME, user.getName());
        contentValues.put(COLUMN_PHONE, user.getPhone());

        long id = sqLiteDatabase.insert(TABLE_USER, null, contentValues);

        if (Constants.isDEBUG) Log.e("inserUser", "inserUser:" + id);

        sqLiteDatabase.close();

    }

    public User getUserByUsername(String username){
        User user = null;
        //xin quyền ghi sqLite
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        //query tìm kiếm user với username = tham số truyền vào
        //1: tên bảng
        //2: array cột cần lấy dữ liệu
        //3: tên cột dùng để query
        //4: giá trị cần tìm
        //5,6,7: null. Là các câu lệnh xắp xếp điều kiện...
        Cursor cursor = sqLiteDatabase.query(TABLE_USER,
                new String[]{COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_NAME, COLUMN_PHONE},
                COLUMN_USERNAME + "=?", new String[]{username},
                null,null,null);

        if (cursor != null && cursor.moveToFirst()){
            String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
            user = new User(user_name, password, name, phone);

        }

        return user ;
    }

    public List<User> getAllUser(){
        List<User> userList = new ArrayList<>();
        //xin quyền đọc dữ liệu

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        //viết câu lệnh select all
        String SELECT_ALL_USER = "SELECT * FROM " + TABLE_USER;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_USER, null);
        if (cursor.moveToFirst()){
            do {
                String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
                User user = new User(user_name, password, name, phone);
                userList.add(user);

            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();

        return userList;
    }

    public void updateUser(User user){
        //xin quyền đọc dữ liệu
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        //sử dụng Contentvalues để sửa dữ liệu vào DB
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, user.getUsername());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_NAME, user.getName());
        contentValues.put(COLUMN_PHONE, user.getPhone());

        sqLiteDatabase.update(TABLE_USER,contentValues,COLUMN_USERNAME+"=?",new String[]{user.getUsername()});
        sqLiteDatabase.close();
    }

    public void deleteUser(String username){
        //xin quyền đọc dữ liệu
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_USER,COLUMN_USERNAME+"=?",new String[]{username});
        sqLiteDatabase.close();
    }


}
