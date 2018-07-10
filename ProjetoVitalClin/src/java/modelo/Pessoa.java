
package modelo;

import java.util.Date;

/**
 *
 * @author bruno
 */
public class Pessoa {
     
    private String nome;
    private String dtNascimento;
    private Sexo sexo;
    private Endereco endereco;
    private String telefoneFixo;
    private String celular;
    private String cpf;
    private Usuario usuario;
    
    public Pessoa (){
        this.sexo = new Sexo();
        this.endereco = new Endereco();
        this.usuario = new Usuario ();
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }
    
    public String getCelular (){
        return celular;
    }
    
    public void setCelular (String celular){
        this.celular = celular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
     
}
