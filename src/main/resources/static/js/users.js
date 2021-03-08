function AbrirModalRegistroUsuario()
{
	$("#modal_registro_usuario").modal('show');
		
	document.getElementById('numDocument').addEventListener('input', function(){
		campo = event.target;
		var cad = document.getElementById("numDocument").value.trim();
        var total = 0;
        var longitud = cad.length;
        var longcheck = longitud - 1;

        if (cad !== "" && longitud === 10){
          for(i = 0; i < longcheck; i++){
            if (i%2 === 0) {
              var aux = cad.charAt(i) * 2;
              if (aux > 9) aux -= 9;
              total += aux;
            } else {
              total += parseInt(cad.charAt(i)); // parseInt o concatenará en lugar de sumar
            }
          }

          total = total % 10 ? 10 - total % 10 : 0;

		if(cad.charAt(longitud-1) == total)
		{
			$(this).css("border","");
			$("#cedulaOK").html("");
			$("#validar_cedula").val("correcto");
		}
		else
		{
			$(this).css("border","1px solid red");
			$("#cedulaOK").html("Cedula Incorrecta");
			$("#validar_cedula").val("incorrecto");
		}

        
        }
	});
}

function userRegister()
{
	$("#modal_registro_usuario").modal('hide');
	//Swal.fire("Mensaje de confirmación","Datos registrados de forma correcta","success");
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#img_user').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

$("#img_input").change(function(){
    readURL(this);
});

function AbrirModalRegistroClientes()
{
	$("#modal_registro_cliente").modal('show');
}