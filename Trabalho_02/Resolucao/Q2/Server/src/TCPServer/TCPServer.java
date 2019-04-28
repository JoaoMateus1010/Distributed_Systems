package TCPServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {
    public ServerSocket serverSocket;
    public Socket socket;
    public DataInputStream dataInputStream;
    public DataOutputStream dataOutputStream;
    
    public TCPServer(int ServerPort) {
        try {
            this.serverSocket = new ServerSocket(ServerPort);
            this.socket = serverSocket.accept();
            this.dataInputStream = new DataInputStream(this.socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public String getRequest(){
        String returnS = "";
        try {
            returnS = this.dataInputStream.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnS;
    }
    public void sendRequest(String response){
        try {
            this.dataOutputStream.writeUTF(response);
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
