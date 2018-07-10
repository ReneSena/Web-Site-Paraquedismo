package modelo.DAO;

import util.ConectaBanco;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.TipoAcesso;

public class FuncionarioDAO {

    private static final String INSERT = "INSERT INTO funcionario(nome, cpf, dtNascimento, sexo, logradouro, bairro, numero, cidade, uf, cep, telFixo, celular, situacao, usuarioFK, contratoFK, cargoFK, salario) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private static final String SELECT_ALL = "SELECT * FROM listafunc order by nome asc;";
    private static final String BUSCAR_NOME = "select f.id as idfuncionario, f.nome, f.cpf, f.dtNascimento, f.sexo, f.logradouro, f.bairro, f.numero, f.cidade,\n" +
"		f.uf, f.cep, f.telFixo, f.celular, f.salario, u.email, t.id as idtipoacesso, t.descricao as descricaotipoacesso,\n" +
"		ct.id as idcontrato, ct.descricao as descricaocontrato, cg.id as idcargo, cg.descricao as descricaocargo \n" +
"		from funcionario f, usuario u, tipoAcesso t, contrato ct, cargo cg where f.usuarioFK = u.id and \n" +
"		f.contratoFK = ct.id and f.cargoFK = cg.id and u.tipoAcessoFK = t.id and f.situacao <> 2 and f.nome=? order by nome asc;";
    private static final String BUSCAR_CARGO = "select f.id as idfuncionario, f.nome, f.cpf, f.dtNascimento, f.sexo, f.logradouro, f.bairro, f.numero, f.cidade,\n" +
"		f.uf, f.cep, f.telFixo, f.celular, f.salario, u.email, t.id as idtipoacesso, t.descricao as descricaotipoacesso,\n" +
"		ct.id as idcontrato, ct.descricao as descricaocontrato, cg.id as idcargo, cg.descricao as descricaocargo \n" +
"		from funcionario f, usuario u, tipoAcesso t, contrato ct, cargo cg where f.usuarioFK = u.id and \n" +
"		f.contratoFK = ct.id and f.cargoFK = cg.id and u.tipoAcessoFK = t.id and f.situacao <> 2 and cg.descricao =? order by nome asc;";
    private static final String BUSCAR_EMAIL = "SELECT u.id as idusuario, u.senha, u.tipoAcessoFK, f.nome, f.id as idfuncionario FROM usuario u, funcionario f WHERE f.usuarioFK = u.id and u.email=? order by nome asc;";
    private static final String DELETE = "UPDATE funcionario set situacao=2 where id=?;";
    private static final String BUSCAR = "select f.id as idfuncionario, f.nome, f.cpf, f.dtNascimento, f.sexo, f.logradouro, f.bairro, f.numero, f.cidade,\n" +
"		f.uf, f.cep, f.telFixo, f.celular, f.salario, u.email, u.id as idusuario, t.id as idtipoacesso, t.descricao as descricaotipoacesso,\n" +
"		ct.id as idcontrato, ct.descricao as descricaocontrato, cg.id as idcargo, cg.descricao as descricaocargo \n" +
"		from funcionario f, usuario u, tipoAcesso t, contrato ct, cargo cg where f.usuarioFK = u.id and \n" +
"		f.contratoFK = ct.id and f.cargoFK = cg.id and u.tipoAcessoFK = t.id and f.situacao <> 2 and f.id=? order by nome asc;";
    private static final String UPDATE = "UPDATE funcionario SET nome=?, cpf=?, dtNascimento=?, sexo=?, logradouro=?, bairro=?, numero=?, cidade=?, uf=?, cep=?, telFixo=?, celular=?, usuarioFK=?, contratoFK=?, cargoFK=?, salario=? WHERE id=?;";

    private Object pstm;

