package com.example.nghenhactrctuyn.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nghenhactrctuyn.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_dianhac extends Fragment {
    CircleImageView circleImageView;
    View view;
    ObjectAnimator objectAnimator;
    Animation animation;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_dianhac,container,false);
        circleImageView=view.findViewById(R.id.imageviewpcicy);
        objectAnimator=ObjectAnimator.ofFloat(circleImageView,"rotation",0f,360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        return view;
    }
    public void Playnhac(String hinhanh){
        Picasso.with(this.getContext()).load(hinhanh).into(circleImageView);
        animation= AnimationUtils.loadAnimation(this.getContext(),R.anim.xoaydianhac);
        circleImageView.startAnimation(animation);
    }
}
