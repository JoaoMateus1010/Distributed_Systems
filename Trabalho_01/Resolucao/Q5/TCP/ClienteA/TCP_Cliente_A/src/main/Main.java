package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
        public static InetAddress inet; // Criação do atributo que corresponde ao InetAddres do Cliente
        public static Socket client; // Criação do atributo que corresponde ao socket do cliente
        public static int portClient = 8880; // Criação do atributo que corresponde a porta do cliente
        public static int portServer = 8974; // Criação do atributo que corresponde a porta do servidor
        public static String ip = "127.0.0.1"; // Criação do atributo que corresponde ao endereço IP
        public static Scanner sc = new Scanner(System.in); // Criação do atributo responsável por receber as mensagens do usuário
        public static DataInputStream in; // Criação do atributo que corresponde ao canal de entrada do cliente
        public static DataOutputStream out; // Criação do atributo que corresponde ao canal de saída do cliente       
        /*
            Será criado um método responsável por ficar lendo o canal de entrada do cliente
        */
        public void initRead(){
            Read read = new Read();
            read.start();
        }
        
    public static void main(String[] args) {                                
        try {
            inet = InetAddress.getByName(ip); // Criação do InetAddres que corresponde ao IP passado
            client = new Socket(inet, portServer, inet, portClient);// Criação do socket do cliente
            in =  new DataInputStream(client.getInputStream());//Criação do canal de entrada do cliente
            out = new DataOutputStream(client.getOutputStream());//Criação do canal de saída do cliente
            Main mn = new Main(); // Criação do objeto da main que será usado para chamar a função que ativa a leitura do canal do cliente
            mn.initRead(); //Inicia a leitura do canal de entrada do cliente
            while (true) {
                System.out.print("Você: ");                
                out.writeUTF(sc.nextLine());// Manda para o servidor o que receber do teclado do usuário
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public class Read extends Thread{
        @Override
        public void run(){ // Método que será chamado quando for inicializado a leitura do canal de entrada do cliente
            while (true) {                
                try {
                    System.out.println("\nAmigo: "+in.readUTF()); //Mostrará na tela o que for recebido do canal de entrada do cliente
                    System.out.print("Você: ");
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
