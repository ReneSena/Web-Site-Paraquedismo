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
import modelo.Agendamento;
import modelo.Tratamento;
import util.ConectaBanco;

/**
 *
 * @author bruno
 */
public class TratamentoDAO {
     private static final String SELECT_ALL = "SELECT * FROM tratamento order by descricao asc;";
     private static final String BUSCA_TRATAMENTO = "SELECT descricao from tratamento where id=? order by descricao asc;";
  
    private Object pstm;

    public java.util.ArrayList<Tratamento> listar() throws SQLException, ClassNotFoundException {
        //cria uma array de obJ Cliente
        java.util.ArrayList<Tratamento> listaTratamento = new ArrayList<Tratamento>();

        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);
        //executa
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Tratamento novoTratamento = new Tratamento();
            novoTratamento.setId(rs.getInt("id"));
            novoTratamento.setDescricao(rs.getString("descricao"));
           

            //add na lista
            listaTratamento.add(novoTratamento);
        }
        return listaTratamento;
    }
    
      public Tratamento BuscaTratamento(Tratamento tratamento) throws SQLException, ClassNotFoundException {
         
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCA_TRATAMENTO);
            
             

            pstmt.setInt(1, tratamento.getId());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            
             rs.next();
             
                 tratamento.setDescricao(rs.getString("descricao"));
                 
                 return tratamento;
             }
        
    
}
