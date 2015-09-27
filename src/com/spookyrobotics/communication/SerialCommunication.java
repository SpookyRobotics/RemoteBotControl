package com.spookyrobotics.communication;

import com.spookyrobotics.botStructs.InstructorCommand;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

// http://rxtx.qbang.org/wiki/index.php/Two_way_communcation_with_the_serial_port
public class SerialCommunication {

   public SerialCommunication(String port){
      this.portName = port;

   }
   private final String portName;

   ArrayList<CommPort> getAvailablePorts(){
       ArrayList<CommPort> result = new ArrayList<>();
       Enumeration ports = CommPortIdentifier.getPortIdentifiers();
       while(ports.hasMoreElements()){
           Object port = ports.nextElement();
           if(port instanceof CommPort){
               result.add((CommPort) port);
           }
       }
       return result;
   }
   void connect() throws Exception {
      CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
      if ( portIdentifier.isCurrentlyOwned() ) {
         System.out.println("Error: Port is currently in use");
      }
      else {
         CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);

         if ( commPort instanceof SerialPort ) {
            SerialPort serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

            InputStream in = serialPort.getInputStream();
            OutputStream out = serialPort.getOutputStream();

            (new Thread(new SerialReader(in))).start();
            (new Thread(new SerialWriter(out))).start();

         }
         else {
            System.out.println("Error: Only serial ports are handled by this example.");
         }
      }
   }

   /** */
   public static class SerialReader implements Runnable
   {
      InputStream in;

      public SerialReader ( InputStream in )
      {
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
   }

   /** */
   public static class SerialWriter implements Runnable
   {
      OutputStream out;
      private final ArrayList<Byte> transmitBuffer;
      public SerialWriter ( OutputStream out ) {
         this.out = out;
         transmitBuffer = new ArrayList<>();
      }

      public void run () {
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
   }

}
