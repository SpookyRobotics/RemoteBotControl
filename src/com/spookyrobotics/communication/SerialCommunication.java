package com.spookyrobotics.communication;

import com.spookyrobotics.botStructs.HandshakeCommand;
import com.spookyrobotics.botStructs.InstructorCommand;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

// http://rxtx.qbang.org/wiki/index.php/Two_way_communcation_with_the_serial_port
public class SerialCommunication {

   private SerialReader serialReader;
   private SerialWriter serialWriter;
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
            serialReader = new SerialReader(in);
            serialWriter = new SerialWriter(out);
            serialReader.start();
            serialWriter.start();

         }
         else {
            System.out.println("Error: Only serial ports are handled by this example.");
         }
      }
   }

   public void write(InstructorCommand command){
      if(serialWriter != null){
         serialWriter.write(command);
      }
   }

   public ArrayList<Integer> readData() {
      return serialReader.readData();
   }

   public boolean handshake() {
      serialWriter.write(HandshakeCommand.flatten());
      if(serialReader.receivedHandshake()){
         return true;
      }
      return false;
   }
}
