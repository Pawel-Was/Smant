package com.example.pawelwas.smant;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button SendButton,ButtonWater1,ButtonWater2,ButtonWater3,ButtonWater4;

    private TextView TextViewTemperature,TextViewHumidity,TextViewLight;
    private TextView TextViewHum1,TextViewHum2,TextViewHum3,TextViewHum4;

    private String ReadTemperature,ReadHumidity,ReadLight;
    private String ReadHum1,ReadHum2,ReadHum3,ReadHum4;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference DBreff;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButtonWater1 = (Button) findViewById(R.id.WaterButton1);
        ButtonWater2 = (Button) findViewById(R.id.WaterButton2);
        ButtonWater3 = (Button) findViewById(R.id.WaterButton3);
        ButtonWater4 = (Button) findViewById(R.id.WaterButton4);
        TextViewTemperature = (TextView) findViewById(R.id.TextViewTemp);
        TextViewHumidity = (TextView) findViewById(R.id.TextViewHum);
        TextViewLight = (TextView) findViewById(R.id.TextViewLight);

        TextViewHum1 = (TextView)  findViewById(R.id.TextViewHum1);
        TextViewHum2 = (TextView)  findViewById(R.id.TextViewHum2);
        TextViewHum3 = (TextView)  findViewById(R.id.TextViewHum3);
        TextViewHum4 = (TextView)  findViewById(R.id.TextViewHum4);

        int i = 0;

        DBreff = database.getReference("Stream");

        ButtonWater1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    Log.i("TOUCH_UP","GORA");
                    return true;

                }else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                    Log.i("TOUCH_DOWN","DOL");
                    return true;
            }
        });


       /* ButtonWater1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBreff.child("PumpControl").child("Pump1").setValue(1);
                Toast.makeText(MainActivity.this,"Podlano kwiatek nr 1",Toast.LENGTH_SHORT).show();}});*/
        ButtonWater2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBreff.child("PumpControl").child("Pump2").setValue(1);
                Toast.makeText(MainActivity.this,"Podlano kwiatek nr 2",Toast.LENGTH_SHORT).show();}
        });
        ButtonWater3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBreff.child("PumpControl").child("Pump3").setValue(1);
                Toast.makeText(MainActivity.this,"Podlano kwiatek nr 3",Toast.LENGTH_SHORT).show();}
        });
        ButtonWater4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBreff.child("PumpControl").child("Pump4").setValue(1);
                Toast.makeText(MainActivity.this,"Podlano kwiatek nr 4",Toast.LENGTH_SHORT).show();}
        });


        DBreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ReadHumidity = dataSnapshot.child("data").child("Humidity").getValue().toString();
                ReadTemperature = dataSnapshot.child("data").child("Temperature").getValue().toString();
                ReadLight = dataSnapshot.child("data").child("Light").getValue().toString();

                ReadHum1 = dataSnapshot.child("data").child("SoilHumidity1").getValue().toString();
                ReadHum2 = dataSnapshot.child("data").child("SoilHumidity2").getValue().toString();
                ReadHum3 = dataSnapshot.child("data").child("SoilHumidity3").getValue().toString();
                ReadHum4 = dataSnapshot.child("data").child("SoilHumidity4").getValue().toString();




                TextViewTemperature.setText(ReadTemperature);
                TextViewHumidity.setText(ReadHumidity);
                TextViewLight.setText(ReadLight);

                TextViewHum1.setText(ReadHum1);
                TextViewHum2.setText(ReadHum2);
                TextViewHum3.setText(ReadHum3);
                TextViewHum4.setText(ReadHum4);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,"DATA LOAD ERROR",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
