package com.spookyrobotics.botStructs;


import java.util.ArrayList;

public class InstructorCommand {
    private static final long MASK_32BIT = 0xffffffff;
    private boolean valid;
    private long startEffectorsIndex;
    private long endEffectorsIndex;
    private long startSensorsIndex;
    private long endSensorsIndex;
    private long effectorsMask;

    public long getSensorsMask() {
        return sensorsMask;
    }

    public void setSensorsMask(long sensorsMask) {
        this.sensorsMask = sensorsMask & MASK_32BIT;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public long getStartEffectorsIndex() {
        return startEffectorsIndex;
    }

    public void setStartEffectorsIndex(long startEffectorsIndex) {
        this.startEffectorsIndex = startEffectorsIndex & MASK_32BIT;
    }

    public long getEndEffectorsIndex() {
        return endEffectorsIndex;
    }

    public void setEndEffectorsIndex(long endEffectorsIndex) {
        this.endEffectorsIndex = endEffectorsIndex & MASK_32BIT;
    }

    public long getStartSensorsIndex() {
        return startSensorsIndex;
    }

    public void setStartSensorsIndex(long startSensorsIndex) {
        this.startSensorsIndex = startSensorsIndex & MASK_32BIT;
    }

    public long getEndSensorsIndex() {
        return endSensorsIndex;
    }

    public void setEndSensorsIndex(long endSensorsIndex) {
        this.endSensorsIndex = endSensorsIndex & MASK_32BIT;
    }

    public long getEffectorsMask() {
        return effectorsMask;
    }

    public void setEffectorsMask(long effectorsMask) {
        this.effectorsMask = effectorsMask;
    }

    private long sensorsMask;

    public byte[] flatten(){
        ArrayList<Byte> result = new ArrayList<>();
        add32Bits(result, startEffectorsIndex);
        add32Bits(result, endEffectorsIndex);
        add32Bits(result, startSensorsIndex);
        add32Bits(result,endSensorsIndex);
        add32Bits(result,effectorsMask);
        byte[] value = new byte[result.size()];
        for(int index = 0; index < value.length; index++){
            value[index] = result.get(index);
        }
        return value;
    }

    public void inflate(byte[] value){
        startEffectorsIndex = inflate32Bits(value);
        value = remove32bits(value);
        endEffectorsIndex = inflate32Bits(value);
        value = remove32bits(value);
        startSensorsIndex = inflate32Bits(value);
        value = remove32bits(value);
        endSensorsIndex = inflate32Bits(value);
        value = remove32bits(value);
        effectorsMask = inflate32Bits(value);
        value = remove32bits(value);
    }

    private byte[] remove32bits(byte[] value) {
        byte[] result = new byte[value.length-4];
        for(int index = 4; index < value.length; index++){
            result[index-4] = value[index];
        }
        return result;
    }

    private static void add32Bits(ArrayList<Byte> list, long startEffectorsIndex) {
        list.add((byte) (startEffectorsIndex >> 24));
        list.add((byte) (startEffectorsIndex >> 16));
        list.add((byte) (startEffectorsIndex >> 8));
        list.add((byte) startEffectorsIndex);
    }

    private static long inflate32Bits(byte[] list) {
        long result = 0;
        result |= list[0] << 24;
        result |= list[1] << 16;
        result |= list[2] << 8;
        result |= list[3];
        return result;
    }

    public static Byte[] transmitPrelude() {
        return null;
    }

    public static Byte[] transmitPostlude(InstructorCommand command) {
        return null;
    }
}
