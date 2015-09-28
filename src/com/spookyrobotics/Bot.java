package com.spookyrobotics;

import com.spookyrobotics.botStructs.InstructorCommand;
import com.spookyrobotics.botStructs.RemoteEffector;
import com.spookyrobotics.botStructs.RemoteSensor;
import com.spookyrobotics.communication.SerialCommunication;

import java.util.ArrayList;

public class Bot implements BotInterface {
    private static int nextId = 0;

    public static Bot create(String port){
        Bot b = new Bot(nextId,port);
        nextId += 1;
        return b;
    }

    private final int id;
    private ArrayList<RemoteEffector> effectors;
    private ArrayList<RemoteSensor> sensors;
    private ArrayList<Integer> data;
    private final String portName;
    private SerialCommunication serialCommunication;

    private Bot(int id, String port){
        this.id = id;
        this.portName = port;
        serialCommunication = new SerialCommunication(portName);
    }


    @Override
    public ArrayList<RemoteEffector> getEffectors() {
        return effectors;
    }

    @Override
    public ArrayList<RemoteSensor> getSensors() {
        return sensors;
    }

    @Override
    public boolean isBotAttached() {
        return serialCommunication.handshake();
    }

    @Override
    public void sendInstructorCommand(InstructorCommand command) {
        serialCommunication.write(command);
    }

    @Override
    public ArrayList<Integer> readBotData() {
        data = serialCommunication.readData();
        return data;
    }

    @Override
    public int getId() {
        return id;
    }
}
