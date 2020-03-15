package com.example.nghenhactrctuyn.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.nghenhactrctuyn.Mainactivity.DanhsachbaihatActivity;
import com.example.nghenhactrctuyn.Mainactivity.Danhsachchude;
import com.example.nghenhactrctuyn.Mainactivity.Theloaitheochude;
import com.example.nghenhactrctuyn.Model.Chude;
import com.example.nghenhactrctuyn.Model.Chudevatheloai;
import com.example.nghenhactrctuyn.Model.Theloai;
import com.example.nghenhactrctuyn.R;
import com.example.nghenhactrctuyn.Service.APIservice;
import com.example.nghenhactrctuyn.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_theloaivachude extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView textViewxemthem;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_trangchuvatheloai,container,false);
        anhxa();
        getdata();
        textViewxemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Danhsachchude.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void anhxa() {
        horizontalScrollView=view.findViewById(R.id.horizontal);
        textViewxemthem=view.findViewById(R.id.txtxemthem);
    }

    private void getdata() {
        Dataservice dataservice= APIservice.getService();
        Call<Chudevatheloai> callback = dataservice.Getchudevatheloai();
        callback.enqueue(new Callback<Chudevatheloai>() {
            @Override
            public void onResponse(Call<Chudevatheloai> call, Response<Chudevatheloai> response) {
                 Chudevatheloai chudevatheloai=response.body();
                 final ArrayList<Chude> chudeArrayList= new ArrayList<>();
                 chudeArrayList.addAll(chudevatheloai.getChude());


                 final ArrayList<Theloai> theloaiArrayList=new ArrayList<>();
                 theloaiArrayList.addAll(chudevatheloai.getTheloai());

                LinearLayout linearLayout=new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(580,250);
                layoutParams.setMargins(10,20,10,30);

                for(int i=0;i<chudeArrayList.size();i++){
                    CardView cardView=new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageViewchude=new ImageView(getActivity());
                    imageViewchude.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(chudeArrayList.get(i).getHinhChuDe()!=null){
                        Picasso.with(getActivity()).load(chudeArrayList.get(i).getHinhChuDe()).into(imageViewchude);

                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageViewchude);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageViewchude.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(), Theloaitheochude.class);
                            intent.putExtra("chude",chudeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                for(int i=0;i<theloaiArrayList.size();i++){
                    CardView cardView=new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageViewtheloai=new ImageView(getActivity());
                    imageViewtheloai.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(theloaiArrayList.get(i).getHinhTheLoai()!=null){
                        Picasso.with(getActivity()).load(theloaiArrayList.get(i).getHinhTheLoai()).into(imageViewtheloai);

                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageViewtheloai);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageViewtheloai.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai",theloaiArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                horizontalScrollView.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<Chudevatheloai> call, Throwable t) {

            }
        });

    }
}
