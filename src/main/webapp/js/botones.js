(function($) {
  "use strict"; // Start of use strict
  
  	//Evento del botón que creara un
	$("#btnCreateProfile").click(function(){
				
		$.ajax( {
			
			type: "GET",
																																																																			
		url: '/Ohtell/SaveUserServlet?name=' + $('#nombres').val() + '&lastname=' + $('#con_lname').val() + '&password=' + $('#cb_password').val() + '&user=' + $('#cb_user').val() + '&comment=' + $('#con_message').val() + '&breakfast=' + $('#checkbox4').val() + '&calification=' + $('#cb_calification').val() + '&pets=' + $('#checkbox1').val() + '&pool=' + $('#checkbox3').val() + '&price=' + $('#ex4').val() + '&wifi=' + $('#checkbox2').val() + '&typeplace=' + $('#cb_typeplace').val(),
			success: function(data) {
			    alert("Resultado: " + data.resultado);
			}
		} );
		
		
	});

})(jQuery); // End of use strict

  