package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import FuncaoAdicional.Function;

public class Main{    
        public static ServerSocket serv;
        
        public static Socket clientA;
        public static Socket clientB;
        
        public static DataInputStream inA;
        public static DataInputStream inB;
        
        public static DataOutputStream outA;
        public static DataOutputStream outB;
        
        public static String msgA;
        public static String msgB;
        
        public ReadA readA;
        public ReadB readB;
        
        public void startReadA(){
            readA = new ReadA();
            readA.start();
        }
        public void startReadB(){
            readB = new ReadB();
            readB.start();
        }
        
    public static void main(String[] args){        
        try {            
            serv = new ServerSocket(8974);
            
            clientA = serv.accept();
            clientB = serv.accept();            
            
            System.out.println("Cliente A: "+clientA.getPort());
            System.out.println("Cliente B: "+clientB.getPort());
            
            inA = new DataInputStream(clientA.getInputStream());
            inB = new DataInputStream(clientB.getInputStream());
            
            outA = new DataOutputStream(clientA.getOutputStream());
            outB = new DataOutputStream(clientB.getOutputStream());                                                            
            
            Main mn = new Main();                        
            mn.startReadA();
            mn.startReadB();
            while (true) {   
                Thread.sleep(1);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    public class ReadA extends Thread{          
        @Override
        public void run(){
            Function function = new Function();            
            System.out.println("Lendo Canal A...");
            while (true) {                
                try {                    
                    msgA = inA.readUTF();
                    /*Função adicional*/
                    if(msgA.equals("randommusic")){
                        msgA=function.RandomYoutubeMusic();// Escolhe uma música aleatória da lista cadastrada
                    }
                    /*Fim da função adicional*/
                    outB.writeUTF(msgA);
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
        public void run(){ 
            Function function = new Function();
            System.out.println("Lendo Canal B...");
            while (true) {                            
                try {                    
                    msgB = inB.readUTF();
                    /*Função adicional*/
                    if(msgB.equals("randommusic")){
                        msgB=function.RandomYoutubeMusic();// Escolhe uma música aleatória da lista cadastrada
                    }
                    /*Fim da função adicional*/
                    outA.writeUTF(msgB);                    
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