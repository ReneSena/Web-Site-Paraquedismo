
package modelo;

/**
 *
 * @author bruno
 */
public class Documento {
    private int id;
    private String descricao;
    private String numero;
    
    public Documento (){};
    
    public Documento (String descricao, String numero){
        this.descricao = descricao;
        this.numero = numero;
    }
    
    public void setID (int id){
        this.id = id;
    }
    
    public int getId (){
        return id;
    }
    
    public void setDescricao (String decricao){
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
