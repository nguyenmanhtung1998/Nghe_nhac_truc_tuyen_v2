package com.example.nghenhactrctuyn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nghenhactrctuyn.Mainactivity.DanhsachbaihatActivity;
import com.example.nghenhactrctuyn.Model.Album;
import com.example.nghenhactrctuyn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.Viewholder>{

    Context context;
    ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    //gan layout
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_album,parent,false);

        return new Viewholder(view);
    }
    //gan gia tri
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
       Album album=albumArrayList.get(position);
       holder.tenalbum.setText(album.getTenalbum());
       holder.casialbum.setText(album.getTencasialbum());
        Picasso.with(context).load(album.getHinhalbum()).into(holder.imageViewalbum);
    }
    //tra ve bao nhieu item
    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView imageViewalbum;
        TextView tenalbum,casialbum;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageViewalbum=(ImageView) itemView.findViewById(R.id.imagealbum);
            tenalbum=(TextView) itemView.findViewById(R.id.textviewtenalbum);
            casialbum=(TextView) itemView.findViewById(R.id.textviewtencasialbum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("albumrieng",albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });



        }
    }
}
