package tcp_servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
class ConnectionServer extends Thread {
    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;
    private String dataClass = "";
    private Server server ;

    public String getDataClass() {
        return dataClass;
    }

    public void setDataClass(String dataClass) {
        this.dataClass = dataClass;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
    
    public String getData() {
        return dataClass;
    }

    public void setData(String data) {
        this.dataClass = data;
    }
    
    public DataInputStream getIn() {
        return in;
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    public void sendMsgToServer(Server server,String dado){
        server.setDataInUTF(dado);
    }
    public ConnectionServer(Socket clientsocket,Server server) {         
        if(clientsocket!=null){
            try {
                clientSocket = clientsocket;
                server.setClientConnectedSocket(clientsocket);
                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());   
                this.server=server;
                //this.start();
                String data = in.readUTF();// read a line of data from the stream 
                this.getServer().setDataInUTF(data);            
                out.writeUTF(data); 
                
                data = in.readUTF();// read a line of data from the stream 
                this.getServer().setDataInUTF(data);            
                out.writeUTF(data); 
                
                data = in.readUTF();// read a line of data from the stream 
                this.getServer().setDataInUTF(data);            
                out.writeUTF(data); 
                
                out.writeUTF("15");
                } catch (IOException e) {
                System.out.println("Connection:" + e.getMessage());
            }
        }else{
            System.out.println("NULL");
        }
    }        
    @Override
    public void run() {
        try {   // an echo server              
            String data = in.readUTF();// read a line of data from the stream 
            this.getServer().setDataInUTF(data);            
            out.writeUTF(data);           
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally{
            try {
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
