package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView mainImageView;
    private ImageView secondImageView;
    private TextView mainText, secondText, skip;
    private Button startButton;
    private int index=1;
    private String mainText2="Ticket Booking";
    private String mainText3="Enjoy Your Holiday";
    private String secondText2="Your room is ready as soon as you verify the booking of your fly ticket. Only few moments and the room is yours";
    private String secondText3="There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainImageView= (ImageView) findViewById(R.id.startMainImageView);
        secondImageView= (ImageView) findViewById(R.id.startIndexImageView);
        mainText= (TextView) findViewById(R.id.startMainTextView);
        secondText= (TextView) findViewById(R.id.startSecondTextView);
        skip= (TextView) findViewById(R.id.startSkipTextView);
        startButton= (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (index==1){
                   mainImageView.setImageDrawable(getDrawable(R.drawable.start2));
                   secondImageView.setImageDrawable(getDrawable(R.drawable.index2));
                   mainText.setText(mainText2);
                   secondText.setText(secondText2);
                   index++;
               }
               else if (index ==2){
                   mainImageView.setImageDrawable(getDrawable(R.drawable.start3));
                   secondImageView.setImageDrawable(getDrawable(R.drawable.index3));
                   mainText.setText(mainText3);
                   secondText.setText(secondText3);
                   startButton.setText("Start");
                   index++;
               }
               else if (index ==3){
                   Intent intent=new Intent(MainActivity.this , SignupActivity.class);
                   startActivity(intent);
                   finish();
               }
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent=new Intent(MainActivity.this , HomeActivity.class);
                    startActivity(intent);
                    finish();

            }
        });

    }
}