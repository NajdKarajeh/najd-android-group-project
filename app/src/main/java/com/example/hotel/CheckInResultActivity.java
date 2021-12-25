package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckInResultActivity extends AppCompatActivity {

    private Button finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_result);

        finish= (Button) findViewById(R.id.buttonDone);

        finish.setOnClickListener(v -> {
            Intent intent=new Intent(CheckInResultActivity.this , HomeActivity.class);
            startActivity(intent);
            finishAffinity();
        });
    }
}