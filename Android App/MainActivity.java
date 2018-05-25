package com.hoxton.flashcards;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView frontSide;
    TextView backSide;
    Button nextButton;
    Button prevButton;
    int current = 0;
    int[] questionArray = {0, 1, 2, 3};

    //Function for shuffling an array courtesy of Dan Bray at:
    // https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
    private static void shuffleArray(int[] array)
    {
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            if (index != i)
            {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }

    public void startGame(View view) {

        startButton.setVisibility(view.INVISIBLE);
        frontSide.setVisibility(view.VISIBLE);
        backSide.setVisibility(view.VISIBLE);
        nextButton.setVisibility(view.VISIBLE);
        prevButton.setVisibility(view.VISIBLE);

        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database"); }
        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle; }

        String[] questionAndAnswer = myDbHelper.returnInfo(questionArray[current]);
        frontSide.setText(questionAndAnswer[0]);
        backSide.setText(questionAndAnswer[1]);

    }

    public void flipCard(View view) {
        frontSide.setAlpha(0);
        backSide.setAlpha(1);
    }

    public void getNext(View view) {
        current++;

        frontSide.setAlpha(1);
        backSide.setAlpha(0);

        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database"); }
        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle; }

        String[] questionAndAnswer = myDbHelper.returnInfo(questionArray[current]);
        frontSide.setText(questionAndAnswer[0]);
        backSide.setText(questionAndAnswer[1]);
    }

    public void getPrev(View view) {
        if (current != 0){
            current--;
        }

        frontSide.setAlpha(1);
        backSide.setAlpha(0);

        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database"); }
        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle; }

        String[] questionAndAnswer = myDbHelper.returnInfo(current);
        frontSide.setText(questionAndAnswer[0]);
        backSide.setText(questionAndAnswer[1]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        frontSide = findViewById(R.id.questionSide);
        backSide = findViewById(R.id.answerSide);
        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);

        shuffleArray(questionArray);
    }
}
