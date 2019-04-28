package server;
import TCPServer.TCPServer;
import despachante.Despachante;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TCPServer tcps = new TCPServer(1515);
        Despachante despachante = new Despachante();
        String msg;        
        while(true){
            msg = tcps.getRequest();
            tcps.sendRequest(despachante.invoke(msg));
            Thread.sleep(1);
        }
    }    
}
