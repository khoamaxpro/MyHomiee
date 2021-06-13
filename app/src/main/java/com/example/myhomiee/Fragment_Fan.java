package com.example.myhomiee;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Fan extends Fragment {
    private RecyclerView recyclerViewRoomFan;
    private List<ItemRoom> itemRooms;
    private ItemRoomAdapter itemRoomAdapter;
    private static LinearLayout linearFanListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fan, container, false);
        recyclerViewRoomFan = (RecyclerView) view.findViewById(R.id.rv_fan);
        linearFanListView = (LinearLayout) view.findViewById(R.id.linearFanListView);
        itemRooms = new ArrayList<>();
        itemRooms.add(new ItemRoom("BedRoom 1","Fan", 3));
        itemRooms.add(new ItemRoom("Living Room", "Fan",4));
        itemRoomAdapter = new ItemRoomAdapter(view.getContext(), itemRooms);
        LinearLayoutManager layoutManager_fan = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewRoomFan.setLayoutManager(layoutManager_fan);
        recyclerViewRoomFan.setHasFixedSize(true);
        recyclerViewRoomFan.setAdapter(itemRoomAdapter);
        MainHomeRoom mainHomeRoom = new MainHomeRoom();
        if(mainHomeRoom.isLight()){
            LightMode();
        }else {
            DarkMode();
        }
        return view;
    }
    public void DarkMode(){
        linearFanListView.setBackgroundColor(Color.parseColor("#222020"));
    }
    public void LightMode(){
        linearFanListView.setBackgroundColor(Color.parseColor("#ffffff"));
    }

}
