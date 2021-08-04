package com.example.myhomiee;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ItemHomeAdapter extends RecyclerView.Adapter<ItemHomeAdapter.DataViewHolder> {

    private Context context;
    private List<ItemHome> itemHomes;

    public ItemHomeAdapter(Context context, List<ItemHome> itemHomes) {
        this.context = context;
        this.itemHomes = itemHomes;
    }

    public void setItemHomes(List<ItemHome> itemHomes) {
        this.itemHomes = itemHomes;
        notifyDataSetChanged();
    }

    public List<ItemHome> getItemHomes() {
        return itemHomes;
    }

    @Override
    public int getItemCount() {
        return itemHomes == null ? 0 : itemHomes.size();
    }

    @Override
    public ItemHomeAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_off, parent, false);
        return new DataViewHolder(itemView);
    }

    // MỌI THỨ DIỄN RA TRONG NÀY
    @Override
    public void onBindViewHolder(ItemHomeAdapter.DataViewHolder holder, int position) {
        ItemHome itemHome = itemHomes.get(position);
        holder.txtRoomName.setText(itemHome.getRoomName());
        holder.txtRoomTemperature.setText(": " + String.valueOf(itemHome.getRoomTemperature()) + "°C");
        holder.txtRoomBrightness.setText(": " + String.valueOf(itemHome.getRoomBrightness()) + " Lux");
        int position_2 = position;
        byte[] data = itemHome.getRoomImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        holder.imageViewRoomImage.setImageBitmap(bmp);
        if(itemHomes.get(position).getOn()){
            holder.txtRoomState.setText("Working");
            holder.txtRoomState.setTextColor(Color.parseColor("#159500"));
            holder.imageViewRoomState.setBackgroundResource(R.drawable.cycle_on);
        }
        else {
            holder.txtRoomState.setText("OFF");
            holder.txtRoomState.setTextColor(Color.parseColor("#535353"));
            holder.imageViewRoomState.setBackgroundResource(R.drawable.cycle_off);
        }
        holder.txtRoomName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(holder.linearLayoutDevice.getVisibility() == holder.view.VISIBLE){
                        holder.linearLayoutDevice.setVisibility(holder.view.GONE);
                    }
                    else {
                        holder.linearLayoutDevice.setVisibility(holder.view.VISIBLE);
                    }
                }
                return false;
            }
        });
        holder.switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for(int i = 0;i < holder.itemLamps.size();i++){
                    holder.itemLamps.get(i).setParentOn(isChecked);
                }
                for(int i = 0;i < holder.itemFans.size();i++){
                    holder.itemFans.get(i).setParentOn(isChecked);
                }
                holder.itemHomeLampAdapter = new ItemHomeDeviceAdapter(holder.context, holder.itemLamps);
                holder.recyclerViewLamp.setLayoutManager(holder.layoutManagerLamp);
                holder.recyclerViewLamp.setHasFixedSize(true);
                holder.recyclerViewLamp.setAdapter(holder.itemHomeLampAdapter);

                holder.itemHomeFanAdapter = new ItemHomeDeviceAdapter(context, holder.itemFans);
                holder.recyclerViewFan.setLayoutManager(holder.layoutManagerFan);
                holder.recyclerViewFan.setHasFixedSize(true);
                holder.recyclerViewFan.setAdapter(holder.itemHomeFanAdapter);
                itemHomes.get(position_2).setOn(isChecked);
                setItemHomes(itemHomes);
            }
        });
        if(holder.itemLamps.isEmpty() && holder.itemFans.isEmpty())
        {
            for(int i = 0;i < itemHome.getNumberOfLamp();i++){
                holder.itemLamps.add(new ItemHomeDevice("Lamp" + String.valueOf(i+1), itemHome.getRoomName(), false, itemHome.getOn()));
            }
            for(int i = 0;i < itemHome.getNumberOfFan();i++){
                holder.itemFans.add(new ItemHomeDevice("Fan" + String.valueOf(i+1),  itemHome.getRoomName(),false, itemHome.getOn()));
            }
            holder.itemHomeLampAdapter = new ItemHomeDeviceAdapter(holder.context, holder.itemLamps);
            holder.recyclerViewLamp.setLayoutManager(holder.layoutManagerLamp);
            holder.recyclerViewLamp.setHasFixedSize(true);
            holder.recyclerViewLamp.setAdapter(holder.itemHomeLampAdapter);

            holder.itemHomeFanAdapter = new ItemHomeDeviceAdapter(context, holder.itemFans);
            holder.recyclerViewFan.setLayoutManager(holder.layoutManagerFan);
            holder.recyclerViewFan.setHasFixedSize(true);
            holder.recyclerViewFan.setAdapter(holder.itemHomeFanAdapter);
        }
    }

    /**
     * Data ViewHolder class.
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView txtRoomName, txtRoomTemperature, txtRoomBrightness, txtRoomState;
        private ImageView imageViewRoomImage, imageViewRoomState;
        private SwitchCompat switchCompat;
        private RecyclerView recyclerViewLamp, recyclerViewFan;
        private ItemHomeDeviceAdapter itemHomeLampAdapter, itemHomeFanAdapter;
        private LinearLayout linearLayoutHomeName, linearLayoutDevice;
        private byte[] fanOffImage, fanOnImage, lampOffImage, lampOnImage;
        private View view;
        private Context context;
        LinearLayoutManager layoutManagerLamp, layoutManagerFan;
        private List<ItemHomeDevice> itemLamps, itemFans;
        public DataViewHolder(View itemView) {
            super(itemView);
            txtRoomName = (TextView) itemView.findViewById(R.id.txtRoomName);
            txtRoomState = (TextView) itemView.findViewById(R.id.txtRoomState);
            txtRoomTemperature = (TextView) itemView.findViewById(R.id.txtRoomTemperature);
            txtRoomBrightness = (TextView) itemView.findViewById(R.id.txtRoomBrightness);
            imageViewRoomImage = (ImageView) itemView.findViewById(R.id.imageViewRoomImage);
            imageViewRoomState = (ImageView) itemView.findViewById(R.id.imgViewRoomState);
            switchCompat = (SwitchCompat) itemView.findViewById(R.id.switchRoom);
            recyclerViewLamp = (RecyclerView) itemView.findViewById(R.id.rv_homeLamp);
            recyclerViewFan = (RecyclerView) itemView.findViewById(R.id.rv_homeFan);
            linearLayoutHomeName = (LinearLayout) itemView.findViewById(R.id.linearHomeName);
            linearLayoutDevice = (LinearLayout) itemView.findViewById(R.id.linearDevice);
            view = itemView;
            context = itemView.getContext();
            itemLamps = new ArrayList<>();
            itemFans = new ArrayList<>();
            layoutManagerLamp = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            layoutManagerFan = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            ///////////////////////////// HÌNH ẢNH ///////////////////////////
            Bitmap bitmap = BitmapFactory.decodeResource(itemView.getResources(),R.drawable.fan_off);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            fanOffImage = stream.toByteArray();
            bitmap.recycle();
            bitmap = BitmapFactory.decodeResource(itemView.getResources(),R.drawable.fan_on);
            stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            fanOnImage = stream.toByteArray();
            bitmap.recycle();
            bitmap = BitmapFactory.decodeResource(itemView.getResources(),R.drawable.lamp_off);
            stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            lampOffImage = stream.toByteArray();
            bitmap.recycle();
            bitmap = BitmapFactory.decodeResource(itemView.getResources(),R.drawable.lamp_on);
            stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            lampOnImage = stream.toByteArray();
            bitmap.recycle();
            ///////////////////////////////////////////////////////////////////////



        }
    }
}
