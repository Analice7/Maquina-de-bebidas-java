public class Bebidas {
  double preco;
  String nome;

  // Construtor default
  public Bebidas() {
    this.preco = 0.0;
    this.nome = "";
  }

  public Bebidas(String nome, double preco) {
    this.nome = nome;
    this.preco = preco;
  }

  // Mostra um desenho ASCII da bebida pronta
  public void mostrarDesenhoBebida() {
    
    if (this.nome.equals("Cafe")) {
      System.out.println("\n  ( (\n   ) )\n........\n|      |]\n\\      / \n `----'");
    } 
      
    else if (this.nome.equals("Chocolate")) {
      System.out.println("\n   )  (   \n  (    )  \n   )  (   \n#########\n|       |]\n\\       / \n `-----'");
    } 
      
    else if (this.nome.equals("Cappuccino")) {
      System.out.println("\n  ( (\n   ) )\n.......\n|     |]\n\\     / \n `---'");
    }
  }
  
}
