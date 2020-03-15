package com.example.nghenhactrctuyn.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nghenhactrctuyn.Adapter.PlaylistAdapter;
import com.example.nghenhactrctuyn.Mainactivity.DanhsachbaihatActivity;
import com.example.nghenhactrctuyn.Mainactivity.PlaylistActivity;
import com.example.nghenhactrctuyn.Model.Playlist;
import com.example.nghenhactrctuyn.R;
import com.example.nghenhactrctuyn.Service.APIservice;
import com.example.nghenhactrctuyn.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {
    TextView txtviewtitleplaylist;
    ListView listViewplaylist;
    TextView moreplaylist;
    View view;
    ArrayList<Playlist> mangplaylist;
    PlaylistAdapter playlistAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_playlist,container,false);
         anhxa();
         Getdata();
        listViewplaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), DanhsachbaihatActivity.class);
                intent.putExtra("itemplaylist",mangplaylist.get(position));
                startActivity(intent);
            }
        });
         moreplaylist.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(getActivity(), PlaylistActivity.class);
                 startActivity(intent);
             }
         });
        return view;
    }

    private void anhxa() {
        txtviewtitleplaylist=view.findViewById(R.id.txttitleplaylist);
        moreplaylist=view.findViewById(R.id.textviewmoreplaylist);
        listViewplaylist=view.findViewById(R.id.listviewplaylist);
    }

    private void Getdata() {
        Dataservice dataservice= APIservice.getService();
        Call<List<Playlist>> callback=dataservice.Getplaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                mangplaylist= (ArrayList<Playlist>) response.body();
                playlistAdapter=new PlaylistAdapter(getActivity(),android.R.layout.simple_list_item_1,mangplaylist);
                listViewplaylist.setAdapter(playlistAdapter);
                setListViewHeightBasedOnChildren(listViewplaylist);


            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
