package com.example.nghenhactrctuyn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.nghenhactrctuyn.Mainactivity.DanhsachbaihatActivity;
import com.example.nghenhactrctuyn.Model.Quangcao;
import com.example.nghenhactrctuyn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Banner_Adapter extends PagerAdapter {
    Context context;
    ArrayList<Quangcao> arrayListbanner;
    public Banner_Adapter(Context context, ArrayList<Quangcao> arrayListbanner) {
        this.context = context;
        this.arrayListbanner = arrayListbanner;
    }

    @Override
    public int getCount() {
        return arrayListbanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_banner,null);
        final ImageView imgbackgroundbanner=view.findViewById(R.id.imagebackgroud);
        ImageView imgsongbanner=view.findViewById(R.id.imageviewbanner);
        TextView txttitlesongbanner=view.findViewById(R.id.textviewtitlebannertenbaihat);
        TextView txtnoidung=view.findViewById(R.id.tvnoidungbanner);

        Picasso.with(context).load(arrayListbanner.get(position).getHinhAnh()).into(imgbackgroundbanner);
        Picasso.with(context).load(arrayListbanner.get(position).getHinhBaiHat()).into(imgsongbanner);

        txttitlesongbanner.setText(arrayListbanner.get(position).getTenBaiHat());
        txtnoidung.setText(arrayListbanner.get(position).getNoiDung());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("banner",arrayListbanner.get(position));
                context.startActivities(new Intent[]{intent});
            }
        });


        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
