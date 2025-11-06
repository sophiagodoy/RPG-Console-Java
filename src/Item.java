/// Classe Item
// Nela é possível comparar dois itens (Comparable - compareTo) e criar cópiar idênticas deles (Cloneable - clone())
public class Item implements Comparable<Item>, Cloneable {

    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;

    /// Construtor com parâmetros
    // É chamado toda vez que cria um novo item (usado para inicializar os atributos)
    public Item (String nome, String descricao, String efeito, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;
    }

    /// Getters (acessar os dados)
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEfeito() {
        return efeito;
    }

    public int getQuantidade() {
        return quantidade;
    }

    /// Setter (alterar os dados)
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /// ToString() do Item
    @Override
    public String toString(){
        return "Nome: "+nome+", Descrição: "+descricao+", Efeito: "+efeito+", Quantidade: "+quantidade;
    }

    /// Equals()
    // Para saber se dois itens são exatamente iguais
    @Override
    public boolean equals(Object obj) {

        // Se as duas variáveis apontam para o mesmo objeto na memória, já são igual
        if (this == obj)
            return true;

        // Se obj (objeto que recebo para comparar) for null, não há o que comparar; e
        // se as classes forem diferentes, também não são iguais (ambos precisam ser exatamente da classe Item)
        if (obj == null || getClass() != obj.getClass())
            return false;

        // Avisamos ao Java que queremos tratar obj como Item
        /*
         Item outro ➡ Cria uma nova variável chamada "outro" que é do tipo Item.
         (Item) obj ➡ Essa parte faz o "cast" (conversão) do objeto genérico "obj" para o tipo Item.

         O método equals recebe um parâmetro do tipo Object, que pode representar qualquer tipo de objeto.
         Nesse caso, estamos dizendo explicitamente ao Java para tratar a variável "obj" como um Item,
         pois já verificamos anteriormente que ela realmente pertence à classe Item.
        */
        Item outro = (Item) obj;

        // Compara nome, descrição e efeito dos dois itens que queremos comparar
        // Transforma tudo em minúsculas para ignorar diferenças de maiúsculas/minúsculas
        return nome.equalsIgnoreCase(outro.nome)
                && descricao.equalsIgnoreCase(outro.descricao)
                && efeito.equalsIgnoreCase(outro.efeito);
    }

    /// compareTo()
    // Define como um Item deve ser comparado com outro (em ordem alfabética, nesse caso)
    // Aqui a comparação é feita pelo nome, ignorando maiúsculas e minúsculas
    @Override
    public int compareTo(Item i){
        /*
         Compara o nome do item atual (this.nome) com o nome do outro item (i.nome)

         Retorna:
           < 0 ➡ se o nome do item atual vem antes do outro (ordem alfabética)
           0 ➡ se os nomes são iguais
           > 0 ➡ se o nome do item atual vem depois do outro
        */
        return this.nome.compareToIgnoreCase(i.nome);
    }

    /// Construtor de cópia
    // É chamado quando você quer criar um novo item igual a outro, mas de forma independente (ou seja, alterar um não muda o outro)
    public Item(Item modelo) throws Exception
    {
        // Verifica se o item passado existe
        if (modelo == null)
            throw new Exception("Modelo ausente");

        // Copiamos cada atributo do modelo original para o novo objeto
        // Isso garante que o novo Item tenha os mesmos valores, porém seja um objeto totalmente independente na memória
        this.nome = modelo.nome;
        this.descricao = modelo.descricao;
        this.efeito = modelo.efeito;
        this.quantidade = modelo.quantidade;
    }

    /// Clone()
    // Cria um novo objeto com os mesmos dados, mas em outro espaço da memória (cópias independentes)
    // Chamamos dentro do clone o construtor de cópia do item, e dentro dele é que cada item é clonado individualmente
    @Override
    public Object clone() {

        // Variável que vai guardar a cópia criada
        // Ela começa como null para caso ocorra algum erro, ainda exista um valor seguro para retornar
        Item retorno = null;

        try
        {
            // Chamo o construtor de cópia da classe Item, passando o próprio item atual (this)
            // Assim, é criado um novo objeto Item com os mesmos itens do original, mas de forma independente (alterar um não afeta o outro)
            retorno = new Item(this);
        }

        // Caso ocorra algum erro dentro do try, o catch impede que o programa quebre
        catch (Exception erro) { }

        // Retorna o novo Item criado (a cópia independente do original)
        return retorno;
    }

    /// HashCode
    // Gero um número inteiro que representa o conteúdo do objeto (é como se fosse o "ID" do objeto)
    @Override
    public int hashCode() {

        // Começamos com um número base (ponto de partida para o cálculo do hash)
        int ret = 1;

        // É case-insensitive, pois o método equals também é.
        // Sempre que implementamos equals(), é obrigatório implementar hashCode() para manter a coerência.

        /*
         Multiplica o valor atual do hash (ret) por 2 para dar valores diferentes aos próximos atributos
         Soma o hash do nome (em letras minúsculas) ou 0 se o nome for nulo (retorna 0 nesse caso)
         Isso gera um número único e ajuda a evitar colisões de hash (ou seja, objetos diferentes com o mesmo hash)
        */
        ret = 2 * ret + (nome == null ? 0 : nome.toLowerCase().hashCode());

        // Pega o valor do hash acumulado (nesse caso o resultado do hash do nome) e continua a conta com os próximos atributos
        ret = 2 * ret + (descricao == null ? 0 : descricao.toLowerCase().hashCode());
        ret = 2 * ret + (efeito == null ? 0 : efeito.toLowerCase().hashCode());

        // Retorna o valor calculado
        return ret;
    }
}
