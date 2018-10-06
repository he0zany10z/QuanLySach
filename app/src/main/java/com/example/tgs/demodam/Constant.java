package com.example.tgs.demodam;

public interface Constant {

     String POSITION = "position";
     String DATA = "data";
     String ON_UPDATE_USER = "on_update_user";

    /*table user*/
     String TABLE_USER = "USER";

     String COLUMN_USERNAME = "Username";
     String COLUMN_PASSWORD = "Password";
     String COLUMN_NAME = "Name";
     String COLUMN_PHONE = "PhoneNumber";

     String CREATE_TABLE_USER = " CREATE TABLE " + TABLE_USER + "(" +
            COLUMN_USERNAME + " VARCHAR PRIMARY KEY, " +
            COLUMN_PASSWORD + " VARCHAR, " +
            COLUMN_NAME + " VARCHAR, " +
            COLUMN_PHONE + " VARCHAR " +
            ")";


     //Create table typebook(MaTheLoai Char(5) PRIMARY KEY not null,
    // Type Name NVARCHAR(50) Not null,
    // Description NVARCHAR(255),
    // Position INT)

    String TABLE_TYPE_BOOK = "TypeBook";


    String TB_COLUMN_ID = "MaTheLoai";
    String TB_COLUMN_NAME = "TypeName";
    String TB_COLUMN_DES = "Description";
    String TB_TYPE_POS = "Position";

    String CREATE_TABLE_TYPE_BOOK = "CREATE TABLE " + TABLE_TYPE_BOOK + "(" +
            "" + TB_COLUMN_ID + " PRIMARY KEY NOT NULL," +
            "" + TB_COLUMN_NAME + " NVARCHAR(50) NOT NULL,"+
            "" + TB_COLUMN_DES + " NVARCHAR(255)," +
            "" + TB_TYPE_POS + " INT" +
            ")";

    //table bill
    //create table bill

    String TABLE_BILL = "Bill";
    String B_ID = "MaHoaDon";
    String B_DATE = "NgayMua";

    String CREATE_TABLE_BILL = "CREATE TABLE " + TABLE_BILL + "(" +
            "" + B_ID + " NCHAR(7) PRIMARY KEY NOT NULL," +
            ""+ B_DATE + " LONG NOT NULL" +
            ")";





}
