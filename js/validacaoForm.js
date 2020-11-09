var botao = buscaSeletor(".botao-contato");

botao.addEventListener("click", function(event){
    
    var form = buscaSeletor(".formulario");
    var contato = obtemDadosdoForm(form);
    validaDados(contato);

});


//Busca os seletores no HTML
function buscaSeletor(classe){
    return document.querySelector(classe);
}

//Obtendo os valores digitados pelo usuário, criando um objeto e passando como parâmetro o formulário.
function obtemDadosdoForm(form){

    var contato = {
        nome: form.nome.value,
        email: form.email.value,
        assunto: form.assunto.value,
        mensagem: form.mensagem.value
    }

    return contato;
    
}

//Validar os dados de entrada

function validaDados(contato){

    if(contato.nome.length == 0){
        event.preventDefault();
       alert("Por favor, preencha o campo nome *");
    }
    if(contato.email.length == 0){
        event.preventDefault();
        alert("Por favor, preencha o campo email *");
    }
    if(contato.assunto.length < 4){
        event.preventDefault();
        alert("O campo assunto precisa ter no mínimo 4 caracteres*");
    }
    if(contato.mensagem.length < 4){
        event.preventDefault();
        alert("O campo mensagem precisa ter no mínimo 4 caracteres*");
    }
}