import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SocketClient socketClient = new SocketClient("127.0.0.1",2002,1552);
        socketClient.send("Oi do cliente");
        System.out.println(socketClient.request());
        socketClient.closeAll();
    }
}