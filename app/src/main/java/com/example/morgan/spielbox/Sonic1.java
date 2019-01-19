package com.example.morgan.spielbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sonic1 extends AppCompatActivity {

    private Button buttonE;
    private Button buttonM;
    private Button buttonH;
    private Button buttonU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonic1);

        buttonE = (Button) findViewById(R.id.easy);
        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEasy();
            }
        });
        buttonM = (Button) findViewById(R.id.medium);
        buttonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMedium();
            }
        });

        buttonH = (Button) findViewById(R.id.hard);
        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHard();
            }
        });

        buttonU = (Button) findViewById(R.id.ultimate);
        buttonU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUltimate();
            }
        });

    }

        public void openEasy(){
            Intent intent = new Intent(this, Sonic2.class);
            startActivity(intent);
        }
    public void openMedium(){
        Intent intent = new Intent(this, Medium.class);
        startActivity(intent);
    }
    public void openHard(){
        Intent intent = new Intent(this, Hard.class);
        startActivity(intent);
    }
    public void openUltimate(){
        Intent intent = new Intent(this, Ultimate.class);
        startActivity(intent);
    }
}
