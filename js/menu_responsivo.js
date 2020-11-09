document.querySelector('.menu-abrir').onclick = function(){
    document.documentElement.classList.add('menu-ativo');
}
//Adicionando o evento na raiz do html com o document.documentElement
document.querySelector('.menu-fechar').onclick = function(){
    document.documentElement.classList.remove('menu-ativo');
}
//Quando elemento é clicado fora do menu ele também é fechado...
document.documentElement.onclick = function(event) {
    if (event.target === document.documentElement) {
        document.documentElement.classList.remove('menu-ativo');
    }
};