package com.example.nghenhactrctuyn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nghenhactrctuyn.Mainactivity.Theloaitheochude;
import com.example.nghenhactrctuyn.Model.Chude;
import com.example.nghenhactrctuyn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachchudeAdapter extends RecyclerView.Adapter<DanhsachchudeAdapter.Viewholder>{
    Context context;
    ArrayList<Chude> chudeArrayList;

    public DanhsachchudeAdapter(Context context, ArrayList<Chude> chudeArrayList) {
        this.context = context;
        this.chudeArrayList = chudeArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater=LayoutInflater.from(context);
      View view=inflater.inflate(R.layout.dong_chude,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
      Chude chude=chudeArrayList.get(position);
        Picasso.with(context).load(chude.getHinhChuDe()).into(holder.imageViewmorechude);
    }

    @Override
    public int getItemCount() {
        return chudeArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
         ImageView imageViewmorechude;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageViewmorechude=itemView.findViewById(R.id.imagefullchude);
            imageViewmorechude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, Theloaitheochude.class);
                    intent.putExtra("chude",chudeArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
