package com.example.myhomiee;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    public void LightMode(){
        linearLampListView.setBackgroundColor(Color.parseColor("#ffffff"));
    }
}
