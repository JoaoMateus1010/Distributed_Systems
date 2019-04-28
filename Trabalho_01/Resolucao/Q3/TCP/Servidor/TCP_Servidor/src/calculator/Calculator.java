package calculator;
public class Calculator{
    private  String  Operacao="";
    private double Operador1;
    private double Operador2;    
    private double Result;

    public double getResult() {
        return Result;
    }

    public void setResult(double Result) {
        this.Result = Result;
    }
    
    public String getOperacao() {
        return Operacao;
    }

    public void setOperacao(String Str) {        
        this.Operacao = Str;
    }

    public double getOperador1() {
        return Operador1;
    }

    public void setOperador1(double Operador1) {
        this.Operador1 = Operador1;
    }

    public double getOperador2() {
        return Operador2;
    }

    public void setOperador2(double Operador2) {
        this.Operador2 = Operador2;
    }
    
    @Override
    public String toString() {
        return "Calculator{" + "Operacao=" + Operacao + ", Operador1=" + Operador1 + ", Operador2=" + Operador2 + ", Result=" + Result + '}';
    }
    
    
    public double Calculator(){                 
        switch(Operacao){
            case "+":                
                this.setResult(this.ADD(Operador1,Operador2));
                break;
            case "-":
                this.setResult(this.SUB(Operador1,Operador2));
                break;
            case "*":
                this.setResult(this.MUL(Operador1,Operador2));
                break;
            case "/":
                this.setResult(this.DIV(Operador1,Operador2));
                break;
        }    
    return this.getResult();
    }
    protected double ADD(double Num1, double Num2){      
        return Num1+Num2;
    }
    protected double SUB(double Num1, double Num2){        
        return Num1-Num2;
    }
    protected double MUL(double Num1, double Num2){        
        return Num1*Num2;
    }
    protected double DIV(double Num1, double Num2){       
        return Num1/Num2;
    }
}