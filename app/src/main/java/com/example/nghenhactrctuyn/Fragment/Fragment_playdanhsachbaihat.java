package com.example.nghenhactrctuyn.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nghenhactrctuyn.Adapter.PlaylistAdapter;
import com.example.nghenhactrctuyn.Adapter.playdanhsachbaihatyeuthichAdapter;
import com.example.nghenhactrctuyn.Adapter.playnhacAdapter;
import com.example.nghenhactrctuyn.Mainactivity.PlayvideoActiviti;
import com.example.nghenhactrctuyn.R;

public class Fragment_playdanhsachbaihat extends Fragment {
    com.example.nghenhactrctuyn.Adapter.playdanhsachbaihatyeuthichAdapter Adapter;
    playnhacAdapter playnhacAdapter;
    View view;
    RecyclerView recyclerViewpplaynhac;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_playdanhsachcacbaihat,container,false);
        recyclerViewpplaynhac=view.findViewById(R.id.recycleviewplaybaihat);
        if(PlayvideoActiviti.mangbaihatchung.size()>0){
            playnhacAdapter=new playnhacAdapter(getActivity(),PlayvideoActiviti.mangbaihatchung);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerViewpplaynhac.setLayoutManager(linearLayoutManager);
            recyclerViewpplaynhac.setAdapter(playnhacAdapter);
        }
        return view;
    }
}
