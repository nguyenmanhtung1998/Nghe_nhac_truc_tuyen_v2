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
import com.example.nghenhactrctuyn.Model.Theloai;
import com.example.nghenhactrctuyn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachtheloaitheochudeAdapter extends RecyclerView.Adapter<DanhsachtheloaitheochudeAdapter.Viewholder>{
    Context context;
    ArrayList<Theloai> theloaiArrayList;

    public DanhsachtheloaitheochudeAdapter(Context context, ArrayList<Theloai> theloaiArrayList) {
        this.context = context;
        this.theloaiArrayList = theloaiArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_theloai_theochude,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
     Theloai theloai=theloaiArrayList.get(position);
     holder.texttentheloai.setText(theloai.getTenTheLoai());
     Picasso.with(context).load(theloai.getHinhTheLoai()).into(holder.imageViewten);
    }

    @Override
    public int getItemCount() {
        return theloaiArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView imageViewten;
        TextView texttentheloai;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageViewten=itemView.findViewById(R.id.imagetheloaitheochude);
            texttentheloai=itemView.findViewById(R.id.textviewtentheloaitheochude);
            imageViewten.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("idtheloai",theloaiArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
