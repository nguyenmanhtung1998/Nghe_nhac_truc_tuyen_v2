package com.example.nghenhactrctuyn.Mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nghenhactrctuyn.Fragment.Fragment_dianhac;
import com.example.nghenhactrctuyn.Fragment.Fragment_playdanhsachbaihat;
import com.example.nghenhactrctuyn.Model.Baihatyeuthich;
import com.example.nghenhactrctuyn.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class BaihatyeuthichActivity extends AppCompatActivity {
    Toolbar toolbar;
    SeekBar seekBar;
    Animation animation;
    CircleImageView circleImageView;
    MediaPlayer mediaPlayer;
    TextView textViewtimesong, textViewtotaltimesong;
    ImageButton imageButtonplay, imageButtonrepeat, imageButtonnext, imageButtonpre, imageButtonrandom;
    public static ArrayList<Baihatyeuthich> mangbaihatyeuthichchung = new ArrayList<>();
    boolean repeat = false;
    int position = 0;
    boolean random = false;
    boolean next = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baihatyeuthich);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getdatainten();
        anhxa();
        evenclick();
    }

    private void anhxa() {
        circleImageView=findViewById(R.id.imageviewpcicyyeuthich);
        toolbar = findViewById(R.id.toolbarplaynhacyeuthich);
        textViewtimesong = findViewById(R.id.texttimesongyeuthich);
        textViewtotaltimesong = findViewById(R.id.textviewtotltimesongyeuthich);
        imageButtonplay = findViewById(R.id.imagebuttonplayyeuthich);
        imageButtonnext = findViewById(R.id.imagebuttonnextyeuthich);
        seekBar = findViewById(R.id.seekbarsongyeuthich);
        imageButtonpre = findViewById(R.id.imagebuttonreviewyeuthich);
        imageButtonrandom = findViewById(R.id.imagebuttonyeuthich);
        imageButtonrepeat = findViewById(R.id.imagebuttonrepetyeuthich);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (mangbaihatyeuthichchung.size()>0){
            new playMp3().execute(mangbaihatyeuthichchung.get(position).getLinkbaihat());
            imageButtonplay.setImageResource(R.drawable.iconpause);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mangbaihatyeuthichchung.clear();
                finish();
            }
        });

    }

    private void getdatainten() {
        Intent intent=getIntent();
        mangbaihatyeuthichchung.clear();
        if (intent.hasExtra("cakhucyeuthich")) {
            Baihatyeuthich baihatyeuthich = intent.getParcelableExtra("cakhucyeuthich");
            mangbaihatyeuthichchung.add(baihatyeuthich);
        }
    }
    private void evenclick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                    if (mangbaihatyeuthichchung.size() > 0) {
                        Playnhac(mangbaihatyeuthichchung.get(0).getHinhbaihat(),toolbar);
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }

            }
        }, 500);
        imageButtonplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imageButtonplay.setImageResource(R.drawable.iconplay);
                } else {
                    mediaPlayer.start();
                    imageButtonplay.setImageResource(R.drawable.iconpause);
                }

            }
        });
        imageButtonrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (random == true) {
                        random = false;
                        imageButtonrepeat.setImageResource(R.drawable.iconsyned);
                        imageButtonrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imageButtonrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    imageButtonrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imageButtonrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (random == false) {
                    if (repeat == true) {
                        repeat = false;
                        imageButtonrandom.setImageResource(R.drawable.iconshuffled);
                        imageButtonrepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imageButtonrandom.setImageResource(R.drawable.iconshuffled);
                    random = true;
                } else {
                    random = false;
                    imageButtonrandom.setImageResource(R.drawable.iconsuffle);
                }


            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imageButtonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihatyeuthichchung.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihatyeuthichchung.size())) {
                        imageButtonplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = mangbaihatyeuthichchung.size();
                            }
                            position -= 1;
                        }
                        if (random == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihatyeuthichchung.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangbaihatyeuthichchung.size() - 1)) {
                            position = 0;
                        }
                        new playMp3().execute(mangbaihatyeuthichchung.get(position).getLinkbaihat());
                        toolbar.setTitle(mangbaihatyeuthichchung.get(position).getTenbaihat());
                        updatatime();
                    }
                }
                imageButtonpre.setClickable(false);
                imageButtonnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButtonpre.setClickable(true);
                        imageButtonnext.setClickable(true);
                    }
                }, 5000);
            }
        });
        imageButtonpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihatyeuthichchung.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihatyeuthichchung.size())) {
                        imageButtonplay.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position < 0) {
                            position = mangbaihatyeuthichchung.size() - 1;
                        }
                        if (repeat == true) {
                            position += 1;
                        }
                        if (position > (mangbaihatyeuthichchung.size() - 1)) {
                            position = 0;
                        }
                        if (random == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihatyeuthichchung.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new playMp3().execute(mangbaihatyeuthichchung.get(position).getLinkbaihat());
                        toolbar.setTitle(mangbaihatyeuthichchung.get(position).getTenbaihat());
                        updatatime();
                    }
                }
                imageButtonpre.setClickable(false);
                imageButtonnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButtonpre.setClickable(true);
                        imageButtonnext.setClickable(true);
                    }
                }, 5000);
            }
        });
    }


    class playMp3 extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            Timesong();
            updatatime();
        }
    }
    private void Timesong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewtotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }
    private void updatatime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    textViewtimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (mangbaihatyeuthichchung.size() > 0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        if (position < (mangbaihatyeuthichchung.size())) {
                            imageButtonplay.setImageResource(R.drawable.iconpause);
                            position++;
                            if (repeat == true) {
                                if (position == 0) {
                                    position = mangbaihatyeuthichchung.size();
                                }
                                position -= 1;
                            }
                            if (random == true) {
                                Random random = new Random();
                                int index = random.nextInt(mangbaihatyeuthichchung.size());
                                if (index == position) {
                                    position = index - 1;
                                }
                                position = index;
                            }
                            if (position > (mangbaihatyeuthichchung.size() - 1)) {
                                position = 0;
                            }
                            new playMp3().execute(mangbaihatyeuthichchung.get(position).getLinkbaihat());
                            toolbar.setTitle(mangbaihatyeuthichchung.get(position).getTenbaihat());
                        }
                    }
                    imageButtonpre.setClickable(false);
                    imageButtonnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageButtonpre.setClickable(true);
                            imageButtonnext.setClickable(true);
                        }
                    }, 5000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
    private void Playnhac(String hinh,Toolbar toolbar1){
        Picasso.with(this).load(hinh).into(circleImageView);
        animation= AnimationUtils.loadAnimation(this,R.anim.xoaydianhac);
        circleImageView.startAnimation(animation);
        toolbar1.setTitle(mangbaihatyeuthichchung.get(position).getTenbaihat());
    }
}
