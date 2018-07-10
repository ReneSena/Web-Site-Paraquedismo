
var main = document.querySelector(".conteudo-principal");

var btn = main.querySelector(".btn-pdf");

btn.addEventListener("click", function (){
    var doc = new jsPDF();
    doc.text(20,10, 'dddd');
    doc.save('verifica.pdf');
    
})



