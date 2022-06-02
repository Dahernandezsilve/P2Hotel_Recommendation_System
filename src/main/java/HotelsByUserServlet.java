

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataAccessLayer.EmbeddedNeo4j;

/**
 * Servlet implementation class HotelsByUserServlet
 */
@WebServlet("/HotelsByUserServlet")
public class HotelsByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotelsByUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	 	response.setContentType("application/json");
	 	response.setCharacterEncoding("UTF-8");
	 	JSONObject myResponse = new JSONObject();
	 	
	 	JSONArray HotelesRecomendados = new JSONArray();
	 	
	 	String user = request.getParameter("user");
	 	
	 	List<Map<String,String>> hoteles = null;
	 	
	 	try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://localhost:7687", "neo4j", "test1234" ) )
	        {
	 			hoteles = greeter.getHotelByUser(user);
			 	for (int i = 0; i < hoteles.size(); i++) {
                    //out.println( "<p>" + myactors.get(i) + "</p>" );
                   HotelesRecomendados.add(hoteles.get(i));
               }	        	
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				myResponse.put("resultado", "Error: " + e.getMessage());
			}
	 	
	 	myResponse.put("Hoteles", HotelesRecomendados);
	 	out.print(hoteles);
	 	out.println(myResponse);
	 	out.flush();  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
