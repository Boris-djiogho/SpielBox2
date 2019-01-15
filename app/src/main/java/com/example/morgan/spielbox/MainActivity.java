package com.example.morgan.spielbox;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity{

    private Button labyrithn;
    private Button sonic;
    public MediaPlayer song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        song = MediaPlayer.create(MainActivity.this,R.raw.maybe);

        sonic = (Button) findViewById(R.id.sonic);

        sonic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSonic();

            }
        });

        labyrithn = (Button)findViewById(R.id.laby);

        labyrithn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLabyrithn();
            }
        });

    }

    //Funktion für Catch the Sonic
       public void openSonic (){
        Intent intent1 = new Intent(this, Sonic1.class);
        startActivity(intent1);
       }

    //Funktion für Labyrithn
    public void openLabyrithn (){
        Intent intent2 = new Intent(this, Labyrithn.class);
        startActivity(intent2);
    }

    //Funktion für Musik
    public void playSound(View view){

        Button button = (Button) view;

        if(song.isPlaying() )
        {
            song.pause();
            button.setText(getString(R.string.play_music_btn) );
        }

        else
        {
            song.start();
            button.setText(getString(R.string.pause_music_btn) );
        }
    }

      /* @Override
      protected void onPause() {
         super.onPause();
         song.release();
       }*/

}
