import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.rmi.server.UnicastRemoteObject;
public class Servidor {
    public static String NameService = "ServerJM";
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        try{
            int port = 8512;
            RemoteInterface ri = new Servente();
            Registry registry = LocateRegistry.createRegistry(port);
            Naming.rebind(NameService, ri);
            System.out.println("Executando Sevidor...\nNome do Serviço:"+NameService);
        }catch(MalformedURLException | RemoteException e){
            System.out.println(e.getMessage());
        }
    }
}