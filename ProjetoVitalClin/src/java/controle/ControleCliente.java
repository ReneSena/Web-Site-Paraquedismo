/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.*;
import modelo.DAO.ClienteDAO;
import modelo.DAO.UsuarioDAO;
import util.Email;


/**
 *
 * @author bruno
 */
public class ControleCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset:=UTF-8");

        try {

            //recupera param acao
            String acao = request.getParameter("acao");
            if (acao.equals("Salvar")) {
                String nome = request.getParameter("txtNome");
                String cpf = request.getParameter("txtCpf");
                String dtNascimento = request.getParameter("txtDtNasc");
                String sexo = request.getParameter("btrSexo");
                String cep = request.getParameter("txtCep");
                String logradouro = request.getParameter("txtEndereco");
                int numero = Integer.parseInt(request.getParameter("txtNumero"));
                String bairro = request.getParameter("txtBairro");
                String cidade = request.getParameter("txtCidade");
                String uf = request.getParameter("txtEstado");
                String telFixo = request.getParameter("txtTelFixo");
                String celular = request.getParameter("txtCel");
                String email = request.getParameter("txtEmail");
                String senha = request.getParameter("txtSenha");
                int situacao = 1;

                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setCpf(cpf);
                cliente.setDtNascimento(dtNascimento);
                cliente.getSexo().setDescricao(sexo);
                cliente.setEndereco(new Endereco(logradouro, bairro, numero, cidade, uf, cep));
                cliente.setTelefoneFixo(telFixo);
                cliente.setCelular(celular);
                cliente.setUsuario(new Usuario(email, senha, 1));
                cliente.setSituacao(situacao);
                
                UsuarioDAO usuDAO = new UsuarioDAO();
                usuDAO.cadastrarUsuario(cliente); // vai até o usuarioDAO e cadastra os dados de usuario na tabela usuario

                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.cadastrar((Cliente) usuDAO.pegaID(cliente)); // passa como parametro o cliente e dentro da UsuarioDAO busca o id do usuario que foi cadastrado para inserir no cliente
                
                boolean emailEnviado;
                Email enviaEmail = new Email();
                emailEnviado = enviaEmail.enviarBoasVindas(cliente);
                
 
                RequestDispatcher rd = request.getRequestDispatcher("cadCliSucesso.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Listar")) {

                String buscaNome = request.getParameter("txtBuscaNome");
                String buscaCpf = request.getParameter("txtBuscaCpf");

                if ("".equals(buscaNome)) {
                    buscaNome = null;
                }

                if ("".equals(buscaCpf)) {
                    buscaCpf = null;
                }
                
                ArrayList<Cliente> clientes;
                
                
                ClienteDAO cDAO = new ClienteDAO();

                if (buscaNome != null && buscaCpf == null) {
                    Cliente cliente = new Cliente ();
                    cliente.setNome(buscaNome);
                    clientes = cDAO.listarPorNome(cliente);
                    

                } else if (buscaCpf != null && buscaNome == null) {
                    Cliente cliente = new Cliente ();
                    cliente.setCpf(buscaCpf);
                    clientes = cDAO.listarPorCpf(cliente);

                } else {
                  
                    clientes = cDAO.listar();
                }
                
                request.setAttribute("listaCliente", clientes);
           
                RequestDispatcher rd = request.getRequestDispatcher("CliConsultar.jsp");
                rd.forward(request, response);

            } else if (acao.equals("excluir")) {

                int id = Integer.parseInt(request.getParameter("id"));

                Cliente cliente = new Cliente();
                cliente.setId(id);

                ClienteDAO cdao = new ClienteDAO();
                cdao.excluir(cliente);
              
                RequestDispatcher rd = request.getRequestDispatcher("cliExcluidoSucesso.jsp");
                rd.forward(request, response);

            } else if (acao.equals("alterar")) {

                int id = Integer.parseInt(request.getParameter("id"));

                Cliente cliente = new Cliente();
                cliente.setId(id);

                ClienteDAO cdao = new ClienteDAO();

                cdao.buscar(cliente);
                //redireciono
                request.setAttribute("cliente", cliente);
                RequestDispatcher rd = request.getRequestDispatcher("CliAlterar.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Confirmar")) {

                String nome = request.getParameter("txtNome");
                String cpf = request.getParameter("txtCpf");
                String dtNascimento = request.getParameter("txtDtNasc");
                String sexo = request.getParameter("btrSexo");
                String cep = request.getParameter("txtCep");
                String logradouro = request.getParameter("txtEndereco");
                int numero = Integer.parseInt(request.getParameter("txtNumero"));
                String bairro = request.getParameter("txtBairro");
                String cidade = request.getParameter("txtCidade");
                String estado = request.getParameter("txtEstado");
                String telFixo = request.getParameter("txtTelFixo");
                String celular = request.getParameter("txtCel");
                String email = request.getParameter("txtEmail");
                int id = Integer.parseInt(request.getParameter("txtId"));
                int idUsuario = Integer.parseInt(request.getParameter("idUsu"));
                
                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setCpf(cpf);
                cliente.setDtNascimento(dtNascimento);
                cliente.getSexo().setDescricao(sexo);
                cliente.setEndereco(new Endereco(logradouro, bairro, numero, cidade, estado, cep));
                cliente.setTelefoneFixo(telFixo);
                cliente.setCelular(celular);
                cliente.setUsuario(new Usuario(idUsuario, email));
                cliente.setId(id);
                
                UsuarioDAO usuDAO = new UsuarioDAO(); // cria uma instancia de usuarioDAO
                usuDAO.AlterarUsuario(cliente); // chama o método que altera o usuario, passando o id do usuario que foi cadastrado no cliente

                ClienteDAO cdao = new ClienteDAO();
                cdao.alterar(cliente);
                //redireciono
               
                RequestDispatcher rd = request.getRequestDispatcher("cliEditSucesso.jsp");
                rd.forward(request, response);

            }
            
            else if (acao.equals("ListarCliAgenda")) {

                String buscaNome = request.getParameter("txtBuscaNome");
                String buscaCpf = request.getParameter("txtBuscaCpf");

                if ("".equals(buscaNome)) {
                    buscaNome = null;
                }

                if ("".equals(buscaCpf)) {
                    buscaCpf = null;
                }
                
                ArrayList<Cliente> clientes;
                
                //cria objeto
                ClienteDAO cDAO = new ClienteDAO();

                if (buscaNome != null && buscaCpf == null) {
                    Cliente cliente = new Cliente ();
                    cliente.setNome(buscaNome);
                    clientes = cDAO.listarPorNome(cliente);
                    

                } else if (buscaCpf != null && buscaNome == null) {
                    Cliente cliente = new Cliente ();
                    cliente.setCpf(buscaCpf);
                    clientes = cDAO.listarPorCpf(cliente);

                } else {
                    //executa o mÃ©todo listar
                    clientes = cDAO.listar();
                }
                //add a lista no obJ eto request   
                request.setAttribute("listaCliente", clientes);
                //encaminha o request para o J SP
                RequestDispatcher rd = request.getRequestDispatcher("AgendaBuscaCli.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("erro", e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("paginaErro.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControleCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControleCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
