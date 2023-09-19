package util.Node;

public class No<T, K> {

    private No<T, K> prox;
    
    private T valor;
    
    private K chave;

    public No(T valor, K chave) {
        this.prox = null;
        this.valor = valor;
        this.chave = chave;
    }

    public No<T, K> getProx() {
        return prox;
    }

    public void setProx(No<T, K> prox) {
        this.prox = prox;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public K getChave() {
        return chave;
    }

    public void setChave(K chave) {
        this.chave = chave;
    }
    
}
