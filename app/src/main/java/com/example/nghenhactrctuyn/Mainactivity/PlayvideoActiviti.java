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
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nghenhactrctuyn.Adapter.Viewpagerplaylist;
import com.example.nghenhactrctuyn.Fragment.Fragment_dianhac;
import com.example.nghenhactrctuyn.Fragment.Fragment_playdanhsachbaihat;
import com.example.nghenhactrctuyn.Model.Baihat;
import com.example.nghenhactrctuyn.Model.Baihatyeuthich;
import com.example.nghenhactrctuyn.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayvideoActiviti extends AppCompatActivity {
    Toolbar toolbar;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    TextView textViewtimesong, textViewtotaltimesong;
    ImageButton imageButtonplay, imageButtonrepeat, imageButtonnext, imageButtonpre, imageButtonrandom;
    ViewPager viewPagerplaynhac;
    Fragment_dianhac fragment_dianhac;
    Fragment_playdanhsachbaihat fragment_playdanhsachbaihat;
    public static ArrayList<Baihat> mangbaihatchung = new ArrayList<>();
    public static Viewpagerplaylist viewpagerplaylist;
    boolean repeat = false;
    int position = 0;
    boolean random = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playvideo_activiti);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getdatainten();
        anhxa();
        evenclick();
    }

    private void evenclick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewpagerplaylist.getItem(1) != null) {
                    if (mangbaihatchung.size() > 0) {
                        fragment_dianhac.Playnhac(mangbaihatchung.get(position).getHinhbaihat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
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
                if (mangbaihatchung.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihatchung.size())) {
                        imageButtonplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = mangbaihatchung.size();
                            }
                            position -= 1;
                        }
                        if (random == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihatchung.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangbaihatchung.size() - 1)) {
                            position = 0;
                        }
                        fragment_dianhac.Playnhac(mangbaihatchung.get(position).getHinhbaihat());
                        new playMp3().execute(mangbaihatchung.get(position).getLinkbaihat());
                        toolbar.setTitle(mangbaihatchung.get(position).getTenbaihat());
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
                if (mangbaihatchung.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihatchung.size())) {
                        imageButtonplay.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position < 0) {
                            position = mangbaihatchung.size() - 1;
                        }
                        if (repeat == true) {
                            position += 1;
                        }
                        if (position > (mangbaihatchung.size() - 1)) {
                            position = 0;
                        }
                        if (random == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihatchung.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        fragment_dianhac.Playnhac(mangbaihatchung.get(position).getHinhbaihat());
                        new playMp3().execute(mangbaihatchung.get(position).getLinkbaihat());
                        toolbar.setTitle(mangbaihatchung.get(position).getTenbaihat());
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

    private void getdatainten() {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("dulieu");
        if(bundle!=null){
            position=bundle.getInt("int");
            mangbaihatchung=bundle.getParcelableArrayList("danhsachbaihat");
        }

    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbarplaynhac);
        textViewtimesong = findViewById(R.id.texttimesong);
        textViewtotaltimesong = findViewById(R.id.textviewtotltimesong);
        imageButtonplay = findViewById(R.id.imagebuttonplay);
        imageButtonnext = findViewById(R.id.imagebuttonnext);
        seekBar = findViewById(R.id.seekbarsong);
        imageButtonpre = findViewById(R.id.imagebuttonreview);
        imageButtonrandom = findViewById(R.id.imagebutton);
        imageButtonrepeat = findViewById(R.id.imagebuttonrepet);
        viewPagerplaynhac = findViewById(R.id.viewpagerplaynhac);
        fragment_playdanhsachbaihat = new Fragment_playdanhsachbaihat();
        fragment_dianhac = new Fragment_dianhac();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mangbaihatchung.clear();

            }
        });
        viewpagerplaylist = new Viewpagerplaylist(getSupportFragmentManager());
        viewpagerplaylist.AddFra(fragment_playdanhsachbaihat);
        viewpagerplaylist.AddFra(fragment_dianhac);
        viewPagerplaynhac.setAdapter(viewpagerplaylist);
        fragment_dianhac = (Fragment_dianhac) viewpagerplaylist.getItem(1);
        if (mangbaihatchung.size() > 0)
        {
            getSupportActionBar().setTitle(mangbaihatchung.get(position).getTenbaihat());
            new playMp3().execute(mangbaihatchung.get(position).getLinkbaihat());
            imageButtonplay.setImageResource(R.drawable.iconpause);

        }
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
                    if (mangbaihatchung.size() > 0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        if (position < (mangbaihatchung.size())) {
                            imageButtonplay.setImageResource(R.drawable.iconpause);
                            position++;
                            if (repeat == true) {
                                if (position == 0) {
                                    position = mangbaihatchung.size();
                                }
                                position -= 1;
                            }
                            if (random == true) {
                                Random random = new Random();
                                int index = random.nextInt(mangbaihatchung.size());
                                if (index == position) {
                                    position = index - 1;
                                }
                                position = index;
                            }
                            if (position > (mangbaihatchung.size() - 1)) {
                                position = 0;
                            }
                            fragment_dianhac.Playnhac(mangbaihatchung.get(position).getHinhbaihat());
                            new playMp3().execute(mangbaihatchung.get(position).getLinkbaihat());
                            toolbar.setTitle(mangbaihatchung.get(position).getTenbaihat());
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
    private void Timesong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewtotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
