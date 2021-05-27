package com.example.myhomiee;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemRoomDeviceAdapter extends RecyclerView.Adapter<ItemRoomDeviceAdapter.DataViewHolder> {

    private Context context;
    private List<ItemRoomDevice> itemRoomDevices;

    public ItemRoomDeviceAdapter(Context context, List<ItemRoomDevice> itemRoomDevices) {
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
        String itemRoomDevice = itemRoomDevices.get(position).getDeviceName();
        holder.txtRoomDeviceName.setText(itemRoomDevice);
        holder.txtRoomDeviceName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.linearLayoutGraph.getVisibility() == holder.view.VISIBLE){
                    holder.linearLayoutGraph.setVisibility(holder.view.GONE);
                }
                else {
                    holder.linearLayoutGraph.setVisibility(holder.view.VISIBLE);
                }
            }
        });
        holder.txtHourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.txtHourButton.setTextColor(Color.parseColor("#F83737"));
                holder.txtDayButton.setTextColor(Color.parseColor("#929292"));
                holder.txtWeekButton.setTextColor(Color.parseColor("#929292"));
            }
        });
        holder.txtDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.txtHourButton.setTextColor(Color.parseColor("#929292"));
                holder.txtDayButton.setTextColor(Color.parseColor("#F83737"));
                holder.txtWeekButton.setTextColor(Color.parseColor("#929292"));
            }
        });
        holder.txtWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.txtHourButton.setTextColor(Color.parseColor("#929292"));
                holder.txtDayButton.setTextColor(Color.parseColor("#929292"));
                holder.txtWeekButton.setTextColor(Color.parseColor("#F83737"));
            }
        });
        holder.imageViewGraph.setBackgroundResource(R.drawable.graph);
        MainHomeRoom mainHomeRoom = new MainHomeRoom();
        if(mainHomeRoom.isLight()){
            holder.linearLayoutDeviceList.setBackgroundColor(Color.parseColor("#FCDADA"));
            holder.txtRoomDeviceName.setTextColor(Color.parseColor("#505050"));
            holder.imageViewDeviceList.setBackgroundResource(R.drawable.graph_small);
        }else {
            holder.linearLayoutDeviceList.setBackgroundColor(Color.parseColor("#990000"));
            holder.txtRoomDeviceName.setTextColor(Color.parseColor("#FFFFFF"));
            holder.imageViewDeviceList.setBackgroundResource(R.drawable.graph_small_light);
        }
    }

    /**
     * Data ViewHolder class.
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView txtRoomDeviceName, txtHourButton, txtDayButton, txtWeekButton;
        private ImageView imageViewGraph, imageViewDeviceList;
        private LinearLayout linearLayoutDeviceList, linearLayoutGraph;
        private View view;
        public DataViewHolder(View itemView) {
            super(itemView);
            txtRoomDeviceName = (TextView) itemView.findViewById(R.id.txtRoomNameLampFanDevice);
            txtHourButton = (TextView) itemView.findViewById(R.id.txtHourButton);
            txtDayButton = (TextView) itemView.findViewById(R.id.txtDayButton);
            txtWeekButton = (TextView) itemView.findViewById(R.id.txtWeekButton);
            imageViewGraph = (ImageView) itemView.findViewById(R.id.imgGraph);
            imageViewDeviceList = (ImageView) itemView.findViewById(R.id.imgDeviceList);
            linearLayoutDeviceList = (LinearLayout) itemView.findViewById(R.id.linearDeviceList);
            linearLayoutGraph = (LinearLayout) itemView.findViewById(R.id.linearDeviceGraph);
            view = itemView;
        }
    }
}
