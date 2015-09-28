package com.spookyrobotics.communication;

import com.spookyrobotics.Bot;
import com.spookyrobotics.botStructs.InstructorCommand;

import java.util.ArrayList;

/**
 * Bots can only send all data, or receive a single instructor command at a time
 *
 * Bots are always passive and respond to \n characters that signal end of line
 */
public interface BotCommunicationInterface {
    public boolean isBotAttached();
    public void sendInstructorCommand(InstructorCommand command);

    /*
     * Data sent is as follows
     * [Effectors List Size] [ Effectors List ] [ Sensors List Size] [Sensors List]
     */
    public ArrayList<Integer> readBotData();
}
