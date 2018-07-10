<%-- 
    Document   : Questionario
    Created on : 13/06/2018, 16:55:36
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
        <link rel="stylesheet" href="css/questionario.css">
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
                <h2 class="subtitulo">Avaliação de Anamnese - Vital Beauty</h2>
                <form class="form-questionario">
                    <legend class="legenda-questionario"></legend>
                    <!-- Fornercer dados dos pacientes e do funcionário que irá avaliar -->
                    <fieldset class="dados-paciente">
                        <div>
                            <label for="nome-paciente">Nome do Paciente</label>
                            <input type="text" name="nome" id="nome-paciente" title="Informe seu nome completo..">
                            <label for="idade-paciente">Idade</label>
                            <input type="number" name="idade" id="idade-paciente" title="Informe sua idade..">
                        </div>
                        <label for="avaliador">Avaliador (a)</label>
                        <input type="text" name="avaliador" id="avaliador" title="Informe o nome de quem esta realizando a avaliação ..">
                    </fieldset>
                    <!-- Questões para serem respondidas pelos pacientes -->
                    <fieldset class="questoes-anamnese">

                        <div class="container-form-group">
                            <h3 class="primeira-pergunta pg-quest" title="Costuma permanecer muito tempo sentada?">Costuma permanecer muito tempo sentada?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim1">Sim</label>
                                <input type="radio" name="opcao1" id="opcaoSim1" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao1">Não</label>
                                <input type="radio" name="opcao1" id="opcaoNao1" value="Não" title="Escolha uma das opções...">	
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="segunda-pergunta pg-quest" title="Antecedêntes cirúrgicos?">Antecedêntes cirúrgicos?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim2">Sim</label>
                                <input type="radio" name="opcao2" id="opcaoSim2" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao2">Não</label>
                                <input type="radio" name="opcao2" id="opcaoNao2" value="Não" title="Escolha uma das opções...">	
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="terceira-pergunta pg-quest" title="Tratamento estético anterior?">Tratamento estético anterior?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim3">Sim</label>
                                <input type="radio"  name="opcao3" id="opcaoSim3" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao3">Não</label>
                                <input type="radio"  name="opcao3" id="opcaoNao3" value="Não" title="Escolha uma das opções...">	
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="quarta-pergunta pg-quest" title="Antecedêntes alérgicos?">Antecedêntes alérgicos?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim4">Sim</label>
                                <input type="radio"  name="opcao4" id="opcaoSim4" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao4">Não</label>
                                <input type="radio"  name="opcao4" id="opcaoNao4" value="Não" title="Escolha uma das opções...">
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="quinta-pergunta pg-quest" title="Funcionamento instestinal regular?">Funcionamento instestinal regular?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim5">Sim</label>
                                <input type="radio"  name="opcao5" id="opcaoSim5" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao5">Não</label>
                                <input type="radio"  name="opcao5" id="opcaoNao5" value="Não" title="Escolha uma das opções...">	
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="sexta-pergunta pg-quest" title="Pratica atividade fisica?">Pratica atividade fisica?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim6">Sim</label>
                                <input type="radio"  name="opcao6" id="opcaoSim6" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao6">Não</label>
                                <input type="radio"  name="opcao6" id="opcaoNao6" value="Não" title="Escolha uma das opções...">	
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="setima-pergunta pg-quest" title="Pratica atividade fisica?">Pratica atividade fisica?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim7">Sim</label>
                                <input type="radio"  name="opcao7" id="opcaoSim7" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao7">Não</label>
                                <input type="radio"  name="opcao7" id="opcaoNao7" value="Não" title="Escolha uma das opções...">	
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="oitava-pergunta pg-quest" title="É fumante?">É fumante?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim8">Sim</label>
                                <input type="radio"  name="opcao8" id="opcaoSim8" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao8">Não</label>
                                <input type="radio"  name="opcao8" id="opcaoNao8" value="Não" title="Escolha uma das opções...">	
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="nona-pergunta pg-quest" title="Alimentação balanceada?">Alimentação balanceada?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim9">Sim</label>
                                <input type="radio"  name="opcao9" id="opcaoSim9" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao9">Não</label>
                                <input type="radio"  name="opcao9" id="opcaoNao9" value="Não" title="Escolha uma das opções...">	
                            </div>
                        </div>


                        <div class="container-form-group">
                            <h3 class="decima-pergunta pg-quest" title="Insere líquidos com frequência?">Insere líquidos com frequência?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim10">Sim</label>
                                <input type="radio"  name="opcao10" id="opcaoSim10" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao10">Não</label>
                                <input type="radio"  name="opcao10" id="opcaoNao10" value="Não" title="Escolha uma das opções...">
                            </div>	
                        </div>

                        <div class="container-form-group">
                            <h3 class="decima-primeira-pergunta pg-quest" title="É gestante?">É gestante?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim11">Sim</label>
                                <input type="radio"  name="opcao11" id="opcaoSim11" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao11">Não</label>
                                <input type="radio"  name="opcao11" id="opcaoNao11" value="Não" title="Escolha uma das opções...">	
                            </div>
                        </div>


                        <div class="container-form-group">
                            <h3 class="decima-segundo-pergunta pg-quest" title="Tem algum problema ortopédico?">Tem algum problema ortopédico?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim12">Sim</label>
                                <input type="radio"  name="opcao12" id="opcaoSim12" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao12">Não</label>
                                <input type="radio"  name="opcao12" id="opcaoNao12" value="Não" title="Escolha uma das opções...">	
                            </div>
                        </div>


                        <div class="container-form-group">
                            <h3 class="decima-terceira-pergunta pg-quest" title="Faz algum tratamento médico?">Faz algum tratamento médico?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim13">Sim</label>
                                <input type="radio"  name="opcao13" id="opcaoSim13" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao13">Não</label>
                                <input type="radio"  name="opcao13" id="opcaoNao13" value="Não" title="Escolha uma das opções...">	
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="decima-quarta-pergunta pg-quest" title="Usa ou já usou ácidos na pele?">Usa ou já usou ácidos na pele?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim14">Sim</label>
                                <input type="radio"  name="opcao14" id="opcaoSim14" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao14">Não</label>
                                <input type="radio"  name="opcao14" id="opcaoNao14" value="Não" title="Escolha uma das opções...">	
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="decima-quinta-pergunta pg-quest" title="Faz algum tratamento ortomelecular?">Faz algum tratamento ortomelecular?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim15">Sim</label>
                                <input type="radio"  name="opcao15" id="opcaoSim15" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao15">Não</label>
                                <input type="radio"  name="opcao15" id="opcaoNao15" value="Não" title="Escolha uma das opções...">	
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="decima-sexta-pergunta pg-quest" title="Cuidados diários e produtos em uso?">Cuidados diários e produtos em uso?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim16">Sim</label>
                                <input type="radio"  name="opcao16" id="opcaoSim16" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao16">Não</label>
                                <input type="radio"  name="opcao16" id="opcaoNao16" value="Não" title="Escolha uma das opções...">	
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="decima-setimo-pergunta pg-quest" title="Portador de marca-passo?">Portador de marca-passo?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim17">Sim</label>
                                <input type="radio"  name="opcao17" id="opcaoSim17" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao17">Não</label>
                                <input type="radio"  name="opcao17" id="opcaoNao17" value="Não" title="Escolha uma das opções...">
                            </div>	
                        </div>

                        <div class="container-form-group">
                            <h3 class="decima-oitava-pergunta pg-quest" title="Presença de metais?">Presença de metais?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim18">Sim</label>
                                <input type="radio"  name="opcao18" id="opcaoSim18" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao18">Não</label>
                                <input type="radio"  name="opcao18" id="opcaoNao18" value="Não" title="Escolha uma das opções...">
                            </div>	
                        </div>

                        <div class="container-form-group">
                            <h3 class="decima-nona-pergunta pg-quest" title="Antecentes oncológicos?">Antecentes oncológicos?</h3>
                            <div class="container-opcoes">
                                <label for="opcaoSim19">Sim</label>
                                <input type="radio"  name="opcao19" id="opcaoSim19" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao19">Não</label>
                                <input type="radio"  name="opcao19" id="opcaoNao19" value="Não" title="Escolha uma das opções...">	
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="vigesima-pergunta pg-quest" title="Antecentes oncológicos?">Ciclo menstrual regular?</h3>
                            <div class="container-opcoes"> 
                                <label for="opcaoSim20">Sim</label>
                                <input type="radio"  name="opcao20" id="opcaoSim20" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao20">Não</label>
                                <input type="radio"  name="opcao20" id="opcaoNao20" value="Não" title="Escolha uma das opções...">
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="vigesima-primeira-pergunta pg-quest" title="Usa método anticoncepcional?">Usa método anticoncepcional?</h3>
                            <div class="container-opcoes"> 
                                <label for="opcaoSim21">Sim</label>
                                <input type="radio"  name="opcao21" id="opcaoSim21" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao21">Não</label>
                                <input type="radio"  name="opcao21" id="opcaoNao21" value="Não" title="Escolha uma das opções...">
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="vigesima-segunda-pergunta pg-quest" title="Varizes?">Varizes?</h3>
                            <div class="container-opcoes"> 
                                <label for="opcaoSim22">Sim</label>
                                <input type="radio"  name="opcao22" id="opcaoSim22" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao22">Não</label>
                                <input type="radio"  name="opcao22" id="opcaoNao22" value="Não" title="Escolha uma das opções...">
                            </div>	
                        </div>

                        <div class="container-form-group">
                            <h3 class="vigesima-terceira-pergunta pg-quest" title="Lesões?">Lesões?</h3>
                            <div class="container-opcoes"> 
                                <label for="opcaoSim23">Sim</label>
                                <input type="radio"  name="opcao23" id="opcaoSim23" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao23">Não</label>
                                <input type="radio"  name="opcao23" id="opcaoNao23" value="Não" title="Escolha uma das opções...">
                            </div>
                        </div>

                        <div class="container-form-group">
                            <h3 class="vigesima-quarta-pergunta pg-quest" title="Uso de DIU?">Uso de DIU?</h3>
                            <div class="container-opcoes"> 
                                <label for="opcaoSim24">Sim</label>
                                <input type="radio"  name="opcao24" id="opcaoSim24" value="Sim" title="Escolha uma das opções...">			
                                <label for="opcaoNao24">Não</label>
                                <input type="radio"  name="opcao24" id="opcaoNao24" value="Não" title="Escolha uma das opções...">
                                <div>
                                </div> 
                                </fieldset>

                                <fieldset class="termo-responsabilidade">
                                    <h2 class="Titulo-termo">Termo de Responsabilidade</h2>
                                    <p class="informacoes-termo-de-ciencia">
                                        Estou ciente e de acordo com todas as informações acima relacionadas.
                                    </p>
                                    <div class="assinatura-cliente">
                                        <label for="data">Data ___/___/____</label>
                                        <label for="assinatura">Assinatura</label>
                                    </div>
                                </fieldset>
                                <input class="btn-salvar" type="submit" value="Salvar em Pdf">
                                <!-- <input class="btn-criar" type="submit" value="Inserir">
                                <input class="btn-ok" type="submit" value="Ok"> -->
                                </form>	
                            </div>
                            </main>

                            <footer class="rodape">
                                <p> Copyright &copy; Vital Beauty | Todos os direitos reservados</p>
                            </footer>
                            <script src="js/jspdf.min.js"></script>
                            <!-- <script src="js/botao.js"></script> -->
                            <script src="js/questionario.js"></script>
                            </body>

                            </html>