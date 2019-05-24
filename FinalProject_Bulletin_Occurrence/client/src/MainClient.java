public class MainClient {

    final static String IP         =   "127.0.0.1";
    final static int PortClient    =   1549 ;
    final static int PortServer    =   1550;

    public static void main(String[] args) {
        SocketClient socketClient = new SocketClient(IP,PortClient,PortServer);
    }
}
