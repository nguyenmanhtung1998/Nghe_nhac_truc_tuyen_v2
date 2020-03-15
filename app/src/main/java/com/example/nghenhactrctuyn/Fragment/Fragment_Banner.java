package com.example.nghenhactrctuyn.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.nghenhactrctuyn.Adapter.Banner_Adapter;
import com.example.nghenhactrctuyn.Model.Quangcao;
import com.example.nghenhactrctuyn.R;
import com.example.nghenhactrctuyn.Service.APIservice;
import com.example.nghenhactrctuyn.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
     View view;
     ViewPager viewPager;
     CircleIndicator circleIndicator;
     Banner_Adapter banner_adapter;
     Runnable runnable;
     Handler handler; //thoi gian tu chuyen quang cao
     int curntitem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.banner_layout,container,false);
        anhxa();
        GetData();
        return view;
    }

    private void anhxa() {
        viewPager=view.findViewById(R.id.viewpager);
        circleIndicator=view.findViewById(R.id.indicartordefault);
    }

    private void GetData(){
        Dataservice dataservice= APIservice.getService();
        Call<List<Quangcao>> callback=dataservice.GetdataBanner();
        callback.enqueue(new Callback<List<Quangcao>>() {
            @Override
            public void onResponse(Call<List<Quangcao>> call, Response<List<Quangcao>> response) {
                ArrayList<Quangcao> banner= (ArrayList<Quangcao>) response.body();
                banner_adapter=new Banner_Adapter(getActivity(),banner);
                viewPager.setAdapter(banner_adapter);
                circleIndicator.setViewPager(viewPager);
                handler=new Handler();
                runnable=new Runnable() {
                    @Override
                    public void run() {
                        curntitem=viewPager.getCurrentItem();
                        curntitem++;
                        if(curntitem>=viewPager.getAdapter().getCount()){
                            curntitem=0;
                        }
                        viewPager.setCurrentItem(curntitem,true);
                        handler.postDelayed(runnable,4500);
                    }
                };
                handler.postDelayed(runnable,4500);
            }

            @Override
            public void onFailure(Call<List<Quangcao>> call, Throwable t) {

            }
        });
    }
}
