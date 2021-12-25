package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class RoomActivity extends AppCompatActivity {

    private TextView tvPrice, tvRoomNumber;
    private ImageView imageViewPhoto;
    private Button checkIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        tvRoomNumber= (TextView) findViewById(R.id.textViewRoomNumberMain);
        tvPrice= (TextView) findViewById(R.id.textViewRoomPrice);
        imageViewPhoto= (ImageView) findViewById(R.id.imageViewRoom);
        checkIn= (Button) findViewById(R.id.buttonCheckIn);

        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RoomActivity.this , CheckInActivity.class);
                Bundle b = new Bundle();
                b.putString("price2", tvPrice.getText().toString().trim()); //Your id
                b.putString("key2", tvRoomNumber.getText().toString().trim()); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        Bundle b = getIntent().getExtras();
        String value = "null";
        String price = "null";
        if(b != null)
            value = b.getString("key");
            price = b.getString("price");

        tvRoomNumber.setText(value);
        tvPrice.setText(price);
        Intent intent = getIntent();
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
       // imageViewPhoto.setImageBitmap(bitmap);
        imageViewPhoto.setImageDrawable(HomeActivity.imageView.getDrawable());

    }
}