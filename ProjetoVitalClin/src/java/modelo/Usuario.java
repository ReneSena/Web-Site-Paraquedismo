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
public class Usuario {
    private int id;
    private String email;
    private String senha;
    private TipoAcesso tipo;
    

    public Usuario (){
        this.tipo = new TipoAcesso();
    };
    
     public Usuario (String email, String senha){
         this.tipo = new TipoAcesso();
         this.email = email;
         this.senha = senha;
    };
    
    public Usuario (int id, String email){
        this.id = id;
        this.email = email;
    }
    
    public Usuario (int idUsuario,String email, int idTipoUsuario){
        this.id = idUsuario;
        this.email = email;
        this.tipo = new TipoAcesso(idTipoUsuario);
    }
    
    public Usuario(String email, String senha, int idTipoAcesso){
        this.email = email;
        this.senha = senha;
        this.tipo = new TipoAcesso (idTipoAcesso); 
        
    }
    
    public Usuario (String email){
        this.email = email;
    }
    
    public int getId (){
        return id;
    }
    
    public void setId (int id){
        this.id = id;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public TipoAcesso getTipo (){
        return tipo;
    }
    
    public void setTipo(TipoAcesso tipo){
        this.tipo = tipo;
    }
    
}
