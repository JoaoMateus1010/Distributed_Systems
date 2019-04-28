package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main{
    public static void main(String[] args){
        
        ServerSocket serv;
        
        Socket clientA;
        Socket clientB;
        
        DataInputStream inA;
        DataInputStream inB;
        
        DataOutputStream outA;
        DataOutputStream outB;
        
        String msgA;
        String msgB;
        try {
            serv = new ServerSocket(8974);
            
            clientA = serv.accept();
            clientB = serv.accept();            
            
            inA = new DataInputStream(clientA.getInputStream());
            inB = new DataInputStream(clientB.getInputStream());
            
            outA = new DataOutputStream(clientA.getOutputStream());
            outB = new DataOutputStream(clientB.getOutputStream());
            
            while (true) {
                msgA = inA.readUTF();                           
                outB.writeUTF(msgA);
                msgB = inB.readUTF();
                outA.writeUTF(msgB);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}