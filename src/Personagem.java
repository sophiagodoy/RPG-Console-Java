import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

/// Classe Personagem
// Molde base para criar todos os personagens
abstract class Personagem {

    // Atributos (protected para que eles possam ser herdados de outras classes)
    protected String nome;
    protected short pontosVida;
    protected short vidaMax;
    protected short ataque;
    protected short defesa;
    protected short nivel;
    protected Inventario inventario;
    protected int turnosCongelado = 0;

    /// Construtor com parâmetros
    public Personagem(String nome, short pontosVida, short ataque, short defesa, short nivel, Inventario inventario) {
        this.nome = nome;
        this.pontosVida = pontosVida;

        // Define a vida máxima igual à vida atual,
        // pois ao criar o personagem ele começa com a vida cheia (ou seja, 100% da vida máxima)
        this.vidaMax = pontosVida;

        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = inventario;
    }

    /// Verifica se o personagem está vivo
    public boolean estaVivo() {
        // Retorna true se a vida for maior que zero
        return pontosVida > 0;
    }

    /// Personagem recebe dano
    // È é chamado quando o personagem leva um golpe e perde pontos de vida
    public void receberDano(short dano) {
        // Diminui a vida de acordo com o dano causado
        pontosVida -= dano;

        // Garante que a vida não fique negativo, ela zera em vez disso
        if (pontosVida < 0) {
            pontosVida = 0;
        }
    }

    /// Cura o personagem (sem ultrapassar o máximo)
    public void curar(short quantidade) {
        // Se a quantidade for zero ou negativa, não faz nada (ele já está morto)
        if (quantidade <= 0) return;

        // Calcula o quanto falta para a vida do personagem chegar no limite
        int falta = vidaMax - pontosVida;

        // Garante que a cura nunca passe do máximo permitido e nunca seja negativa
        /*
         Math.min(falta, quantidade) → pega o menos valor entre quantidade e falta

         Exemplo:
         → falta = 20 e quantidade = 10
         → Pega o 10

         Math.max(0, ...) → pega o maior valor entre 0 e o resultado anterior, isso garante que o resultado nunca seja negativo

         Exemplo:
         → Math.min(falta, quantidade) = 10
         → Entre 0 e 10, o maior é 10, então pega o 10

         → Math.min(falta, quantidade) = -5
         → Entre 0 e -5, o maior é 0, então pega o 0 (não deixa ser negativo)
         */
        int curada = Math.max(0, Math.min(falta, quantidade));

        // Se não há pontos para recuperar (vida já cheia), apenas mostra a mensagem e encerra
        if (curada == 0) {
            System.out.println(nome + " já está com HP cheio. (" + pontosVida + "/" + vidaMax + ")");
            return;
        }

        // Aplica a cura de fato
        pontosVida += curada; // Adiciona a cura à vida (pontosVida)
        System.out.println(nome + " recuperou " + curada + " pontos de vida. HP atual: " + pontosVida + "/" + vidaMax);
    }

    /// Faz o personagem subir de nível
    public void subirNivel(int quantidade) {

        // Aumenta o nível do personagem pelo número informado
        // Quantidade representa quantos níveis o personagem vai subir de uma vez
        this.nivel += quantidade;

        /*
         Cada vez que sobe de nível, ele ganha um aumento em todos os atributos
         Aqui é o bônus padrão (ou seja, todos os personagens ganham isso)

         (short) (5 * quantidade) → esse cast força o Java a converter o resultado em short, porque por padrão ele multiplica como int
        */
        short aumentoVidaMax = (short) (5 * quantidade);
        short aumentoAtaque = (short) (2 * quantidade);
        short aumentoDefesa = (short) (1 * quantidade);

        /*
         Verifica qual é a classe do personagem e aplica o bônus extra correspondente
         Esse bônus NÃO substitui o anterior, ele soma valores adicionais ao bônus padrão
         Ele pega o resultado do bonus padrão e somo com o bonus de cada classe

         instanceof é um operador do Java que verifica se um objeto é de um tipo específico (ou seja, de uma classe específica)
        */
        if (this instanceof Guerreiro) {
            aumentoVidaMax += (short) (3 * quantidade);
            aumentoDefesa += (short) (2 * quantidade);
        } else if (this instanceof Mago) {
            aumentoAtaque += (short) (3 * quantidade);
        } else if (this instanceof Arqueiro) {
            aumentoAtaque += (short) (2 * quantidade);
            aumentoDefesa += (short) (1 * quantidade);
        }

        /*
         Atualiza os atributos do personagem com os aumentos calculados
         Pega a vida máxima atual do personagem e adiciona o aumento total (base + bônus de classe)
         Esse é o momento em que o personagem realmente fica mais forte
        */
        this.vidaMax += aumentoVidaMax;
        this.ataque += aumentoAtaque;
        this.defesa += aumentoDefesa;

        // Exibe a mensagem de evolução
        System.out.println("\n=== SUBIU DE NÍVEL! ===");
        System.out.println("Novo nível: " + this.nivel);
        System.out.println("Ganhou +" + aumentoVidaMax + " Vida Máxima, +" + aumentoAtaque + " Ataque, +" + aumentoDefesa + " Defesa!");

        // O personagem recebe uma cura automática, equivalente à metade da vida que ele ganhou ao subir de nível
        /*
         Exemplo:
         vida antes: 45/60
         aumentoVidaMax = + 10
         nova vida máxima = 60 + 10 = 70
         cura aplicada = 10 ÷ 2 = 5
         nova vida atual = 45 + 5 = 70
        */
        curar((short)(aumentoVidaMax / 2));

        System.out.println("HP: " + pontosVida + "/" + vidaMax);
    }

