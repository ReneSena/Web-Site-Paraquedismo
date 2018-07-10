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
import modelo.Contrato;
import util.ConectaBanco;

/**
 *
 * @author bruno
 */
public class ContratoDAO {
    private static final String SELECT_ALL = "SELECT * FROM contrato;";
  
    private Object pstm;

 
    public ArrayList<Contrato> listar() throws SQLException, ClassNotFoundException {
        //cria uma array de obJ Cliente
        ArrayList<Contrato> listaContrato = new ArrayList<Contrato>();

        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);
        //executa
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Contrato novoContrato = new Contrato();
            novoContrato.setId(rs.getInt("id"));
            novoContrato.setDescricao(rs.getString("descricao"));
           

            //add na lista
            listaContrato.add(novoContrato);
        }
        return listaContrato;
    }
}
