package com.example.myhomiee;

public class ItemHomeDevice {
    private String deviceName;
    private String roomName;
    private boolean isOn;
    private boolean isParentOn;

    public ItemHomeDevice(String deviceName, String roomName, boolean isOn, boolean isParentOn) {
        this.deviceName = deviceName;
        this.roomName = roomName;
        this.isOn = isOn;
        this.isParentOn = isParentOn;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean getOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public boolean getParentOn() {
        return isParentOn;
    }

    public void setParentOn(boolean parentOn) {
        isParentOn = parentOn;
    }
}
