package com.example.nghenhactrctuyn.Mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.nghenhactrctuyn.Adapter.Moredanhsachadapter;
import com.example.nghenhactrctuyn.Model.Playlist;
import com.example.nghenhactrctuyn.R;
import com.example.nghenhactrctuyn.Service.APIservice;
import com.example.nghenhactrctuyn.Service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistActivity extends AppCompatActivity {
  Playlist playlist;
    Toolbar toolbar;
    RecyclerView recyclerViewmore;
    ArrayList<Playlist> playlistArrayListmore;
    Moredanhsachadapter moredanhsachadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        anhxa();
        init();
        getdatainten();
        gerdata();
    }

    private void gerdata() {
        Dataservice dataservice= APIservice.getService();
        Call<List<Playlist>> callback=dataservice.Getfulldanhsach();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                playlistArrayListmore= (ArrayList<Playlist>) response.body();
                moredanhsachadapter=new  Moredanhsachadapter (PlaylistActivity.this,playlistArrayListmore );
                recyclerViewmore.setLayoutManager(new GridLayoutManager(PlaylistActivity.this,2));
                recyclerViewmore.setAdapter(moredanhsachadapter);

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        toolbar=findViewById(R.id.toolbarmoredanhsach);
        recyclerViewmore=findViewById(R.id.recycleviewmore);
    }

    @SuppressLint("ResourceAsColor")
    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlist");
        toolbar.setTitleTextColor(R.color.playlistcolor);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getdatainten() {
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("moreplaylist")){
                playlist= (Playlist) intent.getSerializableExtra("moreplaylist");
            }
        }

    }
}
