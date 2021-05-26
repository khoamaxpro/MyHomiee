package com.frost.mqtttutorial;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class Fragment_Home extends Fragment {

    private RecyclerView recyclerViewHome;
    private List<ItemHome> itemHomes;
    private ItemHomeAdapter itemHomeAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewHome = (RecyclerView) view.findViewById(R.id.rv_home);
        itemHomes = new ArrayList<>();
        ///////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////// DANH SÁCH PHÒNG Ở ĐÂY, GHI DỮ LIỆU VÀO ĐÂY //////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////

        itemHomes.add(new ItemHome("BedRoom 1", 32, 100, getImage(R.drawable.bedroom), false, 3, 2));
        itemHomes.add(new ItemHome("BedRoom 2", 32, 100, getImage(R.drawable.bedroom), false, 3, 2));
        itemHomes.add(new ItemHome("Living Room", 35, 200, getImage(R.drawable.livingroom), false, 4,3));

        ///////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////
        itemHomeAdapter = new ItemHomeAdapter(view.getContext(), itemHomes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewHome.setLayoutManager(layoutManager);
        recyclerViewHome.setHasFixedSize(true);
        recyclerViewHome.setAdapter(itemHomeAdapter);
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
}
