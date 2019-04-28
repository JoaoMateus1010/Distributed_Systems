package calculator;
import java.util.ArrayList;
import tcp_servidor.Client;
public class UserCalc {
    private Client UserCalc_Client;
    private Calculator UserCalc_Calculator;
    private int Step;

    public UserCalc(Client UserCalc_Client, Calculator UserCalc_Calculator, int Step) {
        this.UserCalc_Client = UserCalc_Client;
        this.UserCalc_Calculator = UserCalc_Calculator;
        this.Step = Step;
    }        

    public int getStep() {
        return Step;
    }

    public void setStep(int Step) {
        this.Step = Step;
    }

    
    
    public Client getUserCalc_Client() {
        return UserCalc_Client;
    }

    public void setUserCalc_Client(Client UserCalc_Client) {
        this.UserCalc_Client = UserCalc_Client;
    }

    public Calculator getUserCalc_Calculator() {
        return UserCalc_Calculator;
    }

    public void setUserCalc_Calculator(Calculator UserCalc_Calculator) {
        this.UserCalc_Calculator = UserCalc_Calculator;
    }
    public UserCalc getUserCalc_Client_To_Port(ArrayList<UserCalc> List_UserCalcs,int Port){
        UserCalc Val_UserCalc_Return= null;
        for(UserCalc for_cliClient:List_UserCalcs){
            if(for_cliClient.getUserCalc_Client().getClientPort()==Port){
                Val_UserCalc_Return = for_cliClient;
            }
        }
        return Val_UserCalc_Return;
    }
    @Override
    public String toString() {
        return "UserCalc{" + "UserCalc_Client=" + UserCalc_Client + ", UserCalc_Calculator=" + UserCalc_Calculator + '}';
    }
    
}
