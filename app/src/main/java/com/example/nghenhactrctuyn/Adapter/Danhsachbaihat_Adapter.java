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

import com.example.nghenhactrctuyn.Mainactivity.DanhsachbaihatActivity;
import com.example.nghenhactrctuyn.Mainactivity.PlayvideoActiviti;
import com.example.nghenhactrctuyn.Model.Baihat;
import com.example.nghenhactrctuyn.R;
import com.example.nghenhactrctuyn.Service.APIservice;
import com.example.nghenhactrctuyn.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

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
             holder.sothutu.setText(baihat.getIdBaiHat());
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
          TextView sothutu,tenbaihat,tencasi;
          ImageView like;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            sothutu=itemView.findViewById(R.id.textviewsothutu);
            tenbaihat=itemView.findViewById(R.id.tenbaihat);
            tencasi=itemView.findViewById(R.id.tencasi);
            like=itemView.findViewById(R.id.imvluotthichdanhsachbaihat);
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    like.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice= APIservice.getService();
                    Call<String> call=dataservice.Getupdateluotthich("1",baihatArrayList.get(getPosition()).getIdBaiHat());
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua=response.body();
                            if(ketqua.equals("sucset")){
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "Lối!!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, PlayvideoActiviti.class);
                    intent.putExtra("cakhuc",baihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
