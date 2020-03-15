package com.example.nghenhactrctuyn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nghenhactrctuyn.Model.Baihat;
import com.example.nghenhactrctuyn.R;

import java.util.ArrayList;

public class playnhacAdapter extends RecyclerView.Adapter<playnhacAdapter.Viewholder> {

    Context context;
    ArrayList<Baihat> baihatArrayList;

    public playnhacAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_playbaihat,parent,false);
        return new Viewholder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
       Baihat baihat=baihatArrayList.get(position);
       holder.tencasi.setText(baihat.getCasi());
       holder.tenbaihat.setText(baihat.getTenbaihat());
       holder.index.setText(position +1 +"");
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
          TextView tencasi,tenbaihat,index;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tencasi=itemView.findViewById(R.id.texttencasi);
            tenbaihat=itemView.findViewById(R.id.textplaynhactenbaihat);
            index=itemView.findViewById(R.id.textplayindex);
        }
    }
}
