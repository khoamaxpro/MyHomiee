package com.frost.mqtttutorial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;



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
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new DataViewHolder(itemView);
    }

    // MỌI THỨ DIỄN RA TRONG NÀY
    @Override
    public void onBindViewHolder(final DataViewHolder holder, int position) {
        ItemRoom itemRoom= itemRooms.get(position);
        holder.txtRoomName.setText(itemRoom.getRoomName());
        List<String> roomDevices = new ArrayList<>();
        for(int i = 0;i < itemRoom.getNumberOfDevice();i++){
            roomDevices.add(itemRoom.getDeviceName() + String.valueOf(i+1));
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

    }

    /**
     * Data ViewHolder class.
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView txtRoomName;
        private RecyclerView recyclerView;
        private ItemRoomDeviceAdapter itemRoomDeviceAdapter;
        private LinearLayout linearLayoutRoomDevice;
        private View view;
        private Context context;
        private LinearLayoutManager layoutManager_Room;
        public DataViewHolder(View itemView) {
            super(itemView);
            txtRoomName = (TextView) itemView.findViewById(R.id.txtRoomNameLampFan);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_room);
            linearLayoutRoomDevice = (LinearLayout) itemView.findViewById(R.id.linearRoomDevice);
            view = itemView;
            context = itemView.getContext();
            layoutManager_Room = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);

        }
    }

}

