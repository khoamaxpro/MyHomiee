package com.example.myhomiee;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemRoomAdapter extends RecyclerView.Adapter<ItemRoomAdapter.DataViewHolder> {

    private Context context;
    private List<ItemRoom> itemRooms;

    public ItemRoomAdapter(Context context, List<ItemRoom> itemRooms) {
        this.context = context;
        this.itemRooms = itemRooms;
    }

    @Override
    public int getItemCount() {
        return itemRooms == null ? 0 : itemRooms.size();
    }

    @Override
    public ItemRoomAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new DataViewHolder(itemView);
    }

    // MỌI THỨ DIỄN RA TRONG NÀY
    @Override
    public void onBindViewHolder(ItemRoomAdapter.DataViewHolder holder, int position) {
        ItemRoom itemRoom= itemRooms.get(position);
        holder.txtRoomName.setText(itemRoom.getRoomName());
        List<ItemRoomDevice> roomDevices = new ArrayList<>();
        for(int i = 0;i < itemRoom.getNumberOfDevice();i++){
            roomDevices.add(new ItemRoomDevice(itemRoom.getDeviceName() + String.valueOf(i+1)));
        }
        holder.itemRoomDeviceAdapter = new ItemRoomDeviceAdapter(holder.context, roomDevices);
        holder.recyclerView.setLayoutManager(holder.layoutManager_Room);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setAdapter(holder.itemRoomDeviceAdapter);

        holder.txtRoomName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(holder.linearLayoutRoomDevice.getVisibility() == holder.view.VISIBLE){
                        holder.linearLayoutRoomDevice.setVisibility(holder.view.GONE);
                    }
                    else {
                        holder.linearLayoutRoomDevice.setVisibility(holder.view.VISIBLE);
                    }
                }
                return false;
            }
        });
        MainHomeRoom mainHomeRoom = new MainHomeRoom();
        if(mainHomeRoom.isLight()){
            holder.linearLayoutRoomName.setBackgroundColor(Color.parseColor("#F5F5F5"));
            holder.linearLayoutRoomDevice.setBackgroundColor(Color.parseColor("#F9F9F9"));
        }else {
            holder.linearLayoutRoomName.setBackgroundColor(Color.parseColor("#ABABAB"));
            holder.linearLayoutRoomDevice.setBackgroundColor(Color.parseColor("#222020"));
        }
    }

    /**
     * Data ViewHolder class.
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView txtRoomName;
        private RecyclerView recyclerView;
        private ItemRoomDeviceAdapter itemRoomDeviceAdapter;
        private LinearLayout linearLayoutRoomName, linearLayoutRoomDevice;
        private View view;
        private Context context;
        private LinearLayoutManager layoutManager_Room;
        public DataViewHolder(View itemView) {
            super(itemView);
            txtRoomName = (TextView) itemView.findViewById(R.id.txtRoomNameLampFan);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_room);
            linearLayoutRoomName = (LinearLayout) itemView.findViewById(R.id.linearRoomName);
            linearLayoutRoomDevice = (LinearLayout) itemView.findViewById(R.id.linearRoomDevice);
            view = itemView;
            context = itemView.getContext();
            layoutManager_Room = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false);

        }
    }

}

