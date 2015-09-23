package com.spookyrobotics;

import com.spookyrobotics.botStructs.RemoteEffector;
import com.spookyrobotics.botStructs.RemoteSensor;
import com.spookyrobotics.communication.BotCommunication;

import java.util.ArrayList;

public class Bot {
    private static int nextId = 0;
    private final String portName;

    public static Bot create(String port){
        Bot b = new Bot(nextId,port);
        nextId+= 1;
        return b;
    }

    private final int id;
    private ArrayList<RemoteEffector> effectors;
    private ArrayList<RemoteSensor> sensors;
    private ArrayList<Integer> data;
    private final BotCommunication communication;
    private Bot(int id, String port){
        this.id = id;
        this.portName = port;
        communication = new BotCommunication(portName);
    }




}