    /// Atacar personagem
    public void atacar(Personagem alvo) {

        // Objeto sorteador de um número aleatório
        /*
         Aqui você está criando um objeto da classe Random
         Essa classe é da biblioteca java.util e serve para gerar números aleatórios
         Obs: È como se fosse um dado invisível que o computador pode rolar para você sempre que quiser um número novo e imprevisível
        */
        Random random = new Random();

        // Gerando o número aleatório
        /*
         Gero um dado de 6 lados ((5-1) + 1), ou seja, pode ser sorteado um número aleatório de 1 a 6
         O valor sorteado é armazenado na variável rolagem
         (short) serve para forçar o resultado a ser desse tipo, pois quando estamos calculando estamos trabalhando com int
        */
        short rolagem = (short) (random.nextInt(6) + 1);

        // Calcula o poder total do ataque
        // Soma o valor do ataque do personagem + rolagem do dado resultando o poder total dele
        short poderTotal = (short) (ataque + rolagem);

        // Mostra a mensagem de início do ataque
        System.out.println("\n" + nome + " parte para o ataque contra " + alvo.nome + "!");

        // Mostra a rolagem e o cálculo detalhado
        System.out.println("\n" + nome + " rola o dado e tira: " + rolagem);
        System.out.println("Ataque base de " + nome + ": " + ataque);
        System.out.println("(Rolagem do dado " + rolagem + " + Ataque " + ataque + ") = Poder total de " + nome + ": " + poderTotal);

        // Se o poder do personagem for maior que a defesa do inimigo (alvo é o parâmetro do método)
        if (poderTotal > alvo.defesa) {

            // Calcula o dano causado (diferença entre ataque e defesa)
            short dano = (short) (poderTotal - alvo.defesa);
            alvo.receberDano(dano); // Reduz o HP do inimigo

            System.out.println("O golpe de " + nome + " acerta " + alvo.nome + " e causa " + dano + " de dano!");
            System.out.println("Vida restante de " + alvo.nome + ": " + alvo.pontosVida);
        } else {
            // Caso contrário, o inimigo bloqueia o ataque
            System.out.println("\n" + nome + " ataca, mas " + alvo.nome + " bloqueia o golpe!");
            System.out.println("(Poder total do ataque de " + nome + ": " + poderTotal + " | Defesa de " + alvo.nome + ": " + alvo.defesa + ")");
            System.out.println("O ataque não foi forte o suficiente para causar dano!");
        }
    }

