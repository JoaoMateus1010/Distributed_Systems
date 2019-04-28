package main;

import tcp_servidor.*;
import calculator.Calculator;
import calculator.UserCalc;
import java.io.IOException;
import java.util.ArrayList;
public class Main{
    public static void main(String[] args) throws IOException{ 
        /*Create Objects*/
        Server server = new Server();        
        ArrayList<UserCalc> ListUserCalc = new ArrayList<>();
        UserCalc CurrentUser=null;
        String Aux;
        //Create Variables
        int step = 0;
        /*
            Step = 0 : Set Signal
            Step = 1 : Set Op1
            Step = 2 : Set Op2
            Step = 3 : Set Result
            Step = 4 : Configure new Step
        
        */
        // Configure Server
        server.CreateServer(server,7896); 
        //Init Server
        server.StartServer();                          
        while(true){            
            if(server.getStateHaveMens()){// trecho de código que será responsável quando uma mensagem chegar no servidor                    
                //Teste para saber se essa porta já se conectou com o servidor
                if(server.getTcp_Servidor().getClientPort(server.getClientConnectedSocket().getPort())==null){                    
                    //Nenhum cliente cadastrado com essa porta                    
                    //Create a new Client to Server
                    Client client = new Client(server.getClientConnectedSocket().getPort(),server.getClientConnectedSocket().getInetAddress().getHostAddress(),server.getClientConnectedSocket());                    
                    //Create a CurrentUser
                    Calculator calculator = new Calculator();
                    CurrentUser = new UserCalc(client,calculator,0);
                    //add to ListUser
                    ListUserCalc.add(CurrentUser);        
                    //Add in ListServerClients
                    server.getTcp_Servidor().AddClient(client);                                       
                    //System.out.println("Client List -> "+server.getTcp_Servidor().getClients().toString());
                    //System.out.println("User List -> "+ListUserCalc.toString());                    
                }else{
                    //Cliente cadastrado com essa porta                                   
                    CurrentUser = CurrentUser.getUserCalc_Client_To_Port(ListUserCalc, server.getClientConnectedSocket().getPort());
                }                
                //System.out.println("Lista de Usuários do Calc -> "+ListUserCalc.toString());
                //System.out.println("Lista de Clients no Server -> "+server.getTcp_Servidor().getClients().toString());                                                               
                System.out.println(CurrentUser.getUserCalc_Client().getClientSocket().isBound()+" "+CurrentUser.getUserCalc_Client().getClientSocket().isClosed()+" "+CurrentUser.getUserCalc_Client().getClientSocket().isConnected()+ " "+ CurrentUser.getUserCalc_Client().getClientSocket().isInputShutdown()+" "+CurrentUser.getUserCalc_Client().getClientSocket().isOutputShutdown());
                switch(CurrentUser.getStep()){
                    case (0):
                        System.out.println("Esperando por Operação"); 
                        Aux = server.read(CurrentUser.getUserCalc_Client());                        
                        if(Aux!=null){                            
                            CurrentUser.getUserCalc_Calculator().setOperacao(Aux);
                            CurrentUser.setStep(CurrentUser.getStep()+1);                            
                        }else{
                            System.out.println("Vazou no op");                            
                            server.setStateHaveMens(false);
                        }                                    
                        break;
                    case (1):
                        System.out.println("Esperando por primeiro número");
                        Aux=server.read(CurrentUser.getUserCalc_Client());
                        if(Aux!=null){
                            CurrentUser.getUserCalc_Calculator().setOperador1(Double.valueOf(Aux));
                            CurrentUser.setStep(CurrentUser.getStep()+1);
                        }else{
                            System.out.println("Vazou no num1");
                            server.setStateHaveMens(false);                            
                        }                                                                        
                        break;
                    case (2):
                        System.out.println("Esperando por segundo número");
                        Aux=server.read(CurrentUser.getUserCalc_Client());
                        if(Aux!=null){
                            CurrentUser.getUserCalc_Calculator().setOperador2(Double.valueOf(Aux));
                            CurrentUser.setStep(CurrentUser.getStep()+1);                            
                        }else{                            
                            System.out.println("Vazou no num2");
                            server.setStateHaveMens(false);                            
                        }                         
                        break;
                    case (3):
                        System.out.println("Enviando resultado para o cliente");
                        CurrentUser.getUserCalc_Calculator().setResult(CurrentUser.getUserCalc_Calculator().Calculator());                                                                
                        server.write(CurrentUser.getUserCalc_Client(),String.valueOf(CurrentUser.getUserCalc_Calculator().getResult()));
                        CurrentUser.setStep(CurrentUser.getStep()+1);
                        break;
                    case (4):
                        System.out.println("Conf new Step");
                        CurrentUser.setStep(0);
                        Aux="";
                        break;
                }
                System.out.println(ListUserCalc.toString());
                //System.out.println(CurrentUser.getUserCalc_Calculator().toString());                
            }            
            try{
                Thread.sleep(10);
            }catch(InterruptedException ex){
            
            }            
        }
    }
}