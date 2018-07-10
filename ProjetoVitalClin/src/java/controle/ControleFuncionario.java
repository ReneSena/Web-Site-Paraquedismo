/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cargo;
import modelo.Contrato;
import modelo.DAO.CargoDAO;
import modelo.DAO.ContratoDAO;
import modelo.Funcionario;
import modelo.DAO.FuncionarioDAO;
import modelo.DAO.TipoAcessoDAO;
import modelo.DAO.UsuarioDAO;
import modelo.Endereco;
import modelo.TipoAcesso;
import modelo.Usuario;

/**
 *
 * @author bruno
 */
public class ControleFuncionario extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
                int idCargo = Integer.parseInt(request.getParameter("cmbCargo"));
                int idTipoContrato = Integer.parseInt(request.getParameter("cmbTipoContrato"));
                double salario = Double.parseDouble(request.getParameter("txtSalario"));
                int idTipoUsuario = Integer.parseInt(request.getParameter("cmbTipoUsuario"));
                String senha = request.getParameter("txtSenha");
                int situacao = 1;
            

                Funcionario funcionario = new Funcionario();
                funcionario.setNome(nome);
                funcionario.setCpf(cpf);
                funcionario.setDtNascimento(dtNascimento);
                funcionario.getSexo().setDescricao(sexo);
                funcionario.setEndereco(new Endereco(logradouro, bairro, numero, cidade, uf, cep));
                funcionario.setTelefoneFixo(telFixo);
                funcionario.setCelular(celular);
                funcionario.setUsuario(new Usuario(email, senha, idTipoUsuario));
                funcionario.setContrato(new Contrato(idTipoContrato));
                funcionario.setCargo(new Cargo(idCargo));
                funcionario.setSalario(salario);
                funcionario.setSituacao(situacao);
                
                
                UsuarioDAO usuDAO = new UsuarioDAO();
                usuDAO.cadastrarUsuarioFunc(funcionario); // vai até o usuarioDAO e cadastra os dados de usuario na tabela usuario

                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                funcionarioDAO.cadastrar((Funcionario) usuDAO.pegaIDFunc(funcionario)); // passa como parametro o funcionario e dentro da UsuarioDAO busca o id do usuario que foi cadastrado para inserir no funcionario
                
                //redireciono
 
                RequestDispatcher rd = request.getRequestDispatcher("CadFunSucesso.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Listar")) {

                String buscaNome = request.getParameter("txtBuscaNome");
                String buscaCargo = request.getParameter("txtBuscaCargo");

                if ("".equals(buscaNome)) {
                    buscaNome = null;
                }

                if ("".equals(buscaCargo)) {
                    buscaCargo = null;
                }
                
                ArrayList<Funcionario> funcionarios;
                
                //cria objeto
                FuncionarioDAO cDAO = new FuncionarioDAO();

                if (buscaNome != null && buscaCargo == null) {
                    Funcionario funcionario = new Funcionario ();
                    funcionario.setNome(buscaNome);
                    funcionarios = cDAO.listarPorNome(funcionario);
                    

                } else if (buscaCargo!= null && buscaNome == null) {
                    Funcionario funcionario = new Funcionario ();
                    funcionario.getCargo().setDescricao(buscaCargo);
                    funcionarios = cDAO.listarPorCargo(funcionario);

                } else {
                    //executa o mÃ©todo listar
                    funcionarios = cDAO.listar();
                }
                //add a lista no obJ eto request   
                request.setAttribute("listaFuncionario", funcionarios);
                //encaminha o request para o J SP
                RequestDispatcher rd = request.getRequestDispatcher("FunConsultar.jsp");
                rd.forward(request, response);

            } else if (acao.equals("excluir")) {

                int id = Integer.parseInt(request.getParameter("id"));

                Funcionario funcionario = new Funcionario();
                funcionario.setId(id);

                FuncionarioDAO cdao = new FuncionarioDAO();

                cdao.excluir(funcionario);
                //redireciono
                
                RequestDispatcher rd = request.getRequestDispatcher("FunExcluidoSucesso.jsp");
                rd.forward(request, response);

            } else if (acao.equals("alterar")) {

                int id = Integer.parseInt(request.getParameter("id"));

                Funcionario funcionario = new Funcionario();
                funcionario.setId(id);

                FuncionarioDAO fdao = new FuncionarioDAO();

                fdao.buscar(funcionario);
                //redireciono
                 //cria objeto
                CargoDAO cargoDAO = new CargoDAO();
                ContratoDAO contratoDAO = new ContratoDAO();
                TipoAcessoDAO tipoDAO = new TipoAcessoDAO();
                //executa o método listar
                ArrayList<Cargo> cargos = cargoDAO.listar();
                ArrayList<Contrato> contratos = contratoDAO.listar();
                ArrayList<TipoAcesso> tiposAcesso = tipoDAO.listar();
                //add a lista no obJ eto request   
                request.setAttribute("listaCargos", cargos);
                request.setAttribute("listaContratos", contratos);
                request.setAttribute("listaTiposAcesso", tiposAcesso);
                request.setAttribute("funcionario", funcionario);
                
                RequestDispatcher rd = request.getRequestDispatcher("FunAlterar.jsp");
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
                String uf = request.getParameter("txtEstado");
                String telFixo = request.getParameter("txtTelFixo");
                String celular = request.getParameter("txtCel");
                String email = request.getParameter("txtEmail");
                int idCargo = Integer.parseInt(request.getParameter("cmbCargo"));
                int idTipoContrato = Integer.parseInt(request.getParameter("cmbTipoContrato"));
                double salario = Double.parseDouble(request.getParameter("txtSalario"));
                int idTipoUsuario = Integer.parseInt(request.getParameter("cmbTipoUsuario"));
                int id = Integer.parseInt(request.getParameter("txtId"));
                int idUsuario = Integer.parseInt(request.getParameter("idUsu"));
                
              
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(nome);
                funcionario.setCpf(cpf);
                funcionario.setDtNascimento(dtNascimento);
                funcionario.getSexo().setDescricao(sexo);
                funcionario.setEndereco(new Endereco(logradouro, bairro, numero, cidade, uf, cep));
                funcionario.setTelefoneFixo(telFixo);
                funcionario.setCelular(celular);
                funcionario.setUsuario(new Usuario(idUsuario, email, idTipoUsuario));
                funcionario.setId(id);
                funcionario.setContrato(new Contrato(idTipoContrato));
                funcionario.setCargo(new Cargo(idCargo));
                funcionario.setSalario(salario);
                
                UsuarioDAO usuDAO = new UsuarioDAO(); // cria uma instancia de usuarioDAO
                usuDAO.AlterarUserFunc(funcionario); // chama o método que altera o usuario, passando o id do usuario que foi cadastrado no funcionario

                FuncionarioDAO fdao = new FuncionarioDAO();
                fdao.alterar(funcionario);
                //redireciono
               
                RequestDispatcher rd = request.getRequestDispatcher("FunEditSucesso.jsp");
                rd.forward(request, response);

            }else if (acao.equals("cmb")) {
                
                //cria objeto
                CargoDAO cDAO = new CargoDAO();
                ContratoDAO contratoDAO = new ContratoDAO();
                TipoAcessoDAO tipoDAO = new TipoAcessoDAO();
                //executa o método listar
                ArrayList<Cargo> cargos = cDAO.listar();
                ArrayList<Contrato> contratos = contratoDAO.listar();
                ArrayList<TipoAcesso> tiposAcesso = tipoDAO.listar();
                //add a lista no obJ eto request   
                request.setAttribute("listaCargos", cargos);
                request.setAttribute("listaContratos", contratos);
                request.setAttribute("listaTiposAcesso", tiposAcesso);
                //encaminha o request para o J SP
                RequestDispatcher rd = request.getRequestDispatcher("FunCadastro.jsp");
                rd.forward(request, response);

            }else if (acao.equals("MeusDados")) {
                
                int id = Integer.parseInt(request.getParameter("id"));

                Funcionario funcionario = new Funcionario();
                funcionario.setId(id);

                FuncionarioDAO fdao = new FuncionarioDAO();

                fdao.buscar(funcionario);
                //redireciono
                 //cria objeto
                CargoDAO cargoDAO = new CargoDAO();
                ContratoDAO contratoDAO = new ContratoDAO();
                TipoAcessoDAO tipoDAO = new TipoAcessoDAO();
                //executa o método listar
                ArrayList<Cargo> cargos = cargoDAO.listar();
                ArrayList<Contrato> contratos = contratoDAO.listar();
                ArrayList<TipoAcesso> tiposAcesso = tipoDAO.listar();
                //add a lista no obJ eto request   
                request.setAttribute("listaCargos", cargos);
                request.setAttribute("listaContratos", contratos);
                request.setAttribute("listaTiposAcesso", tiposAcesso);
                request.setAttribute("funcionario", funcionario);
                
                RequestDispatcher rd = request.getRequestDispatcher("MeusDados.jsp");
                rd.forward(request, response);

            }else if (acao.equals("MaisFuncionario")) {
                
                int id = Integer.parseInt(request.getParameter("id"));

                Funcionario funcionario = new Funcionario();
                funcionario.setId(id);

                FuncionarioDAO fdao = new FuncionarioDAO();

                fdao.buscar(funcionario);
                //redireciono
                 //cria objeto
                CargoDAO cargoDAO = new CargoDAO();
                ContratoDAO contratoDAO = new ContratoDAO();
                TipoAcessoDAO tipoDAO = new TipoAcessoDAO();
                //executa o método listar
                ArrayList<Cargo> cargos = cargoDAO.listar();
                ArrayList<Contrato> contratos = contratoDAO.listar();
                ArrayList<TipoAcesso> tiposAcesso = tipoDAO.listar();
                //add a lista no obJ eto request   
                request.setAttribute("listaCargos", cargos);
                request.setAttribute("listaContratos", contratos);
                request.setAttribute("listaTiposAcesso", tiposAcesso);
                request.setAttribute("funcionario", funcionario);
                
                RequestDispatcher rd = request.getRequestDispatcher("MaisFuncionario.jsp");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
