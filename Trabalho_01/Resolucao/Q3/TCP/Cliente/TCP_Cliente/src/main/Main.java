package main;

import java.util.Scanner;
import telas.Telas;
import tcp_cliente.TCP_Cliente;
public class Main {
    public static void main(String[] args) {                
        Telas tl = new Telas();
        TCP_Cliente tcp = new TCP_Cliente();
        Scanner sc = new Scanner(System.in);
        String str="";
        String Operacao="";
        double Operador1;
        double Operador2;  
        /*
        Configura TCP        
        */        
        try{
            tcp._init_TCP_Client(7896,1237,"127.0.0.1");            
            tcp._init_input_TCP();                   
            tcp._init_output_TCP();              
            while(true){                
            System.out.println("Digite a Operação");
            tcp.sendMSG(sc.nextLine());            
//            System.out.println(tcp.ReadMSG());
            System.out.println("Digite O primeiro Número");
            tcp.sendMSG(sc.nextLine());            
//            System.out.println(tcp.ReadMSG()); 
            System.out.println("Digite o segundo número");
            tcp.sendMSG(sc.nextLine()); 
            System.out.println("Resultado: ");
            System.out.println(tcp.ReadMSG()); 
            }
//            System.out.println(tcp.ReadMSG()); 
        }catch(Exception e){
            System.out.println("Falha no _init_TCP do TCP "+e);            
        }           
        
        /*
            Check de conexão e dados
        */
//        String backup="";
//        tl.showTelaInicial();
//        int escolha = 0;        
//        while(true){            
//            //backup = tcp.send_msg_TCP("!");
//            //System.out.println("BACKUP: "+backup);
//            if(backup.equals("")){
//                tl.showMenu();  
//                escolha = Integer.valueOf(sc.nextLine());
//                try {
//                    switch(escolha){
//                case (1):                    
//                    Operacao="+";
//                    str=str.concat(Operacao).concat(":");
//                    //tcp.send_msg_TCP(str);
//                    break;
//                case (2):                                        
//                    Operacao="-";
//                    str=str.concat(Operacao).concat(":");
//                    //tcp.send_msg_TCP(str);
//                    break;
//                case (3):                                        
//                    Operacao="*";
//                    str=str.concat(Operacao).concat(":");
//                    //tcp.send_msg_TCP(str);
//                    break;
//                case(4):                                        
//                    Operacao="/";
//                    str=str.concat(Operacao).concat(":");
//                    //tcp.send_msg_TCP(str);
//                    break;    
//                case (5):
//                    //tcp.send_msg_TCP("ex"); 
//                    break;
//                }                    
//                } catch (Exception e) {
//                    tl.showError();
//                }               
//            }else{
//                System.out.println("Operação requisiteada anteriormente:"+backup);
//                Operacao=backup;
//                str=str.concat(Operacao).concat(":");
//            
//            }                                                                                     
//            tl.showMenuNumber1();
//            str=str.concat(sc.nextLine()).concat(":");
//            //tcp.send_msg_TCP(str);
//            tl.showMenuNumber2();
//            str=str.concat(sc.nextLine());
//            //tl.showResult(Double.valueOf(tcp.send_msg_TCP(str)));   
//            str="";
//            Operacao="";
//        }
    }
}
