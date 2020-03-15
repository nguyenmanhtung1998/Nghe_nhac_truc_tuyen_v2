package com.example.nghenhactrctuyn.Mainactivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.nghenhactrctuyn.Adapter.Danhsachbaihat_Adapter;
import com.example.nghenhactrctuyn.Adapter.DanhsachchudeAdapter;
import com.example.nghenhactrctuyn.Model.Chude;
import com.example.nghenhactrctuyn.R;
import com.example.nghenhactrctuyn.Service.APIservice;
import com.example.nghenhactrctuyn.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Danhsachchude extends AppCompatActivity {
    RecyclerView recyclerViewchude;
    Toolbar toolbar;
    Chude chude;
    ArrayList<Chude> chudeArrayList;
    DanhsachchudeAdapter danhsachchudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachchude);
        anhxa();
        init();
        getdata();
    }

    private void getdata() {
        Dataservice dataservice= APIservice.getService();
        Call<List<Chude>> callback=dataservice.Getfullchude();
        callback.enqueue(new Callback<List<Chude>>() {
            @Override
            public void onResponse(Call<List<Chude>> call, Response<List<Chude>> response) {
                chudeArrayList= (ArrayList<Chude>) response.body();
                danhsachchudeAdapter=new DanhsachchudeAdapter(Danhsachchude.this,chudeArrayList);
                LinearLayoutManager linearLayout=new LinearLayoutManager(Danhsachchude.this);
                linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewchude.setLayoutManager(linearLayout);
                recyclerViewchude.setAdapter(danhsachchudeAdapter);
            }

            @Override
            public void onFailure(Call<List<Chude>> call, Throwable t) {

            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void init() {
       setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setTitle("Tat Ca Chu De");
      toolbar.setTitleTextColor(R.color.playlistcolor);
      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
      });



    }

    private void anhxa() {
        recyclerViewchude=(RecyclerView) findViewById(R.id.recycleviewchude);
        toolbar=findViewById(R.id.toolbarchude);
    }
}
