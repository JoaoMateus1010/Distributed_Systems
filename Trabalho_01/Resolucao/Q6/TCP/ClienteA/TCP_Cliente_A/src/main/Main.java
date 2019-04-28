package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
        public static InetAddress inet;
        public static Socket client;
        public static int portClient = 8880;
        public static int portServer = 8974;
        public static String ip = "127.0.0.1";
        public static Scanner sc = new Scanner(System.in);
        public static DataInputStream in;
        public static DataOutputStream out;
        public static String msg;
        
        public void initRead(){
            Read read = new Read();
            read.start();
        }
        
    public static void main(String[] args) {                                
        try {
            inet = InetAddress.getByName(ip);
            client = new Socket(inet, portServer, inet, portClient);
            in =  new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            Main mn = new Main();
            mn.initRead();
            while (true) {
                System.out.print("Você: ");                
                out.writeUTF(sc.nextLine());                
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public class Read extends Thread{
        @Override
        public void run(){
            while (true) {                
                try {
                    msg = in.readUTF();
                    if(msg.contains("https://www.youtube.com")){
                        java.awt.Desktop.getDesktop().browse( new java.net.URI(msg));                        
                    }else{
                        System.out.println("\nAmigo: "+msg);
                        System.out.print("Você: ");
                    }
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
