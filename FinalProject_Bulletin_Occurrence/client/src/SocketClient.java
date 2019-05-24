import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private InetAddress Inet;

    public SocketClient(Socket socket, DataInputStream in, DataOutputStream out, InetAddress inet) {
        this.socket = socket;
        this.in = in;
        this.out = out;
        this.Inet = inet;
    }

    public SocketClient(String IPClient,int PortClient,int PortServer){
        try {
            InetAddress inet = InetAddress.getByName(IPClient);
            Socket sc = new Socket(inet,PortServer,inet,PortClient);
            DataInputStream tempIn = new DataInputStream(sc.getInputStream());
            DataOutputStream tempOut = new DataOutputStream(sc.getOutputStream());
            SocketClient init = new SocketClient(sc,tempIn,tempOut,inet);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
