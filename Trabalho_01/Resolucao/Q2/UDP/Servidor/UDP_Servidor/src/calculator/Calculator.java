package calculator;
public class Calculator {    
    public double Calculator(String STR){        
    double NumReturn=0;
    if(!STR.isEmpty()){		
        String splitStr[] = STR.split(":");
        switch(splitStr[0]){
            case "+":                
                NumReturn = this.ADD(Double.valueOf(splitStr[1]),Double.valueOf(splitStr[2]));
                break;
            case "-":
                NumReturn = this.SUB(Double.valueOf(splitStr[1]),Double.valueOf(splitStr[2]));
                break;
            case "*":
                NumReturn = this.MUL(Double.valueOf(splitStr[1]),Double.valueOf(splitStr[2]));
                break;
            case "/":
                NumReturn = this.DIV(Double.valueOf(splitStr[1]),Double.valueOf(splitStr[2]));
                break;
        }
    }
    return NumReturn;
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