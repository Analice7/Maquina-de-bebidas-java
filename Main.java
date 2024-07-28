import java.util.Scanner;

//OBS 1: Na operacao de inserir moedas, o usuario deve digitar o valor da moeda pelo teclado

//OBS 2: A opcao de cancelar pedido devolve as moedas inseridas pelo usuario

public class Main {
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    MaquinaDeBebidas maquina = new MaquinaDeBebidas();
    maquina.menu(sc);
    sc.close();
    
  }
}