    /// Batalhar contra outro personagem
    public boolean batalhar(Inimigo inimigo, BufferedReader br, String local) throws IOException {

        // Mensagens iniciais (abertura da batalha)
        System.out.println("\n================================================================");
        System.out.println("                 INÍCIO DA BATALHA");
        System.out.println("================================================================");
        System.out.println("Local: " + local);
        System.out.println("Você: " + this.nome + "  VS  Inimigo: " + inimigo.nome);
        System.out.println("================================================================");

        Random random = new Random();

        // A batalha começa no primeiro turno
        int turno = 1;

        // A batalha continua enquanto os dois estiverem vivos
        while (this.estaVivo() && inimigo.estaVivo()) {

            // Exibe o número do turno
            System.out.println("\n----------------------------------------------------------------");
            System.out.println("                       TURNO " + turno);
            System.out.println("----------------------------------------------------------------");

            /// TURNO DO JOGADOR
            // Cria uma variável para guardar o que o jogador vai escolher (atacar ou fugir)
            int escolha;

            // Mostra as opções (atacar e fugir) e lê o que o jogador digitou
            while (true) {
                System.out.println("\nÉ o seu turno, " + nome + "!");
                System.out.println("1 - Atacar");
                System.out.println("2 - Tentar fugir");
                System.out.print("Escolha um número: ");

                // Lê o que o usuário digitou no teclado
                String entrada = br.readLine().trim();

                // Se a entrada que o usuário digitou não for válida
                if (entrada.equals("1") || entrada.equals("2")) {
                    // Converte o texto digitado (String) para número inteiro
                    escolha = Integer.parseInt(entrada);
                    break;
                } else {
                    System.out.println("\nOpção inválida! Digite apenas 1 ou 2.\n");
                }
            }

            /// ATACAR
            if (escolha == 1) {

                // Antes de atacar, o jogo pergunta se o jogador quer usar um item
                int usarItem; // Armazena o que o usuário deseja

                // Repete até o jogador digitar uma opção válida (1 ou 2)
                while (true) {
                    System.out.println("\nDeseja usar um item antes de atacar?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");
                    System.out.print("Escolha um número: ");

                    // Lê o que o usuário digitou no teclado
                    String entradaItem = br.readLine().trim();

                    // Se a entrada que o usuário digitou não for válida
                    if (entradaItem.equals("1") || entradaItem.equals("2")) {
                        // Converte o texto digitado (String) para número inteiro
                        usarItem = Integer.parseInt(entradaItem);
                        break;
                    } else {
                        System.out.println("\nOpção inválida! Digite apenas 1 ou 2.\n");
                    }
                }

                /// USAR ITEM
                if (usarItem == 1) {

                    // Chama o método usarItem() da classe Jogo
                    Jogo.usarItem(this, inimigo, br);

                    // Mostra status completo após usar o item
                    // Mostra na tela como ficou a vida, ataque e defesa do jogador e do inimigo depois de usar o item
                    System.out.println("\n=== STATUS APÓS USAR ITEM ===");

                    System.out.println(this.nome + " (JOGADOR)");
                    System.out.println("Vida Atual: " + this.pontosVida);
                    System.out.println("Vida Máxima: " + this.vidaMax);
                    System.out.println("Ataque: " + this.ataque);
                    System.out.println("Defesa: " + this.defesa);
                    System.out.println("Nível: " + this.nivel);

                    System.out.println("\n" + inimigo.nome + " (INIMIGO)");
                    System.out.println("Vida Atual: " + inimigo.pontosVida);
                    System.out.println("Vida Máxima: " + inimigo.vidaMax);
                    System.out.println("Ataque: " + inimigo.ataque);
                    System.out.println("Defesa: " + inimigo.defesa);
                    System.out.println("Nível: " + inimigo.nivel);

                    System.out.println("=======================================");
                }

                // Depois de usar (ou não usar) um item, o jogador realiza o ataque
                // O "this" representa o jogador atual, e o "inimigo" é o alvo do ataque
                this.atacar(inimigo);
            }

            /// FUGIR
            else if (escolha == 2) {
                // Chama o método fugir() da classe Jogo
                Jogo.fugir(this, br, local);
                return false; // Retorna false porque a batalha terminou com fuga
            } else {
                System.out.println("Opção inválida! Escolha novamente.");
                continue; // Volta para o início do loop para o jogador tentar novamente (escolher atacar ou fugir)
            }

            /// TURNO DO INIMIGO
            // Se o inimigo ainda estiver vivo após o turno do jogador, ele realiza sua ação
            if (inimigo.estaVivo()) {

                // Mensagem indicando o início do turno do inimigo
                System.out.println("\n----------------------------------------------------------------");
                System.out.println("                   TURNO DO INIMIGO: " + inimigo.nome);
                System.out.println("----------------------------------------------------------------");

                // Verifica se o inimigo está congelado
                /*
                 Se o inimigo estiver congelado, ele perde o turno e não ataca.
                 Caso contrário, ele realiza um ataque normal contra o jogador.
                */
                if (inimigo.estaCongelado()) {

                    // Exibe mensagem informando que o inimigo não pode agir
                    System.out.println(inimigo.nome + " está congelado e não pode agir neste turno!");

                    // Reduz o número de turnos que o inimigo ainda vai ficar congelado
                    inimigo.reduzirTurnoCongelado();
                } else {
                    // Se o inimigo não estiver congelado, ele ataca o jogador
                    // O "this" aqui representa o jogador atual
                    inimigo.atacar(this);
                }

                // Caso o jogador morra após o ataque do inimigo
                if (!this.estaVivo()) {
                    System.out.println("\nVocê foi derrotado por " + inimigo.nome + "...");
                    System.out.println("O Reino de Aurora cai nas sombras.");
                    System.out.println("Fim de jogo!");

                    // Encerra completamente o programa
                    System.exit(0);
                }
            }

            // Exibe o status dos dois personagens após o turno
            System.out.println("\n=================== STATUS APÓS O TURNO ===================");
            System.out.println(this.nome + " → HP: " + this.pontosVida + " | Ataque: " + this.ataque + " | Defesa: " + this.defesa);
            System.out.println(inimigo.nome + " → HP: " + inimigo.pontosVida + " | Ataque: " + inimigo.ataque + " | Defesa: " + inimigo.defesa);
            System.out.println("================================================================");

            // Caso os dois ainda estejam vivos, indica que a batalha continua
            if (this.estaVivo() && inimigo.estaVivo()) {
                System.out.println("\nA batalha continua...");
            }

            // Soma 1 número ao turno
            turno++;
        }

        /// FIM DA BATALHA - RESULTADO FINAL
        System.out.println("\n================================================================");

        // Se o jogador ainda está vivo e o inimigo morreu = vitória
        if (this.estaVivo() && !inimigo.estaVivo()) {

            System.out.println("VITÓRIA! " + this.nome + " derrotou " + inimigo.nome + "!");

            // Subtrai 1 porque o contador foi incrementado uma última vez depois que a luta terminou, então ele está “um passo à frente"
            System.out.println("Batalha encerrada em " + (turno - 1) + " turnos.");
            System.out.println("================================================================");

            // Retorna true → batalha vencida
            return true;
        } else {
            // Caso contrário, o jogador foi derrotado
            System.out.println("DERROTA... " + this.nome + " caiu em combate.");
            System.out.println("================================================================");

            // Retorna false → batalha perdida
            return false;
        }
    }

