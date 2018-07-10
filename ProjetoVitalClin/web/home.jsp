<%-- 
    Document   : home
    Created on : 06/06/2018, 18:39:35
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
        <link rel="stylesheet" href="css/questionario.css">
        <link rel="stylesheet" href="css/indexDash.css">
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
        <div class="dividir-container">
            <main class="conteudo-principal">
                <div class="container-main">
                    <span class="background-boas-vindas">
                        <h1 class="titulo-principal-dash">Clínica Vital Beauty</h1>
                        <h2 class="subtitulo-principal">Beleza ao seu Alcance</h2>
                    </span> 
                    <aside class="navegacao-dash-home">
                        <nav class="menu-nav-dash-home">
                            <ul class="lista-menu-navegacao-admin">
                                <!-- <li class="item"><a class="home" href="#">Home</a></li> -->
                                <li class="item"><a class="agendamento" href="ControleAgendamento?acao=Listar">Agendamento</a></li>
                                <li class="item"><a class="cadastro" href="CliCadastro.jsp">Cadastro</a></li>
<!--                                <li class="item"><a class="tratamento" href="#">Tratamento</a></li>
                                <li class="item"><a class="estoque" href="#">Estoque</a></li>
                                <li class="item"><a class="despesas" href="#">Despesas</a></li>-->
                            </ul>
                        </nav>
                    </aside>
                </div>

            </main>
        </div>
        <!-- <footer class="rodape">
                <p> Copyright &copy; Vital Beauty | Todos os direitos reservados</p>
        </footer> -->
        <!-- <script src="js/botao.js"></script> -->
        <script src="js/btnLogin.js"></script>
    </body>
</html>