package esqueleto;
import calculator.Calc;
public class Esqueleto {
    public String add(String args){
        String Sreturn = "";
        String op[];  
        op = args.split(":");
        Calc calc = new Calc();
        Sreturn = String.valueOf(calc.add(Double.valueOf(op[1]), Double.valueOf(op[2])));
        return Sreturn;        
    }
    public String sub(String args){
        String Sreturn = "";
        String op[];  
        op = args.split(":");
        Calc calc = new Calc();
        Sreturn = String.valueOf(calc.sub(Double.valueOf(op[1]), Double.valueOf(op[2])));
        return Sreturn;        
    }
    public String mult(String args){
        String Sreturn = "";
        String op[];  
        op = args.split(":");
        Calc calc = new Calc();
        Sreturn = String.valueOf(calc.mult(Double.valueOf(op[1]), Double.valueOf(op[2])));
        return Sreturn;        
    }
    public String div(String args){
        String Sreturn = "";
        String op[];  
        op = args.split(":");
        Calc calc = new Calc();
        Sreturn = String.valueOf(calc.div(Double.valueOf(op[1]), Double.valueOf(op[2])));
        return Sreturn;        
    }
}
