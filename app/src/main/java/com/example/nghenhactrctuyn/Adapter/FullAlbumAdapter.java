package com.example.nghenhactrctuyn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nghenhactrctuyn.Mainactivity.DanhsachbaihatActivity;
import com.example.nghenhactrctuyn.Model.Fullalbum;
import com.example.nghenhactrctuyn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FullAlbumAdapter extends RecyclerView.Adapter<FullAlbumAdapter.Viewholder>{
    Context context;
    ArrayList<Fullalbum> fullalbums;

    public FullAlbumAdapter(Context context, ArrayList<Fullalbum> fullalbums) {
        this.context = context;
        this.fullalbums = fullalbums;
    }

    @NonNull
    @Override

    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_full_album,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
     Fullalbum fullalbum =  fullalbums.get(position);
     holder.tencasi.setText(fullalbum.getTenCaSiAlbum());
     holder.tenchude.setText(fullalbum.getTenAlbum());
     Picasso.with(context).load(fullalbum.getHinhAlbum()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return fullalbums.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tenchude,tencasi;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageviewfullalbum);
            tenchude=itemView.findViewById(R.id.tenfullalbum);
            tencasi=itemView.findViewById(R.id.tencaccasifullalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("album",fullalbums.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
