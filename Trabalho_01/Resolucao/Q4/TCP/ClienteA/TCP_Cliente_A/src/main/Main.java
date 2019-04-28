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
    public static void main(String[] args) {                        
        InetAddress inet;
        Socket client;
        int portClient = 8880;
        int portServer = 8974;
        String ip = "127.0.0.1";
        Scanner sc = new Scanner(System.in);
        DataInputStream in;
        DataOutputStream out;
        try {
            inet = InetAddress.getByName(ip);
            client = new Socket(inet, portServer, inet, portClient);
            in =  new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            while (true) {
                System.out.print("Você: ");
                out.writeUTF(sc.nextLine());
                System.out.println("Amigo : "+in.readUTF());
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
