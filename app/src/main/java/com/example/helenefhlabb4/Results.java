package com.example.helenefhlabb4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Results extends AppCompatActivity implements View.OnClickListener{

    Button main, back, play, info;
    String word, result;
    TextView tResult, tWord, tTries;
    int tries;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        tResult = findViewById(R.id.results);
        tWord = findViewById(R.id.word_was);
        tTries = findViewById(R.id.times_tried);

        resultIntent();
        theResult();

        main = (Button) findViewById(R.id.b_main);
        main.setOnClickListener(this);
        back = (Button) findViewById(R.id.b_back);
        back.setOnClickListener(this);
        play = (Button) findViewById(R.id.new_game);
        play.setOnClickListener(this);
        info = (Button) findViewById(R.id.r_about);
        info.setOnClickListener(this);

    }

    public void resultIntent() { // Hämtar resultat från spelet

        Bundle extras = getIntent().getExtras();
        String resultsFromIntent, wordFromIntent;
        int triesFromIntent;

        if (extras != null) {

            resultsFromIntent = extras.getString("result");
            wordFromIntent = extras.getString("word");
            triesFromIntent = extras.getInt("tries");

            result = resultsFromIntent;
            tries = triesFromIntent;
            word = wordFromIntent;
        }
    }

    @SuppressLint("SetTextI18n") // Sätter rätt resultat beroende på om man vinner/förlorar
    public void theResult() {

            tResult.setText(result);
            tWord.setText("Ordet var: " + word);
            tTries.setText("Du gissade fel " + tries + "/7 ggr.");

    }

    @Override
    public void onClick(View v) { // Sätter on click listsener på alla knappar

        switch (v.getId()) {
            case R.id.b_main:
                startActivity(new Intent(Results.this, MainActivity.class));
                break;
            case R.id.b_back:
                startActivity(new Intent(Results.this, MainActivity.class));
                break;
            case R.id.new_game:
                startActivity(new Intent(Results.this, Hangman.class));
               break;
            case R.id.r_about:
                startActivity(new Intent(Results.this, About.class));
                break;
            default:
                break;
        }

    }

}

