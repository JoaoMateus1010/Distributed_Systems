package proxy;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tcp_client.TCPClient;

public class Proxy {
    public TCPClient tcpc;
    
    public Proxy(){
        this.tcpc = new TCPClient(1354,1515,"127.0.0.1");
    }
    
    public double add(double op1,double op2){
        tcpc.SendRequest("+:"+String.valueOf(op1)+":"+String.valueOf(op2));
        return Double.valueOf(tcpc.getResponse());
    }
    public double sub(double op1,double op2){
        tcpc.SendRequest("-:"+String.valueOf(op1)+":"+String.valueOf(op2));
        return Double.valueOf(tcpc.getResponse());
    }
    public double mult(double op1,double op2){
        tcpc.SendRequest("*:"+String.valueOf(op1)+":"+String.valueOf(op2));
        return Double.valueOf(tcpc.getResponse());
    }public double div(double op1,double op2){
        tcpc.SendRequest("/:"+String.valueOf(op1)+":"+String.valueOf(op2));
        return Double.valueOf(tcpc.getResponse());
    }
    public void close(){
        try {
            this.tcpc.SocketClient.close();
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
