const botao = document.querySelector(".btn-salvar");
const form = document.querySelector('.form-questionario');

botao.onclick = function(){
    event.preventDefault();

 var respostas = ObtemValoresFormulario(form);
//  var CreateInput = CriarInput(respostas);
 
    var doc = new jsPDF();   
    doc.setFontSize(18);
    doc.text(55, 20, 'Avaliação de Anamnese - Vital Beauty');
    doc.setFontSize(12);
    doc.text(20,35, 'Nome do Paciente:  ' + respostas.nome);
    doc.text(160,35, 'Idade:  ' + respostas.idade);    
    doc.text(20, 40, 'Avaliador(a): ' + respostas.avaliador);    
    doc.setFontSize(16);
    doc.text(90, 55, 'Questionário'); 
    doc.line();
    doc.setFontSize(12);
    doc.text(20, 65, 'Costuma permanecer muito tempo sentada:  ' + respostas.op1);   
    doc.text(20, 73, 'Antecedêntes cirúrgicos:  ' + respostas.op2);      
    doc.text(20, 81,'Tratamento estético anterior:  ' + respostas.op3);   
    doc.text(20, 89, 'Antecedêntes alérgicos:  ' + respostas.op4);   
    doc.text(20, 97, 'Funcionamento instestinal regular:  ' + respostas.op5);   
    doc.text(20, 105, 'Pratica atividade fisica:  ' + respostas.op6);   
    doc.text(20, 113, 'Pratica atividade fisica:  ' + respostas.op7);   
    doc.text(20, 121, 'É fumante:  ' + respostas.op8);   
    doc.text(20, 129, 'Alimentação balanceada:  ' + respostas.op9);   
    doc.text(20, 137, 'Insere líquidos com frequência:  ' + respostas.op10);   
    doc.text(20, 145, 'É gestante:  '+ respostas.op11);   
    doc.text(20, 153, 'Tem algum problema ortopédico:  ' + respostas.op12);   
    doc.text(20, 161, 'Faz algum tratamento médico:  ' + respostas.op13);   
    doc.text(20, 169, 'Usa ou já usou ácidos na pele:  ' + respostas.op14);   
    doc.text(20, 177, 'Faz algum tratamento ortomelecular:  ' + respostas.op15);   
    doc.text(20, 185, 'Cuidados diários e produtos em uso:  ' + respostas.op16);   
    doc.text(20, 193, 'Portador de marca-passo:  ' + respostas.op17);   
    doc.text(20, 201, 'Presença de metais:  ' + respostas.op18);   
    doc.text(20, 209, 'Antecentes oncológicos:  ' + respostas.op19);   
    doc.text(20, 217, 'Ciclo menstrual regular:  ' + respostas.op20);   
    doc.text(20, 225, 'Usa método anticoncepcional:  ' + respostas.op21);   
    doc.text(20, 233, 'Varizes:  ' + respostas.op22);   
    doc.text(20, 241, 'Lesões:  ' + respostas.op23);   
    doc.text(20, 249, 'Uso de DIU:  ' + respostas.op24);     
    //Termo de responsabilidade 
    doc.setFontSize(16);
    doc.text(75, 260, 'Termo de Responsabilidade'); 
    doc.setFontSize(11);
    doc.text(45, 268, 'Estou ciente e de acordo com todas as informações acima relacionadas.'); 
    doc.setFontSize(12);
    doc.text(20, 280, 'Data ___/___/____ '); 
    doc.text(100, 280,' Assinatura _____________________________' );     
    doc.autoPrint();
    doc.save('Avaliacao_Questionario_'+ respostas.nome + '.pdf'); 
}

//Função que pega os valores selecionados pelo funcionários
function ObtemValoresFormulario(form){

    var resp = {
        nome: form.nome.value,
        idade: form.idade.value,
        avaliador: form.avaliador.value,
        op1: form.opcao1.value,
        op2: form.opcao2.value,
        op3: form.opcao3.value,
        op4: form.opcao4.value,
        op5: form.opcao5.value,
        op6: form.opcao6.value,
        op7: form.opcao7.value,
        op8: form.opcao8.value,
        op9: form.opcao9.value,
        op10: form.opcao10.value,
        op11: form.opcao11.value,
        op12: form.opcao12.value,
        op13: form.opcao13.value,
        op14: form.opcao14.value,
        op15: form.opcao15.value,
        op16: form.opcao16.value,
        op17: form.opcao17.value,
        op18: form.opcao18.value,
        op19: form.opcao19.value,
        op20: form.opcao20.value,
        op21: form.opcao21.value,
        op22: form.opcao22.value,
        op23: form.opcao23.value,
        op24: form.opcao24.value
    }
        return resp;
}


// const btnCriar = document.querySelector('.btn-criar');
// const btnOk = document.querySelector('.btn-ok');

// btnCriar.addEventListener("click", function(event){
//     event.preventDefault();
    
//     var input = document.createElement("input");
//     form.appendChild(input);


//     btnOk.addEventListener("click", function(event){
//         event.preventDefault();
        
//         if(input.value.length > 0){
//             var paragrafo = document.createElement("p");
//             var button = document.createElement("button");
//             form.appendChild(paragrafo, button);
//             form.appendChild(button);
           
//             paragrafo.textContent = input.value;
//             button.textContent = "X";
            
//             button.addEventListener("click", function(event){
//                 event.preventDefault();
//                  paragrafo.textContent = "";
//                  button.style.display = "none";
//             });

//             input.addEventListener("input", function(event){
//                 event.preventDefault();
//                 paragrafo.textContent = input.value;
//             });
//         }
//     });
// });









