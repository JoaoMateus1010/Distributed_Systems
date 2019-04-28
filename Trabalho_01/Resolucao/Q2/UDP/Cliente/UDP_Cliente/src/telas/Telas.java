package telas;

public class Telas {
    public void showTelaInicial(){
        System.out.println("Trabalho Sistemas Distribuídos Questão 2:");
    }
    public void showMenu(){
        System.out.println("1) Somar\n2)Subtrair\n3)Multiplicar\n4)Dividir\n5)Sair\nEscolha: ");
    }
    public void showMenuNumber1(){
        System.out.println("Digite o primeiro Número:");
    }
    public void showMenuNumber2(){
        System.out.println("Digite o segundo Número:");
    }
    public void showResult(double result){
        System.out.println("O Resultado é :"+result);
    }
    public void showError(){
        System.out.println("Digite um número");
    }
}
