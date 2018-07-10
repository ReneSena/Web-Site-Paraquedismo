/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.Pessoa;
import modelo.Usuario;
import util.ConectaBanco;

/**
 *
 * @author bruno
 */
public class UsuarioDAO {

    private static final String LOGIN = "SELECT * FROM usuario WHERE email=? and senha=?;"; 
    private static final String INSERT = "INSERT INTO usuario(email, senha, tipoAcessoFK) values(?,?,?);";
    private static final String BUSCAR_ID = "SELECT id from usuario WHERE email=?"; 
    private static final String UPDATE_EMAIL = "UPDATE usuario SET email=? where id=?;"; 
    private static final String UPDATE_SENHA = "UPDATE usuario SET senha=? where id=?;"; 
    private static final String UPDATE_USERFUNC = "UPDATE usuario SET email=?, tipoAcessoFK=? where id=?;"; 
    private static final String BUSCAR_EMAIL = "SELECT id, senha FROM cliente WHERE email=?;"; 
    private static final String CONFERIR_EMAIL = "SELECT id FROM usuario WHERE email=?;"; 
   
    private Object pstm;

    public boolean cadastrarUsuario(Cliente cliente) throws SQLException, ClassNotFoundException {

        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, cliente.getUsuario().getEmail());
            pstmt.setString(2, cliente.getUsuario().getSenha());
            pstmt.setInt(3, cliente.getUsuario().getTipo().getId());

            pstmt.execute();
            return true;

        } catch (Exception ex) {
            System.out.println("Erro: , " + ex.getMessage());
            return false;

        } finally {

            conexao.close();
        }
    }
    
    
    public boolean cadastrarUsuarioFunc(Funcionario funcionario) throws SQLException, ClassNotFoundException {

        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, funcionario.getUsuario().getEmail());
            pstmt.setString(2, funcionario.getUsuario().getSenha());
            pstmt.setInt(3, funcionario.getUsuario().getTipo().getId());

            pstmt.execute();
            return true;

        } catch (Exception ex) {
            System.out.println("Erro: , " + ex.getMessage());
            return false;

        } finally {

            conexao.close();
        }
    }


    public Pessoa pegaID(Cliente cliente) throws SQLException, ClassNotFoundException { //polimorfismo
        //cria uma array de obJ Cliente
        Connection conexao = ConectaBanco.getConexao();

        PreparedStatement pstmt = conexao.prepareStatement(BUSCAR_ID);

        pstmt.setString(1, cliente.getUsuario().getEmail());

        ResultSet rs = pstmt.executeQuery();

        //Conexao
        //cria comando SQL
        //executa
        rs.next();
        //a cada loop

        cliente.getUsuario().setId(rs.getInt("id"));

        return cliente;
    }
    
      public Pessoa pegaIDFunc(Funcionario funcionario) throws SQLException, ClassNotFoundException { //polimorfismo
        //cria uma array de obJ Cliente
        Connection conexao = ConectaBanco.getConexao();

        PreparedStatement pstmt = conexao.prepareStatement(BUSCAR_ID);

        pstmt.setString(1, funcionario.getUsuario().getEmail());

        ResultSet rs = pstmt.executeQuery();

        //Conexao
        //cria comando SQL
        //executa
        rs.next();
        //a cada loop

        funcionario.getUsuario().setId(rs.getInt("id"));

        return funcionario;
    }

    public boolean logar(Usuario usuario) throws SQLException, ClassNotFoundException {
        try {
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(LOGIN);

            pstmt.setString(1, usuario.getEmail());
            pstmt.setString(2, usuario.getSenha());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

            usuario.getTipo().setId(rs.getInt("tipoAcessoFK"));
            usuario.setId(rs.getInt("id"));

            //implementar a seguranca
            return true;

        } catch (Exception e) {

            //
            return false;
        }

    }


    public void AlterarUsuario(Cliente cliente) throws SQLException, ClassNotFoundException { // Polimorfismo

        try {

            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE_EMAIL);
            //executa
            pstmt.setString(1, cliente.getUsuario().getEmail());
            pstmt.setInt(2, cliente.getUsuario().getId());

            pstmt.execute();
        } catch (Exception ex) {

        }

    }
    
     public void AlterarUserFunc(Funcionario funcionario) throws SQLException, ClassNotFoundException { // Polimorfismo

        try {

            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE_USERFUNC);
            //executa
            pstmt.setString(1, funcionario.getUsuario().getEmail());
            pstmt.setInt(2, funcionario.getUsuario().getTipo().getId());
            pstmt.setInt(3, funcionario.getUsuario().getId());

            pstmt.execute();
        } catch (Exception ex) {

        }

    }

    
    public Cliente pegaUsuario(Cliente cliente) {
        try {
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR_EMAIL);

            pstmt.setString(1, cliente.getUsuario().getEmail());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

            cliente.setId(rs.getInt("id"));
            cliente.getUsuario().setSenha(rs.getString("senha"));

        } catch (Exception e) {

            //
        }
        return cliente;
    }    
}
