package tcp_client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {    
    public Socket SocketClient;
    public DataInputStream dataInputStream;
    public DataOutputStream dataOutputStream;
    public TCPClient(int ClientPort,int ServerPort,String Ip){
        try {
            InetAddress idnet = InetAddress.getByName(Ip);
            this.SocketClient = new Socket(idnet, ServerPort, idnet, ClientPort);
            this.dataInputStream = new DataInputStream(this.SocketClient.getInputStream());
            this.dataOutputStream = new DataOutputStream(this.SocketClient.getOutputStream());
        } catch (UnknownHostException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void SendRequest(String request){
        try {
            this.dataOutputStream.writeUTF(request);
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getResponse(){
        String returnS = "";
        try {
            returnS=this.dataInputStream.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnS;
    }
    public void close(){
        try {
            this.SocketClient.close();
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
