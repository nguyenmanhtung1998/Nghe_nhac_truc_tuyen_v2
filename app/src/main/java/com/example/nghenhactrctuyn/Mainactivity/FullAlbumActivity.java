package com.example.nghenhactrctuyn.Mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nghenhactrctuyn.Adapter.FullAlbumAdapter;
import com.example.nghenhactrctuyn.Model.Album;
import com.example.nghenhactrctuyn.Model.Fullalbum;
import com.example.nghenhactrctuyn.R;
import com.example.nghenhactrctuyn.Service.APIservice;
import com.example.nghenhactrctuyn.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullAlbumActivity extends AppCompatActivity {
    FullAlbumAdapter adapter;
    Toolbar toolbar;
    RecyclerView recyclerView;
    Fullalbum fullalbum;
    ArrayList<Fullalbum> albumArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_album);
        anhxa();
        init();
        getdata();
    }

    private void getdata() {
        Dataservice dataservice= APIservice.getService();
        Call<List<Fullalbum>> call=dataservice.Getfullalbum();
        call.enqueue(new Callback<List<Fullalbum>>() {
            @Override
            public void onResponse(Call<List<Fullalbum>> call, Response<List<Fullalbum>> response) {
                albumArrayList= (ArrayList<Fullalbum>) response.body();
                adapter= new FullAlbumAdapter(FullAlbumActivity.this,albumArrayList);
                recyclerView.setLayoutManager(new GridLayoutManager(FullAlbumActivity.this,2));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Fullalbum>> call, Throwable t) {

            }
        });

    }

    private void anhxa() {
        toolbar=findViewById(R.id.fullchude);
        recyclerView=findViewById(R.id.recycleviewfullalbum);
    }

    @SuppressLint("ResourceAsColor")
    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả Album");
        toolbar.setTitleTextColor(R.color.playlistcolor);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
