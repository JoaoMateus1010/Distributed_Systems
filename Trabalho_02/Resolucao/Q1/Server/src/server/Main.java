package server;

import TCPServer.TCPServer;
import calculator.Calc;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        
        TCPServer tcps = new TCPServer(1515);
        Calc calc = new Calc();
        
        String msg;
        String op[];
        
        while(true){
            
            msg = tcps.getRequest();            
            op = msg.split(":");
            
            switch(op[0]){
                
                case "+":
                    tcps.sendRequest(String.valueOf(calc.add(Double.valueOf(op[1]), Double.valueOf(op[2]))));
                    break;
                    
                case "-":
                    tcps.sendRequest(String.valueOf(calc.sub(Double.valueOf(op[1]), Double.valueOf(op[2]))));
                    break;
                    
                case "*":
                    tcps.sendRequest(String.valueOf(calc.mult(Double.valueOf(op[1]), Double.valueOf(op[2]))));
                    break;
                    
                case "/":
                    tcps.sendRequest(String.valueOf(calc.div(Double.valueOf(op[1]), Double.valueOf(op[2]))));
                    break;
                    
            }   
            
            Thread.sleep(1);
        }
    }    
}
