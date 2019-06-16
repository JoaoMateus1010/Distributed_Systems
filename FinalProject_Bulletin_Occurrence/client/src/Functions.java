import java.util.ArrayList;

public class Functions {
    int IPC = 1558;
    public static ArrayList<Person> ListPersonVitima = new ArrayList<>();
    public static ArrayList<Person> ListPersonAcusado = new ArrayList<>();
    public static ArrayList<OccurrenceBoletin> ListBO = new ArrayList<>();
    public static ArrayList<Message> ListMensagensSend = new ArrayList<>();
    public static ArrayList<Message> ListMensagensReply = new ArrayList<>();

    public Message doOperation(Message msgToSend){
        Message return_msg = new Message(-1,-1,null,-1,null,null);
        SocketClient socketClient = new SocketClient("127.0.0.1",2009,IPC);
        socketClient.send(msgToSend.getJSONMessage(msgToSend).toString());
        ListMensagensSend.add(msgToSend);
        Message tempmsg = null;
        tempmsg = return_msg.convertStrJSONToMessage(socketClient.request());
        if(return_msg.getMessageType()==1){ // Se for Reply
            if((msgToSend.getRequestId()==return_msg.getRequestId())){ // Respondeu a mensagem certa
                return_msg = tempmsg;
                ListMensagensReply.add(return_msg);
            }
        }
        socketClient.closeAll();
        IPC++;
        return return_msg;
    }
    public void ListVitimas(){
        if(ListPersonVitima.size()>0){
            System.out.println("    >> "+"Vítimas <<   ");
            for(Person vit:ListPersonVitima){
                System.out.println("    > "+vit.getName());
            }
        }else{
            System.out.println("Empty!");
        }
    }
    public void ListAcusados(){
        if(ListPersonAcusado.size()>0){
            System.out.println("    >> "+"Acusado <<   ");
            for(Person vit:ListPersonAcusado){
                System.out.println("    > "+vit.getName());
            }
        }else{
            System.out.println("Empty!");
        }
    }
    public void ListBO(){
        if(ListBO.size()>0){
            System.out.println("    > "+"Vítima"+" <-> "+"Acusado");
            for(OccurrenceBoletin bo:ListBO){
                System.out.println("    > "+bo.getPerson_Victim().getName()+" <-> "+bo.getPerson_accused().getName());
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
    public OccurrenceBoletin getBOFromPersonVIC(Person VIC){
        OccurrenceBoletin bo_to_ret = null;
        if(VIC != null){
            for (OccurrenceBoletin bo:ListBO){
                if(bo.getPerson_Victim().getName().equals(VIC.getName())){
                    bo_to_ret = bo;
                }
            }
        }
        return bo_to_ret;
    }
    public OccurrenceBoletin getBOFromPersonACC(Person ACC){
        OccurrenceBoletin bo_to_ret = null;
        if(ACC != null){
            for (OccurrenceBoletin bo:ListBO){
                if(bo.getPerson_accused().getName().equals(ACC.getName())){
                    bo_to_ret = bo;
                }
            }
        }
        return bo_to_ret;
    }
}
