package com.example.nghenhactrctuyn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nghenhactrctuyn.Mainactivity.PlayvideoActiviti;
import com.example.nghenhactrctuyn.Model.Baihat;
import com.example.nghenhactrctuyn.R;
import com.example.nghenhactrctuyn.Service.APIservice;
import com.example.nghenhactrctuyn.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimkiemAdapter extends RecyclerView.Adapter<TimkiemAdapter.Viewholder> {
    Context context;
    ArrayList<Baihat> baihatArrayList;

    public TimkiemAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
       View view= inflater.inflate(R.layout.dong_timkiem,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
               Baihat baihat=baihatArrayList.get(position);
               holder.tenbaihattk.setText(baihat.getTenbaihat());
               holder.cstk.setText(baihat.getCasi());
               Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imageViewhinhanhtimkiem);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
         ImageView imageViewhinhanhtimkiem,love;
         TextView tenbaihattk,cstk;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageViewhinhanhtimkiem=itemView.findViewById(R.id.imagehinhanhtimkiem);
            love=itemView.findViewById(R.id.like);
            tenbaihattk=itemView.findViewById(R.id.Tenbaihattimkiem);
            cstk=itemView.findViewById(R.id.Casitimkiem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, PlayvideoActiviti.class);
                    intent.putExtra("cakhuc",baihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    love.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice=APIservice.getService();
                    Call<String> call=dataservice.Getupdateluotthich("1",baihatArrayList.get(getPosition()).getIdBaiHat());
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua=response.body();
                            if (ketqua.equals("sucset")){
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Lỗi!!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    love.setEnabled(false);
                }
            });
        }
    }
}
