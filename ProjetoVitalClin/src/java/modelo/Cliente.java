
package modelo;


public class Cliente extends Pessoa{
    private int    id;
    private int    situacao;
  
    public Cliente (){}
    
    public void setId (int id){
        this.id = id;
    }
    
    public int getId (){
        return id;
    }
    
    public void setSituacao (int situacao){
        this.situacao = situacao;
    }
    
    public int getSituacao (){
        return situacao;
    }
    
    
}
