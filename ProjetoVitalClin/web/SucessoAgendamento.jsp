<%-- 
    Document   : SucessoAgendamento
    Created on : 07/06/2018, 01:08:42
    Author     : bruno
--%>

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
    <link rel="stylesheet" href="css/cadastro.css">
    <link rel="stylesheet" href="css/cadCliSucesso.css">
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


    <main class="conteudo-principal">
        <div class="container-main">
            <div class="div-main">
            <img class="img" src="img/calendar3.png">
            <h1 class="titulo-form">O agendamento foi realizado com sucesso!</h1>

                <div class="div-a">
                <a class="botao-a" href="ControleCliente?acao=ListarCliAgenda">Realizar Novo Agendamento</a>
                <a class="botao-a" href="ControleCliente?acao=Listar">Consultar agendamentos</a>
                <a class="botao-a" href="home.jsp">Ir para tela de home</a>
                 </div>
            </div>


        </div>
    </main>

    <footer class="rodape">
        <p> Copyright &copy; Vital Beauty | Todos os direitos reservados</p>
    </footer>
    <script src="js/botao.js"></script>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/btnLogin.js"></script>

</body>

</html>