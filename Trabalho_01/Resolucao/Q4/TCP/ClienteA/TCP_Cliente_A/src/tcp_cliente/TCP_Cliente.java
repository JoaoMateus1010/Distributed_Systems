package tcp_cliente;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TCP_Cliente {
    protected int serverPort;
    protected int PortClient;    
    protected Socket s;
    protected InetAddress idNet;
    protected DataInputStream in;
    protected DataOutputStream out;      

    public TCP_Cliente(int serverPort, int PortClient, Socket s, InetAddress idNet, DataInputStream in, DataOutputStream out) {
        this.serverPort = serverPort;
        this.PortClient = PortClient;
        this.s = s;
        this.idNet = idNet;
        this.in = in;
        this.out = out;
    }

    public TCP_Cliente() {
    }
    
    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getPortClient() {
        return PortClient;
    }

    public void setPortClient(int PortClient) {
        this.PortClient = PortClient;
    }
  
    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    public InetAddress getIdNet() {
        return idNet;
    }

    public void setIdNet(InetAddress idNet) {
        this.idNet = idNet;
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
    public void sendMesg(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException ex) {
            Logger.getLogger(TCP_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String ReadUTF(){
        String valReturn=null;
        try {
            valReturn = in.readUTF();                        
        } catch (IOException ex) {
            Logger.getLogger(TCP_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                this.getS().close();
            } catch (IOException ex) {
                Logger.getLogger(TCP_Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return valReturn;
    }
    public boolean _init_TCP_Client(int ServerPort,int PortClient,String IP){
    boolean Valreturn=true;
    this.setPortClient(PortClient);
    this.setServerPort(ServerPort);
        try {
            this.setIdNet(InetAddress.getByName(IP));
        } catch (Exception ex) {
            Valreturn=false;
            Logger.getLogger(TCP_Cliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: Criação setIdNet");            
        }
        try {
            this.setS(new Socket(this.getIdNet(),this.getServerPort() , this.getIdNet(),this.getPortClient()));
        } catch (Exception ex) {
            Valreturn=false;
            Logger.getLogger(TCP_Cliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: criação new Socket");            
        }        
    return Valreturn;
    }
    public boolean _init_input_TCP(){
        boolean ValReturn = true;
        try {
            this.setIn(new DataInputStream(this.getS().getInputStream()));
        } catch (IOException ex) {
            System.out.println("ERROR: Erro na definição de INPUT setIn");
            Logger.getLogger(TCP_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ValReturn;
    }
    public boolean _init_output_TCP(){
        boolean ValReturn = true;
        try {
            this.setOut(new DataOutputStream(this.getS().getOutputStream()));
        } catch (IOException ex) {
            System.out.println("ERROR: Erro na definição de OUTPUT setOut");
            Logger.getLogger(TCP_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ValReturn;
    }
    public boolean sendMSG(String msg){
    boolean ValReturn=true;
        try {
            this.getOut().writeUTF(msg);
        } catch (Exception ex) {
            System.out.println("ERROR: Erro no envio da mensagem");
            Logger.getLogger(TCP_Cliente.class.getName()).log(Level.SEVERE, null, ex);
            ValReturn=false;
        }finally{
            
        }
    return ValReturn;
    }
    public String ReadMSG(){
        String msg = "";
        try {
            msg=this.getIn().readUTF();            
        } catch (Exception ex) {
            Logger.getLogger(TCP_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           
        }
        return msg;
    }    
    public boolean isConnected(){
        boolean ValReturn;
        ValReturn=this.getS().isConnected();
        return ValReturn;
    }
    public boolean isClosed(){
        boolean ValReturn;
        ValReturn=this.getS().isClosed();
        return ValReturn;
    }
    public void close(){
        try {
            this.getS().close();
        } catch (IOException ex) {
            Logger.getLogger(TCP_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}