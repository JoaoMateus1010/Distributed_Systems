package udp_servidor;

import calculator.Calculator;
import java.net.*;
import java.io.*;
public class UDP_Servidor{

    public static void main(String args[]) {
        Calculator calc = new Calculator();             
        DatagramSocket aSocket = null;
        try {            
            aSocket = new DatagramSocket(6789);
            // create socket at agreed port
            byte[] buffer = new byte[1000];            
            while (true) {                         
                System.out.println(new String(buffer)+" Tamanho: "+buffer.length);                
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);  
                aSocket.receive(request);
                String recebido = new String(buffer);
                System.out.println("Requisição de Operação Recebida: " + recebido); 
                recebido = String.valueOf(calc.Calculator(recebido));                
                buffer = recebido.getBytes();
                request.setData(recebido.getBytes());
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
