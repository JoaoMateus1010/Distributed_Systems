package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main{ 
        /*Criação de Atributos*/
        public static ServerSocket serv; // Criação do atributo que corresponde ao Socket do servidor
        
        public static Socket clientA; // Criação do atributo que corresponde ao socket do cliente A
        public static Socket clientB; // Criação do atributo que corresponde ao socket do cliente B
        
        public static DataInputStream inA; // Criação do atributo que corresponde ao canal de entrada co cliente A
        public static DataInputStream inB; // Criação do atributo que corresponde ao canal de entrada do cliente B
        
        public static DataOutputStream outA; // Criação do atributo que corresponde ao canal de saída do cliente A
        public static DataOutputStream outB; // Criação do atributo que corresponde ao canal de saída do cliente B
        
        public static String msgA; // Criação do atributo que corresponde a mensagem do cliente A
        public static String msgB; // Criação do atributo que corresponde a mensagem do cliente B
        
        public ReadA readA; // Criação do atributo que corresponde ao objeto da classe responsavel pela leitura do canal do cliente A
        public ReadB readB; // Criação do atributo que corresponde ao objeto da classe responsavel pela leitura do canal do cliente B
        /*Criação de funções para iniciar a leitura dos canais*/
        public void startReadA(){//Método para ler o canal do cliente A
            readA = new ReadA();
            readA.start();
        }
        public void startReadB(){//Método para ler o canal do cliente B
            readB = new ReadB();
            readB.start();
        }
    public static void main(String[] args){        
        try {
            serv = new ServerSocket(8974); //Criação de um Socket tipo servidor
            
            clientA = serv.accept(); // Esperando o primeiro cliente conectar
            clientB = serv.accept(); // Esperando o segundo cliente conectar
            
            System.out.println("Cliente A: "+clientA.getPort()); // Mostrando a porta do primeiro cliente
            System.out.println("Cliente B: "+clientB.getPort()); // Mostrando a porta do segundo cliente
            
            inA = new DataInputStream(clientA.getInputStream()); // Criando canal de entrada do cliente A
            inB = new DataInputStream(clientB.getInputStream()); // Criando canal de entrada do cliente B
            
            outA = new DataOutputStream(clientA.getOutputStream()); // Criando canal de saída do cliente A
            outB = new DataOutputStream(clientB.getOutputStream()); // Criando canal de saída do cliente B                    
            
            Main mn = new Main(); // Criando um objeto tipo Main para usar os métodos de iniciar leitura dos canais
            mn.startReadA(); // Iniciar leitura do canal A
            mn.startReadB(); // Iniciar leitura do canal B
            while (true) {
                
                Thread.sleep(1); //Servidor apenas ler os dois canais e no cliclo infinito do while apenas espera.
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    /*
        Será criado duas classes que serão herdadas da Classe Thread. 
        Uma classe para o canal de leitura do cliente A 
        e outra classe para a leitura do canal do cliente B.
    */
    public class ReadA extends Thread{
        @Override
        public void run(){ // Métdo que será executado quando o método executar o comando start();
            System.out.println("Lendo Canal A...");
            while (true) {                
                try {                    
                    msgA = inA.readUTF(); // Esta Thread irá ficar esperando uma mensagem do cliente A, quando essa mensagem chegar irá para a variável msgA
                    outB.writeUTF(msgA); // Escrita no canal de Saída de B o que foi recebido na variável msgA                   
                    Thread.sleep(1); 
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
    }
    public class ReadB extends Thread{ 
        @Override
        public void run(){ // Métdo que será executado quando o método executar o comando start(); 
            System.out.println("Lendo Canal B...");
            while (true) {                            
                try {                    
                    msgB = inB.readUTF(); // Esta Thread irá ficar esperando uma mensagem do cliente B, quando essa mensagem chegar irá para a variável msgB 
                    outA.writeUTF(msgB);  // Escrita no canal de Saída de A o que foi recebido na variável msgB
                    Thread.sleep(1);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}