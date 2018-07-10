var senha = document.querySelector("#senha");
var cofirmaSenha = document.querySelector("#confirmaSenha");
var botao = document.querySelector(".botao");
console.log(senha);
console.log(confirmaSenha);

botao.addEventListener("click", function(){

    if(senha.value != confirmaSenha.value){
        event.preventDefault();
        alert("As senhas não Conferem !! Digite novamente !!");
        senha.classList.add("erroSenha");
        confirmaSenha.classList.add("erroSenha");
    }

})