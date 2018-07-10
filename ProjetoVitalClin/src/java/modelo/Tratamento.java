/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author bruno
 */
public class Tratamento {
    private int id;
    private String descricao;
    private int numeroSessão;

    public Tratamento(){}
    
    public Tratamento(int id){
        this.id= id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNumeroSessão() {
        return numeroSessão;
    }

    public void setNumeroSessão(int numeroSessão) {
        this.numeroSessão = numeroSessão;
    }
   

}
