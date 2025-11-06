/// Classe Arqueiro
public class Arqueiro extends Personagem {

    // Construtor da classe Arqueiro
    public Arqueiro(
            String nome,
            short pontosVida,
            short ataque,
            short defesa,
            short nivel,
            Inventario inventario // Recebe um objeto do tipo Inventario e o armazena na variável inventario
    ) {
        // Chama o construtor da classe Personagem
        super(nome, pontosVida, ataque, defesa, nivel, inventario);

        // Cria um objeto Item que representa a arma inicial do Arqueiro
        Item flechaInicial = new Item(
                "Flecha Envenenada",
                "Uma flecha mergulhada em toxina de serpente.",
                "Causa +5 de dano no Inimigo ao qual for acertada.",
                1
        );

        // O item criado é adicionado ao inventário do Arqueiro
        /*
         this → representa o próprio objeto Arqueiro que está sendo criado (a instância atual = o personagem inteiro, não o item que está sendo criado)
         inventario → é o atributo herdado da classe Personagem, que guarda os itens do personagem
         adicionarItem() → é o método da classe Inventario responsável por inserir um novo Item na lista de itens
        */
        this.inventario.adicionarItem(flechaInicial);
    }
}
