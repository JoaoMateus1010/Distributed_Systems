package udp_cliente;
import java.net.*;
import java.io.*;
import telas.Telas;
public class UDP_Cliente {
    public void send_msg_UDP(String msg){
        DatagramSocket aSocket = null;
        Telas tl = new Telas();
        String str = null;
        try {            
            aSocket = new DatagramSocket();                        
            byte[] m = msg.getBytes();            
            InetAddress aHost = InetAddress.getByName("127.0.0.1");
            int serverPort = 6789;
            DatagramPacket request = new DatagramPacket(m, msg.length(), aHost, serverPort);
            System.out.println("Dado que será enviado: "+new String(request.getData()));
            aSocket.send(request);
            //******  RESPOSTA SERVER ******
            byte[] buffer = new byte[1000];               
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            str = new String(reply.getData());
            tl.showResult(Double.valueOf(str));
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
