 $(document).ready(function() {
    $('.js-example-basic-single').select2();
	
	
	$.ajax({
        url:"/cargar-cliente",
		dataType: "json",
        type:'POST',
    }).done(function(resp){
        //let datos_paciente = JSON.parse(resp);
        let cadena = "";
        cadena += "<option value=''>Cédula - Nombre y Apellido</option>"
        if(resp.length>0)
        {
            for(let i = 0; i < resp.length; i++)
            {
                cadena += "<option value='"+resp[i].id_customer+"'>"+resp[i].document_customer+" - "+resp[i].names_customer+" - "+resp[i].lastnames_customer+"</option>";
            }
            $("#datos_cliente").html(cadena);
        }
        else
        {
            cadena += "<option value=''>No se encontraron resultados</option>"
            $("#datos_cliente").html(cadena);
        }
    })
});