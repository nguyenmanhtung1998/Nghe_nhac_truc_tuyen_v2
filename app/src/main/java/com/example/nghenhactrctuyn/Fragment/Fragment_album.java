package com.example.nghenhactrctuyn.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nghenhactrctuyn.Adapter.AlbumAdapter;
import com.example.nghenhactrctuyn.Mainactivity.FullAlbumActivity;
import com.example.nghenhactrctuyn.Model.Album;
import com.example.nghenhactrctuyn.R;
import com.example.nghenhactrctuyn.Service.APIservice;
import com.example.nghenhactrctuyn.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_album extends Fragment {
    View view;
    RecyclerView recyclerView;
    TextView textViewtitlealbum,textViewalbumxemthem;
    AlbumAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_album,container,false);
        anhxa();
        getdata();
        return view;
    }

    private void anhxa() {
        textViewalbumxemthem=(TextView) view.findViewById(R.id.tvxemthem);
        textViewtitlealbum=(TextView) view.findViewById(R.id.Tvtitlealbum);
        recyclerView=(RecyclerView) view.findViewById(R.id.recycleviewalbum);
        textViewalbumxemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), FullAlbumActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getdata() {
        Dataservice dataservice= APIservice.getService();
        Call<List<Album>> callback=dataservice.Getalbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albums= (ArrayList<Album>) response.body();
                adapter=new AlbumAdapter(getActivity(),albums);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
