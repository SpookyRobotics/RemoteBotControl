package com.spookyrobotics;

import com.spookyrobotics.botStructs.RemoteEffector;
import com.spookyrobotics.botStructs.RemoteSensor;
import com.spookyrobotics.communication.BotCommunicationInterface;

import java.util.ArrayList;

public interface BotInterface extends BotCommunicationInterface{
    public ArrayList<RemoteEffector> getEffectors();
    public ArrayList<RemoteSensor> getSensors();
    public int getId();
}
