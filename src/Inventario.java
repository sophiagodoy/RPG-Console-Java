import java.util.ArrayList;
import java.util.Collections;

/// Classe Inventário
public class Inventario implements Cloneable {

    // Cria uma lista chamada itens que vai guardar os objetos da classe Item
    private ArrayList<Item> itens;

    /// Construtor com parâmetros
    // È chamado toda vez que eu crio um novo inventário, inicialmente vazio
    public Inventario() {
        /*
         Cria uma nova lista vazia do tipo Item.
         'this.itens' se refere ao atributo da classe Inventario (a lista de itens deste inventário).
         'new ArrayList<>()' inicializa essa lista para que ela possa armazenar objetos do tipo Item
        */
        this.itens = new ArrayList<>();
    }

    /// Adicionar item ao inventário
    // Recebe um parâmetro do tipo Item, chamado novoItem
    public void adicionarItem(Item novoItem) {

        // Percorrendo todos os item que já existe na lista "itens"
        for (Item item : itens) {

            /*
             Pega o nome do item atual e o nome do novo item que está sendo adicionado
             Transforma ambos em letras minúsculas (toLowerCase) para não dar erro com maiúsculas/minúsculas
             e compara se os nomes são iguais usando o método equals()
            */
            if (item.getNome().toLowerCase().equals(novoItem.getNome().toLowerCase())) {

                /*
                 Se o nome for igual, quer dizer que o item já existe no inventário
                 Então, somamos a quantidade antiga com a quantidade do novo item
                 e atualizamos o valor total usando o método setQuantidade() da classe Item
                */
                item.setQuantidade(item.getQuantidade() + novoItem.getQuantidade());
                return; // Encerra o método imediatamente já que não precisamos retornar nada, apenas atualizar
            }
        }

        // Adicionado normalmente a lista
        itens.add(novoItem);
    }

    /// Remover um item do inventário
    public void removerItem(String nomeItem, int quantidade) {

        // Remove espaços em branco no início e no fim do texto (evita erro na comparação)
        nomeItem = nomeItem.trim();

        // Percorre a lista de itens no inventário de acordo com o tamanho dela
        for (int i = 0; i < itens.size(); i++) {

            // Pega o item atual da lista na posição i
            Item item = itens.get(i);

            /*
             Verifica se o nome do item digitado existe dentro do nome de algum item do inventário
             Transforma ambos em letras minúsculas para comparar sem erro
             O método 'contains' verifica se o texto digitado está contido dentro do nome do item
            */
            if (item.getNome().toLowerCase().contains(nomeItem.toLowerCase())) {

                // Calcula o novo total de itens depois da remoção e armazena na variável novaQtd
                int novaQtd = item.getQuantidade() - quantidade;

                // Se ainda sobrar itens na lista atualiza a quantidade
                if (novaQtd > 0) {
                    item.setQuantidade(novaQtd);
                }

                // Caso a nova quantidade de itens na lista seja zero ou negativa
                // o código remove o item inteiro do inventário
                else {
                    itens.remove(i);
                }
                return;
            }
        }
        System.out.println("Item não encontrado no inventário.");
    }

    /// Listar todos os itens do inventário
    public boolean listarItens() {

        // Verifica se a lista itens está vazia
        if (itens.isEmpty()) {
            System.out.println("\nSeu inventário está vazio.");
            return false;
        }

        /*
         Ordena os itens da lista em ordem alfabética (sort() → ordena a lista) "Collections" é uma classe utilitária
         do Java que tem métodos prontos para manipular listas, como ordenar, embaralhar, inverter etc.
         Ao chamar Collections.sort(itens), o Java usa o método compareTo() que está implementado dentro da classe Item (comparando os nomes)
        */
        Collections.sort(itens);

        // Percorre a lista inteira e imprime os itens
        System.out.println("\n=== ITENS NO INVENTÁRIO ===");
        for (Item item : itens) {
            System.out.println(item);
        }
        System.out.println("==============================");

        // Retorna true, indicando que a listagem foi feita com sucesso
        return true;
    }

    /// Verificar se está o inventário está vazio
    public boolean estaVazio() {
        // Retorna true se a lista não tiver itens, false caso contrário
        return itens.isEmpty();
    }

    /// Verifica se o jogador tem um item específico no inventário
    public boolean temItem(String nomeItem) {

        // Pega o nome digitado pelo jogador, remove espaços extras e converte tudo para minúsculas (evita erros)
        nomeItem = nomeItem.trim().toLowerCase();

        // Percorre cada objeto da classe Item dentro da lista de itens
        for (Item item : itens) {

            // Pega o nome do item atual, transforma em minúsculas
            // e verifica se o texto digitado pelo jogador está contido dentro desse nome
            if (item.getNome().toLowerCase().contains(nomeItem)) {
                return true;
            }
        }

        // Se o loop terminar sem encontrar nada, retorna false
        return false;
    }

    // Retorna a lista completa de itens do inventário
    // Ele é usado internamente em situações como clonagem ou saque de itens de um inimigo
    public ArrayList<Item> getItens() {
        // Retorna a lista armazenada dentro do inventário
        return this.itens;
    }

    /// Clone()
    // Cria um novo objeto com os mesmos dados, mas em outro espaço da memória (cópias independentes)
    // Chamamos dentro do clone o construtor de cópia do inventário, e dentro dele é que cada item é clonado individualmente
    @Override
    public Object clone() {

        // Variável que vai guardar a cópia criada.
        // Ela começa como null para caso ocorra algum erro, ainda exista um valor seguro para retornar
        Inventario retorno = null;

        try
        {
            // Chamo o construtor de cópia da classe Inventário, passando o próprio inventário atual (this)
            // Assim, é criado um novo objeto Inventario com os mesmos itens do original, mas de forma independente (alterar um não afeta o outro)
            retorno = new Inventario(this);
        }

        // Caso ocorra algum erro dentro do try, o catch impede que o programa quebre
        catch (Exception erro) {}

        // Retorna o novo Inventário criado (a cópia independente do original)
        return retorno;
    }

    /// Construtor de cópia
    // É chamado quando você quer criar um novo inventário igual a outro, mas de forma independente (ou seja, alterar um não muda o outro)
    public Inventario(Inventario modelo) throws Exception {

        // Verifica se o inventário passado existe
        if (modelo == null)
            throw new Exception("Modelo ausente");

        // Cria uma nova lista de itens vazia (independente da original)
        this.itens = new ArrayList<>();

        // Percorre todos os itens do inventário original e adiciona clones deles (chamando o clone() de cada item)
        // Isso garante que cada item do novo inventário seja uma cópia independente do original
        for (Item item : modelo.itens)
            this.itens.add((Item)item.clone());
    }

    /// ToString do inventário
    @Override
    public String toString() {
        return "Inventário com " + itens.size() + " itens.";
    }
}

/*
    OBS:

    ➤ equals() → compara se os dois textos são exatamente iguais
      Exemplo:
          String nome1 = "Poção de Cura";
          String nome2 = "poção de cura";
          nome1.equals(nome2) → false
          (pois o segundo começa com letra minúscula)

    ➤ contains() → verifica se um texto está contido dentro de outro
      Exemplo:
          String nome1 = "Poção de Cura";
          String nome2 = "cura";
          nome1.contains(nome2) → true
          (pois "cura" aparece dentro de "Poção de Cura")

    Resumo:
      • equals → igualdade total (mesmo texto)
      • contains → busca parcial (parte do texto)
*/
