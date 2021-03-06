import java.io.File;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Arrays;
import java.util.Scanner;
public class ShapeListClient {
    public static void main(String[] args) {

        System.setSecurityManager(new SecurityManager());        
        RemoteInterface ri = null;
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("INFO :");
            System.out.println(Arrays.toString(Naming.list("ServerJM")));
            ri=(RemoteInterface)Naming.lookup("ServerJM");
            System.out.print("STATUS : ");
            if(!ri.getStatus().equals("Ok")){
                System.out.println("Not found Service");
                System.exit(0);
            }
            System.out.println("Found Server");
            int op = 4;
            /*
            * 1- Ver próprios arquivos
            * 2- Ver arquivos do servidor
            * 3- Baixar Arquivos
            * 4- Sair
            * */
            while(true){
                System.out.println("1- Ver próprios arquivos\n2- Ver arquivos do servidor\n3- Baixar arquivo\n4- Sair");
                System.out.print("Waiting: ");
                op = scanner.nextInt();
                switch (op){
                    case 1:
                        System.out.println("---------- Client Files ----------");
                        if(ri.getListFiles("FILES_CLIENT").length==0){
                            System.out.println("Empty");
                        }else{
                            for(File file:ri.getListFiles("FILES_CLIENT")){
                                System.out.println(file.getName());
                            }
                        }
                        System.out.println("----------------------------------");
                        break;
                    case 2:
                        System.out.println("---------- Server Files ----------");
                        if(ri.getListFiles("FILES_SERVER").length==0){
                            System.out.println("Empty");
                        }else{
                            for(File file:ri.getListFiles("FILES_SERVER")){
                                System.out.println(file.getName());
                            }
                        }
                        System.out.println("----------------------------------");
                        break;
                    case 3:
                        String nameFileToTake = null;
                        System.out.print("waiting name file: ");
                        nameFileToTake = scanner.next();
                        if(ri.cpFileServer(nameFileToTake)){
                            System.out.println("--> Transferencia realizada com sucesso!");
                        }else{
                            System.out.println("Erro na transferencia");
                        }
                        break;
                    case 4:
                        System.exit(0);
                        break;
                }
            }
        } catch (MalformedURLException | NotBoundException | RemoteException e){
            System.out.println("Server não encontrado");
            System.exit(0);
        }
    }
}
