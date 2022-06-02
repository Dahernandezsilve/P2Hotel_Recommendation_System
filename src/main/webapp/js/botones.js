(function($) {
  "use strict"; // Start of use strict
  
  	//Evento del botón que creara un usuario
	$("#btnCreateProfile").click(function(){
			
		
		$.ajax( {
			
			type: "GET",

			url: '/Ohtell1/SaveUserServlet?name=' + $('#nombres').val() + '&lastname=' + $('#con_lname').val() + '&password=' + $('#cb_password').val() + '&user=' + $('#cb_user').val() + '&comment=' + $('#con_message').val() + '&breakfast=' + $('#checkbox4').val() + '&calification=' + $('#cb_calification').val() + '&pets=' + $('#checkbox1').val() + '&pool=' + $('#checkbox3').val() + '&price=' + $('#ex4').val() + '&wifi=' + $('#checkbox2').val() + '&typeplace=' + $('#cb_typeplace').val(),
			success: function(data) {
				alert("Resultado: " + data.resultado);
				
				
			}
		} );
		location.href = "portfolio-3-col.html";
		
	});

	//Evento del botón para iniciar sesión
	$("#btn_iniciarsesion").click(function(){
				
		$.ajax( {
			
			type: "GET",

		url: '/Ohtell1/UserLogInServlet?user=' + $('#cb_user_si').val() + '&password=' + $('#cb_password_si').val(),
			success: function(data) {
				alert("Resultado: " + data.resultado);
				location.href = 'portfolio-3-col.html';
			}
		} );
		
		
	});


	$("#p").click(function(){
		//alert("The button was clicked 1");
				
		$.ajax( {
			
			type: "GET",
			url: '/Ohtell1/HotelsByUserServlet?user=dahernandez',
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlActorsList = '<ul>';
				$.each(data.Hoteles, function(i,item){
					  htmlActorsList += '<li>' + item + '</li>';
				});
				htmlActorsList += '</ul>';
				$('#r2').html("");
				$('#r2').append(htmlActorsList);
			}
		} );
		
		
	});
	
})(jQuery); // End of use strict

  