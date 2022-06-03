(function($) {
  "use strict"; // Start of use strict
  
  	//Evento del botón que creara un usuario
	$("#btnCreateProfile").click(function(){
			
		
		$.ajax( {
			
			type: "GET",

			url: '/Ohtell1/SaveUserServlet?name=' + $('#nombres').val() + '&lastname=' + $('#con_lname').val() + '&password=' + $('#cb_password').val() + '&user=' + $('#cb_user').val() + '&comment=' + $('#con_message').val() + '&breakfast=' + $('#checkbox4').val() + '&calification=' + $('#cb_calification').val() + '&pets=' + $('#checkbox1').val() + '&pool=' + $('#checkbox3').val() + '&price=' + $('#ex4').val() + '&wifi=' + $('#checkbox2').val() + '&typeplace=' + $('#cb_typeplace').val(),
			success: function(data) {
				location.href = "portfolio-3-col.html";
				alert("Resultado: " + data.resultado);	
		
			}
		} );
		
		
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


	window.onload = (function(){
		//alert("The button was clicked 1");
				
		$.ajax( {
			
			type: "GET",
			url: '/Ohtell1/HotelsByUserServlet?user=dahernandez',
			success: function(data) {
				//alert("Result" + data.resultado);
			    var hoteles = data.hoteles;
      			var name1 = hoteles[1].get("name");
				var name2 = hoteles[2].get("name");
				var name3 = hoteles[3].get("name");
				var name4 = hoteles[4].get("name");
				var name5 = hoteles[5].get("name");
				var name6 = hoteles[6].get("name");
				var name7 = hoteles[7].get("name");
				var name8 = hoteles[8].get("name");
				var name9 = hoteles[9].get("name");
				var name10 = hoteles[10].get("name");
				var name11 = hoteles[11].get("name");
				var name11 = hoteles[12].get("name");

				document.getElementById('name1').value= name1;
				document.getElementById('name2').value = name2;
				document.getElementById('name3').value = name3;
			}
		} );
		
		
	});
	
})(jQuery); // End of use strict

  