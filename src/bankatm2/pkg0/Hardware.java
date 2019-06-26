/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankatm2.pkg0;
import com.fazecast.jSerialComm.*;
import java.util.HashSet;

/**
 *
 * @author tding
 */
public class Hardware {
    SerialPort comPort;
    
    public Hardware(String hcp){
        comPort = SerialPort.getCommPort(hcp);
        comPort.setComPortParameters(9600, 8, 1, 0);
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
    }
    
    public void giveOutput(String op){
        if(comPort.openPort()){
            byte[] writeBuffer = op.getBytes();
            comPort.writeBytes(writeBuffer, writeBuffer.length);
        } else {
            return;
        }
    }
    
    public String getInput(){
        if(comPort.openPort()){
            byte[] readBuffer = new byte[comPort.bytesAvailable()];
            comPort.readBytes(readBuffer, readBuffer.length);
            return new String(readBuffer);
        } else {
            return "";
        }
    }
}
