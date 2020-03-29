package com.example.nghenhactrctuyn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nghenhactrctuyn.Mainactivity.BaihatyeuthichActivity;
import com.example.nghenhactrctuyn.Mainactivity.PlaylistActivity;
import com.example.nghenhactrctuyn.Mainactivity.PlayvideoActiviti;
import com.example.nghenhactrctuyn.Model.Baihatyeuthich;
import com.example.nghenhactrctuyn.R;
import com.example.nghenhactrctuyn.Service.APIservice;
import com.example.nghenhactrctuyn.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Baihatyeuthich_Adapter  extends RecyclerView.Adapter<Baihatyeuthich_Adapter.Viewholder>{
    Context context;
    ArrayList<Baihatyeuthich> baihatyeuthichArrayList;

    public Baihatyeuthich_Adapter(Context context, ArrayList<Baihatyeuthich> baihatyeuthichArrayList) {
        this.context = context;
        this.baihatyeuthichArrayList = baihatyeuthichArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_baihatyeuthich,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Baihatyeuthich baihatyeuthich=baihatyeuthichArrayList.get(position);

        holder.tencasi.setText(baihatyeuthich.getCasi());
        holder.tenbaihat.setText(baihatyeuthich.getTenbaihat());
        Picasso.with(context).load(baihatyeuthich.getHinhbaihat()).into(holder.imageViewhinhanhbaihat);
    }

    @Override
    public int getItemCount() {
        return baihatyeuthichArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
          ImageView imageViewhinhanhbaihat,like;
          TextView tenbaihat,tencasi;
        public Viewholder(@NonNull final View itemView) {
            super(itemView);
            imageViewhinhanhbaihat=(ImageView) itemView.findViewById(R.id.imagehinhanhbaihatyeuthich);
            like=(ImageView) itemView.findViewById(R.id.like);
            tenbaihat=(TextView) itemView.findViewById(R.id.Tenabihatyeuthich);
            tencasi=(TextView) itemView.findViewById(R.id.Cáiyeuthich);

            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    like.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice= APIservice.getService();
                    Call<String> callback=dataservice.Getupdateluotthich("1",baihatyeuthichArrayList.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua=response.body();
                            if(ketqua.equals("sucset")){
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "Lỗi!!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    like.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetPosison(getAdapterPosition());
                    Intent intent=new Intent(context, BaihatyeuthichActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("int",GetPosison(getAdapterPosition()));
                    bundle.putParcelableArrayList("danhsachbaihat",baihatyeuthichArrayList);
                    intent.putExtra("dulieu",bundle);
                    context.startActivity(intent);
                }
            });
        }
    }

    private int GetPosison(int adapterPosition) {
        int posison=adapterPosition;
        return posison;
    }
}
