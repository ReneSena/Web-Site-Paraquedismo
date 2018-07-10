<%-- 
    Document   : MeusDados
    Created on : 11/06/2018, 11:24:07
    Author     : bruno
--%>

<%@page import="modelo.Funcionario"%>
<%@page import="modelo.TipoAcesso"%>
<%@page import="modelo.Contrato"%>
<%@page import="modelo.Cargo"%>
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
        <link rel="stylesheet" href="css/meusDados.css">
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
                    <a class="meus-dados" href="ControleFuncionario?acao=MeusDados&id=<%=idFuncionario%>">Meus Dados</a>
                </li>
                <li class="li-login b">
                    <a class="logout-cli botao-collapsed" href="ControleAutentica?acao=Sair">Sair</a>
                </li>
            </ol>	
        </header>
        <%
            ArrayList<Cargo> listaTiposCargos = (ArrayList<Cargo>) request.getAttribute("listaCargos");
            ArrayList<Contrato> listaContratos = (ArrayList<Contrato>) request.getAttribute("listaContratos");
            ArrayList<TipoAcesso> listaTiposAcesso = (ArrayList<TipoAcesso>) request.getAttribute("listaTiposAcesso");

            //recuperar a lista
            Funcionario funcionario = (Funcionario) request.getAttribute("funcionario");
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
                <h1 class="titulo-form">Mais informações</h1>
                <form class="formulario" action="ControleFuncionario?acao=alterar&id=<%= funcionario.getId()%>" method="POST">
                    <fieldset class="field">
                        <legend class="legenda">Dados pessoais</legend>

                        <label for="nome">Nome</label>
                        <input type="text" id="nome" size="5" name="txtNome"  value="<%= funcionario.getNome()%>" placeholder="Informe o nome" title="Preencha com nome completo"
                               disabled>


                        <label for="CPF">CPF</label>
                        <input type="text" id="CPF" size="5" name="txtCpf"  value="<%= funcionario.getCpf()%>" placeholder="Informe o CPF" title="Informe um CPF válido ex: 44411122299 "
                               onkeydown="javascript: fMasc(this, mCPF);" disabled>


                        <label for="DtNasc">Data Nasc.</label>
                        <input type="hidden" id="hDtNascimento" name="hiddenDtNascimento" value="<%= funcionario.getDtNascimento()%>">
                        <input type="date" id="DtNasc" size="5" name="txtDtNasc" placeholder="Digite a data de nascmiento" title="Preencha com a data de nascmiento ex: 03/05/1989" disabled>


                        <label>Sexo</label>
                        <input type="hidden" id="hSexo" name="hiddenSexo" value="<%= funcionario.getSexo().getDescricao()%>">
                        <input type="radio" id="masculino" name="btrSexo" value="M" title="Escolha uma das opções" disabled>
                        <label for="masculino">Masculino</label>
                        <input type="radio" id="feminino" name="btrSexo" value="F" title="Escolha uma das opções" disabled>
                        <label for="feminino">Feminino</label>

                    </fieldset>
                    <fieldset>
                        <legend class="legenda">Localização</legend>


                        <label for="CEP">CEP</label>
                        <input type="text" id="CEP" size="5" name="txtCep" value="<%=funcionario.getEndereco().getCep()%>" placeholder="Ex: 08777-650" title="Preencha com o CEP" disabled onkeydown="javascript: fMasc(this, mCEP);">


                        <label for="Endereco" class="txEnd">Endereço</label>
                        <input class="inpEnd" type="text" id="Endereco" size="5" name="txtEndereco" value="<%=funcionario.getEndereco().getLogradouro()%>" placeholder="Rua: José Macedo " title="Preencha com o nome da rua"
                               disabled>


                        <label for="numero">Nº</label>
                        <input class="inpNum" type="text" id="numero" size="5" name="txtNumero" value="<%=funcionario.getEndereco().getNumero()%>" placeholder="Ex: 132" title="Preencha com o numero da residência"
                               disabled>


                        <label for="bairro">Bairro</label>
                        <input type="text" id="bairro" size="5" name="txtBairro" value="<%=funcionario.getEndereco().getBairro()%>" placeholder=" Ex: Bairro vila teste" title="Preencha com o nome bairro"
                               disabled>


                        <label for="cidade">Cidade</label>
                        <input type="text" id="cidade" size="5" name="txtCidade" value="<%=funcionario.getEndereco().getCidade()%>" placeholder="Ex: Mogi das Cruzes" title="Preencha com o nome da cidade..."
                               disabled>


                        <label for="estado">Estado</label>
                        <input type="text" id="estado" size="5" name="txtEstado" value="<%=funcionario.getEndereco().getUf()%>" placeholder="Ex: SP" title="Preencha com o nome do estado..." disabled>

                    </fieldset>
                    <fieldset>
                        <legend class="legenda">Contato</legend>

                        <label for="TelFixo">Tel Fixo</label>
                        <input type="text" id="TelFixo" size="5" name="txtTelFixo" value="<%=funcionario.getTelefoneFixo()%>" placeholder="Informe o numero de telefone fixo ex: (11) 4444-44444" title="Preencha com o telefone"
                               disabled onkeydown="javascript: fMasc(this, mTel);">

                        <label for="Cel">Cel</label>
                        <input type="text" id="Cel" size="5" name="txtCel" value="<%=funcionario.getCelular()%>" placeholder="Informe o numero do celular ex: (11) 95555-4444" title="Preencha com o numero do celular"
                               disabled onkeydown="javascript: fMasc(this, mTel);">

                        <label for="email">E-mail</label>
                        <input type="email" id="email" size="5" name="txtEmail" value="<%=funcionario.getUsuario().getEmail()%>" placeholder="Informe o email" title="Informe um endereço de email valido ex: exe.exemplo@gmail.com" disabled>
                        <input type="hidden" name="txtId" value="<%= funcionario.getId()%>"/>
                    </fieldset>
                    <% if (nivelDeAcesso > 2) {%>
                    <fieldset>
                        <legend class="legenda">Dados da Contratação</legend>

                        <label for="select" class="labelCargo">Cargo</label>
                        <select class="combo" name="cmbCargo" disabled>
                            <%
                                for (Cargo cargo : listaTiposCargos) {

                                    if (funcionario.getCargo().getId() == cargo.getId()) {
                            %>

                            <option selected=selected value="<%= cargo.getId()%>"><%= cargo.getDescricao()%></option>

                            <%
                            } else {
                            %>

                            <option  value="<%= cargo.getId()%>"><%= cargo.getDescricao()%></option>

                            <%
                                    }

                                }
                            %> 
                        </select>
                        <label for="select" class="labelTipoContrato">Tipo de Contratação</label>
                        <select class="combo" name="cmbTipoContrato" disabled>
                            <%
                                for (Contrato contrato : listaContratos) {

                                    if (funcionario.getContrato().getId() == contrato.getId()) {
                            %>

                            <option selected=selected value="<%= contrato.getId()%>"><%= contrato.getDescricao()%></option>

                            <%
                            } else {
                            %>

                            <option  value="<%= contrato.getId()%>"><%= contrato.getDescricao()%></option>

                            <%
                                    }

                                }
                            %> 
                        </select>
                        <label for="salario">Salário</label>
                        <input type="text" id="salario" size="5" name="txtSalario"  value="<%=funcionario.getSalario()%>" placeholder="Informe o salário do funcionário" title="Informe o salário do funcionário" disabled>

                    </fieldset>
                    <fieldset>
                        <legend class="legenda">Segurança</legend>

                        <label for="select" class="labelTipoUsuario">Tipo de Usuario</label>
                        <select class="combo" name="cmbTipoUsuario" disabled>
                            <%
                                for (TipoAcesso tipoAcesso : listaTiposAcesso) {

                                    if (funcionario.getUsuario().getTipo().getId() == tipoAcesso.getId()) {
                            %>

                            <option selected=selected value="<%= tipoAcesso.getId()%>"><%= tipoAcesso.getDescricao()%></option>

                            <%
                            } else {
                            %>

                            <option  value="<%= tipoAcesso.getId()%>"><%= tipoAcesso.getDescricao()%></option>

                            <%
                                    }

                                }
                            %> 
                        </select>

                        <input type="hidden" id="hDtNascimento" name="txtIdUsu" value="<%= funcionario.getDtNascimento()%>">
                    </fieldset>
                    <%}%>
                    <div class="btn-meusDados">
                        <a class="btn-voltar" href="javascript:window.history.go(-1)">Voltar</a>
                        <% if (nivelDeAcesso > 2) {%>
                        <input type="submit" name="acao" class="botao" value="Alterar" title="Clique para enviar os dados do formulário">
                        <%}%>
                    </div>
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