function cargarSitter(id) {
    $.ajax({url: "/sitters/"+id, success: function(result){
    var html="";
    if (result!=""){
            var obj = jQuery.parseJSON(result);
          html+="<h3>Mi nombre</h3>";
          html+="<p>"+obj.nombre+" "+obj.apellidos +"</p>";
          html+="<h3>¿Quién soy?</h3>";
          html+="<p>"+obj.descripcion+"</p>";
          html+="<h3>Provincia</h3>";
          html+="<p>"+obj.provincia+"</p>";
          html+="<h3>Mi tarifa es:</h3>";
          html+="<p>"+obj.tarifa+",00€/hora</p>";   
    }else{
            html+="<h3>No se han encontrado resultados</h3>";
    }    
    $("#nombreDivContenedor").html(html);
    },
    error: function(xhr, ajaxOptions, thrownError) {
        alert(thrownError);
    }});
 
}