package client;
import java.util.Scanner;
import proxy.Proxy;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        
        Proxy proxy = new Proxy();
        Scanner scanner = new Scanner(System.in);
        
        int step;
        double op1;
        double op2;
        double result;
        
        while(true){
            System.out.println("Bem Vindo!");
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
                    System.out.println("Resultado: "+String.valueOf(proxy.add(op1, op2)));
                    break;
                case 2:
                    System.out.println("Digite o Operador 1:");
                    op1 = scanner.nextDouble();
                    System.out.println("Digite o Operador 2:");                    
                    op2 = scanner.nextDouble();
                    System.out.println("Resultado: "+String.valueOf(proxy.sub(op1, op2)));
                    break;
                case 3:
                    System.out.println("Digite o Operador 1:");
                    op1 = scanner.nextDouble();
                    System.out.println("Digite o Operador 2:");                    
                    op2 = scanner.nextDouble();
                    System.out.println("Resultado: "+String.valueOf(proxy.mult(op1, op2)));
                    break;
                case 4:
                    System.out.println("Digite o Operador 1:");
                    op1 = scanner.nextDouble();
                    System.out.println("Digite o Operador 2:");                    
                    op2 = scanner.nextDouble();
                    System.out.println("Resultado: "+String.valueOf(proxy.div(op1, op2)));
                    break;
                case 5:
                    proxy.close();
                    System.exit(0);                    
                    break;
                default:
                    break;
            }
            Thread.sleep(1);
        }
    }    
}
