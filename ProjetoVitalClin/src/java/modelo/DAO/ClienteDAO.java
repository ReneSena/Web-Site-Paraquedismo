package modelo.DAO;

import util.ConectaBanco;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;

public class ClienteDAO {

    private static final String INSERT = "INSERT INTO cliente(nome, cpf, dtNascimento, sexo, logradouro, bairro, numero, cidade, uf, cep, telFixo, celular, situacao, usuarioFK) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private static final String SELECT_ALL = "SELECT c.*, u.email FROM cliente c, usuario u WHERE c.usuarioFK = u.id and c.situacao<>2 order by nome asc;";
    private static final String BUSCAR_NOME = "SELECT c.*, u.email FROM cliente c, usuario u WHERE c.usuarioFK = u.id and nome=? and c.situacao<>2 order by nome asc;";
    private static final String BUSCAR_CPF = "SELECT c.*, u.email FROM cliente c, usuario u WHERE c.usuarioFK = u.id and c.cpf=? and c.situacao<>2 order by nome asc;";
    private static final String BUSCAR_EMAIL = "SELECT id, senha FROM cliente WHERE email=?;";
    private static final String DELETE = "UPDATE cliente set situacao=2 where id=?;";
    private static final String BUSCAR = "SELECT c.*, u.email FROM cliente c, usuario u WHERE c.usuarioFK = u.id and c.id=? and c.situacao<>2 order by nome asc;";
    private static final String UPDATE = "UPDATE cliente SET nome=?, cpf=?, dtNascimento=?, sexo=?, logradouro=?, bairro=?, numero=?, cidade=?, uf=?, cep=?, telFixo=?, celular=? WHERE id=?;";

    private Object pstm;

