package com.example.myhomiee;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import helpers.MqttHelper;

public class Fragment_Home extends Fragment {

    private RecyclerView recyclerViewHome;
    private List<ItemHome> itemHomes;
    private ItemHomeAdapter itemHomeAdapter;
    private LinearLayout linearHomeListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewHome = (RecyclerView) view.findViewById(R.id.rv_home);
        linearHomeListView = (LinearLayout) view.findViewById(R.id.linearHomeListView);
        itemHomes = new ArrayList<>();
        startMqtt();
        ///////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////// DANH SÁCH PHÒNG Ở ĐÂY, GHI DỮ LIỆU VÀO ĐÂY //////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////

        itemHomes.add(new ItemHome("BedRoom 1", 32, 100, getImage(R.drawable.bedroom), false, 0, 0));

        ///////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////
        itemHomeAdapter = new ItemHomeAdapter(view.getContext(), itemHomes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewHome.setLayoutManager(layoutManager);
        recyclerViewHome.setHasFixedSize(true);
        recyclerViewHome.setAdapter(itemHomeAdapter);
        MainHomeRoom mainHomeRoom = new MainHomeRoom();
        if(mainHomeRoom.isLight()){
            LightMode();
        }else {
            DarkMode();
        }
        return view;
    }
    private byte[] getImage(int image){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),image);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        bitmap.recycle();
        return byteArray;
    }
    public void DarkMode(){
        linearHomeListView.setBackgroundColor(Color.parseColor("#222020"));
    }
    public void LightMode(){
        linearHomeListView.setBackgroundColor(Color.parseColor("#ffffff"));
    }
    public void startMqtt(){
        MqttHelper mqttHelper = new MqttHelper(getContext());
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
                // bên itemHOmeDevice nhận dc là ok

                Log.e("RECEIVE", mqttMessage.toString());
                ItemHome itemHome = itemHomes.get(0);
                itemHomes.clear();
                if(s.name.equals("LED")){
                    itemHomes.add(new ItemHome("BedRoom 1", 32, 100, getImage(R.drawable.bedroom), itemHome.getOn(), s.data, itemHome.getNumberOfFan()));
                }else {
                    itemHomes.add(new ItemHome("BedRoom 1", 32, 100, getImage(R.drawable.bedroom), itemHome.getOn(),itemHome.getNumberOfLamp(), s.data));
                }
                itemHomeAdapter = new ItemHomeAdapter(getContext(), itemHomes);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerViewHome.setLayoutManager(layoutManager);
                recyclerViewHome.setHasFixedSize(true);
                recyclerViewHome.setAdapter(itemHomeAdapter);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }


}
