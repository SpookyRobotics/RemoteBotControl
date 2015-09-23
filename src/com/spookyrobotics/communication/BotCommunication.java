package com.spookyrobotics.communication;

import com.spookyrobotics.Bot;
import com.spookyrobotics.botStructs.InstructorCommand;

import java.util.ArrayList;

/**
 * Bots can only send all data, or receive a single instructor command at a time
 *
 * Bots are always passive and respond to \n characters that signal end of line
 */
public class BotCommunication {
    private final String portName;

    public Bot getAttachedBot(){return null;}
    public void sendInstructorCommand(InstructorCommand command){}

    /*
     * Data sent is as follows
     * [Effectors List Size] [ Effectors List ] [ Sensors List Size] [Sensors List]
     */
    public ArrayList<Integer> readBotData(){ return null;}

    private final SerialCommunication serialCommunication;

    public BotCommunication(String port){
        this.portName = port;
        serialCommunication = new SerialCommunication(portName);
    }
}
