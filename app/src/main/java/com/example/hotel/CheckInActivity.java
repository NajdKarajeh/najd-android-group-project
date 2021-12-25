package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class CheckInActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    private TextView nights,total,mainTV;
    private SeekBar seekBar;
    private Spinner spinner;
    private int totalInt;
    private int price=0;
    private RadioButton withRS, withoutRS;
    private Button buttonCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        Bundle b = getIntent().getExtras();
        String priceString = "";
        String roomNo = "";
        if(b != null)
        priceString = b.getString("price2");
        roomNo = b.getString("key2");
        price= Integer.parseInt(priceString);


        nights= (TextView) findViewById(R.id.textViewNights);
        total= (TextView) findViewById(R.id.textViewTotal);
        seekBar= (SeekBar) findViewById(R.id.seekBar);
        buttonCount=(Button) findViewById(R.id.buttonCont);

        mainTV= (TextView) findViewById(R.id.textViewMain);
        mainTV.setText(roomNo);

        withRS= (RadioButton) findViewById(R.id.radioButtonWithRoomService);
        withoutRS= (RadioButton) findViewById(R.id.radioButtonWithoutRoomService);


        withoutRS.setChecked(true);

        withRS.setOnClickListener(v -> {
            withoutRS.setChecked(false);
            totalInt= (int) (totalInt*1.25);
            total.setText(""+totalInt);
        });

        withoutRS.setOnClickListener(v -> {
            withRS.setChecked(false);
            totalInt= (Integer.parseInt(nights.getText().toString()))*price;
            total.setText(""+totalInt);
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                nights.setText(""+progress);
                totalInt= price*progress;
                total.setText(""+totalInt);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        buttonCount.setOnClickListener(v -> {
            Intent intent=new Intent(CheckInActivity.this , CheckInResultActivity.class);
            startActivity(intent);
            finish();

        });

        spinner=(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text= adapterView.getItemAtPosition(i).toString();
        switch(text){
         //here we could decide what to do with the spinner choice
            default: { break;}
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}