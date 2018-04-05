package com.example.abhayaradhya.slotmachine2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout spinnnerLayout;
    private ImageView spinnerBox1;
    private ImageView spinnerBox2;
    private ImageView spinnerBox3;
    private SeekBar difficultyBar;
    private Button startButton;
    private Button rulesButton;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private int playerScore;
    private TextView scoreDisplay;
    private int[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            playerScore = savedInstanceState.getInt("score");
        } else{playerScore = 0;}

        spinnnerLayout = findViewById(R.id.spinnerLayout);
        difficultyBar = findViewById(R.id.difficultyBar);
        startButton = findViewById(R.id.startButton);
        rulesButton = findViewById(R.id.rulesButton);
        spinnerBox1 = findViewById(R.id.spinner1);
        spinnerBox2 = findViewById(R.id.spinner2);
        spinnerBox3 = findViewById(R.id.spinner3);
        scoreDisplay = findViewById(R.id.score);


        if(savedInstanceState != null){
            colors = savedInstanceState.getIntArray("colors");
        } else{
            colors = new int[10];
            int color;
            for(int i=0; i<colors.length; i++){
                color = (int)(Math.random() * 0x84111111);
                colors[i] = color;
            }
        }

        if(savedInstanceState != null){difficultyBar.setProgress(savedInstanceState.getInt("difficulty"));}

        int rate;
        rate = ((int)(Math.random()*400)+400);
        spinner1 = new Spinner(spinnerBox1, rate, colors);
        rate = ((int)(Math.random()*400)+400);
        spinner2 = new Spinner(spinnerBox2, rate, colors);
        rate = ((int)(Math.random()*400)+400);
        spinner3 = new Spinner(spinnerBox3, rate, colors);

        if(savedInstanceState != null && savedInstanceState.getBoolean("spinner1IsStopped")){spinner1.setImageIndex(savedInstanceState.getInt("spinner1"));}
        if(savedInstanceState != null && savedInstanceState.getBoolean("spinner2IsStopped")){spinner2.setImageIndex(savedInstanceState.getInt("spinner2"));}
        if(savedInstanceState != null && savedInstanceState.getBoolean("spinner3IsStopped")){spinner3.setImageIndex(savedInstanceState.getInt("spinner3"));}

    }

    /*public void resetSpinners(){
        spinnerBox1.setBackgroundColor(colors[0]);
        spinnerBox2.setBackgroundColor(colors[0]);
        spinnerBox3.setBackgroundColor(colors[0]);
        int rate;
        rate = ((int)(Math.random()*400)+400);
        spinner1.setRate(rate);
        rate = ((int)(Math.random()*400)+400);
        spinner2.setRate(rate);
        rate = ((int)(Math.random()*400)+400);
        spinner3.setRate(rate);
    }*/

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("spinner1", spinner1.getColorIndex());
        savedInstanceState.putBoolean("spinner1IsStopped", spinner1.isStopped());
        savedInstanceState.putInt("spinner2", spinner2.getColorIndex());
        savedInstanceState.putBoolean("spinner2IsStopped", spinner2.isStopped());
        savedInstanceState.putInt("spinner3", spinner3.getColorIndex());
        savedInstanceState.putBoolean("spinner3IsStopped", spinner3.isStopped());
        savedInstanceState.putInt("difficulty", difficultyBar.getProgress());
        savedInstanceState.putIntArray("colors", colors);
        savedInstanceState.putInt("score", playerScore);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // *** TO DO *** finish implementing restore instance state
    }

    public void showRules(View view) {
        Intent intent = new Intent(this, RulesActivity.class);
        intent.putExtra("score", playerScore);
        startActivity(intent);
    }

    public void score(){
        if(spinner1.getColorIndex()==spinner2.getColorIndex() && spinner1.getColorIndex()==spinner3.getColorIndex()){
            playerScore += 500;
            scoreDisplay.setText(String.valueOf(playerScore));
        }
    }

    public void startStopButton(View v){
        if(spinner1.isStopped() && spinner2.isStopped() && spinner3.isStopped()){
            int rate;
            rate = spinner1.getBaseRate()/(difficultyBar.getProgress()+1);
            spinner1.setRate(rate);
            rate = spinner2.getBaseRate()/(difficultyBar.getProgress()+1);
            spinner2.setRate(rate);
            rate = spinner3.getBaseRate()/(difficultyBar.getProgress()+1);
            spinner3.setRate(rate);
            spinner1.startSpinning();
            spinner2.startSpinning();
            spinner3.startSpinning();
            difficultyBar.setEnabled(false);
        } else {
            if (!spinner1.isStopped()){
                spinner1.stopSpinning();
            } else if(!spinner2.isStopped()){
                spinner2.stopSpinning();
            } else if(!spinner3.isStopped()){
                spinner3.stopSpinning();
                score();
                difficultyBar.setEnabled(true);
            }
        }

    }
}
