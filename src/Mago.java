/// Classe Mago
public class Mago extends Personagem {

    // Construtor da classe Mago
    public Mago(
            String nome,
            short pontosVida,
            short ataque,
            short defesa,
            short nivel,
            Inventario inventario
    ) {
        // Chama o construtor da classe Personagem
        super(nome, pontosVida, ataque, defesa, nivel, inventario);

        // Cria um objeto Item que representa a arma inicial do Mago
        Item pocaoInicial = new Item(
                "Elixir do Vento Adormecido",
                "Um elixir antigo que invoca ventos frios dos vales místicos.",
                "impede ações do inimigo no próximo turno",
                1
        );

        // O item criado é adicionado ao inventário do Arqueiro
        this.inventario.adicionarItem(pocaoInicial);
    }
}
