import java.util.Scanner;
import java.io.*;
public class MainClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SocketClient socketClient = new SocketClient("127.0.0.1",2006,1552);
        try{
            Person vitima   =     new Person("VT","000.000.000-00","00000000-0","00/00/0000","F");
            Person acc      =     new Person("AC","111.111.111-11","11111111-1","11/11/1111","M");
            OccurrenceBoletin boletin01 = new OccurrenceBoletin(vitima,acc,"homem alto loiro de olhos azuis com mancha no rosto e tatuagem de tartaruga nas costas","Rua Francisco Bocalho",true,"AK-47","Sargento Sarmento",false);
            Message msg01 = new Message(0,0,"OpenBO",0,"Sem argumentos",boletin01);
            File oc01 = new File("oc01.json");
            FileWriter oc01FW = new FileWriter(oc01);
            oc01FW.write(msg01.getJSONMessage(msg01).toString());
            socketClient.send(msg01.getJSONMessage(msg01).toString());
            if(socketClient.request().equals("True")){
                System.out.println("Solved");
            }else{
                System.out.println("Not Solved");
            }
            oc01FW.close();
        }catch (IOException e){
            System.out.println(e);
        }
        socketClient.closeAll();
    }
}