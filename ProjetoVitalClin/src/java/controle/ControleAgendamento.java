/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Agendamento;
import modelo.Cliente;
import modelo.DAO.AgendamentoDAO;
import modelo.DAO.ClienteDAO;
import modelo.DAO.TratamentoDAO;
import modelo.Horario;
import modelo.Tratamento;
import util.Email;

/**
 *
 * @author bruno
 */
public class ControleAgendamento extends HttpServlet {

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
            String acao = request.getParameter("acao");
            
           // condição que realiza a busca de horários disponíveis 
            if(acao.equals("busca Horarios")){ 
                
            LocalDate dtEscolhida = LocalDate.parse(request.getParameter("txtDtEscolhida")); 
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            String nomeCliente = request.getParameter("nomeCliente");
            int idTratamento = Integer.parseInt(request.getParameter("idTratamento"));
            Agendamento agendamento = new Agendamento(dtEscolhida);
            
            ArrayList<Horario> listHorarios = new ArrayList<Horario>();

            for (int i = 8; i <= 20; i++) {
                Horario hdisponiveis = new Horario();
                if (i < 10) {
                    hdisponiveis.setDescricao("0" + i + ":00");
                    hdisponiveis.setStatus("Disponível");
                } else {
                    hdisponiveis.setDescricao(i + ":00");
                    hdisponiveis.setStatus("Disponível");
                }
                listHorarios.add(hdisponiveis);

            }

            ArrayList<Agendamento> listaAgendamentos = new ArrayList<Agendamento>();
            AgendamentoDAO agenDAO = new AgendamentoDAO();
            listaAgendamentos = agenDAO.ConsultaDisponibilidadeHorario(agendamento);

            for (Horario h : listHorarios) {

                for (Agendamento g : listaAgendamentos) {

                    if (h.getDescricao().equals(g.getHora())) {
                        h.setStatus("Indisponível");
                    }
                }

            }
            
            request.setAttribute("idTratamento", idTratamento);
            request.setAttribute("idCliente", idCliente);
            request.setAttribute("nomeCliente", nomeCliente);
            request.setAttribute("dtEscolhida", dtEscolhida);
            request.setAttribute("listaHorarios", listHorarios);
            //encaminha o request para o J SP
            RequestDispatcher rd = request.getRequestDispatcher("AgendarHora.jsp");
            rd.forward(request, response);
            
            // Condição para carregar a combo de tratamento direto do banco na pagina de agendamento escolha de tratamento
            }else if (acao.equals("cmb")) {
                int idCliente = Integer.parseInt(request.getParameter("id"));
               
                //cria objeto
                TratamentoDAO tDAO = new TratamentoDAO();
                
                //executa o método listar
                ArrayList<Tratamento> tratamentos = tDAO.listar();
               
                //add a lista no obJ eto request   
                request.setAttribute("listaTratamentos", tratamentos);
                request.setAttribute("idCliente", idCliente);
                //encaminha o request para o J SP
                RequestDispatcher rd = request.getRequestDispatcher("AgendaTratamento.jsp");
                rd.forward(request, response);
            
                // Condição para começar o agendamento
            }  else if (acao.equals("Agendar")){
                
                int idCliente = Integer.parseInt(request.getParameter("id"));
                String nomeCliente = (request.getParameter("CliDescricao"));
                request.setAttribute("idCliente", idCliente);
                request.setAttribute("nomeCliente", nomeCliente);
               
                //encaminha o request para o J SP
                RequestDispatcher rd = request.getRequestDispatcher("ControleAgendamento?acao=cmb");
                rd.forward(request, response);
                
                
            } else if (acao.equals("Escolher")){
                
                int idTratamento = Integer.parseInt(request.getParameter("cmbTrat"));
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                String nomeCliente = (request.getParameter("nomeCliente"));
                
                request.setAttribute("idCliente", idCliente);
                request.setAttribute("nomeCliente", nomeCliente);
                request.setAttribute("idTratamento", idTratamento);
                
                 RequestDispatcher rd = request.getRequestDispatcher("AgendarData.jsp");
                 rd.forward(request, response);
              
                 
            } else if (acao.equals("Selecionar horario")){
                
                Horario horarioEscolhido = new Horario();
                horarioEscolhido.setDescricao(request.getParameter("cmbHorario"));
                
                String hora = horarioEscolhido.getDescricao();
                        
                LocalDate dtEscolhida = LocalDate.parse(request.getParameter("dtEscolhida"));
                int idTratamento = Integer.parseInt(request.getParameter("idTrat"));
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                String nomeCliente = request.getParameter("idCliente");
                
                Tratamento tratamento = new Tratamento(idTratamento);
                Tratamento tratamento2 = new Tratamento(idTratamento);
                
                TratamentoDAO tDAO = new TratamentoDAO();
                tratamento2 = tDAO.BuscaTratamento(tratamento);
                
                String desTrat = tratamento2.getDescricao();
                
                request.setAttribute("idCliente", idCliente);
                request.setAttribute("nomeCliente", nomeCliente);
                request.setAttribute("idTratamento", idTratamento);
                request.setAttribute("descricaoTratamento", desTrat);
                request.setAttribute("dtEscolhida", dtEscolhida);
                request.setAttribute("horarioEscolhido", hora);
                
                
                 RequestDispatcher rd = request.getRequestDispatcher("ConfirmarAgen.jsp");
                 rd.forward(request, response);
                
            } else if (acao.equals("Cadastrar")){
           
                
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                int idTratamento = Integer.parseInt(request.getParameter("idTratamento"));
                int idFuncionario = Integer.parseInt(request.getParameter("idFuncionario"));
                String hora = request.getParameter("txHora");
                LocalDate data = LocalDate.parse(request.getParameter("txData"));
                String nomeCliente = request.getParameter("txtCliente");
                String nomeTratamento = request.getParameter("txTratamento");
                        
                Agendamento agendamento = new Agendamento();
                agendamento.getCliente().setId(idCliente);
                agendamento.getCliente().setNome(nomeCliente);
                agendamento.getTratamento().setDescricao(nomeTratamento);
                agendamento.getTratamento().setId(idTratamento);
                agendamento.getFuncionario().setId(idFuncionario);
                agendamento.setData(data);
                agendamento.setHora(hora);
                
                AgendamentoDAO agDAO = new AgendamentoDAO();
                agDAO.cadastrar(agendamento);
                
                
                Cliente cliente = new Cliente();
                cliente.setId(idCliente);
                
                ClienteDAO cliDAO = new ClienteDAO();
                cliDAO.buscar(cliente); // busca os dados do cliente através do id
                
                agendamento.getCliente().getUsuario().setEmail(cliente.getUsuario().getEmail()); // seta email
                
                 //envia email para o cliente com os dados do agendamento
                Email email = new Email();
                email.enviarDadosAgendamento(agendamento);
               
                
 
                RequestDispatcher rd = request.getRequestDispatcher("SucessoAgendamento.jsp");
                rd.forward(request, response);
            
            } else if (acao.equals("Listar")){
                
                String dtEspecifica = (String)request.getParameter("txtDtEspecifica");
                
                Agendamento agend = new Agendamento();
                ArrayList<Agendamento> agendamentos;
                
                AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
               
                if (dtEspecifica != null) {
                    LocalDate data = LocalDate.parse(dtEspecifica);
                    agend.setData(data);
                    agendamentos = agendamentoDAO.ConferirSeOcorreu(agend);

                } else {
                    
                    LocalDate hoje = LocalDate.now(); // pega data de hoje
                    agend.setData(hoje);
                    agendamentos = agendamentoDAO.ConferirSeOcorreu(agend);
                
                
                 for (Agendamento agen : agendamentos) {
                       
                        LocalTime horaCompletoAtual = LocalTime.now(); // pega hora, minuto e segundo atual
   
                        int horarioAtual = horaCompletoAtual.getHour(); // pega somente a hora atual
                        
                        String horaStringAgendamento = agen.getHora().substring(0,2); // pega o horario do agendamento
                        
                        int horaAgendamento = Integer.parseInt(horaStringAgendamento); // transforma em int o agendamento
                        
                        
                        if (horaAgendamento <= horarioAtual){
                            
                            agen.setSituacao("Ocorrido");
                            
                        } else {
                            agen.setSituacao("Nao ocorrido");
                        }
                        
                        agendamentoDAO.auterarSituacao(agen);
                        
                }
               }   
       
                request.setAttribute("ListaAgendamentos", agendamentos); // envio os agendamentos verificados para jsp
                RequestDispatcher rd = request.getRequestDispatcher("ListaAgendamentos.jsp");
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
