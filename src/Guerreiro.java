/// Classe Guerreiro
public class Guerreiro extends Personagem {

        // Construtor da classe Guerreiro
        public Guerreiro(
                String nome,
                short pontosVida,
                short ataque,
                short defesa,
                short nivel,
                Inventario inventario
        ) {
            // Chama o construtor da classe Personagem
            super(nome, pontosVida, ataque, defesa, nivel, inventario);

            // Cria um objeto Item, que representa a arma inicial do Guerreiro
            Item facaInicial = new Item(
                    "Faca Inicial",
                    "Uma lâmina simples, mas confiável.",
                    "+5 Ataque",
                    1
            );

            // O item criado é adicionado ao inventário do Guerreiro
            this.inventario.adicionarItem(facaInicial);
        }
    }
