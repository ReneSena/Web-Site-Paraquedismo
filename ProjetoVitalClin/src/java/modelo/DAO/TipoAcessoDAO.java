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
import modelo.TipoAcesso;
import util.ConectaBanco;


/**
 *
 * @author bruno
 */
public class TipoAcessoDAO {
     private static final String SELECT_ALL = "SELECT * FROM TipoAcesso;";
  
    private Object pstm;

    public java.util.ArrayList<TipoAcesso> listar() throws SQLException, ClassNotFoundException {
        //cria uma array de obJ Cliente
        java.util.ArrayList<TipoAcesso> listaTipoAcesso = new ArrayList<TipoAcesso>();

        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);
        //executa
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            TipoAcesso novoTipoAcesso = new TipoAcesso();
            novoTipoAcesso.setId(rs.getInt("id"));
            novoTipoAcesso.setDescricao(rs.getString("descricao"));
           

            //add na lista
            listaTipoAcesso.add(novoTipoAcesso);
        }
        return listaTipoAcesso;
    }
    
}
