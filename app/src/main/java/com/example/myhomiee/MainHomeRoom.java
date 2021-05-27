package com.example.myhomiee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainHomeRoom extends AppCompatActivity {

    static boolean lightMode = true;
    ImageButton imgbtnHome, imgbtnFan, imgbtnLamp, imgbtnMenu;
    LinearLayout linearHome, linearLamp, linearFan, linearMenu, linearHomeListView, linearLampListView, linearFanListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_room);
        try {
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){}
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
        Fragment_Lamp fragment_lamp = new Fragment_Lamp();
        Fragment_Fan fragment_fan = new Fragment_Fan();
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
        imgbtnMenu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                MenuBuilder menuBuilder = new MenuBuilder(MainHomeRoom.this);
                MenuInflater inflater = new MenuInflater(MainHomeRoom.this);
                inflater.inflate(R.menu.menu_myhomiee, menuBuilder);
                MenuPopupHelper optionsMenu = new MenuPopupHelper(MainHomeRoom.this, menuBuilder, v);
                optionsMenu.setForceShowIcon(true);

                menuBuilder.setCallback(new MenuBuilder.Callback() {
                    @Override
                    public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menuDayMode:
                                linearMenu.setBackgroundColor(Color.parseColor("#FF4D4D"));
                                lightMode = true;
                                return true;
                            case R.id.menuNightMode:
                                linearMenu.setBackgroundColor(Color.parseColor("#6B0606"));
                                lightMode = false;
                                return true;
                            case R.id.menuSetting:
                                Toast.makeText(MainHomeRoom.this, "Setting", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }

                    @Override
                    public void onMenuModeChange(MenuBuilder menu) { }
                });

                optionsMenu.show();
            }
        });
    }
    void ShowMenu(){
        PopupMenu popupMenu = new PopupMenu(this, imgbtnMenu);
        popupMenu.getMenuInflater().inflate(R.menu.menu_myhomiee, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuDayMode:
                        Toast.makeText(MainHomeRoom.this, "Day Mode", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuNightMode:
                        Toast.makeText(MainHomeRoom.this, "Night Mode", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuSetting:
                        Toast.makeText(MainHomeRoom.this, "Setting", Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });
        popupMenu.show();

    }

    public boolean isLight(){
        return lightMode;
    }

    void anhXa(){
        imgbtnHome = (ImageButton) findViewById(R.id.imgbtnHome);
        imgbtnLamp = (ImageButton) findViewById(R.id.imgbtnLamp);
        imgbtnFan = (ImageButton) findViewById(R.id.imgbtnFan);
        imgbtnMenu = (ImageButton) findViewById(R.id.imgbtnMenu);
        linearHome = (LinearLayout) findViewById(R.id.linearHome);
        linearLamp = (LinearLayout) findViewById(R.id.linearLamp);
        linearFan = (LinearLayout) findViewById(R.id.linearFan);
        linearMenu = (LinearLayout) findViewById(R.id.linearMenu);
    }
}