package com.spookyrobotics.botStructs;

public class RemoteSensor {
    private static final int MASK_32BIT = 0xffffffff;
    byte[] name = new byte[12];
    int pinNumber;
    long sensorMapBit;


    public long getSensorMapBit() {
        return sensorMapBit;
    }

    public void setSensorMapBit(long sensorMapBit) {
        this.sensorMapBit = sensorMapBit & MASK_32BIT;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public byte[] getName() {
        return name;
    }

    public void setName(byte[] update) {
        int length = update.length < name.length ? update.length : name.length;
        for(int index = 0; index<length; index++){
            name[index] = update[index];
        }
        for(int index = length; length < name.length; index++){
            name[index] = 0;
        }
    }

}
