package com.example.myhomiee;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import helpers.MqttHelper;

public class MainHomeRoom extends AppCompatActivity {

    static boolean lightMode = true;
    static String temparature = "";
    static String timeLight = "";
    static String timeNightMode = "";
    static String timeSleep = "";
    EditText editTextTemparature, editTextLightTime, editTextNightModeTime, editTextSleepTime;
    TimePickerDialog timePickerDialog;
    ImageButton imgbtnHome, imgbtnFan, imgbtnLamp, imgbtnMenu;
    LinearLayout linearHome, linearLamp, linearFan, linearMenu, linearHomeListView, linearLampListView, linearFanListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_room);
        startMqtt();
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
                if(!timeNightMode.isEmpty()){
                    String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                    currentTime = currentTime.replace(":", "");
                    String timeNightMode_2 = timeNightMode;
                    timeNightMode_2 = timeNightMode_2.replace(":","");
                    if(Integer.parseInt(timeNightMode_2) < Integer.parseInt(currentTime)){
                        linearMenu.setBackgroundColor(Color.parseColor("#6B0606"));
                        lightMode = false;
                    }else{
                        linearMenu.setBackgroundColor(Color.parseColor("#FF4D4D"));
                        lightMode = true;
                    }
                }

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
                if(!timeNightMode.isEmpty()){
                    String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                    currentTime = currentTime.replace(":", "");
                    String timeNightMode_2 = timeNightMode;
                    timeNightMode_2 = timeNightMode_2.replace(":","");
                    if(Integer.parseInt(timeNightMode_2) < Integer.parseInt(currentTime)){
                        linearMenu.setBackgroundColor(Color.parseColor("#6B0606"));
                        lightMode = false;
                    }else{
                        linearMenu.setBackgroundColor(Color.parseColor("#FF4D4D"));
                        lightMode = true;
                    }
                }
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
                if(!timeNightMode.isEmpty()){
                    String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                    currentTime = currentTime.replace(":", "");
                    String timeNightMode_2 = timeNightMode;
                    timeNightMode_2 = timeNightMode_2.replace(":","");
                    if(Integer.parseInt(timeNightMode_2) < Integer.parseInt(currentTime)){
                        linearMenu.setBackgroundColor(Color.parseColor("#6B0606"));
                        lightMode = false;
                    }else{
                        linearMenu.setBackgroundColor(Color.parseColor("#FF4D4D"));
                        lightMode = true;
                    }
                }
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
                                showSettingDialog();
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

    void showSettingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainHomeRoom.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(MainHomeRoom.this).inflate(
                R.layout.layout_setting,
                (LinearLayout)findViewById(R.id.linearSetting)
        );
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        editTextTemparature = (EditText) view.findViewById(R.id.editTextTemparature);
        editTextLightTime = (EditText) view.findViewById(R.id.editTextLightTime);
        editTextNightModeTime = (EditText) view.findViewById(R.id.editTextNightModeTime);
        editTextSleepTime = (EditText) view.findViewById(R.id.editTextSleepTime);

        editTextTemparature.setText(temparature);
        editTextLightTime.setText(timeLight);
        editTextLightTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextLightTime.setText("");
                timePickerDialog = new TimePickerDialog(MainHomeRoom.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(minute < 10){
                            editTextLightTime.setText(hourOfDay + ":0" + minute);
                        }
                        else{
                            editTextLightTime.setText(hourOfDay + ":" + minute);
                        }
                    }
                }, 0,0,false);
                timePickerDialog.show();
            }
        });

        editTextNightModeTime.setText(timeNightMode);
        editTextNightModeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNightModeTime.setText("");
                timePickerDialog = new TimePickerDialog(MainHomeRoom.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(minute < 10){
                            editTextNightModeTime.setText(hourOfDay + ":0" + minute);
                        }
                        else{
                            editTextNightModeTime.setText(hourOfDay + ":" + minute);
                        }
                    }
                }, 0,0,false);
                timePickerDialog.show();
            }
        });
        editTextSleepTime.setText(timeSleep);
        editTextSleepTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSleepTime.setText("");
                timePickerDialog = new TimePickerDialog(MainHomeRoom.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(minute < 10){
                            editTextSleepTime.setText(hourOfDay + ":0" + minute);
                        }
                        else{
                            editTextSleepTime.setText(hourOfDay + ":" + minute);
                        }
                    }
                }, 0,0,false);
                timePickerDialog.show();
            }
        });

        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temparature = editTextTemparature.getText().toString().trim();
                timeLight = editTextLightTime.getText().toString().trim();
                timeNightMode = editTextNightModeTime.getText().toString().trim();
                timeSleep = editTextSleepTime.getText().toString().trim();
                Toast.makeText(MainHomeRoom.this, "Điều chỉnh Setting thành công", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
    public void startMqtt(){
        MqttHelper mqttHelper = new MqttHelper(this);
        mqttHelper.mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                Log.w("Debug","Connected");
            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Gson g = new Gson();
                dataReponse s = g.fromJson(mqttMessage.toString(), dataReponse.class);
                // cục DATA ở đây nè, là thằng S đó, nó là class dataReponse
                // hoặc ôgn lấy cái string là mqttMEssage.toString() cũng được
                // bên itemHOmeDevice nhận dc là oke
                Log.e("RECEIVED",mqttMessage.toString());

//                if (s.name.equals("LED") && s.data == 1) {
//                    for (int i =0  ; i<itemHomeDevices.size();i++) {
//                        if (itemHomeDevices.get(i).getDeviceName().equals("Lamp1")){
//                            itemHomeDevices.get(i).setOn(true);
//                            setItemHomeDevices(itemHomeDevices);
//                        }
//
//                    }
//                }
//                else if (s.name.equals("LED") && s.data == 0) {
//                    for (int i =0  ; i<itemHomeDevices.size();i++) {
//                        if (itemHomeDevices.get(i).getDeviceName().equals("Lamp1")){
//                            itemHomeDevices.get(i).setOn(false);
//                            setItemHomeDevices(itemHomeDevices);
//
//                        }
//                    }
//                }
//                else if (s.name.equals("DRV") && s.data == 0) {
//                    for (int i =0  ; i<itemHomeDevices.size();i++) {
//                        if (itemHomeDevices.get(i).getDeviceName().equals("Fan1")){
//                            itemHomeDevices.get(i).setOn(false);
//                            setItemHomeDevices(itemHomeDevices);
//
//                        }
//                    }
//                }
//                else if (s.name.equals("DRV") && s.data == 255) {
//                    for (int i =0  ; i<itemHomeDevices.size();i++) {
//                        if (itemHomeDevices.get(i).getDeviceName().equals("Fan1")){
//                            itemHomeDevices.get(i).setOn(true);
//                            setItemHomeDevices(itemHomeDevices);
//
//                        }
//                    }
//                }
//                else if (s.name.equals("LIGHT") && s.data < 300 && itemHomeDevices.get(0).getParentOn() ) {
//                    for (int i =0  ; i<itemHomeDevices.size();i++) {
//                        if (itemHomeDevices.get(i).getDeviceName().equals("Lamp1")){
//                            itemHomeDevices.get(i).setOn(true);
//                            setItemHomeDevices(itemHomeDevices);
//
//                        }
//                    }
//                }

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }
}