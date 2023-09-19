package util.Aberto;

import util.Node.No;

public class TableAberta<T, K> {
    
    private No<T, K> tabela[];

    private Integer M;

    private Integer size;

    public TableAberta(Integer tamanho){
        this.M = tamanho;
        this.tabela = new No[this.M];
        this.size = 0;
    }

    private Integer hash(Integer chave){
        return chave % this.M;
    }

    public void put(K chave, T valor){
        Integer posicao = hash((Integer)chave);

        while(this.tabela[posicao] != null){
            if(this.tabela[posicao].getChave().equals(chave)) break;
            posicao = (posicao+1)%this.M;
        }

        if(this.tabela[posicao] == null){
            this.tabela[posicao] = new No<>(valor, chave);
        }

        this.size++;
        System.out.println("Fator de carga = " + fatorDeCarga());

        System.out.println("Adicionando " + valor.toString());
    }

    public void att(T valor, K chave){
        Integer posicao = hash((Integer) chave);

        while(this.tabela[posicao] != null){
            if(this.tabela[posicao].getChave().equals(chave)) break;
            posicao = (posicao+1)%this.M;
        }

        if(this.tabela[posicao] != null){
            No<T, K> no = this.tabela[posicao];
            no.setValor(valor);
            this.tabela[posicao] = no;
        }

        System.out.println("Atualizando");

    }

    public void remover(K chave){
        Integer posicao = hash((Integer)chave);
        while (this.tabela[posicao] != null) {
            if(this.tabela[posicao].getChave().equals(chave)) this.tabela[posicao] = null;
            posicao = (posicao+1)%this.M;
        }
        this.size--;
        System.out.println("Removendo");
        System.out.println("Fator de carga = " + fatorDeCarga());
    }

    public Double fatorDeCarga(){
        int total = 0;
        for(int i = 0; i < this.M; i++){
            if(this.tabela[i] != null) total++;
        }
        return (double) total / this.M;
    }

    public T buscar(K chave){
        Integer posicao = hash((Integer)chave);
        System.out.println("Buscando");
        while (this.tabela[posicao] != null) {
            if(this.tabela[posicao].getChave().equals(chave)) return this.tabela[posicao].getValor();
            posicao = (posicao+1)%this.M;
        }
        return null;
    }

    public String print(){
        String res = "";
        System.out.println("Listando");
        for(int i = 0; i < this.M; i++){
            if(this.tabela[i] != null){
                res += (i + " => " + this.tabela[i].getValor()) + "\n";
            } else res += i + "\n";
        }
        return res;
    }

    public Integer getSize(){
        return this.size;
    }

}
