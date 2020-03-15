package com.example.nghenhactrctuyn.Mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nghenhactrctuyn.Adapter.DanhsachtheloaitheochudeAdapter;
import com.example.nghenhactrctuyn.Model.Chude;
import com.example.nghenhactrctuyn.Model.Theloai;
import com.example.nghenhactrctuyn.R;
import com.example.nghenhactrctuyn.Service.APIservice;
import com.example.nghenhactrctuyn.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Theloaitheochude extends AppCompatActivity {
    DanhsachtheloaitheochudeAdapter adapter;
    Toolbar toolbar;
    RecyclerView recyclerViewtheloaitheochude;
  Chude chude;
  ArrayList<Theloai> theloaiArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theloaitheochude);
        getintent();
        init();
        getdata();
    }

    private void getdata() {
        Dataservice dataservice=APIservice.getService();
        Call<List<Theloai>> listCall=dataservice.Gettheloaitheochude(chude.getIdChuDe());
        listCall.enqueue(new Callback<List<Theloai>>() {
            @Override
            public void onResponse(Call<List<Theloai>> call, Response<List<Theloai>> response) {
                theloaiArrayList= (ArrayList<Theloai>) response.body();
                adapter=new DanhsachtheloaitheochudeAdapter(Theloaitheochude.this,theloaiArrayList);
                recyclerViewtheloaitheochude.setLayoutManager(new GridLayoutManager(Theloaitheochude.this,2));
                recyclerViewtheloaitheochude.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Theloai>> call, Throwable t) {

            }
        });
    }

    private void getintent() {
       Intent intent=getIntent();
       if(intent.hasExtra("chude")){
           chude= (Chude) intent.getSerializableExtra("chude");
       }
    }
    @SuppressLint("ResourceAsColor")
    private void init() {
        toolbar=findViewById(R.id.toolbartheloaitheochude);
        recyclerViewtheloaitheochude=findViewById(R.id.recycleviewtheloaitheochude);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chude.getTenChuDe());
        toolbar.setTitleTextColor(R.color.playlistcolor);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

}
