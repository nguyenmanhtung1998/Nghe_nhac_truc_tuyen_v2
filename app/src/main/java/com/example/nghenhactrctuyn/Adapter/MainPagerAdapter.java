package com.example.nghenhactrctuyn.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    private ArrayList<String> arrayListtitle=new ArrayList<>();
    public MainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    public void addFragment (Fragment fragment,String title){
        fragmentArrayList.add(fragment);
        arrayListtitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayListtitle.get(position);
    }
}
