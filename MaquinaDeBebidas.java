import java.util.Scanner;
import java.text.DecimalFormat;

public class MaquinaDeBebidas {

  double qtdMoedas;
  Bebidas bebida;
  int pedidoPendente;
  boolean pedidoConcluido;

  public MaquinaDeBebidas() {
    this.qtdMoedas = 0.0;
    this.bebida = null;
    this.pedidoPendente = 0;
  }

  // Verifica se a moeda inserida e valida
  public boolean moedaValida(double moeda) {

    if (moeda == 0.05 || moeda == 0.10 || moeda == 0.25 || moeda == 0.50 || moeda == 1.0) {
      return true;
    }
    return false;
  }

  // Insere moedas
  public void inserirMoeda(Scanner sc) {

    double moeda = -1.0;

    System.out.println("\n MOEDAS ACEITAS PELA MAQUINA:");
    System.out.println("\n0.05 | 0.10 | 0.25 | 0.50 | 1.00");

    while (moeda != 0.0) {

      // Lẽ valores ate o usuario digitar 0 (o usuario deve digitar o valor da moeda)
      System.out.println("\nInsira uma moeda (digite 0 para finalizar a insercao):");
      moeda = sc.nextDouble();

      if (moedaValida(moeda)) {
        somarMoedas(moeda);
      }
    }
    limparConsole();
    System.out.println("\n[VALOR INSERIDO]");
    System.out.println("\nValor total de moedas inseridas: " + this.qtdMoedas);
  }

  // Soma as moedas inseridas
  public void somarMoedas(double valor) {
    this.qtdMoedas += valor;
  }

  // Troca a opcao de bebida escolhida pelo usuario
  public boolean trocarBebida(Scanner sc) {

    sc.nextLine();

    char op = 'N';

    while (true) {

      System.out.println("\n[Voce ja escolheu uma bebida]\n\nDeseja trocar? [S/N]");

      String input = sc.nextLine().toUpperCase();
      if (input.length() == 1 && Character.isLetter(input.charAt(0))
          && (input.charAt(0) == 'S' || input.charAt(0) == 'N')) {
        op = input.charAt(0);
        break;
      } else {
        System.out.println("[Letra invalida]");
      }
    }

    if (op == 'S') {
      this.bebida = null;
      return true;
    } else {
      return false;
    }
  }

  // o usuario escolhe a bebida
  public void EscolherBebida(Scanner sc) {

    if (this.pedidoPendente != 0) {
      if (!trocarBebida(sc)) {
        return;
      }
    }

    int op;
    System.out.println("\nOPCOES DE BEBIDAS\n");
    System.out.println("[1] - Cafe - R$ 2.00");
    System.out.println("[2] - Chocolate - R$ 5.00");
    System.out.println("[3] - Cappuccino - R$ 3.00");
    op = sc.nextInt();

    switch (op) {
      case 1:
        this.bebida = new Bebidas("Cafe", 2.00);
        break;
      case 2:
        this.bebida = new Bebidas("Chocolate", 5.00);
        break;
      case 3:
        this.bebida = new Bebidas("Cappuccino", 3.00);
        break;
      default:
        System.out.println("Opcao invalida");
    }
    limparConsole();
    System.out.println("\nBebida escolhida: " + this.bebida.nome + " - R$ " + this.bebida.preco);
    this.pedidoPendente = 1;
  }

  // Verifica se a quantidade de moedas inseridas e suficiente para comprar a bebida escolhida
  public boolean valorInsuficiente() {
    if (qtdMoedas < this.bebida.preco) {
      System.out.println("\n[Valor Insuficiente]");
      System.out.println("\nValor total de moedas inseridas: " + this.qtdMoedas);
      System.out.println("\nPreco do " + this.bebida.nome + ": " + this.bebida.preco);
      return true;
    }
    return false;
  }

  // Calcula o troco e exibe o valor na tela
  public void calcularTroco() {
    if (this.pedidoConcluido) {
      if (qtdMoedas >= this.bebida.preco) {
        qtdMoedas -= this.bebida.preco;
      }
    }
    DecimalFormat df = new DecimalFormat("0.00");
    System.out.println("\nTroco: " + df.format(qtdMoedas));
    removerPedido();
  }
  
  // Reinicia o construtor
  public void removerPedido() {
    this.qtdMoedas = 0.0;
    this.bebida = null;
    this.pedidoPendente = 0;
  }

  // Limpa o console
  public void limparConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public void prepararBebida(Scanner sc) {

    sc.nextLine();

    if (this.pedidoPendente == 0) {
      System.out.println("\n[Escolha uma bebida antes de preparar]");
      return;
    }

    if (valorInsuficiente()) {
      return;
    }

    char op;

    System.out.println("\n[Apos essa operacao o pedido nao podera ser cancelado]");
    System.out.println("\nDeseja continuar? [S/N]");

    while (true) {

      String input = sc.nextLine().toUpperCase();

      if (input.length() == 1 && Character.isLetter(input.charAt(0))
          && (input.charAt(0) == 'S' || input.charAt(0) == 'N')) {
        op = input.charAt(0);
        break;
      } else {
        System.out.println("[Letra invalida]");
      }
    }

    limparConsole();

    if (op == 'S') {
      System.out.println("\nO " + this.bebida.nome + " esta pronto");
      bebida.mostrarDesenhoBebida();
      this.pedidoConcluido = true;
      calcularTroco();
    } else if (op == 'N') {
      System.out.println("\nPedido cancelado");
      calcularTroco();
    }

  }

  // Cancela o pedido, reinicia o construtor e devolve as moedas inseridas
  public void cancelarPedido() {

    System.out.println("\n[PEDIDO CANCELADO]");
    calcularTroco();
    removerPedido();

  }

  // Exibe o menu principal
  public void menu(Scanner sc) {

    int op = -1;

    while (op != 0) {

      // limparConsole();
      System.out.println("\n       MENU\n");
      System.out.println("[1] - Inserir moedas");
      System.out.println("[2] - Escolher bebida");
      System.out.println("[3] - Preparar bebida");
      System.out.println("[4] - Cancelar pedido");
      System.out.println("[0] - Sair\n");
      op = sc.nextInt();
      limparConsole();

      switch (op) {
        case 1:
          inserirMoeda(sc);
          break;
        case 2:
          EscolherBebida(sc);
          break;
        case 3:
          prepararBebida(sc);
          break;
        case 4:
          cancelarPedido();
          break;
        case 0:
          System.out.println("\nSecao encerrada.");
          break;
        default:
          System.out.println("\n[OPCAO INVALIDA]");
      }
    }
  }
}
