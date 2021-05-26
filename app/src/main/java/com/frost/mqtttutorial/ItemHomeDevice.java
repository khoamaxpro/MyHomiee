package com.frost.mqtttutorial;

public class ItemHomeDevice {
    private String deviceName;
    private boolean isOn;
    private boolean isParentOn;

    public ItemHomeDevice(String deviceName, boolean isOn, boolean isParentOn) {
        this.deviceName = deviceName;
        this.isOn = isOn;
        this.isParentOn = isParentOn;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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