    public boolean cadastrar(Funcionario funcionario) throws SQLException, ClassNotFoundException {

        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCpf());
            pstmt.setString(3, funcionario.getDtNascimento());
            pstmt.setString(4, funcionario.getSexo().getDescricao());
            pstmt.setString(5, funcionario.getEndereco().getLogradouro());
            pstmt.setString(6, funcionario.getEndereco().getBairro());
            pstmt.setInt(7, funcionario.getEndereco().getNumero());
            pstmt.setString(8, funcionario.getEndereco().getCidade());
            pstmt.setString(9, funcionario.getEndereco().getUf());
            pstmt.setString(10, funcionario.getEndereco().getCep());
            pstmt.setString(11, funcionario.getTelefoneFixo());
            pstmt.setString(12, funcionario.getCelular());
            pstmt.setInt(13, funcionario.getSituacao());
            pstmt.setInt(14, funcionario.getUsuario().getId());
            pstmt.setInt(15, funcionario.getContrato().getId());
            pstmt.setInt(16, funcionario.getCargo().getId());
            pstmt.setDouble(17, funcionario.getSalario());

            pstmt.execute();
            return true;

        } catch (Exception ex) {
            System.out.println("Erro: , " + ex.getMessage());
            return false;

        } finally {

            conexao.close();
        }
    }

    public ArrayList<Funcionario> listar() throws SQLException, ClassNotFoundException {
        //cria uma array de obJ Funcionario
        ArrayList<Funcionario> listaFuncionario = new ArrayList<Funcionario>();

        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);
        //executa
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Funcionario novoFuncionario = new Funcionario();
            novoFuncionario.setId(rs.getInt("idfuncionario"));
            novoFuncionario.setNome(rs.getString("nome"));
            novoFuncionario.setCpf(rs.getString("cpf"));
            novoFuncionario.setDtNascimento(rs.getString("dtNascimento"));
            novoFuncionario.getSexo().setDescricao(rs.getString("sexo"));
            novoFuncionario.getEndereco().setLogradouro(rs.getString("logradouro"));
            novoFuncionario.getEndereco().setBairro(rs.getString("bairro"));
            novoFuncionario.getEndereco().setNumero(rs.getInt("numero"));
            novoFuncionario.getEndereco().setCidade(rs.getString("cidade"));
            novoFuncionario.getEndereco().setUf(rs.getString("uf"));
            novoFuncionario.getEndereco().setCep(rs.getString("cep"));
            novoFuncionario.setTelefoneFixo(rs.getString("telFixo"));
            novoFuncionario.setCelular(rs.getString("celular"));
            novoFuncionario.setSalario(rs.getDouble("salario")); // pega o salario que esta associado ao cargo
            novoFuncionario.getUsuario().setEmail(rs.getString("email"));
            novoFuncionario.getUsuario().setId(rs.getInt("idusuario"));
            novoFuncionario.getUsuario().getTipo().setId(rs.getInt("idtipoacesso")); // pegando o id do tipo de acesso
            novoFuncionario.getUsuario().getTipo().setDescricao(rs.getString("descricaotipoacesso")); // pegando a descricao do tipo de acesso
            novoFuncionario.getContrato().setId(rs.getInt("idcontrato")); // pega o id do contrato
            novoFuncionario.getContrato().setDescricao(rs.getString("descricaocontrato")); // pega a descricao do contrato
            novoFuncionario.getCargo().setId(rs.getInt("idcargo")); // pega o id do cargo
            novoFuncionario.getCargo().setDescricao(rs.getString("descricaocargo")); // pega a descricao do cargo

            //add na lista
            listaFuncionario.add(novoFuncionario);
        }
        return listaFuncionario;
    }

    public boolean excluir(Funcionario funcionario) throws SQLException {
        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, funcionario.getId());

            pstmt.execute();

            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            conexao.close();
        }

    }

    public void buscar(Funcionario funcionario) {
        try {
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR);

            pstmt.setInt(1, funcionario.getId());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

            funcionario.setId(rs.getInt("idfuncionario"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setDtNascimento(rs.getString("dtNascimento"));
            funcionario.getSexo().setDescricao(rs.getString("sexo"));
            funcionario.getEndereco().setLogradouro(rs.getString("logradouro"));
            funcionario.getEndereco().setBairro(rs.getString("bairro"));
            funcionario.getEndereco().setNumero(rs.getInt("numero"));
            funcionario.getEndereco().setCidade(rs.getString("cidade"));
            funcionario.getEndereco().setUf(rs.getString("uf"));
            funcionario.getEndereco().setCep(rs.getString("cep"));
            funcionario.setTelefoneFixo(rs.getString("telFixo"));
            funcionario.setCelular(rs.getString("celular"));
            funcionario.setSalario(rs.getDouble("salario")); // pega o salario que esta associado ao cargo
            funcionario.getUsuario().setEmail(rs.getString("email"));
            funcionario.getUsuario().setId(rs.getInt("idusuario"));
            funcionario.getUsuario().getTipo().setId(rs.getInt("idtipoacesso")); // pegando o id do tipo de acesso
            funcionario.getUsuario().getTipo().setDescricao(rs.getString("descricaotipoacesso")); // pegando a descricao do tipo de acesso
            funcionario.getContrato().setId(rs.getInt("idcontrato")); // pega o id do contrato
            funcionario.getContrato().setDescricao(rs.getString("descricaocontrato")); // pega a descricao do contrato
            funcionario.getCargo().setId(rs.getInt("idcargo")); // pega o id do cargo
            funcionario.getCargo().setDescricao(rs.getString("descricaocargo")); // pega a descricao do cargo

        } catch (Exception e) {

            //
        }
    }

    public boolean alterar(Funcionario funcionario) throws SQLException {
        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCpf());
            pstmt.setString(3, funcionario.getDtNascimento());
            pstmt.setString(4, funcionario.getSexo().getDescricao());
            pstmt.setString(5, funcionario.getEndereco().getLogradouro());
            pstmt.setString(6, funcionario.getEndereco().getBairro());
            pstmt.setInt(7, funcionario.getEndereco().getNumero());
            pstmt.setString(8, funcionario.getEndereco().getCidade());
            pstmt.setString(9, funcionario.getEndereco().getUf());
            pstmt.setString(10, funcionario.getEndereco().getCep());
            pstmt.setString(11, funcionario.getTelefoneFixo());
            pstmt.setString(12, funcionario.getCelular());
            pstmt.setInt(13, funcionario.getUsuario().getId());
            pstmt.setInt(14, funcionario.getContrato().getId());
            pstmt.setInt(15, funcionario.getCargo().getId());
            pstmt.setDouble(16, funcionario.getSalario());
            pstmt.setInt(17, funcionario.getId());
            pstmt.execute();
            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            conexao.close();
        }

    }

    public ArrayList<Funcionario> listarPorNome(Funcionario funcionario) throws SQLException, ClassNotFoundException {

        ArrayList<Funcionario> listaFuncionario = new ArrayList<Funcionario>();

        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL

        PreparedStatement pstmt = conexao.prepareStatement(BUSCAR_NOME);
        //executa
        pstmt.setString(1, funcionario.getNome());

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Funcionario novoFuncionario = new Funcionario();
            novoFuncionario.setId(rs.getInt("idfuncionario"));
            novoFuncionario.setNome(rs.getString("nome"));
            novoFuncionario.setCpf(rs.getString("cpf"));
            novoFuncionario.setDtNascimento(rs.getString("dtNascimento"));
            novoFuncionario.getSexo().setDescricao(rs.getString("sexo"));
            novoFuncionario.getEndereco().setLogradouro(rs.getString("logradouro"));
            novoFuncionario.getEndereco().setBairro(rs.getString("bairro"));
            novoFuncionario.getEndereco().setNumero(rs.getInt("numero"));
            novoFuncionario.getEndereco().setCidade(rs.getString("cidade"));
            novoFuncionario.getEndereco().setUf(rs.getString("uf"));
            novoFuncionario.getEndereco().setCep(rs.getString("cep"));
            novoFuncionario.setTelefoneFixo(rs.getString("telFixo"));
            novoFuncionario.setCelular(rs.getString("celular"));
            novoFuncionario.setSalario(rs.getDouble("salario")); // pega o salario que esta associado ao cargo
            novoFuncionario.getUsuario().setEmail(rs.getString("email"));
            novoFuncionario.getUsuario().getTipo().setId(rs.getInt("idtipoacesso")); // pegando o id do tipo de acesso
            novoFuncionario.getUsuario().getTipo().setDescricao(rs.getString("descricaotipoacesso")); // pegando a descricao do tipo de acesso
            novoFuncionario.getContrato().setId(rs.getInt("idcontrato")); // pega o id do contrato
            novoFuncionario.getContrato().setDescricao(rs.getString("descricaocontrato")); // pega a descricao do contrato
            novoFuncionario.getCargo().setId(rs.getInt("idcargo")); // pega o id do cargo
            novoFuncionario.getCargo().setDescricao(rs.getString("descricaocargo")); // pega a descricao do cargo

            //add na lista
            listaFuncionario.add(novoFuncionario);
        }

        return listaFuncionario;

    }

    public ArrayList<Funcionario> listarPorCargo(Funcionario funcionario) throws SQLException, ClassNotFoundException {

        ArrayList<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(BUSCAR_CARGO);
        //executa
        pstmt.setString(1, funcionario.getCargo().getDescricao());

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Funcionario novoFuncionario = new Funcionario();
            novoFuncionario.setId(rs.getInt("idfuncionario"));
            novoFuncionario.setNome(rs.getString("nome"));
            novoFuncionario.setCpf(rs.getString("cpf"));
            novoFuncionario.setDtNascimento(rs.getString("dtNascimento"));
            novoFuncionario.getSexo().setDescricao(rs.getString("sexo"));
            novoFuncionario.getEndereco().setLogradouro(rs.getString("logradouro"));
            novoFuncionario.getEndereco().setBairro(rs.getString("bairro"));
            novoFuncionario.getEndereco().setNumero(rs.getInt("numero"));
            novoFuncionario.getEndereco().setCidade(rs.getString("cidade"));
            novoFuncionario.getEndereco().setUf(rs.getString("uf"));
            novoFuncionario.getEndereco().setCep(rs.getString("cep"));
            novoFuncionario.setTelefoneFixo(rs.getString("telFixo"));
            novoFuncionario.setCelular(rs.getString("celular"));
            novoFuncionario.setSalario(rs.getDouble("salario")); // pega o salario que esta associado ao cargo
            novoFuncionario.getUsuario().setEmail(rs.getString("email"));
            novoFuncionario.getUsuario().getTipo().setId(rs.getInt("idtipoacesso")); // pegando o id do tipo de acesso
            novoFuncionario.getUsuario().getTipo().setDescricao(rs.getString("descricaotipoacesso")); // pegando a descricao do tipo de acesso
            novoFuncionario.getContrato().setId(rs.getInt("idcontrato")); // pega o id do contrato
            novoFuncionario.getContrato().setDescricao(rs.getString("descricaocontrato")); // pega a descricao do contrato
            novoFuncionario.getCargo().setId(rs.getInt("idcargo")); // pega o id do cargo
            novoFuncionario.getCargo().setDescricao(rs.getString("descricaocargo")); // pega a descricao do cargo

            listaFuncionario.add(novoFuncionario);
        }
        return listaFuncionario;
    }

    public Funcionario pegaUsuario(Funcionario funcionario) {
        try {
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR_EMAIL);

            pstmt.setString(1, funcionario.getUsuario().getEmail());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

            funcionario.setId(rs.getInt("idfuncionario"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.getUsuario().setSenha(rs.getString("senha"));
            funcionario.getUsuario().setTipo(new TipoAcesso (rs.getInt("tipoAcessoFK")));

        } catch (Exception e) {

            //
        }
        return funcionario;
    }

}
