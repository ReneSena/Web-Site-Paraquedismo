$(document).ready(function(){
    $("#CEP").focusout(function(){
        var cep = $("#CEP").val();
        cep = cep.replace ("-","");
        cep = cep.replace(".","");

        var urlStr = "https://viacep.com.br/ws/" + cep + "/json/";

        $.ajax({
            url : urlStr,
            type : "get",
            dataType : "json",
            success : function (data){
                console.log(data);

                $("#Endereco").val(data.logradouro);
                $("#bairro").val(data.bairro);
                $("#cidade").val(data.localidade);
                $("#estado").val(data.uf);

            },
            error : function (){
                console.log(erro);
            }

        })

    })
})