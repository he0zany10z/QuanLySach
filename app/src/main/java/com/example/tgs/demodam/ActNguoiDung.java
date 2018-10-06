package com.example.tgs.demodam;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

import com.example.tgs.demodam.adapter.AdapterUser;
import com.example.tgs.demodam.database.DatabaseHelper;
import com.example.tgs.demodam.model.User;
import com.example.tgs.demodam.sqlitedao.UserDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ActNguoiDung extends AppCompatActivity implements Constant {
    private RecyclerView lvnguoidung;
    private AdapterUser adapterUser;
    private LinearLayoutManager linearLayoutManager;
    private List<User> userList;
    private DatabaseHelper databaseHelper;
    private UserDAO userDAO;
    private BroadcastReceiver onUpdateUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initViews();

        initData();
    }


    private void initData() {
        userList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
        databaseHelper = new DatabaseHelper(this);
        userDAO = new UserDAO(databaseHelper);
        userList = userDAO.getAllUser();

        adapterUser = new AdapterUser(this,userList,userDAO);
        lvnguoidung.setAdapter(adapterUser);
        lvnguoidung.setLayoutManager(linearLayoutManager);

        onUpdateUser = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int position = intent.getIntExtra(POSITION,-1);
                User userUpdated = (User) intent.getSerializableExtra(DATA);

                userList.get(position).setName(userUpdated.getName());
                userList.get(position).setPhone(userUpdated.getPhone());
                userList.get(position).setPassword(userUpdated.getPassword());
                adapterUser.notifyDataSetChanged();


            }
        };

        IntentFilter intentFilter = new IntentFilter(ON_UPDATE_USER);
        registerReceiver(onUpdateUser, intentFilter);
    }

    private void initViews() {
        setContentView(R.layout.act_nguoi_dung);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        lvnguoidung = findViewById(R.id.lvnguoidung);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nguoidung, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.them){
            Intent intent = new Intent(this,NguoiDungActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