    /// Congelar personagem
    // Congela personagem por uma certa quantidade de turnos
    public void congelar(int turnos) {
        /*
         O parâmetro 'turnos' representa quantos turnos o personagem vai ficar congelado.
         Exemplo: se turnos = 2, o personagem não poderá agir por 2 turnos seguidos.

         O 'this.turnosCongelado' é o atributo da classe Personagem
         que guarda quantos turnos ainda faltam para o congelamento acabar.

         Aqui nós apenas definimos o valor da quantidade de turnos que ele fica congelado.
         Quem diminui o contador até chegar em 0 é o método reduzirTurnoCongelado
        */
        this.turnosCongelado = turnos;
    }

    /// Verifica se o personagem ainda está congelado
    public boolean estaCongelado() {
        // Se ainda estiver congelado retorna true, se não retorna false
        return this.turnosCongelado > 0;
    }

    /// Reduz o contador de turnos do personagem congelado
    // Diminui em 1 a quantidade de turnos que o personagem ainda vai permanecer congelado
    public void reduzirTurnoCongelado() {
        // Verifica se o personagem ainda está congelado (ou seja, se o número de turnos restantes é maior que zero)
        if (this.turnosCongelado > 0)
            this.turnosCongelado--; // Se estiver congelado, diminui 1 turno
    }

    /// ToString do personagem
    @Override
    public String toString() {
        return "\n===== STATUS DO PERSONAGEM =====" +
                "\nNome: " + nome +
                "\nVida Atual: " + pontosVida +
                "\nVida Máxima: " + vidaMax +
                "\nAtaque: " + ataque +
                "\nDefesa: " + defesa +
                "\nNível: " + nivel;
    }
}
