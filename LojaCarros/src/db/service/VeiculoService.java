package db.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Server.IServer;
import db.model.Veiculo;
import util.Aberto.TableAberta;
import util.Exterior.TableExterior;

public class VeiculoService extends UnicastRemoteObject implements IServer {

    private final Integer SIZE = 97;

    private TableAberta<Veiculo, Integer> tableAberta;

    private TableExterior<Veiculo, Integer> tableExterior;

    private Boolean type;

    public VeiculoService(Boolean tipo) throws RemoteException {
        this.type = tipo;
        if(tipo) this.tableExterior = new TableExterior<>(SIZE);
        else this.tableAberta = new TableAberta<>(SIZE);
    }

    @Override
    public void adicionar(Veiculo veiculo) throws RemoteException {
        if(this.type){
            this.tableExterior.put(veiculo, Integer.parseInt( veiculo.getRenavam() ));
        } else {
            this.tableAberta.put(Integer.parseInt( veiculo.getRenavam() ), veiculo);
        }
    }

    @Override
    public String remover(String renavam) throws RemoteException {
        if(this.type){
            this.tableExterior.remover(Integer.parseInt( renavam ));
            return "Carro renavam = [ " + renavam + " ] removido !\n";
        } else {
            this.tableAberta.remover(Integer.parseInt( renavam ));
            return "Carro renavam = [ " + renavam + " ] removido !\n";
        }
    }

    @Override
    public String listar() throws RemoteException {
        if(this.type){
            return this.tableExterior.print();
        } else {
            return this.tableAberta.print();
        }
    }

    @Override
    public Veiculo buscar(String renavam) throws RemoteException {
        if(this.type){
            return this.tableExterior.buscar(Integer.parseInt( renavam ));
        } else {
            return this.tableAberta.buscar(Integer.parseInt( renavam ));
        }
    }

    @Override
    public void atualizar(Veiculo novo, String renavam) throws RemoteException {
        
        Veiculo veiculo = this.buscar( renavam);
        
        if(attCampos(novo, veiculo)){
            if(this.type){
                this.tableExterior.att(veiculo, Integer.parseInt( renavam ));
            } else {
                this.tableAberta.att(veiculo, Integer.parseInt( renavam ));
            }
        }
        
    }

    @Override
    public Integer quantidadeDeCarros() throws RemoteException {
        if(this.type){
            return this.tableExterior.getSize();
        } else {
            return this.tableAberta.getSize();
        }
    }

    @Override
    public Double fatorDeCarga() throws RemoteException {
        if(this.type){
            return this.tableExterior.fatorDeCarga();
        } else {
            return this.tableAberta.fatorDeCarga();
        }
    }

    private Boolean attCampos(Veiculo novo, Veiculo velho){

        boolean hasAtt = false;

        if(novo.getNome() != null && !novo.getNome().isEmpty() && !novo.getNome().equalsIgnoreCase("*")){
            velho.setNome(novo.getNome());
            hasAtt = true;
        }
        if(novo.getModelo() != null && !novo.getModelo().isEmpty() && !novo.getModelo().equalsIgnoreCase("*")){
            velho.setModelo(novo.getModelo());
            hasAtt = true;
        }
        if(novo.getPlaca() != null && !novo.getPlaca().isEmpty() && !novo.getPlaca().equalsIgnoreCase("*")){
            velho.setPlaca(novo.getPlaca());
            hasAtt = true;
        }
        if(novo.getDataFabricacao() != null && !novo.getDataFabricacao().equals("*")){
            velho.setDataFabricacao(novo.getDataFabricacao());
            hasAtt = true;
        }
        if(novo.getCondutor().getNome() != null && !novo.getCondutor().getNome().isEmpty() && !novo.getCondutor().getNome().equalsIgnoreCase("*")){
            velho.getCondutor().setNome(novo.getCondutor().getNome());
            hasAtt = true;
        }
        if(novo.getCondutor().getCpf() != null && !novo.getCondutor().getCpf().isEmpty() && !novo.getCondutor().getCpf().equalsIgnoreCase("*")){
            velho.getCondutor().setCpf(novo.getCondutor().getCpf());
            hasAtt = true;
        }

        return hasAtt;
        
    }
    
}