    public boolean cadastrar(Cliente cliente) throws SQLException, ClassNotFoundException {

        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setString(3, cliente.getDtNascimento());
            pstmt.setString(4, cliente.getSexo().getDescricao());
            pstmt.setString(5, cliente.getEndereco().getLogradouro());
            pstmt.setString(6, cliente.getEndereco().getBairro());
            pstmt.setInt(7, cliente.getEndereco().getNumero());
            pstmt.setString(8, cliente.getEndereco().getCidade());
            pstmt.setString(9, cliente.getEndereco().getUf());
            pstmt.setString(10, cliente.getEndereco().getCep());
            pstmt.setString(11, cliente.getTelefoneFixo());
            pstmt.setString(12, cliente.getCelular());
            pstmt.setInt(13, cliente.getSituacao());
            pstmt.setInt(14, cliente.getUsuario().getId());
            

            pstmt.execute();
            return true;

        } catch (Exception ex) {
            System.out.println("Erro: , " + ex.getMessage());
            return false;

        } finally {

            conexao.close();
        }
    }

    public ArrayList<Cliente> listar() throws SQLException, ClassNotFoundException {
        //cria uma array de obJ Cliente
        ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();

        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);
        //executa
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Cliente novoCliente = new Cliente();
            novoCliente.setId(rs.getInt("id"));
            novoCliente.setNome(rs.getString("nome"));
            novoCliente.setCpf(rs.getString("cpf"));
            novoCliente.setDtNascimento(rs.getString("dtNascimento"));
            novoCliente.getSexo().setDescricao(rs.getString("sexo"));
            novoCliente.getEndereco().setLogradouro(rs.getString("logradouro"));
            novoCliente.getEndereco().setBairro(rs.getString("bairro"));
            novoCliente.getEndereco().setNumero(rs.getInt("numero"));
            novoCliente.getEndereco().setCidade(rs.getString("cidade"));
            novoCliente.getEndereco().setUf(rs.getString("uf"));
            novoCliente.getEndereco().setCep(rs.getString("cep"));
            novoCliente.setTelefoneFixo(rs.getString("telFixo"));
            novoCliente.setCelular(rs.getString("celular"));
            novoCliente.getUsuario().setEmail(rs.getString("email"));
            novoCliente.setSituacao(rs.getInt("situacao"));

            //add na lista
            listaCliente.add(novoCliente);
        }
        return listaCliente;
    }

    public boolean excluir(Cliente cliente) throws SQLException {
        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, cliente.getId());

            pstmt.execute();

            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            conexao.close();
        }

    }

    public void buscar(Cliente cliente) {
        try {
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR);

            pstmt.setInt(1, cliente.getId());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setDtNascimento(rs.getString("dtNascimento"));
            cliente.getSexo().setDescricao(rs.getString("sexo"));
            cliente.getEndereco().setLogradouro(rs.getString("logradouro"));
            cliente.getEndereco().setBairro(rs.getString("bairro"));
            cliente.getEndereco().setNumero(rs.getInt("numero"));
            cliente.getEndereco().setCidade(rs.getString("cidade"));
            cliente.getEndereco().setUf(rs.getString("uf"));
            cliente.getEndereco().setCep(rs.getString("cep"));
            cliente.setTelefoneFixo(rs.getString("telFixo"));
            cliente.setCelular(rs.getString("celular"));
            cliente.getUsuario().setId(rs.getInt("usuarioFK"));
            cliente.getUsuario().setEmail(rs.getString("email"));
            cliente.setSituacao(rs.getInt("situacao"));

        } catch (Exception e) {

            //
        }
    }

    public boolean alterar(Cliente cliente) throws SQLException {
        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setString(3, cliente.getDtNascimento());
            pstmt.setString(4, cliente.getSexo().getDescricao());
            pstmt.setString(5, cliente.getEndereco().getLogradouro());
            pstmt.setString(6, cliente.getEndereco().getBairro());
            pstmt.setInt(7, cliente.getEndereco().getNumero());
            pstmt.setString(8, cliente.getEndereco().getCidade());
            pstmt.setString(9, cliente.getEndereco().getUf());
            pstmt.setString(10, cliente.getEndereco().getCep());
            pstmt.setString(11, cliente.getTelefoneFixo());
            pstmt.setString(12, cliente.getCelular());
            pstmt.setInt(13, cliente.getId());

            pstmt.execute();
            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            conexao.close();
        }

    }

    public  ArrayList<Cliente> listarPorNome(Cliente cliente) throws SQLException, ClassNotFoundException {
            
            ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
       
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR_NOME);
            //executa
            pstmt.setString(1, cliente.getNome());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                //a cada loop
                Cliente novoCliente = new Cliente();
                novoCliente.setId(rs.getInt("id"));
                novoCliente.setNome(rs.getString("nome"));
                novoCliente.setCpf(rs.getString("cpf"));
                novoCliente.setDtNascimento(rs.getString("dtNascimento"));
                novoCliente.getSexo().setDescricao(rs.getString("sexo"));
                novoCliente.getEndereco().setLogradouro(rs.getString("logradouro"));
                novoCliente.getEndereco().setBairro(rs.getString("bairro"));
                novoCliente.getEndereco().setNumero(rs.getInt("numero"));
                novoCliente.getEndereco().setCidade(rs.getString("cidade"));
                novoCliente.getEndereco().setUf(rs.getString("uf"));
                novoCliente.getEndereco().setCep(rs.getString("cep"));
                novoCliente.setTelefoneFixo(rs.getString("telFixo"));
                novoCliente.setCelular(rs.getString("celular"));
                novoCliente.getUsuario().setEmail(rs.getString("email"));
                novoCliente.setSituacao(rs.getInt("situacao"));

                //add na lista
                listaCliente.add(novoCliente);
            }

         return listaCliente;

    }

    public  ArrayList<Cliente> listarPorCpf(Cliente cliente) throws SQLException, ClassNotFoundException {
        
         ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(BUSCAR_CPF);
        //executa
        pstmt.setString(1, cliente.getCpf());

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Cliente novoCliente = new Cliente();
            novoCliente.setId(rs.getInt("id"));
            novoCliente.setNome(rs.getString("nome"));
            novoCliente.setCpf(rs.getString("cpf"));
            novoCliente.setDtNascimento(rs.getString("dtNascimento"));
            novoCliente.getSexo().setDescricao(rs.getString("sexo"));
            novoCliente.getEndereco().setLogradouro(rs.getString("logradouro"));
            novoCliente.getEndereco().setBairro(rs.getString("bairro"));
            novoCliente.getEndereco().setNumero(rs.getInt("numero"));
            novoCliente.getEndereco().setCidade(rs.getString("cidade"));
            novoCliente.getEndereco().setUf(rs.getString("uf"));
            novoCliente.getEndereco().setCep(rs.getString("cep"));
            novoCliente.setTelefoneFixo(rs.getString("telFixo"));
            novoCliente.setCelular(rs.getString("celular"));
            novoCliente.getUsuario().setEmail(rs.getString("email"));
            novoCliente.setSituacao(rs.getInt("situacao"));
            
            listaCliente.add(novoCliente);
        }
         return listaCliente;
        
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
