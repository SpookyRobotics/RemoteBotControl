package com.spookyrobotics.communication;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/** */
public class SerialReader extends Thread {
   private InputStream in;
   public SerialReader(InputStream in) {
      this.in = in;
   }

   public void run () {
      byte[] buffer = new byte[1024];
      int len = -1;
      try {
         while ( ( len = this.in.read(buffer)) > -1 ) {
            System.out.print(new String(buffer,0,len));
         }
      }
      catch ( IOException e ) {
         e.printStackTrace();
      }
   }

   public ArrayList<Integer> readData(){
      return null;
   }

   public boolean receivedHandshake() {
      return false;
   }
}
