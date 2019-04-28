package tcp_servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread{
    private TCP_Servidor tcp_Servidor;
    private int PortServer;    
    private String dataInUTF;
    private Socket ClientConnectedSocket;
    private boolean StateHaveClient;
    private DataInputStream in;
    private DataOutputStream out;
    
    public boolean getStateHaveMens() {
        return StateHaveClient;
    }

    public void setStateHaveMens(boolean StateHaveMens) {
        this.StateHaveClient = StateHaveMens;
    }

    
    public Server(TCP_Servidor tcp_Servidor, int PortServer, String dataInUTF) {
        this.tcp_Servidor = tcp_Servidor;
        this.PortServer = PortServer;        
        this.dataInUTF = dataInUTF;
    }
    public Server() {
        
        this.dataInUTF="";
    }

    public Socket getClientConnectedSocket() {
        return ClientConnectedSocket;
    }

    public void setClientConnectedSocket(Socket ClientConnectedSocket) {
        this.ClientConnectedSocket = ClientConnectedSocket;
    }
    
    
    public String getDataInUTF() {         
        return this.dataInUTF;
    }

    public void setDataInUTF(String dataInUTF) {        
        this.dataInUTF = dataInUTF;
    }                   
    
    public TCP_Servidor getTcp_Servidor() {
        return tcp_Servidor;
    }

    public void setTcp_Servidor(TCP_Servidor tcp_Servidor) {
        this.tcp_Servidor = tcp_Servidor;
    }

    public int getPortServer() {
        return PortServer;
    }        
    public void setPortServer(int PortServer) {
        this.PortServer = PortServer;
    }
    public void StartServer(){
        this.start();
    }
    public boolean ServerAlive(){
        return this.getTcp_Servidor().getServerSocket().isClosed();
    }
    public void CreateServer(Server server,int ServerPort){
        
        tcp_Servidor = new TCP_Servidor(ServerPort,server);                
    }
    
    public void closeConnection(Client client){
        try {
            client.getClientSocket().close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String read(Client client) throws IOException{
        String msg = null;           
        try {
            DataInputStream input = new DataInputStream(client.getClientSocket().getInputStream());                 
            msg= input.readUTF();
        } catch (IOException ex) {              
            msg=null;            
        }
        return msg;
    }
    
    public void write(Client client,String msg){
        try {
            DataOutputStream out = new DataOutputStream(client.getClientSocket().getOutputStream());   
            out.writeUTF(msg);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void run(){
        tcp_Servidor._init_Server(tcp_Servidor);
    }    
}
//finally {
//            try {
//                System.out.println("FECHOU");
//                clientSocket.close();
//            } catch (IOException e) {/*close failed*/
//            }
//        }