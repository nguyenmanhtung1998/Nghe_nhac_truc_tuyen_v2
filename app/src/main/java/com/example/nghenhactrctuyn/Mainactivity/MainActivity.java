package com.example.nghenhactrctuyn.Mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.nghenhactrctuyn.Adapter.MainPagerAdapter;
import com.example.nghenhactrctuyn.Fragment.Fragmen_Trang_Chu;
import com.example.nghenhactrctuyn.Fragment.Fragment_Tim_Kiem;
import com.example.nghenhactrctuyn.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       anhxa();
       init();


    }
    private  void anhxa(){
        tabLayout=(TabLayout) findViewById(R.id.mytablayout);
        viewPager=(ViewPager) findViewById(R.id.myview);
    }
    private void init(){
        MainPagerAdapter mainPagerAdapter=new MainPagerAdapter(getSupportFragmentManager());
        mainPagerAdapter.addFragment(new Fragmen_Trang_Chu(),"Trang chủ");
        mainPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"Tìm kiếm");
        viewPager.setAdapter(mainPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
    }
}
