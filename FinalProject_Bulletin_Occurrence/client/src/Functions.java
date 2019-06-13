import java.util.ArrayList;

public class Functions {

    public static ArrayList<Person> ListPersonVitima = new ArrayList<>();
    public static ArrayList<Person> ListPersonAcusado = new ArrayList<>();
    public static ArrayList<OccurrenceBoletin> ListBO = new ArrayList<>();
    public static ArrayList<Message> ListMensagens = new ArrayList<>();

    public Message doOperation(Message msgToSend){
        System.out.println("SEND -------->");
        System.out.println(msgToSend.toString());
        Message return_msg = msgToSend;
        SocketClient socketClient = new SocketClient("127.0.0.1",2008,1557);
        socketClient.send(msgToSend.getJSONMessage(msgToSend).toString());
        //return_msg = return_msg.convertStrJSONToMessage(socketClient.request());
        System.out.println("REPLY -------->");
        System.out.println(return_msg.toString());
        if((msgToSend.getRequestId()==return_msg.getRequestId())){
            System.out.println("Respondeu a mensagem certa");
        }else{
           System.out.println("Respondeu a mensagem errada");
        }
        socketClient.closeAll();
        return return_msg;
    }
    public void ListVitimas(){
        if(ListPersonVitima.size()>0){
            for(Person vit:ListPersonVitima){
                System.out.println("    > "+vit.getName());
            }
        }else{
            System.out.println("Empty!");
        }
    }
    public void ListAcusados(){
        if(ListPersonAcusado.size()>0){
            for(Person vit:ListPersonAcusado){
                System.out.println("    > "+vit.getName());
            }
        }else{
            System.out.println("Empty!");
        }
    }
    public void ListBO(){
        if(ListBO.size()>0){
            for(OccurrenceBoletin bo:ListBO){
                System.out.println("    > "+bo.getPerson_Victim().getName()+" <-> "+bo.getPerson_accused().getName()+" -> "+bo.getName_Responsible_For_Case());
            }
        }else{
            System.out.println("Empty!");
        }
    }

    public boolean isNameListVitimas(String Name){ // Retorna true se tiver uma vítima com esse nome cadastrada
        boolean ret = false;
        for(Person vit:ListPersonVitima){
            if(vit.getName().equals(Name)){
                ret = true;
                break;
            }
        }
        return ret;
    }
    public boolean isNameListAcusado(String Name){ // Retorna true se tiver um acusado com esse nome cadastrada
        boolean ret = false;
        for(Person vit:ListPersonAcusado){
            if(vit.getName().equals(Name)){
                ret = true;
                break;
            }
        }
        return ret;
    }

    public Person getPersonVicFromName(String Name){
        Person person = null;
        for (Person ps:ListPersonVitima){
            if(ps.getName().equals(Name)){
                person=ps;
                break;
            }
        }
        return person;
    }
    public Person getPersonAccFromName(String Name){
        Person person = null;
        for (Person ps:ListPersonAcusado){
            if(ps.getName().equals(Name)){
                person=ps;
                break;
            }
        }
        return person;
    }
}
