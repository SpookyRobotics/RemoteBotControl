package com.spookyrobotics.botStructs;


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

}
