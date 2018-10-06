package com.example.tgs.demodam.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tgs.demodam.adapter.fragment.HomeFragment;
import com.example.tgs.demodam.adapter.fragment.NewsFragment;
import com.example.tgs.demodam.adapter.fragment.SettingFragment;

public class MyPagerAdapter extends FragmentPagerAdapter  {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new NewsFragment();
            case 2:
                return new SettingFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Tìm Kiếm";
            case 1:
                return "Thêm Sách";
            case 2:
                return "Hóa Đơn";
            default:
                return "Tìm Kiếm";
        }
    }

}
