package com.example.nghenhactrctuyn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nghenhactrctuyn.Model.Baihatyeuthich;
import com.example.nghenhactrctuyn.R;

import java.util.ArrayList;

public class playdanhsachbaihatyeuthichAdapter extends RecyclerView.Adapter<playdanhsachbaihatyeuthichAdapter.Viewholder> {

    Context context;
    ArrayList<Baihatyeuthich> baihatyeuthiches;

    public playdanhsachbaihatyeuthichAdapter(Context context, ArrayList<Baihatyeuthich> baihatyeuthiches) {
        this.context = context;
        this.baihatyeuthiches = baihatyeuthiches;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_playbaihatyeuthichchung,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Baihatyeuthich baihatyeuthich=baihatyeuthiches.get(position);
        holder.tenbaihatyeuthich.setText(baihatyeuthich.getTenbaihat());
        holder.tencasiyeuthich.setText(baihatyeuthich.getCasi());
        holder.indexyeuthich.setText(position +1+"");
    }

    @Override
    public int getItemCount() {
        return baihatyeuthiches.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView tencasiyeuthich,tenbaihatyeuthich,indexyeuthich;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tencasiyeuthich=itemView.findViewById(R.id.texttencasiyeuthich);
            tenbaihatyeuthich=itemView.findViewById(R.id.textplaynhactenbaihatyeuthich);
            indexyeuthich=itemView.findViewById(R.id.textplayindexyeuthich);

        }
    }
}
