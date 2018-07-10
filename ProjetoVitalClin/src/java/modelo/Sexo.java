
package modelo;

/**
 *
 * @author bruno
 */
public class Sexo {
    private int id;
    private String descricao;
    
    public Sexo (){};
    
    public Sexo(String descricao){
        this.descricao= descricao;
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
}
