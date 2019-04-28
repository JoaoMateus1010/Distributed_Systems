package udp_servidor;

import java.net.*;
import java.io.*;
public class UDP_Servidor{

    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        try {            
            aSocket = new DatagramSocket(6789);
            // create socket at agreed port
            byte[] buffer = new byte[1000];
            while (true) {                
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                int Tamanho = 0;    
                for(int i=0;buffer[i]!=0;i++){
                    Tamanho=i;
                }
                System.out.println(Tamanho);
                if (Tamanho != 0) {                    
                    System.out.println("Mensagem Recebida: " + new String(buffer));                    
                }else{
                    System.out.println("Mensagem vazia");
                }
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(),
                        request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
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
