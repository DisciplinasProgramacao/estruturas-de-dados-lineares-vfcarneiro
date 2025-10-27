import java.util.function.Function;
import java.util.function.Predicate;

public class Lista<E> {

	private Celula<E> primeiro;
	private Celula<E> ultimo;
	private int tamanho;
	
	public Lista() {
		
		Celula<E> sentinela = new Celula<>();
		
		this.primeiro = this.ultimo = sentinela;
		this.tamanho = 0;
	}
	
	public boolean vazia() {
		
		return (this.primeiro == this.ultimo);
	}
	
	public void inserir(E novo, int posicao) {
		
		Celula<E> anterior, novaCelula, proximaCelula;
		
		if ((posicao < 0) || (posicao > this.tamanho))
			throw new IndexOutOfBoundsException("Não foi possível inserir o item na lista: "
					+ "a posição informada é inválida!");
		
		anterior = this.primeiro;
		for (int i = 0; i < posicao; i++)
			anterior = anterior.getProximo();
				
		novaCelula = new Celula<>(novo);
			
		proximaCelula = anterior.getProximo();
			
		anterior.setProximo(novaCelula);
		novaCelula.setProximo(proximaCelula);
			
		if (posicao == this.tamanho)
			this.ultimo = novaCelula;
			
		this.tamanho++;		
	}
	
	public E remover(int posicao) {
		
		Celula<E> anterior, celulaRemovida, proximaCelula;
		
		if (vazia())
			throw new IllegalStateException("Não foi possível remover o item da lista: "
					+ "a lista está vazia!");
		
		if ((posicao < 0) || (posicao >= this.tamanho))
			throw new IndexOutOfBoundsException("Não foi possível remover o item da lista: "
					+ "a posição informada é inválida!");
			
		anterior = this.primeiro;
		for (int i = 0; i < posicao; i++)
			anterior = anterior.getProximo();
				
		celulaRemovida = anterior.getProximo();
				
		proximaCelula = celulaRemovida.getProximo();
				
		anterior.setProximo(proximaCelula);
		celulaRemovida.setProximo(null);
				
		if (celulaRemovida == this.ultimo)
			this.ultimo = anterior;
				
		this.tamanho--;
				
		return (celulaRemovida.getItem());	
	}
	
    public E obterElemento(int posicao) {
    
    	if (vazia()) {
            throw new IllegalStateException("A lista está vazia.");
        }

        if (posicao < 0 || posicao >= this.tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }

        Celula<E> atual = this.primeiro.getProximo();
        for (int i = 0; i < posicao; i++) {
            atual = atual.getProximo();
        }

        return atual.getItem();
    }
    
    public int contar(Predicate<E> condicional){
        
    	int contagem = 0;
        Celula<E> atual = this.primeiro.getProximo();

        while (atual != null) {
            if (condicional.test(atual.getItem())) {
                contagem++;
            }
            atual = atual.getProximo();
        }

        return contagem;
    }
    
   	public double obterSoma(Function<E, Double> extrator) {
   	
   		// TODO
   		return 0.0;
   	}
    
    public int tamanho(){
        return tamanho;
    }
    
    @Override
	public String toString() {
    	
		Celula<E> aux;
		StringBuilder listaString = new StringBuilder("A lista está vazia!");
		
	    if (!vazia()) {
            listaString.setLength(0); // Limpa a mensagem "A lista está vazia!"
            int contador = 0;
			aux = primeiro.getProximo();
			while (aux != null) {
                String dado = String.format("Posição %d: %s\n", contador, aux.getItem().toString());
				listaString.append(dado);
				aux = aux.getProximo();
                contador++;
			}
		} 	
        return listaString.toString();
	}
}
