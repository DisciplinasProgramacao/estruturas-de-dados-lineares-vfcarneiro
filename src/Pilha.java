import java.util.NoSuchElementException;

public class Pilha<E> {

    private Celula<E> topo;
    private Celula<E> fundo;

    public Pilha() {
        Celula<E> sentinela = new Celula<E>();
        fundo = sentinela;
        topo = sentinela;
    }

    public boolean vazia() {
        return fundo == topo;
    }

    public void empilhar(E item) {
        topo = new Celula<E>(item, topo);
    }

    public E desempilhar() {
        E desempilhado = consultarTopo();
        topo = topo.getProximo();
        return desempilhado;
    }

    public E consultarTopo() {
        if (vazia()) {
            throw new NoSuchElementException("Nao há nenhum item na pilha!");
        }
        return topo.getItem();
    }

    public Pilha<E> subPilha(int numItens) {
        if (numItens < 0) {
            throw new IllegalArgumentException("O número de itens deve ser positivo!");
        }

        Pilha<E> aux = new Pilha<>();
        int contador = 0;

        while (!vazia() && contador < numItens) {
            aux.empilhar(desempilhar());
            contador++;
        }

        if (contador < numItens) {
            while (!aux.vazia()) {
                empilhar(aux.desempilhar());
            }
            throw new IllegalArgumentException("A pilha possui menos de " + numItens + " elementos!");
        }

        Pilha<E> sub = new Pilha<>();
        Pilha<E> restaurar = new Pilha<>();

        while (!aux.vazia()) {
            E item = aux.desempilhar();
            sub.empilhar(item);
            restaurar.empilhar(item);
        }

        while (!restaurar.vazia()) {
            empilhar(restaurar.desempilhar());
        }

        return sub;
    }
}
