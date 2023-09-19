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
        if(this.type){
            this.tableExterior.att(novo, Integer.parseInt( renavam ));
        } else {
            this.tableAberta.att(novo, Integer.parseInt( renavam ));
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
    
}
