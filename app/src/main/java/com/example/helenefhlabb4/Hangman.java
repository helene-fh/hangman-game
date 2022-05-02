package com.example.helenefhlabb4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Hangman extends AppCompatActivity implements View.OnClickListener {

    Button back, guess, newGame, about;
    TextView usedLetters, attemptsLeft, theWord;
    String findWord, letterHint;
    EditText userInput;
    ImageView hangman;

    ArrayList<String> animalWords = new ArrayList<>();
    ArrayList<Character> guesses = new ArrayList<>(0);
    StringBuilder builder = new StringBuilder();
    Random r = new Random();

    char input;
    int currentTries = 0;
    int maxTries = 7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangman_activity);

        NewGame(); // Resetar spelet och börjar ett nytt

    }


    public ArrayList<String> animals() { // Lista med ord att gissa på

        animalWords.add("hund");
        animalWords.add("katt");
        animalWords.add("tiger");
        animalWords.add("marsvin");
        animalWords.add("kanin");
        animalWords.add("lejon");
        animalWords.add("elefant");
        animalWords.add("panda");
        animalWords.add("svan");
        animalWords.add("giraff");

        return animalWords;
    }

    public int randomizeWord() { // Hämtar ett random ord från listan med ord

        animalWords = animals();
        int randomize;
        randomize = r.nextInt(9);
        return randomize;
    }

    public void NewGame() { // Resetar alla fält för ett nytt spel
        initScreen();

        builder.setLength(0);
        guesses.clear();
        usedLetters.setText(Arrays.toString(guesses.toArray()));
        currentTries = 0;
        attemptsLeft.setText(currentTries + "/" + maxTries);
        hangman.setImageResource(R.drawable.game0);
        userInput.setText("");

        Collections.shuffle(animalWords);
        findWord = animalWords.get(randomizeWord());

        for (int i = 0; i <findWord.length(); i++) {    // Bygger upp random ordet med underscores lika många som antal tecken
            builder.append('_');
        }
        letterHint = builder.toString();
        theWord.setText(letterHint);

        play();
    }

    public void moreThenOne(char input) { // Om den gissade bokstaven finns på två ställen, skrivs de ut direkt

        for (int i = 0; i <findWord.length(); i++) {

            if(findWord.charAt(i) == input) {

                builder.setCharAt(i,input);
                letterHint = builder.toString();
                theWord.setText(letterHint);
            }
        }

    }

    public void play() { // Spelare skriver in en bostav och trycker på gissa knappen

        guess.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                userInput = (EditText) findViewById(R.id.user_input);

                int i;

                if (userInput.length() == 1) {
                    input = userInput.getText().charAt(0);

                    userInput.setText("");

                    for (i = 0; i < findWord.length(); i++) {

                        if (guesses.contains(input)) {
                            Toast.makeText(Hangman.this, "Du har redan gissat på denna bokstav!", Toast.LENGTH_SHORT).show();
                            break;

                        } else if (findWord.charAt(i) == input) {

                            moreThenOne(input);
                            Toast.makeText(Hangman.this, "Du gissa rätt!", Toast.LENGTH_SHORT).show();

                            if (builder.toString().equals(findWord)) {
                                Intent intent = new Intent(Hangman.this, Results.class);
                                intent.putExtra("result", "Du vann!");
                                intent.putExtra("word", findWord);
                                intent.putExtra("tries", currentTries);
                                startActivity(intent);
                            }
                            break;

                        }

                    }

                    if (i >= findWord.length()) {
                        guesses.add(input); // vill bara addera om bokstaven är fel
                        Toast.makeText(Hangman.this, "Du gissa fel!", Toast.LENGTH_SHORT).show();
                        usedLetters.setText(Arrays.toString(guesses.toArray()));
                        currentTries++;
                        attemptsLeft.setText(currentTries + "/" + maxTries);
                        getHangmanDrawable();

                        if (currentTries == maxTries) {
                            Intent intent = new Intent(Hangman.this, Results.class);
                            intent.putExtra("result", "Du förlora!");
                            intent.putExtra("word", findWord);
                            intent.putExtra("tries", currentTries);
                            startActivity(intent);
                        }
                    }


                }   else {
                   Toast.makeText(Hangman.this, "Du måste gissa på en bokstav!", Toast.LENGTH_SHORT).show();
                }
                }
        });

        }

    public void initScreen () { // Hämtar alla views / sätter upp clicklistener

        newGame = (Button) findViewById(R.id.new_game);
        newGame.setOnClickListener(this);

        back = (Button) findViewById(R.id.b_back);
        back.setOnClickListener(this);

        about = (Button) findViewById(R.id.m_about);
        about.setOnClickListener(this);

        guess = (Button) findViewById(R.id.b_guess);
        usedLetters = (TextView) findViewById(R.id.used_letters);
        attemptsLeft = (TextView) findViewById(R.id.tries_left);
        theWord = (TextView) findViewById(R.id.the_word);
        userInput = (EditText) findViewById(R.id.user_input);
        hangman = (ImageView) findViewById(R.id.game_0);

    }

    @SuppressLint("ResourceType")
    public void getHangmanDrawable() { // Häntar alla hangman bilder, byter ut beroende på antal gissningar/försök

        if (currentTries == 0) {
            findViewById(R.drawable.game0);
        }else if (currentTries == 1) {
            hangman.setImageResource(R.drawable.game1);
        }else if (currentTries == 2) {
            hangman.setImageResource(R.drawable.game2);
        }else if (currentTries == 3) {
            hangman.setImageResource(R.drawable.game3);
        }else if (currentTries == 4) {
            hangman.setImageResource(R.drawable.game4);
        }else if (currentTries == 5) {
            hangman.setImageResource(R.drawable.game5);
        }else if (currentTries == 6) {
            hangman.setImageResource(R.drawable.game6);
        }else if (currentTries == 7) {
            hangman.setImageResource(R.drawable.game7);
        }
    }

    @Override
    public void onClick(View v) {  // Sätter upp clicklistener för olika knappar

        switch (v.getId()) {
            case R.id.b_back:
                startActivity(new Intent(Hangman.this, MainActivity.class));
                break;
            case R.id.m_about:
                startActivity(new Intent(Hangman.this, About.class));
                break;
            case R.id.new_game:
                NewGame();
            default:
                break;
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
