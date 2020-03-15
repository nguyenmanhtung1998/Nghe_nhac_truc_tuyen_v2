package com.example.nghenhactrctuyn.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nghenhactrctuyn.Adapter.Baihatyeuthich_Adapter;
import com.example.nghenhactrctuyn.Model.Baihatyeuthich;
import com.example.nghenhactrctuyn.R;
import com.example.nghenhactrctuyn.Service.APIservice;
import com.example.nghenhactrctuyn.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_baihatyeuthich extends Fragment {
    View view;
    RecyclerView recyclerViewbaihatyeuthich;
    TextView texttieudebaihathot,textViewxemthembaihathot;
    Baihatyeuthich_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_baihatyeuthich,container,false);
        anhxa();
        getdata();
        return view;
    }

    private void anhxa() {
        recyclerViewbaihatyeuthich=(RecyclerView) view.findViewById(R.id.recycleviewbaihatyeuthich);
        textViewxemthembaihathot=(TextView) view.findViewById(R.id.Xemthembaihathot);

    }

    private void getdata() {
        Dataservice dataservice= APIservice.getService();
        Call<List<Baihatyeuthich>> callback=dataservice.Getbaihatyeuthich();
        callback.enqueue(new Callback<List<Baihatyeuthich>>() {
            @Override
            public void onResponse(Call<List<Baihatyeuthich>> call, Response<List<Baihatyeuthich>> response) {
                ArrayList<Baihatyeuthich> baihatyeuthichArrayList= (ArrayList<Baihatyeuthich>) response.body();
                adapter=new Baihatyeuthich_Adapter(getActivity(),baihatyeuthichArrayList);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewbaihatyeuthich.setLayoutManager(linearLayoutManager);
                recyclerViewbaihatyeuthich.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Baihatyeuthich>> call, Throwable t) {

            }
        });

    }
}
