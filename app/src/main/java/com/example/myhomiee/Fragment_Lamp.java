package com.example.myhomiee;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Lamp extends Fragment {

    private RecyclerView recyclerViewRoomLamp;
    private List<ItemRoom> itemRooms;
    private ItemRoomAdapter itemRoomAdapter;
    private static LinearLayout linearLampListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lamp, container, false);
        recyclerViewRoomLamp = (RecyclerView) view.findViewById(R.id.rv_lamp);
        linearLampListView = (LinearLayout) view.findViewById(R.id.linearLampListView);
        itemRooms = new ArrayList<>();
        itemRooms.add(new ItemRoom("BedRoom 1","Lamp", 3));
        itemRooms.add(new ItemRoom("Living Room", "Lamp",4));
        itemRoomAdapter = new ItemRoomAdapter(view.getContext(), itemRooms);
        LinearLayoutManager layoutManager_lamp = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewRoomLamp.setLayoutManager(layoutManager_lamp);
        recyclerViewRoomLamp.setHasFixedSize(true);
        recyclerViewRoomLamp.setAdapter(itemRoomAdapter);
        MainHomeRoom mainHomeRoom = new MainHomeRoom();
        if(mainHomeRoom.isLight()){
            LightMode();
        }else {
            DarkMode();
        }
        return view;
    }
    public void DarkMode(){
        linearLampListView.setBackgroundColor(Color.parseColor("#222020"));
    }
    public void LightMode(){ linearLampListView.setBackgroundColor(Color.parseColor("#ffffff"));}
}