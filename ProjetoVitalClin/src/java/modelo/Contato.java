
package modelo;

/**
 *
 * @author bruno
 */
public class Contato {
    private int id;
    private String descricao;
    private String numero;
    
    public Contato (){};
    
    public Contato (String descricao, String numero){
      
        this.descricao = descricao;
        this.numero = numero;
    }
    
    public void setId (int id){
        this.id = id;
    }
    
    public int getId (){
        return id;
    }
    
    public void setDescricao (String descricao){
        this.descricao = descricao;
    }
    
    public String getDescricao (){
        return descricao;
    }
    
    public void setNumero (String numero){
        this.numero = numero;
    }
    
    public String getNumero (){
        return numero;
    }
    
}
