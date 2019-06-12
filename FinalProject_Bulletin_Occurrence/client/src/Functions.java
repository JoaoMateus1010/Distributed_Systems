import java.util.ArrayList;

public class Functions {

    public static ArrayList<Person> ListPersonVitima = new ArrayList<>();
    public static ArrayList<Person> ListPersonAcusado = new ArrayList<>();
    public static ArrayList<OccurrenceBoletin> ListBO = new ArrayList<>();
    public static ArrayList<Message> ListMensagens = new ArrayList<>();

    public Message doOperation(Message msgToSend){
        Message return_msg = null;

        return return_msg;
    }
    public void ListVitimas(){
        if(ListPersonVitima.size()>0){
            for(Person vit:ListPersonVitima){
                System.out.println("> "+vit.getName());
            }
        }else{
            System.out.println("Empty!");
        }
    }
    public void ListAcusados(){
        if(ListPersonAcusado.size()>0){
            for(Person vit:ListPersonAcusado){
                System.out.println("> "+vit.getName());
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
}
