package tcp_servidor;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TCP_Servidor {
    //Atributos
    private int serverPort;
    private ServerSocket serverSocket;
    private ArrayList<Client> clients;
    private Server server;    
    // Construtores 
    public TCP_Servidor(int serverPort, ServerSocket serverSocket, ArrayList<Client> clients) {
        this.serverPort = serverPort;
        this.serverSocket = serverSocket;
        this.clients = clients;
    }

    public TCP_Servidor() {
    }
    public TCP_Servidor(int serverPort,Server server){
        this.serverPort = serverPort;
        this.clients = new ArrayList<>();
        this.server=server;
        try {
            this.serverSocket = new ServerSocket(this.getServerPort());
        } catch (IOException ex) {
            Logger.getLogger(TCP_Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }        
    // GET e SET Genéricos

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
    
    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }
    //ADD e Remove dos clientes do server
    public Client AddClient(Client client){
        //System.out.println(this.toString());
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
    //Métodos de Debug
    public boolean isClientInServer(TCP_Servidor server,Socket clientSocket){
        boolean ValReturn=false;
        if(server.getClientPort(clientSocket.getPort()) != null){
            ValReturn = true;
        } 
        return ValReturn;
    }
    public boolean isSocketIsServerIsClosed(TCP_Servidor server,Socket clientSocket){
        boolean valReturn = false;
        if((server.isClientInServer(server, clientSocket))&&(server.getClientPort(clientSocket.getPort()).getClientSocket().isClosed())){
            valReturn = true;
        }        
        return valReturn;
    }
    @Override
    public String toString() {
        return "TCP_Servidor{" + "serverPort=" + serverPort + ", serverSocket=" + serverSocket + ", clients=" + clients + '}';
    }
    
    //inicialização do server    
    public void _init_Server(TCP_Servidor server){        
       while(true){
           try {
               Socket clientSocket = server.getServerSocket().accept();               
               this.getServer().setClientConnectedSocket(clientSocket);               
               this.getServer().setStateHaveMens(true);
               System.out.println("Conectou, port: "+clientSocket.getPort());
               //System.out.println("Server Contém o Cliente?: -> "+server.isClientInServer(server, clientSocket));
               //System.out.println("Socket is closed? -> "+server.isSocketIsServerIsClosed(server, clientSocket));               
//               ConnectionServer connectionServer = new ConnectionServer(clientSocket,this.getServer());                 
           } catch (IOException ex) {
               Logger.getLogger(TCP_Servidor.class.getName()).log(Level.SEVERE, null, ex);
           }           
       }
    }
}
