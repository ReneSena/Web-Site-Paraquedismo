<%-- 
    Document   : CliAlterar
    Created on : 22/05/2018, 15:39:43
    Author     : bruno
--%>

<%@page import="modelo.Cliente"%>
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
        <link rel="stylesheet" href="css/alterar.css">
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
                    if (nivelDeAcesso < 3) {
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
        <%        //recuperar a lista
            Cliente cliente = (Cliente) request.getAttribute("cliente");
        %>

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
                    <% if (nivelDeAcesso > 2) {%>
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
                <h1 class="titulo-form">Alterar dados do cliente</h1>

                <form class="formulario" action="ControleCliente?idUsu=<%=cliente.getUsuario().getId()%>" method="POST">
                    <fieldset class="field">
                        <legend class="legenda">Dados pessoais</legend>

                        <label for="nome">Nome</label>
                        <input type="text" id="nome" size="5" name="txtNome" value="<%= cliente.getNome()%>"  title="Preencha com seu nome completo" required autofocus >


                        <label for="CPF">CPF</label>
                        <input type="text" id="CPF" size="5" name="txtCpf"  value="<%= cliente.getCpf()%>" title="Informe um CPF válido ex: 44411122299 "  onkeydown="javascript: fMasc(this, mCPF);">


                        <label for="DtNasc">Data Nasc.</label>
                        <input type="hidden" id="hDtNascimento" name="hiddenSexo" value="<%= cliente.getDtNascimento()%>">
                        <input type="date" id="DtNasc" size="5" name="txtDtNasc" value="" title="Preencha com sua data de nascmiento ex: 03/05/1989">


                        <label>Sexo</label>
                        <input type="hidden" id="hSexo" name="hiddenSexo" value="<%= cliente.getSexo().getDescricao()%>">
                        <input type="radio" id="masculino" name="btrSexo" value="M" title="Escolha uma das opções">
                        <label for="masculino">Masculino</label>
                        <input type="radio" id="feminino" name="btrSexo" value="F" title="Escolha uma das opções">
                        <label for="feminino">Feminino</label>  

                    </fieldset>
                    <fieldset>
                        <legend class="legenda">Localização</legend>


                        <label for="CEP">CEP</label>
                        <input type="text" id="CEP" size="5" name="txtCep" value="<%= cliente.getEndereco().getCep()%>" title="Preencha com o CEP" required onkeydown="javascript: fMasc(this, mCEP);">


                        <label for="Endereco" class="txEnd">Endereço</label>
                        <input class="inpEnd" type="text" id="Endereco" size="5" name="txtEndereco" value="<%= cliente.getEndereco().getLogradouro()%>"  title="Preencha com seu endereço, numero, bairro, cidade..." required>


                        <label for="numero">Nº</label>
                        <input class="inpNum" type="text" id="numero" size="5" name="txtNumero"  value="<%= cliente.getEndereco().getNumero()%>" title="Preencha com o numero de sua residência" required>


                        <label for="bairro">Bairro</label>
                        <input type="text" id="bairro" size="5" name="txtBairro" value="<%= cliente.getEndereco().getBairro()%>"  title="Preencha com seu bairro" required>


                        <label for="cidade">Cidade</label>
                        <input type="text" id="cidade" size="5" name="txtCidade" value="<%= cliente.getEndereco().getCidade()%>"  title="Preencha com sua cidade..." required>


                        <label for="estado">Estado</label>
                        <input type="text" id="estado" size="5" name="txtEstado" value="<%= cliente.getEndereco().getUf()%>"  title="Preencha com seu estado..." required>

                    </fieldset>
                    <fieldset>
                        <legend class="legenda">Contato</legend>

                        <label for="TelFixo">Tel Fixo</label>
                        <input type="text" id="TelFixo" size="5" name="txtTelFixo" value="<%= cliente.getTelefoneFixo()%>"  required  onkeydown="javascript: fMasc(this, mTel);">

                        <label for="Cel">Cel</label>
                        <input type="text" id="Cel" size="5" name="txtCel" value="<%= cliente.getCelular()%>"  title="Preencha com o numero de seu celular" required onkeydown="javascript: fMasc(this, mTel);">

                        <label for="email">E-mail</label>
                        <input type="email" id="email" size="5" name="txtEmail" value="<%= cliente.getUsuario().getEmail()%>"  title="Informe um endereço de email valido ex: exe.exemplo@gmail.com">
                        <input type="hidden" name="txtId" value="<%= cliente.getId()%>"/>
                    </fieldset>
                    <input type="submit" name="acao" class="botao" value="Confirmar" title="Clique para enviar os dados do formulário">
                </form>


            </div>
        </main>

        <footer class="rodape">
            <p> Copyright &copy; Vital Beauty | Todos os direitos reservados</p>
        </footer>
        <script src="js/botao.js"></script>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/buscaCep.js"></script>
        <script src="js/mascara.js"></script>
        <script src="js/pegaDados.js"></script>
        <script src="js/btnLogin.js"></script>

    </body>

</html>