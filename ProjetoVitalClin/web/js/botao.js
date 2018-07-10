var modal = buscaSeletor('.modal');
var btn = buscaSeletor('.recuperar-senha');
var close = buscaSeletor('.close');
var dash = buscaSeletor('.menu-navegacao-dashbord');
var Dash = buscaSeletor(".btn-dashbord");


//Função que irá abrir a janela modal
btn.onclick = function(event) { 
    if (event.target === btn) {
        modal.style.display = "block";
    }
}

//Função que irá fechar a janela modal
close.onclick = function(event){
    if (event.target === close){
        modal.style.display = "none";
    }
}

//Função busca seletor no DOM
function buscaSeletor(classe){
	return document.querySelector(classe);
}
