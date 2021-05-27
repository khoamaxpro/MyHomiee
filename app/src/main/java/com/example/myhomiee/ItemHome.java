package com.example.myhomiee;

import java.util.List;

public class ItemHome {

    private String roomName;
    private int roomTemperature;
    private int roomBrightness;
    private byte[] roomImage;
    private boolean isOn;
    private List<ItemHomeDevice> LampList;
    private List<ItemHomeDevice> FanList;

    public ItemHome(String roomName, int roomTemperature, int roomBrightness, byte[] roomImage, boolean isOn, List<ItemHomeDevice> lampList, List<ItemHomeDevice> fanList) {
        this.roomName = roomName;
        this.roomTemperature = roomTemperature;
        this.roomBrightness = roomBrightness;
        this.roomImage = roomImage;
        this.isOn = isOn;
        LampList = lampList;
        FanList = fanList;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomTemperature() {
        return roomTemperature;
    }

    public void setRoomTemperature(int roomTemperature) {
        this.roomTemperature = roomTemperature;
    }

    public int getRoomBrightness() {
        return roomBrightness;
    }

    public void setRoomBrightness(int roomBrightness) {
        this.roomBrightness = roomBrightness;
    }

    public byte[] getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(byte[] roomImage) {
        this.roomImage = roomImage;
    }

    public boolean getOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public List<ItemHomeDevice> getLampList() {
        return LampList;
    }

    public void setLampList(List<ItemHomeDevice> lampList) {
        LampList = lampList;
    }

    public List<ItemHomeDevice> getFanList() {
        return FanList;
    }

    public void setFanList(List<ItemHomeDevice> fanList) {
        FanList = fanList;
    }
}
