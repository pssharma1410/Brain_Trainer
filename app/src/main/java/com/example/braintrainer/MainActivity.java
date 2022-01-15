package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tv11;//for time
    TextView tv12;//for question
    TextView tv10;//for score
    TextView tv20;//Play again Screen
    Button b2;
    Button b3;
    TextView prev;
    TextView  o1;
    TextView  o2;
    TextView  o3;
    TextView  o4;
    //To store current ans
    int ans;
    //Give tags to textViews in grid
    //Below int will store the ansTextView tag
    String currAnsTag;
    //Below will store count of current correct answers
    int count = 0;
    //To store total number of questions attempted till now
    int ques = 0;

    androidx.gridlayout.widget.GridLayout gl;
    public int randomGenerator(int max,int min){
        Random rg = new Random();
        return rg.nextInt(max-min+1) + min;
    }
    public void settingQuestion(){
        //This method will set the question everytime we call it from textViewClick
        //and initially from startgame method
        int first = randomGenerator(50,1);
        int second = randomGenerator(50,1);
        tv12.setText(first+" + "+second);
        //Giving ans to a global variable so that we can use it anywhere
        ans = first+second;
    }
    public void textViewClick(View view){
        TextView temp = (TextView) view;
        if(view.getTag().toString().equals(currAnsTag)){
            count++;
            prev.setText("Great:)\nCorrect");
        }else{
            prev.setText("OOPS:(\nIncorrect");
        }
        ques++;
        //updating textviews
        tv10.setText(count+"/"+ques);
        //update next question
        settingQuestion();
        settingOptions();
    }
    public void settingOptions(){
        //this will store correct option tag
        int c = randomGenerator(4,1);
        currAnsTag = String.valueOf(c);
        if(c == 1){
            o1.setText(ans+"");
        }else if(c == 2){
            o2.setText(ans+"");
        }else if(c == 3){
            o3.setText(ans+"");
        }else if(c == 4){
            o4.setText(ans+"");
        }
        for(int i = 1; i <=4 ; i++){
            if(i == c)
                continue;
            //generate random answers;
            int rans = randomGenerator(100,25);
            if(i == 1){
                o1.setText(rans+"");
            }else if(i == 2){
                o2.setText(rans+"");
            }else if(i == 3){
                o3.setText(rans+"");
            }else if(i == 4){
                o4.setText(rans+"");
            }
        }

    }


    public void startgame(View view){
        tv20.setVisibility(View.INVISIBLE);
        tv11.setVisibility(View.VISIBLE);
        tv12.setVisibility(View.VISIBLE);
        tv10.setVisibility(View.VISIBLE);
        gl.setVisibility(View.VISIBLE);
        b2.setVisibility(View.INVISIBLE);
        prev.setText("");
        //making sure count = 0
        //and ques = 0
        count = 0;
        ques = 0;
        //Making sure that play again button is invisible at start
        b3.setVisibility(View.INVISIBLE);
        tv10.setText(0+"/"+0);
        //initiall giving an question
        settingQuestion();
        settingOptions();
        CountDownTimer cdt = new CountDownTimer(31000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int sec = (int)(millisUntilFinished/1000);
                if(sec < 10) {
                    tv11.setText("0"+sec+"s");
                }else{
                    tv11.setText(sec+"s");
                }
            }

            @Override
            public void onFinish() {
                tv20.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                tv11.setVisibility(View.INVISIBLE);
                tv12.setVisibility(View.INVISIBLE);
                tv10.setVisibility(View.INVISIBLE);
                gl.setVisibility(View.INVISIBLE);
                tv20.setVisibility(View.VISIBLE);
                tv20.setText("Your Score is : \n"+count+" Out of "+ques);
                prev.setText("");
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv11 = findViewById(R.id.textView11);
        tv10 = findViewById(R.id.textView10);
        tv12 = findViewById(R.id.textView12);
        tv20 = findViewById(R.id.textView20);
        gl = findViewById(R.id.gridLayout2);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        o1 = findViewById(R.id.textView1);
        o2 = findViewById(R.id.textView2);
        o3 = findViewById(R.id.textView3);
        o4 = findViewById(R.id.textView4);
        prev = findViewById(R.id.textView);

    }
}