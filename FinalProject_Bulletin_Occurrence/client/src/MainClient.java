import java.util.Scanner;
import java.io.*;
public class MainClient {

    public static void main(String[] args) {

        //SocketClient socketClient = new SocketClient("127.0.0.1",2007,1557);
        int op = -1;
        Scanner sc = new Scanner(System.in);
        Scanner scMenu = new Scanner(System.in);
        Functions functions = new Functions();

        while (true){
            System.out.println("[1] : Fazer cadastro da vítima\n[2] : Fazer cadastro do acusado\n[3] : Adicionar um Boletim de ocorrência\n[4] : Listar Vítimas\n[5] : Listar Acusados\n[6] : Listar Boletins de ocorrências\n[7] : Buscar Boletins de ocorrência por Vítima\n[8] : Buscar boletins de ocorrências por Acusado\n[9] : Listar funções do servidor\n[10] : Sair");
            System.out.print("Digite: ");
            op = sc.nextInt();
            switch (op){
                case 1:
                    String NomeV = "";
                    String CPFV = "";
                    String RGV = "";
                    String DataV = "";
                    String SexoV = "";
                    System.out.println("-> Cadastro de Vítima <-");
                    System.out.print("Nome: ");
                    NomeV = scMenu.nextLine();
                    if(functions.isNameListVitimas(NomeV)){
                        System.out.println("! Nome já cadastrado !");
                        break;
                    }
                    System.out.print("CPF: ");
                    CPFV = scMenu.nextLine();
                    System.out.print("RG: ");
                    RGV = scMenu.nextLine();
                    System.out.print("Data de Nascimento: ");
                    DataV = scMenu.nextLine();
                    System.out.print("Sexo: ");
                    SexoV = scMenu.nextLine();
                    functions.ListPersonVitima.add(new Person(NomeV,CPFV,RGV,DataV,SexoV));
                    System.out.println("-> Completo <-");
                    break;
                case 2:
                    String NomeA = "";
                    String CPFA = "";
                    String RGA = "";
                    String DataA = "";
                    String SexoA = "";
                    System.out.println("-> Cadastro de Acusado <-");
                    System.out.print("Nome: ");
                    NomeA = scMenu.nextLine();
                    if(functions.isNameListAcusado(NomeA)){
                        System.out.println("! Nome já cadastrado !");
                        break;
                    }
                    System.out.print("CPF: ");
                    CPFA = scMenu.nextLine();
                    System.out.print("RG: ");
                    RGA = scMenu.nextLine();
                    System.out.print("Data de Nascimento: ");
                    DataA = scMenu.nextLine();
                    System.out.print("Sexo: ");
                    SexoA = scMenu.nextLine();
                    functions.ListPersonAcusado.add(new Person(NomeA,CPFA,RGA,DataA,SexoA));
                    System.out.println("-> Completo <-");
                    break;
                case 3:

                    break;
                case 4:
                    functions.ListVitimas();
                    break;
                case 5:
                    functions.ListAcusados();
                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:
                    //socketClient.closeAll();
                    System.exit(0);
                    break;
                default:
                    System.out.println("-> Opção inválida <-");
                    break;
            }
        }
        /*Person vitima   =     new Person("VT","000.000.000-00","00000000-0","00/00/0000","F");
        Person acc      =     new Person("AC","111.111.111-11","11111111-1","11/11/1111","M");
        OccurrenceBoletin boletin01 = new OccurrenceBoletin(vitima,acc,"homem alto loiro de olhos azuis com mancha no rosto e tatuagem de tartaruga nas costas","Rua Francisco Bocalho",true,"AK-47","Sargento Sarmento",false);
        Message msg01 = new Message(0,0,"OpenBO",0,"Sem argumentos",boletin01);
        socketClient.send(msg01.getJSONMessage(msg01).toString());
        msg01 = msg01.convertStrJSONToMessage(socketClient.request());*/
    }
}