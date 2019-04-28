package tcp_servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {    
    private int clientPort;
    private String clientIp;
    private Socket clientSocket;

    public Client(int clientPort, String clientIp, Socket clientSocket) {
        this.clientPort = clientPort;
        this.clientIp = clientIp;
        this.clientSocket = clientSocket;
    }

    public Client(int clientPort, String clientIp) {
        this.clientPort = clientPort;
        this.clientIp = clientIp; 
        try {
            this.clientSocket = new Socket(this.getClientIp(), this.getClientPort());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    
    public void setListenSocket(ServerSocket serverSocket){
        try {
            this.setClientSocket(serverSocket.accept());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String toString() {
        return "Client{" + "clientPort=" + clientPort + ", clientIp=" + clientIp + ", clientSocket=" + clientSocket + '}';
    }
        
}
