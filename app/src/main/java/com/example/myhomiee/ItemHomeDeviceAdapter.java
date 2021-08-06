package com.example.myhomiee;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

import java.util.List;

import helpers.MqttHelper;

public class ItemHomeDeviceAdapter extends RecyclerView.Adapter<ItemHomeDeviceAdapter.DataViewHolder> {

    private Context context;
    private List<ItemHomeDevice> itemHomeDevices;

    public ItemHomeDeviceAdapter(Context context, List<ItemHomeDevice> itemHomeDevices) {
        this.context = context;
        this.itemHomeDevices = itemHomeDevices;
    }

    public void setItemHomeDevices(List<ItemHomeDevice> itemHomeDevices) {
        this.itemHomeDevices = itemHomeDevices;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemHomeDevices == null ? 0 : itemHomeDevices.size();
    }

    @Override
    public ItemHomeDeviceAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case 1:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_on, parent, false);
                break;
            case 2:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_off, parent, false);
                break;
            default:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_on, parent, false);
                break;
        }
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHomeDeviceAdapter.DataViewHolder holder, int position) {
        ItemHomeDevice itemHomeDevice = itemHomeDevices.get(position);
        holder.txtDeviceName.setText(itemHomeDevice.getDeviceName());
        holder.switchDevice.setChecked(itemHomeDevice.getOn());
        int position_2 = position;
        if (itemHomeDevice.getParentOn()) {
//            startMqtt();
            holder.txtDeviceName.setTextColor(Color.parseColor("#A4A4A4"));
            holder.switchDevice.setTrackResource(R.drawable.track2);

            if (itemHomeDevice.getDeviceName().startsWith("L")) {
                holder.imageViewDeviceImage.setBackgroundResource(R.drawable.lamp_off);
            } else {
                holder.imageViewDeviceImage.setBackgroundResource(R.drawable.fan_off);
            }
        } else {
            holder.txtDeviceName.setTextColor(Color.BLACK);
            holder.switchDevice.setTrackResource(R.drawable.track1);
            if (itemHomeDevice.getDeviceName().startsWith("L")) {
                holder.imageViewDeviceImage.setBackgroundResource(R.drawable.lamp_on);
            } else {
                holder.imageViewDeviceImage.setBackgroundResource(R.drawable.fan_on);
            }
            holder.imageViewDeviceImage.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    itemHomeDevice.setOn(!itemHomeDevice.getOn());
                    itemHomeDevices.set(position_2, itemHomeDevice);
                    setItemHomeDevices(itemHomeDevices);


                    return false;
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        // Cài đặt kiểu item view type cho từng phần tử, nếu có giới tính là nam thì trả về 1, nữ thì trả về 2.
        if (itemHomeDevices.get(position).getOn()) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * Data ViewHolder class.
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView txtDeviceName;
        private ImageView imageViewDeviceImage;
        private Switch switchDevice;

        public DataViewHolder(View itemView) {
            super(itemView);
            txtDeviceName = (TextView) itemView.findViewById(R.id.txtDeviceName);
            imageViewDeviceImage = (ImageView) itemView.findViewById(R.id.imageViewDevice);
            switchDevice = (Switch) itemView.findViewById(R.id.switchDevice);
        }
    }
}

class dataReponse {
    public String id;
    public int data;
    public String name;
    public String unit;
}

