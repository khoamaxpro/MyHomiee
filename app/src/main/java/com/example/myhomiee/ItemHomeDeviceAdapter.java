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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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

    // MỌI THỨ DIỄN RA TRONG NÀY
    @Override
    public void onBindViewHolder(ItemHomeDeviceAdapter.DataViewHolder holder, int position) {
        ItemHomeDevice itemHomeDevice = itemHomeDevices.get(position);
        holder.txtDeviceName.setText(itemHomeDevice.getDeviceName());
        holder.switchDevice.setChecked(itemHomeDevice.getOn());
        if(itemHomeDevice.getParentOn() == true){
            holder.txtDeviceName.setTextColor(Color.parseColor("#A4A4A4"));
            holder.switchDevice.setTrackResource(R.drawable.track2);

            if(itemHomeDevice.getDeviceName().startsWith("L")){
                holder.imageViewDeviceImage.setBackgroundResource(R.drawable.lamp_off);
            }else {
                holder.imageViewDeviceImage.setBackgroundResource(R.drawable.fan_off);
            }
        }
        else{
            holder.txtDeviceName.setTextColor(Color.BLACK);
            holder.switchDevice.setTrackResource(R.drawable.track1);
            if(itemHomeDevice.getDeviceName().startsWith("L")){
                holder.imageViewDeviceImage.setBackgroundResource(R.drawable.lamp_on);
            }else {
                holder.imageViewDeviceImage.setBackgroundResource(R.drawable.fan_on);
            }
        }

        holder.imageViewDeviceImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                itemHomeDevice.setOn(!itemHomeDevice.getOn());
                itemHomeDevices.set(position, itemHomeDevice);
//                Toast.makeText(context, itemHomeDevice.getDeviceName(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, itemHomeDevice.getRoomName(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, String.valueOf(itemHomeDevice.getOn()), Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, String.valueOf(itemHomeDevice.getParentOn()), Toast.LENGTH_SHORT).show();
                setItemHomeDevices(itemHomeDevices);
                return false;
            }
        });

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

