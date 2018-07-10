
package modelo;

/**
 *
 * @author bruno
 */
public class Endereco {
    
    private int id;
    private String logradouro;
    private String bairro;
    private int numero;
    private String cidade;
    private String uf;
    private String cep;
    
    public Endereco (){};
    
    public Endereco (String logradouro, String bairro, int numero, String cidade, String uf, String cep){
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
     
    }
    
    public void setId (int id){
        this.id = id;
    }
    
    public int getId (){
        return id;
    }
    
    public void setLogradouro (String logradouro){
        this.logradouro = logradouro;
    }
    
    public String getLogradouro (){
        return logradouro;
    }
    
    public void setBairro (String bairro){
        this.bairro = bairro;
    }
    
    public String getBairro (){
        return bairro;
    }
    
    public void setNumero (int numero){
        this.numero = numero;
    }
    
    public int getNumero (){
        return numero;
    }
    
    public void setCidade (String cidade){
        this.cidade = cidade;
    }
    
    public String getCidade (){
        return cidade;
    }
    
    public void setUf (String uf){
        this.uf = uf;
    }
    
    public String getUf (){
        return uf;
    }
    
    public void setCep (String cep){
        this.cep = cep;
    }
    
    public String getCep (){
        return cep;
    }
    

}
