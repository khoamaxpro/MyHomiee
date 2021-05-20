package com.example.myhomiee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemRoomDeviceAdapter extends RecyclerView.Adapter<ItemRoomDeviceAdapter.DataViewHolder> {

    private Context context;
    private List<String> itemRoomDevices;

    public ItemRoomDeviceAdapter(Context context, List<String> itemRoomDevices) {
        this.context = context;
        this.itemRoomDevices = itemRoomDevices;
    }

    @Override
    public int getItemCount() {
        return itemRoomDevices == null ? 0 : itemRoomDevices.size();
    }

    @Override
    public ItemRoomDeviceAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room_device, parent, false);
        return new DataViewHolder(itemView);
    }

    // MỌI THỨ DIỄN RA TRONG NÀY
    @Override
    public void onBindViewHolder(ItemRoomDeviceAdapter.DataViewHolder holder, int position) {
        String itemRoomDevice= itemRoomDevices.get(position);
        holder.txtRoomDeviceName.setText(itemRoomDevice);
    }

    /**
     * Data ViewHolder class.
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView txtRoomDeviceName;
        public DataViewHolder(View itemView) {
            super(itemView);
            txtRoomDeviceName = (TextView) itemView.findViewById(R.id.txtRoomNameLampFanDevice);
        }
    }
}
