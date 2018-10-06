package com.example.tgs.demodam.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tgs.demodam.Constant;
import com.example.tgs.demodam.database.DatabaseHelper;
import com.example.tgs.demodam.model.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillDAO implements Constant {
    private DatabaseHelper databaseHelper;

    public BillDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long insertBill(Bill bill) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(B_ID, bill.id);
        contentValues.put(B_DATE, bill.date);

        long result = sqLiteDatabase.insert(TABLE_BILL, null, contentValues);

        sqLiteDatabase.close();
        return result;
    }

    public long updateBill(Bill bill) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(B_DATE, bill.date);

        long result = sqLiteDatabase.update(TABLE_BILL, contentValues,
                TB_COLUMN_ID + "=?",
                new String[]{bill.id});

        sqLiteDatabase.close();

        return result;
    }

    public long deleteBill(String billID) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        long result = sqLiteDatabase.delete(TABLE_BILL, TB_COLUMN_ID + "=?",
                new String[]{billID});

        sqLiteDatabase.close();

        return result;
    }

    public List<Bill> getAllBills() {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        List<Bill> bills = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_BILL,
                null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {

                String id = cursor.getString(cursor.getColumnIndex(B_ID));
                long date = cursor.getLong(cursor.getColumnIndex(B_DATE));

                Bill bill = new Bill(id, date);

                bills.add(bill);

            } while (cursor.moveToNext());

        }
        sqLiteDatabase.close();

        return bills;

    }

    public Bill getBillByID(String billID){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Bill bill = null;

        Cursor cursor = sqLiteDatabase.query(TABLE_BILL,
                new String[]{B_ID, B_DATE}, TB_COLUMN_ID + "=?",
                new String[]{billID}, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            String id = cursor.getString(cursor.getColumnIndex(B_ID));
            long date = cursor.getLong(cursor.getColumnIndex(B_DATE));
            bill = new Bill(id, date);

        }

        sqLiteDatabase.close();

        return bill;
    }
}
