<%-- 
    Document   : AgendaBuscaCli
    Created on : 06/06/2018, 10:46:53
    Author     : bruno
--%>

<%@page import="modelo.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="descripion" content="Clínica de Estética Vital Beauty">
        <meta name="author" content="André, Bruno e André">
        <title>Dashbord - Vital Beauty</title>

        <link rel="icon" href="img/favicon.png">
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/configuracao.css">
        <link rel="stylesheet" href="css/tabela.css">
        <link rel="stylesheet" href="css/pedidoBuscaCli.css">
        <!-- Fontes  -->
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
    </head>

    <body>
          <%
            String nomeUsu = null;
            String primeiroNome = null;
            int nivelDeAcesso = 0;
            int idFuncionario = 0;
            HttpSession L_sessao = request.getSession(false);
            if (L_sessao != null) {
                nomeUsu = (String) L_sessao.getAttribute("nomeFuncionario"); // recebendo da controle nome do funcionario
                if (nomeUsu == null) {
                    L_sessao.setAttribute("mensagem", "Acesso proibido sem login!");
                    response.sendRedirect("Login.jsp");

                } else {
                    nivelDeAcesso = (int) L_sessao.getAttribute("acesso").hashCode();
                    if (nivelDeAcesso < 2) {
                        L_sessao.setAttribute("mensagem", "Acesso somente permitido para gerentes!");
                        response.sendRedirect("Login.jsp");

                    } else {
                        idFuncionario = (int) L_sessao.getAttribute("refFuncionario").hashCode();
                        String[] s = nomeUsu.trim().split(" "); // função para pegar posição das palavras
                        primeiroNome = s[0]; // pegando o primeiro nome do funcionario
                    }
                }
            } else {
                
                L_sessao.removeAttribute("mensagem");
                L_sessao.removeAttribute("nomeUsu");
            }


        %>
        <header class="cabecalho">
             <div class="container">
            <h1 class="titulo-principal">Vital Beauty</h1>
            <nav class="navegacao-topo">
                <ul class="menu-topo">
                    <li>
                        <a class="Nome-cli botao-collapse" href="#"><%=primeiroNome%></a>
                    </li>           
                </ul>
            </nav>
        </div>
        <ol class="logout-dash">
            <li class="li-login">
                <a class="meus-dados" href="ControleFuncionario?acao=MeusDados&id=<%= idFuncionario%>">Meus Dados</a>
            </li>
            <li class="li-login b">
                <a class="logout-cli botao-collapsed" href="ControleAutentica?acao=Sair">Sair</a>
            </li>
        </ol>	
        </header>

        <aside class="menu-navegacao-dashbord">
            <nav class="nav-dashbord">
                <ul class="menu-left">
                    <li>
                        <a class="link-home" href="home.jsp">Home</a>
                    </li>
                    <li class="dropdown">
                    <a class="link-agendamento" href="#">Agendamentos</a>
                    <div class="dropdown-content">
                        <a href="ControleCliente?acao=ListarCliAgenda">Agendar</a>
                        <a href="ControleAgendamento?acao=Listar">Consultar Agenda</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a class="link-cadastro dropbtn" href="#">Cliente</a>
                    <div class="dropdown-content">
                        <a href="CliCadastro.jsp">Realizar cadastro</a>
                        <a href="ControleCliente?acao=Listar">Consultar</a>
                        <a href="#">Questionário</a>
                        <a href="#">Ficha de Avaliação</a>
                    </div>
                </li>
                <% if (nivelDeAcesso > 2){%>
                <li class="dropdown">
                    <a class="link-cadastro dropbtn" href="#">Funcionário</a>
                    <div class="dropdown-content">
                        <a href="ControleFuncionario?acao=cmb">Realizar cadastro</a>
                        <a href="ControleFuncionario?acao=Listar">Consultar</a>
                    </div>
                </li>
                <%}%>
                </ul>
            </nav>
        </aside>
        <%        //recuperar a lista
                        ArrayList<Cliente> listaCliente = (ArrayList<Cliente>) request.getAttribute("listaCliente");
        %>

        <main class="conteudo-principal">
            <div class="container-main">
                <h1 class="titulo-form">Realizar Agendamento</h1>
                <form class="formulario" action="ControleCliente?acao=ListarCliAgenda" method="POST">
                    <fieldset class="field">
                        <legend class="legenda">Buscar cliente</legend>

                        <label for="buscaNome">Buscar por nome</label>
                        <input type="text" id="buscaNome" size="5" name="txtBuscaNome" placeholder="Digite o nome do cliente" title="Preencha com o nome do cliente" required autofocus>


                        <label for="buscaCPF">Buscar por CPF</label>
                        <input type="text" id="buscaCPF" size="5" name="txtBuscaCpf" placeholder="Digite o CPF do cliente" title="Informe um CPF válido ex: 44411122299 " onkeydown="javascript: fMasc(this, mCPF);">

                    </fieldset>

                    <input type="submit" name="acao" class="botao" value="Buscar" title="Clique para buscar o cliente">
                </form>

                <div class="container-table">
                    <table class="table-fill">
                        <thead class="cabecalho-table">
                            <tr>
                                 <th class="text-left">Nome</th>
                                <th class="text-left">Cpf</th>
                                <th class="text-left">Telefone</th>
                                <th class="text-left">Ações</th>
                            </tr>
                        </thead>
                        <tbody class="table-hover">
 <%
                                for (Cliente c : listaCliente) {
                            %>

                            <tr>
                                <td class="text-left"><%= c.getNome()%></td>
                                <td class="text-left"><%= c.getCpf()%></td>
                                <td class="text-left"><%= c.getTelefoneFixo()%></td>
                                <td class="text-left">
                                    <a href="ControleAgendamento?acao=Agendar&id=<%= c.getId()%>&CliDescricao=<%=c.getNome()%>">
                                        <input type="submit" name="acao" class="botao-table-e" value="Agendar" title="Clique para editar dados do cliente">
                                    </a>
                                </td>
                            </tr>
                            <%
                                }
                            %>


                        </tbody>
                    </table>
                </div>

            </div>
        </main>

        <footer class="rodape">
            <p> Copyright &copy; Vital Beauty | Todos os direitos reservados</p>
        </footer>
        <script src="js/botao.js"></script>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/mascara.js"></script>
         <script src="js/btnLogin.js"></script>

    </body>

</html>