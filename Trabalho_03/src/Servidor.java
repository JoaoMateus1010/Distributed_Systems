import java.net.MalformedURLException;
import java.rmi.*;
public class Servidor {
    public static String NameService = "ServerJM";
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        try{
            RemoteInterface ri = new Servente();
            Naming.rebind(NameService, ri);
            System.out.println("Executando Sevidor...\nNome do Serviço: "+NameService);
        }catch(MalformedURLException | RemoteException e){
            System.out.println("Conexão recusada, RMI não iniciado");
            System.exit(0);
        }
    }
}