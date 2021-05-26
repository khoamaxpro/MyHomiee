package com.frost.mqtttutorial;

import java.util.List;

public class ItemRoom {
    private String RoomName;
    private String DeviceName;
    private int numberOfDevice;

    public ItemRoom(String roomName, String deviceName, int numberOfDevice) {
        RoomName = roomName;
        DeviceName = deviceName;
        this.numberOfDevice = numberOfDevice;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public int getNumberOfDevice() {
        return numberOfDevice;
    }

    public void setNumberOfDevice(int numberOfDevice) {
        this.numberOfDevice = numberOfDevice;
    }
}
