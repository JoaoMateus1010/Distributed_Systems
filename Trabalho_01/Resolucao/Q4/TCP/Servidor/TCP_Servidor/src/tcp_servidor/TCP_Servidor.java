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
    private Server server;    
    // Construtores 
    public TCP_Servidor(int serverPort, ServerSocket serverSocket, ArrayList<Client> clients) {
        this.serverPort = serverPort;
        this.serverSocket = serverSocket;        
    }

    public TCP_Servidor() {
    }
    public TCP_Servidor(int serverPort,Server server){
        this.serverPort = serverPort;        
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
