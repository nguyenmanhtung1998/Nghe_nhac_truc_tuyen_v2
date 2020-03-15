package com.example.nghenhactrctuyn.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class Viewpagerplaylist extends FragmentPagerAdapter {
    public final ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    public Viewpagerplaylist(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    public void AddFra(Fragment fragment){ fragmentArrayList.add(fragment);
    }
}
