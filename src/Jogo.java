import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/// Classe principal do Jogo
public class Jogo {

    public static void main(String[] args) throws IOException {

        // Cria um objeto chamado br que serve para ler o que o jogador digita no console
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /// INÍCIO OFICIAL DO JOGO
        // Exibe a história introdutória e o objetivo principal do jogo
        System.out.println("Bem-vindo ao Reino de Aurora!");
        System.out.println("Há séculos, o reino viveu em paz... até que o temido dragão Rex despertou.");
        System.out.println("Com seu poder, vilas foram destruídas e criaturas sombrias voltaram a andar pela terra.");
        System.out.println("\nSeu objetivo: descobrir onde o dragão está escondido e derrotá-lo para restaurar a paz em Aurora!");
        System.out.println("Mas cuidado... o caminho até ele é cheio de armadilhas e inimigos poderosos.\n");

        // Cria um inventário vazio para começar o jogo
        Inventario inventarioInicial = new Inventario();

        // Cria uma variável jogador, que será definida depois (quando o jogador escolher a classe (o que deseja ser))
        Personagem jogador = null;

        // È uma variável de controle pra manter o loop de escolha ativo até o jogador decidir
        boolean escolhaClasse = true;

        /// ESCOLHA DO PERSONAGEM
        // Continua rodando até o jogador escolher uma classe válida
        while (escolhaClasse == true) {

            /// PERSONAGENS E SUAS CARACTERÍSTICAS
            System.out.println("\nEscolha seu personagem");
            System.out.println("1 - Guerreiro");
            System.out.println("Alta vida (60 HP), boa defesa (10), ataque equilibrado (12).");
            System.out.println("Ideal para combates longos e resistência física.\n");

            System.out.println("2 - Mago");
            System.out.println("Vida baixa (45 HP), defesa fraca (7), ataque alto (15).");
            System.out.println("Confiável em ataques poderosos e magias ofensivas.\n");

            System.out.println("3 - Arqueiro");
            System.out.println("Vida média (50 HP), defesa média (8), ataque ágil (13).");
            System.out.println("Especialista em ataques rápidos e precisão à distância.\n");

            System.out.print("Digite o número do personagem escolhido: ");

            // Lê o número digitado pelo jogador e converte o texto digitado (String) em número inteiro (int)
            int opcao = Integer.parseInt(br.readLine());

            if (opcao == 1) {
                // Cria um objeto do tipo Guerreiro com os seus atributos
                jogador = new Guerreiro("Guerreiro", (short) 60, (short) 12, (short) 10, (short) 1, inventarioInicial);
                System.out.println("\nVocê escolheu ser um Guerreiro!");
                escolhaClasse = false;

            } else if (opcao == 2) {
                // Cria um objeto do tipo Mago com os seus atributos
                jogador = new Mago("Mago", (short) 45, (short) 15, (short) 7, (short) 1, inventarioInicial);
                System.out.println("\nVocê escolheu ser um Mago!");
                escolhaClasse = false;

            } else if (opcao == 3) {
                // Cria um objeto do tipo Arqueiro com os seus atributos
                jogador = new Arqueiro("Arqueiro", (short) 50, (short) 13, (short) 8, (short) 1, inventarioInicial);
                System.out.println("\nVocê escolheu ser um Arqueiro!");
                escolhaClasse = false;

            } else {
                System.out.println("Opção inválida. Tente novamente!");
            }
        }

        /// MOSTRA STATUS INICIAIS DO PERSONAGEM
        System.out.println("\n=== Status do Personagem ===");
        System.out.println(jogador);
        System.out.println("============================");

        // Verifica se o personagem escolheu alguma classe
        if (jogador == null) {
            System.out.println("Erro: Nenhum personagem foi selecionado. Encerrando o jogo.");
            return; // Retorna ao método main
        }

        /// INÍCIO DA EXPLORAÇÃO
        System.out.println("\nSua jornada começa agora...");
        explorar(jogador, br); // Chama o método explorar
    }

