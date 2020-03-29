package com.example.nghenhactrctuyn.Mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.nghenhactrctuyn.Adapter.AlbumAdapter;
import com.example.nghenhactrctuyn.Adapter.Danhsachbaihat_Adapter;
import com.example.nghenhactrctuyn.Model.Album;
import com.example.nghenhactrctuyn.Model.Baihat;
import com.example.nghenhactrctuyn.Model.Baihatyeuthich;
import com.example.nghenhactrctuyn.Model.Fullalbum;
import com.example.nghenhactrctuyn.Model.Playlist;
import com.example.nghenhactrctuyn.Model.Quangcao;
import com.example.nghenhactrctuyn.Model.Theloai;
import com.example.nghenhactrctuyn.R;
import com.example.nghenhactrctuyn.Service.APIservice;
import com.example.nghenhactrctuyn.Service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {
    Quangcao quangcao;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerViewbaihat;
    FloatingActionButton floatingActionButton;
    ImageView imageViewdanhsachcakhuc;
    ArrayList<Baihat> mangbaihat;
    Danhsachbaihat_Adapter danhsachbaihat_adapter;
    Playlist playlist;
    Theloai theloai;
    Fullalbum fullalbum;
    Album album;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        anhxa();
        init();
        if(album!=null&&!album.getTenalbum().equals("")){
            getDataAlbumrieng(album.getIdAlbum());
        }
        if(quangcao!=null&& !quangcao.getTenBaiHat().equals("")){
            getDataquangcao(quangcao.getIdQuangCao());

        }
        if(playlist !=null&& !playlist.getTen().equals("")){
            getDataplaylist(playlist.getIdPlayList());
        }
        if(theloai!=null &&!theloai.getTenTheLoai().equals("")){
            getDatatheloai(theloai.getIdTheLoai());
        }
        if(fullalbum!=null&&!fullalbum.getTenAlbum().equals("")){
            getDataAlbum(fullalbum.getIdAlbum());
        }

    }
    private void getDataAlbumrieng(String idalbum) {
        Dataservice dataservice=APIservice.getService();
        Call<List<Baihat>> call=dataservice.Getbaihattheoalbum(idalbum);
        call.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihat_adapter=new Danhsachbaihat_Adapter(DanhsachbaihatActivity.this,mangbaihat);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(DanhsachbaihatActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerViewbaihat.setLayoutManager(linearLayoutManager);
                recyclerViewbaihat.setAdapter(danhsachbaihat_adapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void getDataAlbum(String idalbum) {
        Dataservice dataservice=APIservice.getService();
        Call<List<Baihat>> call=dataservice.Getbaihattheoalbum(idalbum);
        call.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihat_adapter=new Danhsachbaihat_Adapter(DanhsachbaihatActivity.this,mangbaihat);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(DanhsachbaihatActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerViewbaihat.setLayoutManager(linearLayoutManager);
                recyclerViewbaihat.setAdapter(danhsachbaihat_adapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void getDatatheloai(String idtheloai) {
         Dataservice dataservice=APIservice.getService();
         Call<List<Baihat>> callback=dataservice.Gettheloai(idtheloai);
         callback.enqueue(new Callback<List<Baihat>>() {
             @Override
             public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                 mangbaihat= (ArrayList<Baihat>) response.body();
                 danhsachbaihat_adapter=new Danhsachbaihat_Adapter(DanhsachbaihatActivity.this,mangbaihat);
                 LinearLayoutManager linearLayoutManager=new LinearLayoutManager(DanhsachbaihatActivity.this);
                 linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                 recyclerViewbaihat.setLayoutManager(linearLayoutManager);
                 recyclerViewbaihat.setAdapter(danhsachbaihat_adapter);
             }

             @Override
             public void onFailure(Call<List<Baihat>> call, Throwable t) {

             }
         });
    }

    private void getDataplaylist(String idplaylist) {
        Dataservice dataservice=APIservice.getService();
        Call<List<Baihat>> callback =dataservice.Getdanhsachbaihattheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihat_adapter=new Danhsachbaihat_Adapter(DanhsachbaihatActivity.this,mangbaihat);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(DanhsachbaihatActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewbaihat.setLayoutManager(linearLayoutManager);
                recyclerViewbaihat.setAdapter(danhsachbaihat_adapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void getDataquangcao(String idquangcao) {
        Dataservice dataservice= APIservice.getService();
        Call<List<Baihat>> callback=dataservice.Getdanhsachbaihat(idquangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihat_adapter=new Danhsachbaihat_Adapter(DanhsachbaihatActivity.this,mangbaihat);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(DanhsachbaihatActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewbaihat.setLayoutManager(linearLayoutManager);
                recyclerViewbaihat.setAdapter(danhsachbaihat_adapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbar=findViewById(R.id.toolbar);
        recyclerViewbaihat=(RecyclerView) findViewById(R.id.recycleviewDanhsachbaihat);
    }

    private void DataIntent() {
        Intent intent=getIntent();
        if(intent!= null){
            if(intent.hasExtra("banner")){
                quangcao= (Quangcao) intent.getSerializableExtra("banner");
            }
            if(intent.hasExtra("itemplaylist")){
                playlist= (Playlist) intent.getSerializableExtra("itemplaylist");

            }
            if(intent.hasExtra("idtheloai")){
                theloai= (Theloai) intent.getSerializableExtra("idtheloai");
            }
            if(intent.hasExtra("album")){
                fullalbum= (Fullalbum) intent.getSerializableExtra("album");
            }
            if(intent.hasExtra("albumrieng")){
                album= (Album) intent.getSerializableExtra("albumrieng");
            }
        }
    }
}
