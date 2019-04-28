package tcp_servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread{
    private TCP_Servidor tcp_Servidor;
    private int PortServer;    
    private String dataInUTF;
    private Socket ClientConnectedSocket;
    private boolean StateHaveClient;
    private ArrayList<Client> clients;    
    
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
        this.clients = new ArrayList<>();
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
    
    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }
    //ADD e Remove dos clientes do server
    public Client AddClient(Client client){        
        if(this.getClients().add(client)){            
            return client;
        }else{
            return null;
        }
    }
    
    public void RemoveClient(Client client){
        this.getClients().remove(client);
    }
    
    public boolean RemoveClientPort(int Port){
        boolean ValReturn=false;
        for (Client client : this.getClients()){
            if(client.getClientPort() == Port){
                this.getClients().remove(client);
                ValReturn = true;
            }
        }
        return ValReturn;
    }  
    //Get de clientes por atributo da classe Client    
    
    public Client getClientPort(int Port){        
        Client client=null;
        for(Client clientIT:this.getClients()){
            if(clientIT.getClientPort() == Port){                
                client=clientIT;
            }
        }
        return client;
    }
    public Client getClientIP(String IP){
        Client client=null;
        for(Client clientIT:this.getClients()){
            if(clientIT.getClientIp().equals(IP)){                
                client=clientIT;
            }
        }
        return client;
    }
    
    public Client getClientSocket(Socket socket){
        System.out.println(socket.toString());
        Client client=null;
        for(Client clientIT:this.getClients()){
            if(clientIT.getClientSocket().equals(socket)){                
                client=clientIT;
            }
        }
        return client;
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