    /// EXPLORAR UM LUGAR
    // Controla todo o sistema de exploração (floresta, caverna, vila e montanha)
    public static void explorar(Personagem jogador, BufferedReader br) throws IOException {

        // Variável que mantém o loop da exploração ativo
        boolean explorando = true;

        /*
         Essas variáveis controlam o progresso do jogador
         Se um lugar já foi visitado, ele vira true
         Assim, o jogo não repete eventos (ex: não dá o mesmo item ou batalha duas vezes)
        */
        boolean florestaExplorada = false;
        boolean trilhaEsquerdaExplorada = false;
        boolean trilhaCentralExplorada = false;
        boolean trilhaDireitaExplorada = false;

        boolean cavernaExplorada = false;
        boolean tunelPrincipalExplorado = false;
        boolean salaCristaisExplorada = false;
        boolean lagoSubterraneoExplorado = false;

        boolean vilaExplorada = false;

        boolean montanhaExplorada = false;

        /// MENU PRINCIPAL DA EXPLORAÇÃO
        while (explorando) {
            System.out.println("\n=== Lugares para explorar ===");
            System.out.println("1 - Floresta Nebulosa");
            System.out.println("2 - Caverna das Sombras");
            System.out.println("3 - Vila Abandonada");
            System.out.println("4 - Montanha Sombria");
            System.out.println("5 - Voltar ao menu principal");
            System.out.print("Escolha para onde ir: ");

            // Lê o número digitado pelo jogador e converte o texto digitado (String) em número inteiro (int)
            int opcao = Integer.parseInt(br.readLine());

            /// FLORESTA NEBULOSA
            if (opcao == 1) {

                // Verifica se o jogador já explorou antes
                if (florestaExplorada) {
                    System.out.println("\nVocê já explorou completamente a Floresta Nebulosa. Não há mais nada de novo por aqui.");
                    continue; // Volta ao menu principal
                }

                // Define que o jogador está atualmente explorando a floresta
                boolean explorandoFloresta = true;

                System.out.println("\nVocê entrou na Floresta Nebulosa.");
                System.out.println("O ar é denso e cheio de névoa... algo se move entre as árvores.\n");

                // Mostra status atual do personagem ao entrar na área
                System.out.println("\nStatus atual antes de explorar:");
                System.out.println(jogador);

                // Enquanto o jogador estiver dentro da floresta
                while (explorandoFloresta) {
                    System.out.println("\n=== Trilhas da Floresta Nebulosa ===");

                    // Exibe apenas as trilhas que ainda não foram exploradas
                    if (!trilhaEsquerdaExplorada)
                        System.out.println("1 - Seguir pela trilha da esquerda");
                    if (!trilhaCentralExplorada)
                        System.out.println("2 - Seguir pela trilha central");
                    if (!trilhaDireitaExplorada)
                        System.out.println("3 - Seguir pela trilha da direita");

                    System.out.println("4 - Sair da floresta");
                    System.out.print("Escolha o caminho: ");

                    // Lê o número digitado pelo jogador e converte o texto digitado (String) em número inteiro (int)
                    int escolha = Integer.parseInt(br.readLine());

                    /// TRILHA ESQUERDA
                    // Nessa trilha o jogador enfrenta um zumbi
                    if (escolha == 1 && !trilhaEsquerdaExplorada) {
                        System.out.println("\nVocê segue pela trilha da esquerda...");
                        System.out.println("Das sombras surge um zumbi!");

                        // Cria um inimigo do tipo Zumbi e inicia a batalha (chama o método batalhar)
                        Inimigo zumbi = new Inimigo("Zumbi", (short) 20, (short) 8, (short) 3, (short) 1, new Inventario());
                        batalhar(jogador, zumbi, br, "Floresta Nebulosa");

                        trilhaEsquerdaExplorada = true; // Marca como explorada
                    }

                    /// TRILHA CENTRAL
                    // O jogador encontra um altar mágico e ganha aumento de HP máximo
                    else if (escolha == 2 && !trilhaCentralExplorada) {
                        System.out.println("\nVocê encontra um pequeno altar coberto por musgo e inscrições antigas...");
                        System.out.println("Um estranho brilho verde emana das pedras... parece reagir à sua presença.");

                        System.out.println("Você se aproxima e toca o altar...");
                        System.out.println("De repente, uma luz intensa envolve sua mão e um objeto se materializa diante de você!\n");

                        // O personagem recebe uma benção: aumenta a vida máxima em 10
                        jogador.vidaMax += 10;

                        System.out.println("Você recebeu a bênção do Amuleto da Floresta!");
                        System.out.println("Seu HP máximo aumentou em 10 pontos!");
                        System.out.println("HP: " + jogador.pontosVida + "/" + jogador.vidaMax);;

                        trilhaCentralExplorada = true; // Marca como explorada
                    }

                    /// TRILHA DIREITA
                    // O jogador é atacado por um lobo sombrio
                    else if (escolha == 3 && !trilhaDireitaExplorada) {
                        System.out.println("\nVocê tenta sair rapidamente da trilha...");
                        System.out.println("Mas uma fera te avista e ataca!");

                        // Cria um inimigo do tipo Lobo Sombrio e inicia a batalha (chama o método batalhar)
                        Inimigo lobo = new Inimigo("Lobo Sombrio", (short) 45, (short) 10, (short) 4, (short) 1, new Inventario());
                        batalhar(jogador, lobo, br, "Floresta Nebulosa");

                        trilhaDireitaExplorada = true; // Marca como explorada
                    }

                    /// SAIR DA FLORESTA
                    else if (escolha == 4) {
                        System.out.println("Você deixa a floresta para trás por enquanto.");
                        explorandoFloresta = false; // Sai do loop da floresta
                    }

                    /// OPÇÃO INVÁLIDA
                    else {
                        System.out.println("Opção inválida ou trilha já explorada.");
                    }

                    // Se todas as trilhas foram exploradas, a floresta é concluída
                    if (trilhaEsquerdaExplorada && trilhaCentralExplorada && trilhaDireitaExplorada) {
                        System.out.println("\nVocê explorou todas as trilhas da Floresta Nebulosa!");
                        System.out.println("Nada mais resta neste lugar misterioso...");

                        florestaExplorada = true; // Marca como concluída no mapa geral
                        explorandoFloresta = false; // Sai da floresta
                    }
                }
            }

            /// CAVERNA DAS SOMBRAS
            else if (opcao == 2) {

                // Verifica se o jogador já explorou antes
                if (cavernaExplorada) {
                    System.out.println("\nVocê já explorou completamente a Caverna das Sombras. O silêncio reina no subsolo...");
                    continue; // Volta ao menu principal
                }

                // Define que o jogador está atualmente explorando a caverna
                boolean explorandoCaverna = true;

                System.out.println("\nVocê entrou na Caverna das Sombras.");
                System.out.println("O ar é pesado e o som de gotas d'água ecoa pelas paredes úmidas...\n");

                // Mostra status atual antes de começar a exploração
                System.out.println("\nStatus atual antes de explorar:");
                System.out.println(jogador);

                // Enquanto o jogador estiver dentro da caverna
                while (explorandoCaverna) {
                    System.out.println("\n=== Setores da Caverna das Sombras ===");

                    // Mostra apenas as partes que ainda não foram exploradas
                    if (!tunelPrincipalExplorado)
                        System.out.println("1 - Seguir pelo túnel principal");
                    if (!salaCristaisExplorada)
                        System.out.println("2 - Explorar a sala dos cristais brilhantes");
                    if (!lagoSubterraneoExplorado)
                        System.out.println("3 - Ir até o lago subterrâneo");

                    System.out.println("4 - Sair da caverna");
                    System.out.print("Escolha: ");

                    // Lê o número digitado pelo jogador e converte o texto digitado (String) em número inteiro (int)
                    int escolha = Integer.parseInt(br.readLine());

                    /// TÚNEL PRINCIPAL
                    // O jogador enfrenta um Troll das Sombras
                    if (escolha == 1 && !tunelPrincipalExplorado) {
                        System.out.println("\nVocê avança pelo túnel principal...");
                        System.out.println("Do escuro surge um enorme Troll das Sombras!");

                        // Cria o inimigo Troll e inicia a batalha (chama o método batalhar)
                        Inimigo troll = new Inimigo("Troll das Sombras", (short) 30, (short) 12, (short) 6, (short) 2, new Inventario());
                        batalhar(jogador, troll, br, "Caverna das Sombras");

                        tunelPrincipalExplorado = true; // Marca como explorado
                    }

                    /// SALA DOS CRISTAIS
                    // O jogador ganha um item mágico que aumenta seu ataque
                    else if (escolha == 2 && !salaCristaisExplorada) {
                        System.out.println("\nVocê entra em uma câmara iluminada por cristais azuis pulsantes...");
                        System.out.println("Ao se aproximar, um deles se destaca e flutua até sua mão!");

                        // Cria o item e adiciona ao inventário
                        Item cristal = new Item("Cristal das Sombras", "Emite energia mágica intensa", "+15 ataque", 1);
                        jogador.inventario.adicionarItem(cristal);

                        System.out.println("Você obteve: " + cristal.getNome() + "! Seu corpo vibra com poder arcano...");

                        // Aumenta o ataque do personagem
                        jogador.ataque += 15;
                        System.out.println("Seu ataque máximo aumentou! Ataque atual: " + jogador.ataque);

                        // Mostra status após o ganho do cristal
                        System.out.println("\n=== Status Atual Após o Ganho do Cristal ===");
                        System.out.println(jogador);
                        System.out.println("============================================");

                        salaCristaisExplorada = true; // Marca como explorado
                    }

                    /// LAGO SUBTERRÂNEO
                    // O jogador enfrenta a Serpente das Profundezas
                    else if (escolha == 3 && !lagoSubterraneoExplorado) {
                        System.out.println("\nVocê chega a um lago subterrâneo de águas negras...");
                        System.out.println("Algo se move sob a superfície, uma Serpente das Profundezas aparece!");

                        // Cria a inimiga Serpente e inicia a batalha (chama o método batalhar)
                        Inimigo serpente = new Inimigo("Serpente das Profundezas", (short) 35, (short) 14, (short) 5, (short) 2, new Inventario());
                        batalhar(jogador, serpente, br, "Caverna das Sombras");

                        lagoSubterraneoExplorado = true;// Marca como explorado
                    }

                    /// SAIR DA CAVERNA
                    else if (escolha == 4) {
                        System.out.println("Você sai da caverna e volta para a superfície.");
                        explorandoCaverna = false; // Sai do loop da caverna
                    }

                    /// OPÇÃO INVÁLIDA
                    else {
                        System.out.println("Opção inválida ou setor já explorado.");
                    }

                    // Verifica se todas as áreas da caverna foram visitadas
                    if (tunelPrincipalExplorado && salaCristaisExplorada && lagoSubterraneoExplorado) {
                        System.out.println("\nVocê explorou todos os setores da Caverna das Sombras!");
                        System.out.println("Nada mais resta nas profundezas...");

                        cavernaExplorada = true; // Marca a caverna como concluída no mapa principal
                        explorandoCaverna = false; // Sai do loop de exploração
                    }
                }
            }

            /// VILA ABANDONADA
            // O jogador encontra com uma forte explosão
            else if (opcao == 3) {

                // Verifica se o jogador já explorou antes
                if (vilaExplorada) {
                    System.out.println("\nVocê já passou pela Vila Abandonada. O silêncio permanece... nada mais pode ser feito aqui.");
                    continue; // Volta ao menu principal
                }

                System.out.println("\nVocê chega à Vila Abandonada...");

                // Mostra status atual do personagem antes de explorar
                System.out.println("\nStatus atual antes de explorar:");
                System.out.println(jogador);

                // Descrição narrativa da vila
                System.out.println("As casas estão em ruínas e há marcas de magia nas paredes...");
                System.out.println("Um frio percorre sua espinha... a energia sombria ainda paira no ar.");
                System.out.println("De repente, uma explosão mágica atinge o chão perto de você!");
                System.out.println("Você é lançado para trás e sente uma forte dor no peito...");

                // O jogador perde 15 pontos de vida por causa da explosão
                jogador.pontosVida -= 15;

                // Garante que a vida nunca fique negativa (ela zero se ficar negativo)
                if (jogador.pontosVida < 0) {
                    jogador.pontosVida = 0;
                }

                System.out.println("Você perdeu 15 pontos de vida! HP atual: " + jogador.pontosVida);

                // Caso o jogador morra com o golpe
                if (!jogador.estaVivo()) {
                    System.out.println("\nA energia maligna consome suas forças...");
                    System.out.println("Você cai de joelhos e tudo escurece...");
                    System.out.println("Fim de jogo!");

                    // Encerra o jogo
                    System.exit(0);
                }

                System.out.println("Mesmo ferido, você consegue se arrastar até uma parede quebrada e respirar por um instante...");

                // Mostra status completo após o evento
                System.out.println("\n=== STATUS ATUAL APÓS O INCIDENTE ===");
                System.out.println(jogador);
                System.out.println("=====================================\n");

                vilaExplorada = true; // Marca como já explorada
            }

            /// MONTANHA SOMBRIA
            // Finalmente encontra o dragão Rex
            else if (opcao == 4) {

                // Impede o jogador de enfrentar o dragão mais de uma vez
                if (montanhaExplorada) {
                    System.out.println("\nVocê já enfrentou o que havia na Montanha Sombria. O eco distante do dragão é tudo que resta...");
                    continue; // Volta ao menu principal
                }

                System.out.println("\nVocê chegou à Montanha Sombria. O ar é pesado e o chão treme sob seus pés...");

                // Mostra status atual antes da batalha final
                System.out.println("\nStatus atual antes da batalha final:");
                System.out.println(jogador);

                System.out.println("O dragão Rex te aguarda no topo da montanha!");

                // Cria o inimigo final, o dragão Rex e faz a batalha (chama o método batalhar)
                Inimigo dragao = new Inimigo("Rex", (short) 90, (short) 20, (short) 10, (short) 5, new Inventario());
                batalhar(jogador, dragao, br, "Montanha Sombria");

                // Mensagem de vitória final
                System.out.println("\nAs chamas do dragão se apagam lentamente... você venceu uma batalha lendária!");
                System.out.println("O Reino de Aurora está finalmente livre do terror de Rex.");

                montanhaExplorada = true; // Marca como já explorada
            }

            /// SAIR DA EXPLORAÇÃO
            else if (opcao == 5) {
                System.out.println("\nVocê retorna ao menu principal.");
                explorando = false; // Sai do loop principal de exploração
            }

            /// OPÇÃO INVÁLIDA
            else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    /// BATALHAR CONTRA UM INIMIGO
    public static boolean batalhar(Personagem jogador, Inimigo inimigo, BufferedReader br, String local) throws IOException {

        // Mostra o nome do inimigo em destaque antes da luta
        System.out.println("\n===BATALHA CONTRA " + inimigo.nome.toUpperCase() + " ===");

        /// MOSTRA STATUS INICIAIS ANTES DA LUTA
        System.out.println("\n   Você:");
        System.out.println("   Vida: " + jogador.pontosVida);
        System.out.println("   Vida Máx: " + jogador.vidaMax);
        System.out.println("   Ataque: " + jogador.ataque);
        System.out.println("   Defesa: " + jogador.defesa);

        System.out.println("\n   Inimigo:");
        System.out.println("   Nome: " + inimigo.nome);
        System.out.println("   Vida: " + inimigo.pontosVida);
        System.out.println("   Vida Máx: " + inimigo.vidaMax);
        System.out.println("   Ataque: " + inimigo.ataque);
        System.out.println("   Defesa: " + inimigo.defesa);
        System.out.println("\nPrepare-se para o combate!\n");

        // Inicia a batalha chamando o método 'batalhar' da classe Personagem e armazena o resultado da batalha na variável venceu
        boolean venceu = jogador.batalhar(inimigo, br, local);

        /// SE O JOGADOR PERDER
        // Verifica se o jogador ficou sem vida após a luta
        if (!jogador.estaVivo()) {
            System.out.println("\nVocê foi derrotado... o Reino de Aurora cai nas sombras.");
            System.out.println("Fim de jogo!");

            // Encerra o jogo
            System.exit(0);
        }

        /// SE O JOGADOR VENCER
        if (venceu) {
            System.out.println("Você vasculha o corpo do inimigo em busca de algo útil...");

            // Objeto sorteador de um número aleatório
            /*
             Aqui você está criando um objeto da classe Random
             Essa classe é da biblioteca java.util e serve para gerar números aleatórios
             Obs: È como se fosse um dado invisível que o computador pode rolar para você sempre que quiser um número novo e imprevisível
            */
            Random random = new Random();

            // Gera um valor aleatório do tipo boolean, ou seja, true ou false (50% de ser true e 50% de ser false)
            boolean dropou = random.nextBoolean();

            // Se tiver sorte de encontrar algo (dropou = true)
            if (dropou) {
                // Gerando o número aleatório
                /*
                 Gero um dado de 6 lados ((6-1) + 1), ou seja, pode ser sorteado um número aleatório de 1 a 6
                 O valor sorteado é armazenado na variável sorteio
                */
                int sorteio = random.nextInt(6) + 1;

                // Cria uma variável do tipo Item que vai guardar o item que o jogador ganhar
                Item itemDropado;

                // Escolhe o item conforme o número sorteado
                /*
                 O comando switch verifica o valor da variável 'sorteio' (entre 1 e 6)
                 e executa o bloco de código correspondente ao número sorteado
                */
                switch (sorteio) {
                    case 1:
                        itemDropado = new Item(
                                "Poção de Cura",
                                "Recupera vida",
                                "+20 HP",
                                1
                        );
                        break;
                    case 2:
                        itemDropado = new Item(
                                "Raiz de Mirtilha",
                                "Recupera vida",
                                "+8 HP",
                                1
                        );
                        break;
                    case 3:
                        itemDropado = new Item(
                                "Amuleto Guardião",
                                "Fornece alta proteção",
                                "+20 defesa",
                                1
                        );
                        break;
                    case 4:
                        itemDropado = new Item(
                                "Escudo Velho",
                                "Fornece leve proteção",
                                "+5 defesa",
                                1
                        );
                        break;
                    case 5:
                        itemDropado = new Item(
                                "Orbe do Desespero",
                                "Libera uma onda de energia que causa dano aos inimigos",
                                "8 de dano ao inimigo",
                                1
                        );
                        break;
                    case 6:
                        itemDropado = new Item(
                                "Poção de Fúria",
                                "Uma poção que aumenta a força",
                                "+12 ataque",
                                1
                        );
                        break;
                    default: // Se nenhum dos cases anteriores for correspondido (verdadeiro) é executado esse pedaço
                        itemDropado = new Item(
                                "Item Desconhecido",
                                "Você não sabe o que é isso",
                                "???",
                                1
                        );
                }

                // Mostra o item obtido e adiciona ao inventário do jogador
                System.out.println("O inimigo deixou cair: " + itemDropado.getNome() + "!");
                jogador.inventario.adicionarItem(itemDropado);
            } else {
                System.out.println("O inimigo não deixou nenhum item para trás.");
            }

            /// CHANCE RARA DE ITEM EXTRA (10%)
            /*
             * Além do drop normal, há uma chance de 10% de encontrar um item
             * extra no inventário do inimigo. Isso simula uma recompensa rara.
            */

            // Objeto sorteador de um número aleatório
            /*
             Aqui você está criando um objeto da classe Random
             Essa classe é da biblioteca java.util e serve para gerar números aleatórios
             Obs: È como se fosse um dado invisível que o computador pode rolar para você sempre que quiser um número novo e imprevisível
            */
            Random chanceExtra = new Random();

            // Gerando o número aleatório
            /*
             Gero um dado de 100 lados ((100-1) + 1), ou seja, pode ser sorteado um número aleatório de 1 a 100
             O valor sorteado é armazenado na variável rolagemExtra
            */
            int rolagemExtra = chanceExtra.nextInt(100) + 1; // de 1 a 100

            // Se o número sorteado for menor ou igual a 10, significa que o jogador teve 10% de chance de sorte
            // Garante que o inimigo tem um inventário criado (ou seja, não é null)
            // Verifica se o inventário não está vazio (! = negação)
            if (rolagemExtra <= 10 && inimigo.inventario != null && !inimigo.inventario.estaVazio()) {

                try {
                    // Clona o inventário do inimigo
                    Inventario inventarioClonado = (Inventario) inimigo.inventario.clone();

                    // Pega o primeiro item da cópia (pois inimigos têm apenas 1)
                    Item itemExtra = inventarioClonado.getItens().get(0);

                    // Adiciona o item ao inventário do jogador
                    jogador.inventario.adicionarItem(itemExtra);

                    System.out.println("\nVocê vasculha melhor o inimigo e encontra algo a mais!");
                    System.out.println("Você obteve um item raro: " + itemExtra.getNome() + "!");
                } catch (Exception erro) {
                    System.out.println("Erro ao tentar copiar o item extra do inimigo.");
                }
            }

            /// GANHO DE NÍVEL (XP)
            /*
             * O jogador ganha experiência proporcional à dificuldade do inimigo.
             * Inimigos mais fortes (como o Dragão Rex) concedem mais níveis.
            */
            int niveisGanho;

            if (inimigo.nome.contains("Rex")) {
                niveisGanho = 5;
            } else if (inimigo.nome.contains("Zumbi")) {
                niveisGanho = 1;
            } else if (inimigo.nome.contains("Troll das Sombras")) {
                niveisGanho = 2;
            } else if (inimigo.nome.contains("Lobo Sombrio")) {
                niveisGanho = 3;
            } else {
                niveisGanho = 1;
            }

            // Aumenta o nível do jogador (chama o método subirNivel)
            jogador.subirNivel(niveisGanho);

            // Mostra status atualizado do jogador após a luta
            System.out.println("\nSeu estado atual após a batalha:");
            System.out.println(jogador);
        }

        // Retorna o resultado da batalha (true = venceu, false = perdeu/fugiu)
        return venceu;
    }

    /// USAR ITEM
    public static void usarItem(Personagem jogador, Personagem inimigo, BufferedReader br) throws IOException {

        // Verifica se o inventário está vazio
        if (jogador.inventario.estaVazio()) {
            System.out.println("Seu inventário está vazio!");
            System.out.println("Você não pode usar nenhum item agora.");
            System.out.println("=======================");
            return; // Encerra o método
        }

        // Mostra todos os itens disponíveis
        jogador.inventario.listarItens();

        // Solicita o nome do item que o jogador quer usar
        System.out.print("Digite o nome do item que deseja usar: ");

        // Converte tudo para minúsculo para facilitar a comparação
        String nomeItem = br.readLine().trim().toLowerCase();

        // Verifica se o item realmente existe no inventário
        if (!jogador.inventario.temItem(nomeItem)) {
            System.out.println("Esse item não está no seu inventário!");
            return; // Se não tiver, encerra o método.
        }

        // Objeto sorteador de um número aleatório
        /*
         Aqui você está criando um objeto da classe Random
         Essa classe é da biblioteca java.util e serve para gerar números aleatórios
         Obs: È como se fosse um dado invisível que o computador pode rolar para você sempre que quiser um número novo e imprevisível
        */
        Random random = new Random();

        // Gerando o número aleatório
        /*
         Gero um dado de 6 lados ((6-1) + 1), ou seja, pode ser sorteado um número aleatório de 1 a 6
         O valor sorteado é armazenado na variável sorteio
        */
        int dado = random.nextInt(6) + 1;

        // Sucesso se tirar 4, 5 ou 6
        boolean sucesso = dado >= 4;

        // Caso o jogador consiga usar o item com sucesso
        if (sucesso) {

            /// ITENS DE CURA
            // Itens que restauram pontos de vida
            // Contains não sabe diferenciar com ou sem cento por isso preciso colocar os dois
            if (nomeItem.contains("poção de cura") || nomeItem.contains("pocao de cura")) {
                jogador.curar((short) 20);
            } else if (nomeItem.contains("mirtilha")) {
                jogador.curar((short) 8);
            }

            /// ITENS DE DEFESA
            // Itens que aumentam a defesa do personagem
            else if (nomeItem.contains("amuleto guardião") || nomeItem.contains("amuleto guardiao")) {
                jogador.defesa += 20;
                System.out.println("Você ganhou 20 pontos de defesa! Defesa atual: " + jogador.defesa + "\n");
            } else if (nomeItem.contains("escudo")) {
                jogador.defesa += 5;
                System.out.println("Você ganhou 5 pontos de defesa! Defesa atual: " + jogador.defesa + "\n");
            }

            /// ITENS DE ATAQUE / DANO
            else if (nomeItem.contains("orbe do desespero")) {
                inimigo.receberDano((short) 8); // Causa dano diretamente no inimigo
                System.out.println("Você libera uma onda de energia! O inimigo perde 8 de vida. HP inimigo: " + inimigo.pontosVida + "\n");
            } else if (nomeItem.contains("fúria") || nomeItem.contains("furia")) {
                jogador.ataque += 12;
                System.out.println("Você entra em fúria! +12 de ataque. Ataque atual: " + jogador.ataque + "\n");
            } else if (nomeItem.contains("elixir do vento")) {
                inimigo.congelar(1); // Congela inimigo por 1 turno
                System.out.println("Você congela o inimigo por um turno!\n");
            } else if (nomeItem.contains("flecha")) {
                inimigo.receberDano((short) 5); // Causa 5 danos
                System.out.println("Você dispara a Flecha Envenenada! O inimigo perde 5 pontos de vida.\n");
            } else if (nomeItem.contains("faca")) {
                inimigo.receberDano((short) 5); // Causa 5 danos
                System.out.println("Você golpeia o inimigo com sua faca e causa 5 pontos de dano!\n");
            }
            else if (nomeItem.contains("cristal das sombras")) {
                System.out.println("O cristal já infundiu seu poder quando foi obtido. Nada acontece desta vez.\n");
            }

            /// ITENS LENDÁRIOS
            // São muito poderosos, dando grandes bônus de ataque, defesa ou vida
            else if (nomeItem.contains("espada do crepúsculo") || nomeItem.contains("espada do crepusculo")) {
                jogador.ataque += 30;
                System.out.println("O poder do crepúsculo flui através de você! +30 de ataque. Ataque atual: " + jogador.ataque + "\n");
            } else if (nomeItem.contains("armadura do guardião") || nomeItem.contains("armadura do guardiao")) {
                jogador.defesa += 25;
                System.out.println("Os espíritos protetores o envolvem! +25 de defesa. Defesa atual: " + jogador.defesa + "\n");
            } else if (nomeItem.contains("anel da eternidade")) {
                jogador.vidaMax += 100;   // Isso aumenta o limite máximo de vida do personagem
                jogador.curar((short) 100); // Aqui o personagem recupera 100 pontos de vida (até o novo máximo)
                System.out.println("O poder do Anel da Eternidade flui em você! +100 de Vida Máxima.");
                System.out.println("HP atual: " + jogador.pontosVida + "/" + jogador.vidaMax + "\n");
            } else if (nomeItem.contains("orbe das almas")) {
                jogador.ataque += 15;
                jogador.defesa += 10;
                System.out.println("As almas derrotadas fortalecem você! +15 de ataque, +10 de defesa.\n");
            }

            /// ITEM DE EFEITO ALEATÓRIO
            else if (nomeItem.contains("relíquia") || nomeItem.contains("reliquia")) {

                // Gerando o número aleatório
                /*
                 Gero um dado de 3 lados ((3-1) + 1), ou seja, pode ser sorteado um número aleatório de 1 a 3
                 O valor sorteado é armazenado na variável efeito
                */
                int efeito = random.nextInt(3);

                switch (efeito) {
                    case 0:
                        jogador.ataque += 10;

                        System.out.println("Um poder estranho aumenta seu ataque em +10!\n");
                        break;
                    case 1:
                        jogador.defesa += 10;

                        System.out.println("Uma energia misteriosa fortalece sua defesa em +10!\n");
                        break;
                    case 2:
                        jogador.vidaMax += 30; // aumenta o limite
                        jogador.curar((short) 30); // cura até esse novo máximo

                        System.out.println("Você sente uma força vital inexplicável! +30 de Vida Máxima.");
                        System.out.println("HP atual: " + jogador.pontosVida + "/" + jogador.vidaMax + "\n");
                        break;
                }
            }

            /// ITEM SEM EFEITO CONHECIDO
            else {
                System.out.println("Você usa o item, mas nada acontece... talvez seu poder ainda seja desconhecido.\n");
            }

            // Remove uma unidade do item usado (caso o jogador tenha mais de uma)
            jogador.inventario.removerItem(nomeItem, 1);
        }

        // Caso o nome do item não bata com nenhum dos casos anteriores
        else {
            System.out.println("\nVocê tenta usar o item, mas falha miseravelmente!\n");
        }
    }

    /// FUGIR DE UMA BATALHA
    public static void fugir(Personagem jogador, BufferedReader br, String local) throws IOException {
        System.out.println("\nVocê tenta fugir...");

        // Caso especial: o dragão Rex (não é possível fugir do dração Rex)
        // Verifica se o local atual tem a palavra "Montanha" ou "Rex"
        if (local.contains("Montanha") || local.contains("Rex")) {
            System.out.println("Você dá alguns passos para trás, mas o chão treme violentamente...");
            System.out.println("O rugido ensurdecedor de Rex ecoa pela montanha!");
            System.out.println("Uma torrente de fogo cobre tudo ao seu redor!");

            // Aplica dano igual à vida total do jogador, ou seja, o personagem morre instantaneamente
            jogador.receberDano(jogador.pontosVida);

            /// Personagem é derrotado pelo dragão Rex
            System.out.println("\n=== DERROTA ===");
            System.out.println("Você foi engolido pelas chamas do dragão Rex...");
            System.out.println("O Reino de Aurora cai nas sombras mais uma vez.");
            System.out.println("Fim de jogo.");

            // Encerra o jogo
            System.exit(0);
            return;
        }

        // Objeto sorteador de um número aleatório
        /*
         Aqui você está criando um objeto da classe Random
         Essa classe é da biblioteca java.util e serve para gerar números aleatórios
         Obs: È como se fosse um dado invisível que o computador pode rolar para você sempre que quiser um número novo e imprevisível
        */
        Random random = new Random();

        // Gerando o número aleatório
        /*
         Gero um dado de 6 lados ((6-1) + 1), ou seja, pode ser sorteado um número aleatório de 1 a 6
         O valor sorteado é armazenado na variável sorteio
        */
        int rolagem = random.nextInt(6) + 1;

        // Determina onde o jogador se esconde e mostra uma descrição diferente para cada lugar
        String esconderijo;

        if (local.contains("Floresta")) {
            esconderijo = "entre as árvores";
        } else if (local.contains("Caverna")) {
            esconderijo = "atrás de uma pedra";
        } else if (local.contains("Vila")) {
            esconderijo = "atrás de uma parede quebrada";
        } else if (local.contains("Montanha")) {
            esconderijo = "em uma fenda na rocha";
        } else {
            esconderijo = "em um canto seguro";
        }

        System.out.println("Você se esconde " + esconderijo + " por um certo tempo e logo retoma a exploração.");

        // Decide se a fuga foi bem-sucedido (se o número sorteado for par, o jogador escapa com sucesso)
        if (rolagem % 2 == 0) {
            System.out.println("Você respira aliviado e continua sua jornada.");
            System.out.println("Você consegue escapar com sucesso!");
        }

        // Se o número foi ímpar, o jogador tropeça e leva um golpe antes de fugir
        else {
            System.out.println("Você tropeça ao tentar fugir!");
            System.out.println("O inimigo te alcança e acerta um golpe antes de você escapar!");

            // O jogador perde 8 pontos de vida como penalidade
            short dano = 8;
            jogador.receberDano(dano);

            System.out.println("Você perdeu " + dano + " pontos de vida. HP atual: " + jogador.pontosVida);

            // Mostra o status completo do personagem depois da tentativa
            System.out.println("\n=== STATUS APÓS A TENTATIVA DE FUGA ===");
            System.out.println(jogador);
            System.out.println("=======================================\n");

            // Caso o jogador morra durante a fuga
            // Só morre se o jogador estiver com pouca vida e esse dano de 8 for suficiente para zerar os pontos de vida
            if (!jogador.estaVivo()) {
                System.out.println("\nVocê não resistiu ao golpe durante a fuga...");
                System.out.println("Fim de jogo!");

                // Encerra o jogo
                System.exit(0);
            }

            // Caso sobreviva
            else {
                System.out.println("Mesmo ferido, você consegue se afastar do perigo.");
                System.out.println("Você respira aliviado e continua sua jornada.");
                System.out.println("Você consegue escapar com sucesso!");
            }
        }
    }
}
