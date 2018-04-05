//Buscando seletor no HTML e atrelando o mesmo um evento de click
$('.botao-voltar').click(function(e){
    e.preventDefault();                         //Retardando a ação padrão do elemento

    var id = $(this).attr('href'),              //Criando uma variável e atribuindo o atributo href a ele
        targetOffset = $(id).offset().top;      //Passando como parametro o id, pra a função offset que retornará o top e left do elemento

    //Tempo em que o scroll vai ser animado, adicionando a função animate.
    $('html, body').animate({
            scrollTop: targetOffset
        }, 500);                                //Tempor que será executado a animação no elemento.
    });
