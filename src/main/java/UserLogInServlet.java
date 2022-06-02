

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
 * Servlet implementation class UserLogInServlet
 */
@WebServlet("/UserLogInServlet")
public class UserLogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogInServlet() {
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
	 	
	 	String user = request.getParameter("name");
	 	String password = request.getParameter("password");   
		
	 	
	 	 try ( EmbeddedNeo4j neo4jDriver = new EmbeddedNeo4j( "bolt://localhost:7687", "neo4j", "test1234" ) )
	        {
	 		LinkedList<String> myResultTx = neo4jDriver.logIn(user,password);
	        	
			 	myResponse.put("resultado", myResultTx);
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				myResponse.put("resultado", "Error: " + e.getMessage());
			}
	 	
	 	 for (int i = 0; i < myResponse.size(); i++) {
	            out.println(myResponse.get(i));
	        }
	 	
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
