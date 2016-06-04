package com.hacks001.code32.musicstream;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
Button start,stop,pause;
    MediaPlayer mPlayer;
    String url = "http://programmerguru.com/android-tutorial/wp-content/uploads/2013/04/hosannatelugu.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start= (Button) findViewById(R.id.play);
        stop= (Button) findViewById(R.id.stop);
        pause=(Button)findViewById(R.id.pause);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer = new MediaPlayer();
                Toast.makeText(getApplication(),"Trying to Play",Toast.LENGTH_SHORT).show();
                new Thread (new PlayOnThread()).start();
                if(mPlayer.isPlaying()==true){
                    Toast.makeText(getApplicationContext(),"Yes its Buffering",Toast.LENGTH_LONG).show();
                }

            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.pause();
                Toast.makeText(getApplicationContext(),"The Song is Paused",Toast.LENGTH_LONG).show();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.stop();
                mPlayer.release();
            }
        });
    }

    class PlayOnThread implements Runnable{
        @Override
        public void run() {
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mPlayer.setDataSource(url);
            }catch (IllegalStateException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (SecurityException se){
                se.printStackTrace();
            }
            mPlayer.prepareAsync();
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mPlayer.start();
                }
            });

        }
    }
}
