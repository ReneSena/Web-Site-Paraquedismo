const btnSair = document.querySelector(".botao-collapse");
const btnCollapse = document.querySelector(".botao-collapsed");
const dashLogout = document.querySelector(".logout-dash");

console.log(btnCollapse);

btnSair.addEventListener("mouseover", function(event){
    event.preventDefault();
    dashLogout.classList.add("logou-ativo");
});

btnCollapse.addEventListener("mouseout", function(event){
    dashLogout.classList.remove("logou-ativo");
});

