package com.spookyrobotics.botStructs;

public class RemoteEffector {
    private static final int MASK_32BIT = 0xffffffff;
    byte[] name = new byte[12];
    long[] pinNumbers;

    public long[] getPinNumbers() {
        return pinNumbers;
    }

    public void setPinNumbers(long[] update) {
        int length = update.length < pinNumbers.length ? update.length : pinNumbers.length;
        for(int index = 0; index<length; index++){
            pinNumbers[index] = update[index] & MASK_32BIT;
        }
        for(int index = length; length < pinNumbers.length; index++){
            pinNumbers[index] = 0;
        }
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
