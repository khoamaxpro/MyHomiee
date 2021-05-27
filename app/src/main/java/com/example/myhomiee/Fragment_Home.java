package com.example.myhomiee;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

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
        List<ItemHomeDevice> lampList1 = new ArrayList<>();
        List<ItemHomeDevice> lampList2 = new ArrayList<>();
        List<ItemHomeDevice> fanList1 = new ArrayList<>();
        List<ItemHomeDevice> fanList2 = new ArrayList<>();
        for (int i = 0;i < 3;i++){
            lampList1.add(new ItemHomeDevice("Lamp" + String.valueOf(i+1), true, false));
        }
        for (int i = 0;i < 4;i++){
            lampList2.add(new ItemHomeDevice("Lamp" + String.valueOf(i+1), true, false));
        }
        for (int i = 0;i < 4;i++){
            fanList1.add(new ItemHomeDevice("Fan" + String.valueOf(i+1), true, false));
        }
        for (int i = 0;i < 5;i++){
            fanList2.add(new ItemHomeDevice("Fan" + String.valueOf(i+1), true, false));
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////// DANH SÁCH PHÒNG Ở ĐÂY, GHI DỮ LIỆU VÀO ĐÂY //////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////

        itemHomes.add(new ItemHome("BedRoom 1", 32, 100, getImage(R.drawable.bedroom), false, lampList1, fanList1));
        itemHomes.add(new ItemHome("BedRoom 2", 32, 100, getImage(R.drawable.bedroom), false, lampList1, fanList2));
        itemHomes.add(new ItemHome("Living Room", 35, 200, getImage(R.drawable.livingroom), false, lampList2,fanList2));

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
}
