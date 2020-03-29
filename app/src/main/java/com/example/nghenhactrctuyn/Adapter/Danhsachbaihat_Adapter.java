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

import com.example.nghenhactrctuyn.Mainactivity.DanhsachbaihatActivity;
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
public class Danhsachbaihat_Adapter extends RecyclerView.Adapter<Danhsachbaihat_Adapter.Viewholder>{

    Context context;
    ArrayList<Baihat> baihatArrayList;
    public Danhsachbaihat_Adapter(@NonNull DanhsachbaihatActivity context, ArrayList<Baihat> baihatArrayList) {
        this.context = (Context) context;
        this.baihatArrayList = baihatArrayList;
    }
    @NonNull
    @Override

    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_danhsachbaihat,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
             Baihat baihat=baihatArrayList.get(position);
             holder.tenbaihat.setText(baihat.getTenbaihat());
             holder.tencasi.setText(baihat.getCasi());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.hinhbaihat);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
          TextView tenbaihat,tencasi;
          ImageView like,hinhbaihat;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            hinhbaihat = itemView.findViewById(R.id.imagehinhanhdanhsachbaihat);
            tenbaihat = itemView.findViewById(R.id.Tendanhsachbaihat);
            tencasi = itemView.findViewById(R.id.Casidanhsach);
            like = itemView.findViewById(R.id.like);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetPosison(getAdapterPosition());
                    Intent intent=new Intent(context, PlayvideoActiviti.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("int",GetPosison(getAdapterPosition()));
                    bundle.putParcelableArrayList("danhsachbaihat",baihatArrayList);
                    intent.putExtra("dulieu",bundle);
                    context.startActivity(intent);
                }
            });
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    like.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIservice.getService();
                    Call<String> call = dataservice.Getupdateluotthich("1", baihatArrayList.get(getPosition()).getIdBaiHat());
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("sucset")) {
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Lối!!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            });

        }
    }
    private int GetPosison(int cout) {
        int posison=cout;
        return posison;
    }
}
