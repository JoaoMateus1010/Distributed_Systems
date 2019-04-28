package udp_cliente;
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class UDP_Cliente {
    public static void main(String args[]) {        
        DatagramSocket aSocket = null;
        try {            
            aSocket = new DatagramSocket();
            Scanner sc = new Scanner(System.in);            
            String msg = new String("");        
            msg = sc.nextLine();
            byte[] m = msg.getBytes();
            InetAddress aHost = InetAddress.getByName("127.0.0.1");
            int serverPort = 6789;
            DatagramPacket request = new DatagramPacket(m, msg.length(), aHost, serverPort);
            aSocket.send(request);
            //******  RESPOSTA SERVER ******
            byte[] buffer = new byte[1000];       
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Reply: " + new String(reply.getData()));
            //******************************            
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }            
        }  
    }
}
