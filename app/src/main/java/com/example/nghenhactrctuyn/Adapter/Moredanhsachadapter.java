package com.example.nghenhactrctuyn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nghenhactrctuyn.Mainactivity.DanhsachbaihatActivity;
import com.example.nghenhactrctuyn.Mainactivity.PlaylistActivity;
import com.example.nghenhactrctuyn.Model.Playlist;
import com.example.nghenhactrctuyn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Moredanhsachadapter extends RecyclerView.Adapter<Moredanhsachadapter.Viewholder>{
    Context context;
    ArrayList<Playlist> playlistArrayListmore;
    public Moredanhsachadapter(Context context, ArrayList<Playlist> playlistArrayListmore) {
        this.context = context;
        this.playlistArrayListmore = playlistArrayListmore;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
         View view=inflater.inflate(R.layout.dong_moreplaylist,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
          Playlist playlist=playlistArrayListmore.get(position);
          holder.moretenplaylist.setText(playlist.getTen());
           Picasso.with(context).load(playlist.getHinhPlayList()).into(holder.imageViewmore);


    }

    @Override
    public int getItemCount() {
        return playlistArrayListmore.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
         ImageView imageViewmore;
         TextView textViewmoretencasi,moretenplaylist;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageViewmore=itemView.findViewById(R.id.imgmoreplaylist);
            textViewmoretencasi=itemView.findViewById(R.id.tencaccasi);
            moretenplaylist=itemView.findViewById(R.id.tenmoreplaylist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist",playlistArrayListmore.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
