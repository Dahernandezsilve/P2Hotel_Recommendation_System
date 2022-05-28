

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataAccessLayer.EmbeddedNeo4j;

/**
 * Servlet implementation class SaveMovieServlet
 */
@WebServlet("/SaveMovieServlet")
public class SaveMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveMovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
	 	response.setContentType("application/json");
	 	response.setCharacterEncoding("UTF-8");
	 	JSONObject myResponse = new JSONObject();
	 	
	 	JSONArray insertionResult = new JSONArray();
	 	
	 	String nameHotel = request.getParameter("name");
	 	String idHotel = request.getParameter("id");   
		String breakfastHotel = request.getParameter("breakfast");
	 	String calificationHotel = request.getParameter("calification");
		String descriptionHotel = request.getParameter("description");
		String petsHotel = request.getParameter("pets");
		String placeHotel = request.getParameter("place");
		String poolHotel = request.getParameter("pool");
		String priceHotel = request.getParameter("price");
		String wifiHotel = request.getParameter("wifi");
	 	
	 	 try ( EmbeddedNeo4j neo4jDriver = new EmbeddedNeo4j( "bolt://localhost:7687", "neo4j", "test1234" ) )
	        {
			 	String myResultTx = neo4jDriver.insertHotel(nameHotel, Integer.parseInt(idHotel), breakfastHotel, Integer.parseInt(calificationHotel), descriptionHotel, petsHotel, placeHotel, poolHotel, Integer.parseInt(priceHotel), wifiHotel);
	        	
			 	myResponse.put("resultado", myResultTx);
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				myResponse.put("resultado", "Error: " + e.getMessage());
			}
	 	
	 	
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
