<%-- 
    Document   : Login
    Created on : 16/05/2018, 01:40:41
    Author     : bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="André, Bruno e Rene">
    <title>Login - Vital Beauty</title>
    <link rel="icon" href="img/favicon.png">
    <link rel="stylesheet" href="css/reset.css">    
    <link rel="stylesheet" href="css/login.css">
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">    
</head>
<body>
        <%
        /* Cria uma sessão nesta página JSP. Quando outras partes da aplicação receberem */
        /* requisições sem o objeto de sessão criado, deverão redirecionar o fluxo para cá */
        HttpSession L_sessao = request.getSession(true);
        if (!L_sessao.isNew()) {
            /* a sessão já havia sido criada antes. O fluxo está voltando a essa página. Deve-se ver */
            /* se há alguma mensagem a mostrar para o usuário e remover informações na sessão */
               String Mens_Login = (String) L_sessao.getAttribute("mensagem");
          if (Mens_Login != null) {
    %>
    <h1 class="avisos"><%=Mens_Login%></h1>
    <%
            }
        }
        /* o formulário de login será apresentado em seguida */
    %>
    <header class="cabecalho">
        <nav class="navegacao">
            <h1 class="titulo">Vital Beauty </h1>
            <ul class="menu-nav">
                <li>
                    <a href="index.html">home</a>
                </li>
                <li>
                    <a href="#">Quem somos</a>
                </li>
                <li>
                    <a href="#">Agendar Consulta</a>
                </li>
                <li>
                    <a href="#">Estética Facial</a>
                </li> 
                <li>
                    <a href="#">Estética Corporal</a>
                </li> 
                <li>
                    <a href="#">Serviços</a>
                </li>                
                <li>
                    <a href="#">Contato</a>
                </li>      
            </ul>
        </nav>
    </header>
    <main class="container">
        <form class="formulario" action="ControleAutentica" method="POST">
            <div>
                <legend id="legenda" class="legenda">Login Here</legend>
                <strong>Vital Beauty</strong>
            </div>
            <fieldset class="field">
                
                <label for="email">
                    <input type="email" name="txtEmail" id="email" placeholder="email" required autofocus>
                </label>

                <label for="password">
                    <input type="password" name="txtSenha" id="password" required placeholder="senha">    
                </label>
                
                <input type="submit" name="submit" id="submit" value="Login">                     
                <div class="opcoes">
                    <a class="recuperar-senha" href="#">Esqueci minha senha</a>
                    <a href="#">Criar conta </a> 
                </div>
           
            </fieldset>
        </form>

        <!-- Modal Recuperar Senha -->
        <form id="modal-recuperar_senha" class="modal" action="ControleAutentica?acao=Enviar" method="POST">               
            <button class="close">X</button>                      
            <div class="header-modal">         
                <legend class="legenda-recuperação">Recuperação de Senha</legend>
            </div>  
            <h3 class="titulo-modal">Informe seu email e enviaremos um link com a solicitação de uma nova senha.</h3>           
            <fieldset class="fiel-recuperacao">
                <label for="recuperar-email">Email</label>
                <input type="email" name="recuperar-email" id="recuperar_email" required>
            </fieldset>
            <input type="submit" name="btn-enviar" id="btn-enviar">  
        </form>

    </main> 
    <footer class="rodape">
        <p class="direitos-autorais">Copyright &copy; Vital Beauty | Todos os direitos reservados</p>
    </footer>
    <script src="js/botao.js"></script>
</body>
</html>