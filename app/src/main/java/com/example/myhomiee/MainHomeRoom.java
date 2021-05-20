package com.example.myhomiee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainHomeRoom extends AppCompatActivity {

    ImageButton imgbtnHome, imgbtnFan, imgbtnLamp;
    LinearLayout linearHome, linearLamp, linearFan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_room);
        anhXa();

        imgbtnHome.setBackgroundResource(R.drawable.fragment_home_on);
        linearHome.setBackgroundColor(Color.parseColor("#ffffff"));
        imgbtnLamp.setBackgroundResource(R.drawable.fragment_lamp_off);
        linearLamp.setBackgroundColor(Color.parseColor("#D9D9D9"));
        imgbtnFan.setBackgroundResource(R.drawable.fragment_fan_off);
        linearFan.setBackgroundColor(Color.parseColor("#D9D9D9"));
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment_Home fragment_home = new Fragment_Home();
        fragmentTransaction.replace(R.id.frame_content, fragment_home);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        imgbtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbtnHome.setBackgroundResource(R.drawable.fragment_home_on);
                linearHome.setBackgroundColor(Color.parseColor("#ffffff"));
                imgbtnLamp.setBackgroundResource(R.drawable.fragment_lamp_off);
                linearLamp.setBackgroundColor(Color.parseColor("#D9D9D9"));
                imgbtnFan.setBackgroundResource(R.drawable.fragment_fan_off);
                linearFan.setBackgroundColor(Color.parseColor("#D9D9D9"));
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_Home fragment_home = new Fragment_Home();
                fragmentTransaction.replace(R.id.frame_content, fragment_home);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        imgbtnLamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbtnHome.setBackgroundResource(R.drawable.fragment_home_off);
                linearHome.setBackgroundColor(Color.parseColor("#D9D9D9"));
                imgbtnLamp.setBackgroundResource(R.drawable.fragment_lamp_on);
                linearLamp.setBackgroundColor(Color.parseColor("#ffffff"));
                imgbtnFan.setBackgroundResource(R.drawable.fragment_fan_off);
                linearFan.setBackgroundColor(Color.parseColor("#D9D9D9"));
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_Lamp fragment_lamp = new Fragment_Lamp();
                fragmentTransaction.replace(R.id.frame_content, fragment_lamp);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        imgbtnFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbtnHome.setBackgroundResource(R.drawable.fragment_home_off);
                linearHome.setBackgroundColor(Color.parseColor("#D9D9D9"));
                imgbtnLamp.setBackgroundResource(R.drawable.fragment_lamp_off);
                linearLamp.setBackgroundColor(Color.parseColor("#D9D9D9"));
                imgbtnFan.setBackgroundResource(R.drawable.fragment_fan_on);
                linearFan.setBackgroundColor(Color.parseColor("#ffffff"));
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_Fan fragment_fan = new Fragment_Fan();
                fragmentTransaction.replace(R.id.frame_content, fragment_fan);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    void anhXa(){
        imgbtnHome = (ImageButton) findViewById(R.id.imgbtnHome);
        imgbtnLamp = (ImageButton) findViewById(R.id.imgbtnLamp);
        imgbtnFan = (ImageButton) findViewById(R.id.imgbtnFan);
        linearHome = (LinearLayout) findViewById(R.id.linearHome);
        linearLamp = (LinearLayout) findViewById(R.id.linearLamp);
        linearFan = (LinearLayout) findViewById(R.id.linearFan);
    }
}