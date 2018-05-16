package com.hoxton.flashcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView frontSide;
    TextView backSide;
    Button nextButton;
    Button prevButton;

    public void startGame(View view) {

        startButton.setVisibility(view.INVISIBLE);
        frontSide.setVisibility(view.VISIBLE);
        backSide.setVisibility(view.VISIBLE);
        nextButton.setVisibility(view.VISIBLE);
        prevButton.setVisibility(view.VISIBLE);

    }

    public void flipCard(View view) {

        frontSide.setAlpha(0);
        backSide.setAlpha(1);

    }

    public void getNext(View view) {

        frontSide.setAlpha(1);
        backSide.setAlpha(0);

        //load text
    }

    public void getPrev(View view) {

        frontSide.setAlpha(1);
        backSide.setAlpha(0);

        //load text
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        frontSide = (TextView) findViewById(R.id.questionSide);
        backSide = (TextView) findViewById(R.id.answerSide);
        nextButton = (Button) findViewById(R.id.nextButton);
        prevButton = (Button) findViewById(R.id.prevButton);
    }
}
