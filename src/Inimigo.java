import java.util.Random;

/// Classe Inimigo
public class Inimigo extends Personagem {

    // Construtor da classe Inimigo
    public Inimigo(
            String nome,
            short pontosVida,
            short ataque,
            short defesa,
            short nivel,
            Inventario inventario
    ) {
        // Chama o construtor da classe Personagem
        super(nome, pontosVida, ataque, defesa, nivel, inventario);

        // Objeto sorteador de um número aleatório
        /*
         Aqui você está criando um objeto da classe Random
         Essa classe é da biblioteca java.util e serve para gerar números aleatórios
         Obs: È como se fosse um dado invisível que o computador pode rolar para você sempre que quiser um número novo e imprevisível
        */
        Random random = new Random();

        // Variável do chamada itemInimigo do tipo Item
        // Vai guardar o resultado decidido após o switch, com base no número sorteado
        Item itemInimigo;

        // Gerando o número aleatório
        /*
         Gero um dado de 4 lados ((4-1) + 1), ou seja, pode ser sorteado um número aleatório de 1 a 4
         O valor sorteado é armazenado na variável sorteio
        */
        int sorteio = random.nextInt(4) + 1;

        // Escolhe a opção do número sorteado
        /*
         O comando switch verifica o valor da variável 'sorteio' (entre 1 e 4)
         e executa o bloco de código correspondente ao número sorteado
        */
        switch (sorteio) {
            case 1:
                itemInimigo = new Item(
                        "Espada do Crepúsculo",
                        "Uma lâmina lendária forjada na fronteira entre luz e trevas. Aumenta muito o poder de ataque.",
                        "+30 Ataque",
                        1
                );
                break;
            case 2:
                itemInimigo = new Item(
                        "Armadura do Guardião Ancestral",
                        "Uma armadura antiga encantada por espíritos protetores. Sua defesa é quase impenetrável.",
                        "+25 Defesa",
                        1
                );
                break;
            case 3:
                itemInimigo = new Item(
                        "Anel da Eternidade",
                        "Um anel mágico que aumenta a vida máxima de quem o usa.",
                        "+100 HP",
                        1
                );
                break;
            case 4:
                itemInimigo = new Item(
                        "Orbe das Almas Perdidas",
                        "Um artefato sombrio que canaliza o poder dos inimigos derrotados, amplificando seu ataque e defesa.",
                        "+15 Ataque / +10 Defesa",
                        1
                );
                break;
            default: // Se nenhum dos cases anteriores for correspondido (verdadeiro) é executado esse pedaço
                itemInimigo = new Item(
                        "Relíquia Desconhecida",
                        "Um artefato estranho de origem misteriosa.",
                        "???",
                        1
                );
        }

        // Adiciona o item sorteado dentro do inventário do inimigo
        this.inventario.adicionarItem(itemInimigo);
    }
}

