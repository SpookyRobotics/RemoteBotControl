package com.spookyrobotics.botStructs;

import java.util.ArrayList;

public class Bot {
    private final int id;
    private ArrayList<RemoteEffector> effectors;
    private ArrayList<RemoteSensor> sensors;
    private ArrayList<Integer> data;
    private Bot(int id){this.id = id;}

}
