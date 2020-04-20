package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView logo;
    Button playagain;
    TextView resulttextView;
    TextView finalScore;
    int correctanswerlocation;
    int score=0;
    int numberofQ=0;
    TextView scoretextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView QtextView;
    TextView timertextView;
    ConstraintLayout gamelayout;
    GridLayout gridLayout;
    ArrayList<Integer> answers=new ArrayList<Integer>();

    public void playAgain(View view){
        finalScore.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        score=0;
        numberofQ=0;
        timertextView.setText("30s");
        scoretextView.setText(Integer.toString(score) + "/" + Integer.toString(numberofQ));
        newquestion();
        playagain.setVisibility(View.INVISIBLE);
        resulttextView.setText("");
        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timertextView.setText(String.format("%02d",(millisUntilFinished/1000))+"s");

            }

            @Override
            public void onFinish() {
                resulttextView.setText("Time Up!");
                gridLayout.setVisibility(View.INVISIBLE);
                playagain.setVisibility(View.VISIBLE);
                finalScore.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public void chooseAnswer(View view){
        int tapped=Integer.parseInt(view.getTag().toString());
        if(correctanswerlocation==tapped){
            resulttextView.setText("Correct!");
            score++;
        }else{
            resulttextView.setText("Wrong :(");
        }
        numberofQ++;
        scoretextView.setText(Integer.toString(score) + "/" + Integer.toString(numberofQ));
        finalScore.setText("FINAL SCORE    "+ Integer.toString(score)+"/"+Integer.toString(numberofQ));
        newquestion();
    }

    public void start(View view){
        logo.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        gamelayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timertextView));
    }

    public void newquestion(){
        Random random=new Random();

        int a=random.nextInt(21);
        int b=random.nextInt(21);
        QtextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        correctanswerlocation=random.nextInt(4);

        answers.clear();

        for(int i=0;i<4;i++)
        {
            if(i==correctanswerlocation)
            {
                answers.add(a+b);
            }else{
                int wrong=random.nextInt(41);
                while (wrong==(a+b))
                {
                    wrong=random.nextInt(41);
                }
                answers.add(wrong);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QtextView = findViewById(R.id.QtextView);

        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        timertextView=findViewById(R.id.timertextView);
        resulttextView=findViewById(R.id.resulttextView);
        scoretextView=findViewById(R.id.scoretextView);
        button=findViewById(R.id.button);
        finalScore=findViewById(R.id.finalScrore);
        playagain=findViewById(R.id.playagain);
        gamelayout=findViewById(R.id.gamelayout);
        gridLayout=findViewById(R.id.gridLayout);
        button.setVisibility(View.VISIBLE);
        gamelayout.setVisibility(View.INVISIBLE);
        logo=findViewById(R.id.logo);
    }
}
