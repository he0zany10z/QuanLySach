package com.example.tgs.demodam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.example.tgs.demodam.adapter.MyPagerAdapter;
import com.example.tgs.demodam.adapter.fragment.SettingFragment;

public class HomeActivity extends AppCompatActivity{

        private TabLayout tltabs;
        private ViewPager pagerid;
        private MyPagerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tltabs = findViewById(R.id.tltabs);
        pagerid = findViewById(R.id.pagerid);
        adapter = new MyPagerAdapter(getSupportFragmentManager());


        pagerid.setAdapter(adapter);
        tltabs.setupWithViewPager(pagerid);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nguoidung:
                Intent intentt = new Intent(HomeActivity.this,ActNguoiDung.class);
                startActivity(intentt);
                break;
            case R.id.theloai:
                Intent intent = new Intent(HomeActivity.this,ActTheLoai.class);
                startActivity(intent);
                break;
            case R.id.themsach:
                Intent intent1l = new Intent(HomeActivity.this, ActListBook.class);
                startActivity(intent1l);
                break;
            case R.id.hoadon:
                Intent intent1ll = new Intent(HomeActivity.this, ActListHoaDon.class);
                startActivity(intent1ll);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
