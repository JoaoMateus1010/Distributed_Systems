package despachante;
import esqueleto.Esqueleto;
public class Despachante {
    public String invoke(String msg){
        String op[];        
        String Sreturn = null;
        Esqueleto esqueleto = new Esqueleto();
        op = msg.split(":");
            switch(op[0]){
                case "+":
                    Sreturn=esqueleto.add(msg);                    
                    break;
                case "-":
                    Sreturn=esqueleto.sub(msg);                    
                    break;
                case "*":
                    Sreturn=esqueleto.mult(msg);
                    break;
                case "/":
                    Sreturn=esqueleto.div(msg);                    
                    break;
            }
        return Sreturn;
    }
}
