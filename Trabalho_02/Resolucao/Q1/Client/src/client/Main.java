package client;

import java.util.Scanner;
import tcp_client.TCPClient;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        
        TCPClient tcpc = new TCPClient(1354,1515,"127.0.0.1");
        Scanner scanner = new Scanner(System.in);
        
        int step;
        double op1;
        double op2;
        double result;
        
        System.out.println("Bem Vindo!");
        
        while(true){            
            System.out.println("Digite 1 para somar");
            System.out.println("Digite 2 para subtrair");
            System.out.println("Digite 3 para Multiplicar");
            System.out.println("Digite 4 para dividir");
            System.out.println("Digite 5 para sair");
            System.out.print("Esperando... ");
            step = scanner.nextInt();
            switch(step){
                
                case 1:
                    System.out.println("Digite o Operador 1:");
                    op1 = scanner.nextDouble();
                    System.out.println("Digite o Operador 2:");                    
                    op2 = scanner.nextDouble();
                    tcpc.SendRequest("+:"+String.valueOf(op1)+":"+String.valueOf(op2));
                    System.out.println("Resultado: "+tcpc.getResponse());
                    break;
                    
                case 2:
                    System.out.println("Digite o Operador 1:");
                    op1 = scanner.nextDouble();
                    System.out.println("Digite o Operador 2:");                    
                    op2 = scanner.nextDouble();
                    tcpc.SendRequest("-:"+String.valueOf(op1)+":"+String.valueOf(op2));
                    System.out.println("Resultado: "+tcpc.getResponse());
                    break;
                    
                case 3:
                    System.out.println("Digite o Operador 1:");
                    op1 = scanner.nextDouble();
                    System.out.println("Digite o Operador 2:");                    
                    op2 = scanner.nextDouble();
                    tcpc.SendRequest("*:"+String.valueOf(op1)+":"+String.valueOf(op2));
                    System.out.println("Resultado: "+tcpc.getResponse());
                    break;
                    
                case 4:
                    System.out.println("Digite o Operador 1:");
                    op1 = scanner.nextDouble();
                    System.out.println("Digite o Operador 2:");                    
                    op2 = scanner.nextDouble();
                    tcpc.SendRequest("/:"+String.valueOf(op1)+":"+String.valueOf(op2));
                    System.out.println("Resultado: "+tcpc.getResponse());
                    break;
                    
                case 5:
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Operação inválida!");
                    break;
                    
            }
            
            Thread.sleep(1);
        }
    }    
}
