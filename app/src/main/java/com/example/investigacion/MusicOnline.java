package com.example.investigacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MusicOnline extends AppCompatActivity {
    LinearLayout layout_music1, layout_music2, layout_music3, layout_music4;
    ImageView music_img1, music_img2, music_img3, music_img4;

    MediaPlayer mediaPlayer = new MediaPlayer();


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            mediaPlayer.stop();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);



        layout_music1 = findViewById(R.id.music_list_item1);
        layout_music2 = findViewById(R.id.music_list_item2);
        layout_music3 = findViewById(R.id.music_list_item3);
        layout_music4 = findViewById(R.id.music_list_item4);

        music_img1 = findViewById(R.id.music_img1);
        music_img2 = findViewById(R.id.music_img2);
        music_img3 = findViewById(R.id.music_img3);
        music_img4 = findViewById(R.id.music_img4);


        layout_music1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic("https://dc599.4shared.com/img/jvZeoVTbiq/a21d06db/dlink__2Fdownload_2FjvZeoVTbiq_2FSobrio.mp3_3Fsbsr_3D6e9967607fbc0b18401a236f091e10a1ab7_26bip_3DMTg2LjcyLjE0NS4xNTA_26lgfp_3D52_26bip_3DMTg2LjcyLjE0NS4xNTA/preview.mp3");
            }
        });


        layout_music2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic("https://dc551.4shared.com/img/G2yfVONBea/5eeb6e7a/dlink__2Fdownload_2FG2yfVONBea_2FSi_5Ft_5Fla_5Fves_5F_5FNicky_5FJam_5F_5FLETRA_5F.m4a_3Fsbsr_3D06de74e347107350e8fa1c2f26a5e030ab7_26bip_3DMTg2LjcyLjE0NS4xNTA_26lgfp_3D52_26bip_3DMTg2LjcyLjE0NS4xNTA/preview.mp3");
            }
        });


        layout_music3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic("https://dc770.4shared.com/img/fKssYClZei/ecd7ccb7/dlink__2Fdownload_2FfKssYClZei_2FLa_5FRompe_5FCorazones_5FVideo_5FOfici.mp3_3Fsbsr_3Db50a6d1c69a16f408b7a3bc8988eb32aab7_26bip_3DMTg2LjcyLjE0NS4xNTA_26lgfp_3D52_26bip_3DMTg2LjcyLjE0NS4xNTA/preview.mp3");
            }
        });


        layout_music4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic("https://dc597.4shared.com/img/p_e9yIMeca/e8241268/dlink__2Fdownload_2Fp_5Fe9yIMeca_2FBandolero_5F_5F1_5F.mp3_3Fsbsr_3D67bf9e832af7dd7fcc20ac83b3934788ab7_26bip_3DMTg2LjcyLjE0NS4xNTA_26lgfp_3D52_26bip_3DMTg2LjcyLjE0NS4xNTA/preview.mp3");
            }
        });

    }

    public void playMusic(String url){
        try {

            mediaPlayer.stop();
            mediaPlayer.pause();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (Exception e){
            Log.e("Error: ", String.valueOf(e));
        }
    }
}