import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
    protected Socket socket;
    protected DataInputStream in;
    protected DataOutputStream out;
    protected InetAddress Inet;

    private String IP;
    private int ServerPort;
    private int ClientPort;

    public SocketClient(String IP,int PortServer,int PortClient) {
        try {
            InetAddress inetC = InetAddress.getByName(IP);
            Socket scC = new Socket(inetC,PortServer,inetC,PortClient);
            DataOutputStream outC = new DataOutputStream(scC.getOutputStream());
            DataInputStream inC = new DataInputStream(scC.getInputStream());
            this.socket = scC;
            this.in = inC;
            this.out = outC;
            this.Inet=inetC;
            this.IP=IP;
            this.ServerPort=PortServer;
            this.ClientPort=PortClient;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getServerPort() {
        return ServerPort;
    }

    public void setServerPort(int serverPort) {
        ServerPort = serverPort;
    }

    public int getClientPort() {
        return ClientPort;
    }

    public void setClientPort(int clientPort) {
        ClientPort = clientPort;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
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

    public InetAddress getInet() {
        return Inet;
    }

    public void setInet(InetAddress inet) {
        Inet = inet;
    }

    public void send(String msg){
        try {
            if(this.getOut()==null){
                System.exit(0);
            }
            this.getOut().writeUTF(msg);
        } catch (IOException e) {
            System.out.println("Erro no send");
            System.exit(0);
            e.printStackTrace();
            closeAll();
            System.exit(0);
        }
    }

    public String request(){
        String msg = null;
        try {
            msg=this.getIn().readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;

    }

    public void closeAll(){
        try {
            this.getSocket().close();
            this.getIn().close();
            this.getOut().close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @Override
    public String toString() {
        return "SocketClient{" +
                "socket=" + socket +
                ", in=" + in +
                ", out=" + out +
                ", Inet=" + Inet +
                '}';
    }
}
