package com.example.helenefhlabb4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button play, about, menuPlay, menuAbout;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.b_play);
        play.setOnClickListener(this);
        about = findViewById(R.id.b_about);
        about.setOnClickListener(this);
        menuPlay = findViewById(R.id.m_p);
        menuPlay.setOnClickListener(this);
        menuAbout = findViewById(R.id.m_a);
        menuAbout.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) { // Sätter upp on click listener för alla knappar

        switch (v.getId()) {
            case R.id.b_play:
                startActivity(new Intent(MainActivity.this, Hangman.class));
                break;
            case R.id.b_about:
                startActivity(new Intent(MainActivity.this, About.class));
                break;
            case R.id.m_p:
                startActivity(new Intent(MainActivity.this, Hangman.class));
              break;
            case R.id.m_a:
                startActivity(new Intent(MainActivity.this, About.class));
                break;
            default:
                break;
        }

    }
}