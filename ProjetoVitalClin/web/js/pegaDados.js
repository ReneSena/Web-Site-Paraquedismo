var input = document.querySelector("#hDtNascimento").value;

document.getElementById("DtNasc").setAttribute("value", input);

var inputSexo = document.querySelector("#hSexo").value;

 if (inputSexo === "M"){
     
     document.getElementById("masculino").checked = true;
 } else {
     document.getElementById("feminino").checked = true;
 }