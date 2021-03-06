/**
 * 
 */
package dataAccessLayer;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import org.neo4j.driver.Value;

import static org.neo4j.driver.Values.parameters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * @author Administrator
 *
 */
public class EmbeddedNeo4j implements AutoCloseable{

    private final Driver driver;
    

    public EmbeddedNeo4j( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void close() throws Exception
    {
        driver.close();
    }

    public void printGreeting( final String message )
    {
        try ( Session session = driver.session() )
        {
            String greeting = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "CREATE (a:Greeting) " +
                                                     "SET a.message = $message " +
                                                     "RETURN a.message + ', from node ' + id(a)",
                            parameters( "message", message ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
    }
    
    public LinkedList<String> getActors()
    {
    	 try ( Session session = driver.session() )
         {
    		 
    		 
    		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
             {
                 @Override
                 public LinkedList<String> execute( Transaction tx )
                 {
                     Result result = tx.run( "MATCH (people:Person) RETURN people.name");
                     LinkedList<String> myactors = new LinkedList<String>();
                     List<Record> registros = result.list();
                     for (int i = 0; i < registros.size(); i++) {
                    	 //myactors.add(registros.get(i).toString());
                    	 myactors.add(registros.get(i).get("people.name").asString());
                     }
                     
                     return myactors;
                 }
             } );
             
             return actors;
         }
    }
    
    public LinkedList<String> getMoviesByActor(String actor)
	{
   	 try ( Session session = driver.session() )
        {
   		 
   		 
   		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (tom:Person {name: \"" + actor + "\"})-[:ACTED_IN]->(actorMovies) RETURN actorMovies.title");
                    LinkedList<String> myactors = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 //myactors.add(registros.get(i).toString());
                   	 myactors.add(registros.get(i).get("actorMovies.title").asString());
                    }
                    
                    return myactors;
                }
            } );
            
            return actors;
        }
   }
   
       public String insertHotel(String name, String phone, String breakfastHotel, String calificationHotel, String description, String petsHotel, String direction, String TypePlace, String poolHotel, String priceHotel, String wifiHotel) {
    	try ( Session session = driver.session() )
        {
   		 
   		 String result = session.writeTransaction( new TransactionWork<String>()
   		 
            {
                @Override
                public String execute( Transaction tx )
                {
                    tx.run( "CREATE (n:Hotel {name:'" + name +"', phone:'" + phone +"', breakfast:"+ breakfastHotel +", calification:"+ calificationHotel +", description:'"+ description + "', pets:"+ petsHotel +", direction:'"+ direction +"', TypePlace:'"+ TypePlace +"', pool:"+ poolHotel + ", price:"+ priceHotel +", wifi:"+ wifiHotel+"})");
                    
                    return "OK";
                }
            }
   		 
   		 );
            
            return result;
        } catch (Exception e) {
        	return e.getMessage();
        }
	 
       }

   public String insertUser(String nameU, String lastnameU, String passwordU, String userU, String commentU,String breakfastU, String calificationU, String petsU , String poolU, String priceU, String wifiU, String typeplaceU) {
   		try ( Session session = driver.session() )
           {
      		 
      		 String result = session.writeTransaction( new TransactionWork<String>()
      		 
               {
                   @Override
                   public String execute( Transaction tx )
                   {
                       tx.run( "CREATE (n:usuario {name:'" + nameU +"', lastname:'" + lastnameU +"', password:'"+ passwordU +"', user:'"+ userU +"', comment:'"+ commentU + "', calification:'"+ calificationU +"', pool:'"+ poolU +"', price:"+ priceU +", wifi:'"+ wifiU + "', typeplace:'"+ typeplaceU +"', pets:'"+ petsU + "', breakfast:'"+ breakfastU + "'})");
                       return "OK";
                   }
               }
      		 
      		 );
               
               return result;
           } catch (Exception e) {
           	return e.getMessage();
           }
   	}
   	
	public List<String> logIn(String user, String password)
	{
   	 try ( Session session = driver.session() )
        {
   		 
   		List<String> actors = session.writeTransaction( new TransactionWork<List<String>>()
            {
                @Override
                public List<String> execute( Transaction tx )
                {
                	Result Users = tx.run("MATCH (n:usuario) RETURN n.user");
                    List<Record> registrosU = Users.list();
                    List<String> nombres = new LinkedList<>();
                    List<String> myactors = new LinkedList<>();
                    for (int i = 0; i < registrosU.size(); i++) {
                      	 //myactors.add(registros.get(i).toString());
                      	 nombres.add(registrosU.get(i).get("n.user").asString());
                       }
                    if(nombres.contains(user)) {
	                    myactors.add("true");
	                    Result PassW = tx.run("MATCH (n:usuario {user:'"+ user +"'}) RETURN n.password");
	                    List<Record> registros = PassW.list();
	                    
	                    String tPass = registros.get(0).values().get(0).asString();
	                    if(tPass.equals(password)) {
	                    myactors.add("true");	
	                    }
	                    else {
	                    	myactors.add("false");
	                    }
                    }
                    else {
                    	myactors.add("false");
                    	myactors.add("false");
                    }
                    myactors.add(user);
               
                    return myactors;
                    
                }
            } );
            return actors;
               
        }
	}

	public List<List<String>> getHotelByUser(String user) {
        try ( Session session = driver.session() )
        {


        	List<List<String>> hoteles = session.readTransaction( new TransactionWork <List<List<String>>>()
            {
                @Override
                public List<List<String>> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (n:Hotel) Where not (:usuario {user:'"+user+"'})-[:visito]-(n:Hotel) RETURN n limit 12");
                    List<Record> registro = result.list();
                    List<List<String>>  Lista = new LinkedList<List<String>>();
                    List<String> Hola = new LinkedList<String>();
                    List<String> Url = new LinkedList<String>();
                    for (int i = 0; i < registro.size(); i++) {
	                        Map<String, Object> tempMap = registro.get(i).get(0).asMap();
	                        Map<String, String> tempMap2 = new HashMap<String,String>();
	 	                    for (Map.Entry<String,Object> entry : tempMap.entrySet()) {
		                        tempMap2.put(entry.getKey(),entry.getValue().toString());
		                    }
	 	                    Hola.add(tempMap2.get("name"));
	 	                    Url.add(tempMap2.get("url_img"));
                    	}        
                    Lista.add(Hola);
                    Lista.add(Url);
                    
	                return Lista;
                }
            } );
            return hoteles;
        }
    }

	public String UserVisitoHotel(String user, String hotelname) {
		try ( Session session = driver.session() )
        {	 
   		 String result = session.writeTransaction( new TransactionWork<String>() 
            {
                @Override
                public String execute( Transaction tx )
                {
                    tx.run( "Match (a:usuario), (b:Hotel) where a.user='"+user+"' and b.name='"+hotelname+"' create (a)-[:visito]->(b)");
                    return "OK";
                }
            }
   		 
   		 );     
            return result;
        } catch (Exception e) {
        	return e.getMessage();
        }
	}

	public Map<String, String> getUserProperties(String user) {
		try ( Session session = driver.session() )
        {

			Map<String, String> actors = session.readTransaction( new TransactionWork<Map<String, String>>()
            {
                @Override
                public Map<String, String> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (n:usuario) WHERE n.user = '"+user+"' RETURN n");
                    List<Record> registros = result.list();
                    Map<String, Object> tempMap = registros.get(0).get(0).asMap();
                    Map<String, String> myactors = new HashMap<String,String>();
                    
                    for (Map.Entry<String,Object> entry : tempMap.entrySet()) {
                        myactors.put(entry.getKey(),entry.getValue().toString());
                    }
                    
                    return myactors;
                }
            } );
            
            return actors;
        }
	}
	
	
	public Map<String, String> getHotelProperties(String name) {
		try ( Session session = driver.session() )
        {

			Map<String, String> actors = session.readTransaction( new TransactionWork<Map<String, String>>()
            {
                @Override
                public Map<String, String> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (n:Hotel) WHERE n.name = '"+name+"' RETURN n");
                    List<Record> registros = result.list();
                    Map<String, Object> tempMap = registros.get(0).get(0).asMap();
                    Map<String, String> myactors = new HashMap<String,String>();
                    
                    for (Map.Entry<String,Object> entry : tempMap.entrySet()) {
                        myactors.put(entry.getKey(),entry.getValue().toString());
                    }
                    
                    return myactors;
                }
            } );
            
            return actors;
        }
	}
}
	
	

//   http://localhost:8080/HelloWorld/SaveMovieServlet?name=HolidayInn&breakfast=true&id=15&calification=5&description=UnHotelMuyModernoEnElCentroDeLaCiudad&pets=true&place=CiudadDeGuatemala&pool=true&price=300&wifi=true






