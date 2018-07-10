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
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import modelo.Agendamento;
import modelo.Funcionario;
import util.ConectaBanco;

/**
 *
 * @author bruno
 */
public class AgendamentoDAO {
     private static final String SELECT_ALL = "SELECT id, hora from agendamento where data=?;";
     private static final String UPDATE_SITUACAO = "UPDATE agendamento set situacao=? where id=?;";
     private static final String INSERT = "INSERT INTO agendamento (data, hora, clientefk, tratamentofk, funcionariofk) values (?, ?, ?, ?, ?);";
     private static final String SELECT_CLIENTE = "SELECT c.nome, t.descricao, a.id, a.hora from agendamento a, cliente c, tratamento t WHERE a.clientefk = c.id and a.tratamentofk = t.id and a.data=?;";
     
     private Object pstm;

   
    public ArrayList<Agendamento> ConsultaDisponibilidadeHorario(Agendamento agendamento) throws SQLException, ClassNotFoundException {
         
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);
            
             ArrayList<Agendamento> listaAgendamentos = new ArrayList<Agendamento>();
            
             
            pstmt.setDate(1, java.sql.Date.valueOf((agendamento.getData())));
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            
             while (rs.next()) {
                 Agendamento novoAgendamento = new Agendamento();
                 novoAgendamento.setId(rs.getInt("id"));
                 novoAgendamento.setHora(rs.getString("hora"));
                 
                 listaAgendamentos.add(novoAgendamento);
             }
             
             return listaAgendamentos;
            
        }
            
    
     public boolean cadastrar(Agendamento agendamento) throws SQLException, ClassNotFoundException {

        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setDate(1, java.sql.Date.valueOf((agendamento.getData())));
            pstmt.setString(2, agendamento.getHora());
            pstmt.setInt(3, agendamento.getCliente().getId());
            pstmt.setInt(4, agendamento.getTratamento().getId());
            pstmt.setInt(5, agendamento.getFuncionario().getId());
            

            pstmt.execute();
            return true;

        } catch (Exception ex) {
            System.out.println("Erro: , " + ex.getMessage());
            return false;

        } finally {

            conexao.close();
        }
    }
     
     
     
     public ArrayList<Agendamento> ConferirSeOcorreu(Agendamento agendamento) throws SQLException, ClassNotFoundException {
        
        //cria uma array de obJ Cliente
        ArrayList<Agendamento> listaAgendamentos = new ArrayList<Agendamento>();

        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(SELECT_CLIENTE);
        
        pstmt.setDate(1, java.sql.Date.valueOf((agendamento.getData())));
        //executa
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Agendamento novoAgendamento = new Agendamento();
            novoAgendamento.setId(rs.getInt("id"));
            novoAgendamento.setHora(rs.getString("hora")); 
            novoAgendamento.getCliente().setNome(rs.getString("nome"));
            novoAgendamento.getTratamento().setDescricao(rs.getString("descricao"));

            //add na lista
            listaAgendamentos.add(novoAgendamento);
        }
        return listaAgendamentos;
    }
     
     
      
      public boolean auterarSituacao(Agendamento agendamento) throws SQLException {
        
          Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(UPDATE_SITUACAO);

            pstmt.setString(1, agendamento.getSituacao());
            pstmt.setInt(2, agendamento.getId());

            pstmt.execute();

            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            conexao.close();
        }

    }
      
      
      public ArrayList<Agendamento> buscaCliente(Agendamento agendamento) throws SQLException, ClassNotFoundException {
        
        ArrayList<Agendamento> listaAgendamentos = new ArrayList<Agendamento>();
   
        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(SELECT_CLIENTE);
        //executa
        
          pstmt.setInt(1, agendamento.getCliente().getId());

         pstmt.execute();
            
         ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Agendamento novoAgendamento = new Agendamento();
            novoAgendamento.setId(rs.getInt("id"));
            novoAgendamento.setHora(rs.getString("hora"));
            novoAgendamento.setSituacao(rs.getString("situacao"));
           

            //add na lista
            listaAgendamentos.add(novoAgendamento);
        }
        return listaAgendamentos;
    }

    
}
