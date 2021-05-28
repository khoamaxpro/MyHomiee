package com.example.myhomiee;

import java.util.List;

public class ItemHome {

    private String roomName;
    private int roomTemperature;
    private int roomBrightness;
    private byte[] roomImage;
    private boolean isOn;
    private int numberOfLamp;
    private int numberOfFan;

    public ItemHome(String roomName, int roomTemperature, int roomBrightness, byte[] roomImage, boolean isOn, int numberOfLamp, int numberOfFan) {
        this.roomName = roomName;
        this.roomTemperature = roomTemperature;
        this.roomBrightness = roomBrightness;
        this.roomImage = roomImage;
        this.isOn = isOn;
        this.numberOfLamp = numberOfLamp;
        this.numberOfFan = numberOfFan;
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

    public int getNumberOfLamp() {
        return numberOfLamp;
    }

    public void setNumberOfLamp(int numberOfLamp) {
        this.numberOfLamp = numberOfLamp;
    }

    public int getNumberOfFan() {
        return numberOfFan;
    }

    public void setNumberOfFan(int numberOfFan) {
        this.numberOfFan = numberOfFan;
    }
}
