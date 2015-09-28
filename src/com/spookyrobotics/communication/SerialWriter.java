package com.spookyrobotics.communication;

import com.spookyrobotics.botStructs.InstructorCommand;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class SerialWriter extends Thread{
   OutputStream out;
   private final ArrayList<Byte> transmitBuffer;

   public SerialWriter(OutputStream out) {
      this.out = out;
      transmitBuffer = new ArrayList<>();
   }


   public void run() {
      try {
         int c = -1;
         while ( transmitBuffer.size() > 0 || System.in.available() > -1 ) {
            while(System.in.available() > -1){
               c = System.in.read();
               this.out.write(c);
            }
            if(transmitBuffer.size() > 0){
               synchronized (transmitBuffer){
                  for(Byte b : transmitBuffer) {
                     this.out.write(b);
                  }
                  transmitBuffer.clear();
               }
            }
         }
      }
      catch ( IOException e ) {
         e.printStackTrace();
      }
   }

   public void write(InstructorCommand command){
      byte[] value = command.flatten();
      synchronized (transmitBuffer) {
         Collections.addAll(transmitBuffer, InstructorCommand.transmitPrelude());
         for (byte b : value) {
            transmitBuffer.add(b);
         }
         Collections.addAll(transmitBuffer, InstructorCommand.transmitPostlude(command));
      }
   }

   public void write(byte[] flatten) {
      synchronized (transmitBuffer) {
         for(byte b : flatten){
            transmitBuffer.add(new Byte(b));
         }
      }
   }
}
