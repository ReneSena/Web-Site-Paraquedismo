/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.DAO.ClienteDAO;
import modelo.DAO.FuncionarioDAO;
import modelo.DAO.UsuarioDAO;
import modelo.Funcionario;
import modelo.Usuario;
import util.Email;

/**
 *
 * @author bruno
 */
public class ControleAutentica extends HttpServlet {

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
            HttpSession L_sessao = request.getSession(false);
            String acao = request.getParameter("acao");
            if ("Sair".equals(acao)) {
                if (L_sessao != null) {
                    L_sessao.removeAttribute("nomeFuncionario");
                    L_sessao.invalidate();
                }
                response.sendRedirect("Login.jsp");
                return;
            }

            L_sessao.setMaxInactiveInterval(180);
            if (L_sessao == null) {
                response.sendRedirect("Login.jsp");
                return;
            }
            L_sessao.removeAttribute("Login");

            /* recupera e valida as credenciais do usuário  */
            String email = request.getParameter("txtEmail");
            String senha = request.getParameter("txtSenha");

            Usuario usuario = new Usuario(email, senha);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean logado = usuarioDAO.logar(usuario);

            if (logado) {

                Funcionario funcionario = new Funcionario(usuario);
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                funcionarioDAO.pegaUsuario(funcionario);

                int refFuncionario = funcionario.getId();
                String nomeFuncionario = funcionario.getNome();
                int nivelAcesso = funcionario.getUsuario().getTipo().getId();

                L_sessao.setAttribute("refFuncionario", refFuncionario);
                L_sessao.setAttribute("nomeFuncionario", nomeFuncionario);
                L_sessao.setAttribute("acesso", nivelAcesso);
                response.sendRedirect("home.jsp");

            } else {
                /* se a autenticação falhar, coloca uma mensagem na sessão e redireciona para índex */
                L_sessao.setAttribute("mensagem", "Email ou senha inválida");
                response.sendRedirect("Login.jsp");
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
