package util.Exterior;

import db.handlers.VeiculoNaoEncontrado;
import util.Node.No;

public class TableExterior<T, K> {
    
    private No<T, K>[] tabela;

    private int M;

    private Integer size;

    public TableExterior(Integer tamanho){
        this.M = tamanho;
        this.tabela = new No[this.M];
        this.size = 0;
    }

    private Integer hash(Integer chave){
        return chave % this.M;
    }

    public void put(T valor, K chave){
        Integer posicao = hash((Integer) chave);

        No<T, K> noHash = this.tabela[posicao];

        while(noHash != null){
            if(noHash.getValor().equals(valor)) break;
            noHash = noHash.getProx();
        }

        if(noHash == null){
            noHash = new No<>(valor, chave);
            noHash.setProx(this.tabela[posicao]);
            this.tabela[posicao] = noHash;
        }

        this.size++;
        System.out.println("Fator de carga = " + fatorDeCarga());

        System.out.println("Adicionando" + valor.toString());
    }

    public T buscar(K chave){
        Integer posicao = hash((Integer)chave);
        No<T, K> noHash = this.tabela[posicao];
        System.out.println("Buscando");
        while (noHash != null) {
            if(noHash.getChave().equals(chave)) return noHash.getValor();
            noHash = noHash.getProx();
        }
        throw new VeiculoNaoEncontrado("Veículo não encontrado !");
    }

    public void att(T valor, K chave){
        Integer posicao = hash((Integer) chave);

        No<T, K> noHash = this.tabela[posicao];

        while(noHash != null){
            if(noHash.getChave().equals(chave)) break;
            noHash = noHash.getProx();
        }

        if(noHash != null){
            noHash.setValor(valor);
        }
        System.out.println("Atualizando");
    }

    public Double fatorDeCarga(){
        int total = 0;
        No<T, K> index;
        for(int i = 0; i < this.M; i++){
            index = this.tabela[i];
            while (index != null) {
                index = index.getProx();
                total++;
            }
        }
        return (double) total / this.M;
    }

    public void remover(K chave){
        Integer posicao = hash((Integer)chave);
        if(this.tabela[posicao] == null) throw new VeiculoNaoEncontrado("Veículo não encontrado !");
        No<T, K> noHash = this.tabela[posicao];
        System.out.println("Removendo");
        if(noHash.getChave().equals((Integer)chave)){
            this.tabela[posicao] = noHash.getProx();
            noHash.setProx(null);
            noHash = null;
            this.size--;
            System.out.println("Fator de carga = " + fatorDeCarga());
            return;
        }
        No<T, K> anterior = null;
        while (noHash != null) {
            if(noHash.getChave().equals((Integer)chave)) break;
            anterior = noHash;
            noHash = noHash.getProx();
        }

        if(noHash == null) throw new VeiculoNaoEncontrado("Veículo não encontrado !");
        anterior.setProx(noHash.getProx());
        noHash.setProx(null);
        noHash = null;
        this.size--;
        System.out.println("Fator de carga = " + fatorDeCarga());

    }

    public String print(){
        System.out.println("Listando");
        String res = "";
        No<T, K> index;
        for(int i = 0; i < this.M; i++){
            index = this.tabela[i];
            res += i;
            while (index != null) {
                res += " -- " + index.getValor();
                index = index.getProx();
            }
            res += "\n";
        }
        return res;
    }

    public Integer getSize(){
        return this.size;
    }

}
