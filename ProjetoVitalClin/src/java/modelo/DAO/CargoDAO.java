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
import modelo.Cargo;
import util.ConectaBanco;

/**
 *
 * @author bruno
 */
public class CargoDAO {
    private static final String SELECT_ALL = "SELECT * FROM Cargo;";
  
    private Object pstm;

 
    public ArrayList<Cargo> listar() throws SQLException, ClassNotFoundException {
        //cria uma array de obJ Cliente
        ArrayList<Cargo> listaCargo = new ArrayList<Cargo>();

        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);
        //executa
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Cargo novoCargo = new Cargo();
            novoCargo.setId(rs.getInt("id"));
            novoCargo.setDescricao(rs.getString("descricao"));
           

            //add na lista
            listaCargo.add(novoCargo);
        }
        return listaCargo;
    }
    
}
