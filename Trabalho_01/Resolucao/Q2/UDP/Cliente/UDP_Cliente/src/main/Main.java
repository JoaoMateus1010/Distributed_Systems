package main;
import java.util.Scanner;
import udp_cliente.UDP_Cliente;
import telas.Telas;
public class Main {
    public static void main(String[] args) {
        UDP_Cliente udp = new UDP_Cliente(); 
        Telas telas = new Telas();
        Scanner sc = new Scanner(System.in);        
        String str;        
        telas.showTelaInicial();
        int escolha = 0;
        while(true){            
            telas.showMenu();
            try {
                escolha = Integer.valueOf(sc.nextLine());
            } catch (Exception e) {
                telas.showError();
            }
            str="";
            switch(escolha){
                case (1):
                    str=str.concat("+:");                                       
                    break;
                case (2):
                    str=str.concat("-:");
                    break;
                case (3):
                    str=str.concat("*:");
                    break;
                case(4):
                    str=str.concat("/:");
                    break;                                
            }                       
            if(escolha==5){
                break;
            }
            telas.showMenuNumber1();
            str=str.concat(sc.nextLine()).concat(":");
            telas.showMenuNumber2();
            str=str.concat(sc.nextLine().concat(""));
            System.out.println("Mensagem que será enviada: "+str);
            udp.send_msg_UDP(str);             
        }
    }    
}