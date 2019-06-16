import java.util.Scanner;
import java.io.IOException;
public class MainClient {
    public static int countIDMsg = 0;
    public static void main(String[] args) throws IOException,InterruptedException{
        int op = -1;
        Scanner sc = new Scanner(System.in);
        Scanner scMenu = new Scanner(System.in);
        Functions functions = new Functions();

        while (true){
            System.out.println("[1] : Fazer cadastro da vítima\n[2] : Fazer cadastro do acusado\n[3] : Adicionar um Boletim de ocorrência\n[4] : Listar Vítimas\n[5] : Listar Acusados\n[6] : Listar Boletins de ocorrências\n[7] : Buscar Boletins de ocorrência por Vítima\n[8] : Buscar boletins de ocorrências por Acusado\n[9] : Listar funções do servidor\n[10] : Sair");
            System.out.print("Digite: ");
            try{
                op = sc.nextInt();
            }catch (Exception e){
                op = 10;
            }
            switch (op){
                case 1: // Cadastro de Vítima [Local]
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
                case 2: //Cadastro de Acusado [Local]
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
                case 3: // Adicionar Cadastro de Boletim de ocorrência [Local -> Remoto]

                    Person vitima = null;
                    String NameVic = null;
                    System.out.println("-> Cadastro de Boletim de ocorrência <-");
                    System.out.println("Selecione a vítima");
                    functions.ListVitimas();
                    System.out.print("Nome: ");
                    NameVic = scMenu.nextLine();

                    if(functions.getPersonVicFromName(NameVic)==null){
                        System.out.println("! Vítima não cadastrada !");
                        break;
                    }else{
                        vitima = functions.getPersonVicFromName(NameVic);
                    }
                    Person acusado = null;
                    String NameAcc = null;
                    System.out.println("Selecione o acusado");
                    functions.ListAcusados();
                    System.out.print("Nome: ");
                    NameAcc = scMenu.nextLine();

                    if(functions.getPersonAccFromName(NameAcc)==null){
                        System.out.println("! Acusado não cadastrado !");
                        break;
                    }else{
                        acusado = functions.getPersonAccFromName(NameAcc);
                    }
                    String descricao_acusado = null;
                    System.out.print("Descrição do acusado: ");
                    descricao_acusado = scMenu.nextLine();
                    String local = null;
                    System.out.print("Local: ");
                    local = scMenu.nextLine();
                    String usandoArma = null;
                    System.out.print("Usava arma?[s/n]: ");
                    usandoArma = scMenu.nextLine();
                    boolean UsingW;
                    if(usandoArma.equals("s")){
                        UsingW = true;
                    }else{
                        UsingW = false;
                    }
                    String Arma = "-";
                    if(UsingW){
                        System.out.print("Arma usada: ");
                        Arma = scMenu.nextLine();
                    }
                    OccurrenceBoletin bo = new OccurrenceBoletin(vitima,acusado,descricao_acusado,local,UsingW,Arma,"-",false);
                    functions.ListBO.add(bo);
                    Message msgTosend = new Message(0,countIDMsg,"ADDBO",1,"-",bo);
                    functions.doOperation(msgTosend);
                    System.out.println("-> Completo <-");
                    countIDMsg++;
                    break;
                case 4: // Listar Vítimas [Local]
                    functions.ListVitimas();
                    break;
                case 5: // Listar Acusados [Local]
                    functions.ListAcusados();
                    break;
                case 6: // Listar Boletins [Local]
                    functions.ListBO();
                    break;
                case 7: //Buscar Boletins de ocorrência por Vítima [Local -> Remoto -> Local]
                    System.out.println("-> Buscar Boletim de ocorrência por vítima <-");
                    Person vitima8 = null;
                    String NameVic8 = null;
                    System.out.println("Selecione a vítima");
                    functions.ListVitimas();
                    System.out.print("Nome: ");
                    NameVic8 = scMenu.nextLine();

                    if(functions.getPersonVicFromName(NameVic8)==null){
                        System.out.println("! Vítima não cadastrada !");
                        break;
                    }else{
                        vitima8 = functions.getPersonVicFromName(NameVic8);
                    }
                    OccurrenceBoletin bo8 = functions.getBOFromPersonVIC(vitima8);
                    Message msgTosend8 = new Message(0,countIDMsg,"BuscarBOVitima",2,"-",bo8);
                    Message reply = null;
                    reply = functions.doOperation(msgTosend8);
                    if(reply!=null){
                        System.out.println("-> Completo <-");
                    }else{
                        System.out.println("-> Erro, tente novamente, servidor respondeu a mensagem errada ou não houve resposta. Tente mais tarde! <-");
                    }
                    countIDMsg++;
                    break;
                case 8: // Buscar boletins de ocorrências por Acusado [Local -> Remoto -> Local]
                    System.out.println("-> Buscar Boletim de ocorrência por Acusado <-");
                    Person acusado8 = null;
                    String NameAcc8 = null;
                    System.out.println("-> Cadastro de Boletim de ocorrência <-");
                    System.out.println("Selecione o Acusado");
                    functions.ListAcusados();
                    System.out.print("Nome: ");
                    NameAcc8 = scMenu.nextLine();

                    if(functions.getPersonAccFromName(NameAcc8)==null){
                        System.out.println("! Acusado não cadastrado !");
                        break;
                    }else{
                        acusado8 = functions.getPersonAccFromName(NameAcc8);
                    }
                    OccurrenceBoletin bo82 = functions.getBOFromPersonACC(acusado8);
                    Message msgTosend82 = new Message(0,countIDMsg,"BuscarBOAcusado",3,"-",bo82);
                    functions.doOperation(msgTosend82);
                    System.out.println("-> Completo <-");
                    countIDMsg++;
                    break;
                case 9: // Listar funções do servidor [Local]
                    System.out.println("    > 1 : ADDBO");
                    System.out.println("    > 2 : BuscarBOVitima");
                    System.out.println("    > 3 : BuscarBOAcusado");
                    break;
                case 10: //Sair [Local]
                    System.exit(0);
                    break;
                default:
                    System.out.println("-> Opção inválida <-");
                    break;

            }
        }
    }
}