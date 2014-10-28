import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
 import java.sql.*;
/**
 *
 * @author Alexandra
 */
public class ConexionBD extends UnicastRemoteObject implements IntConexionBD{

    Connection con;          
    public ConexionBD() throws RemoteException {

    }

   
    public String EjecutarQuery(String query) throws RemoteException {
                String respuesta="";
                try{
            	Statement stmt = con.createStatement ();

            	ResultSet rs = stmt.executeQuery (query);

            	int numCols = rs.getMetaData().getColumnCount ();
                while ( rs.next() ) {
		  for (int i=1; i<=numCols; i++) {
                   respuesta+=(rs.getString(i) + "\t" );
                  }  
                  respuesta+="\n";
                } 
                
                       

            	rs.close();
            	stmt.close();
            	con.close();
                }
                catch(Exception e)
                {e.printStackTrace();
                } 
                return respuesta;
    }

    
    public void Conectar(String host, String BD, String User, String Password, String dbms) throws RemoteException {
   try{
        if(dbms.equals("postgresql"))
	Class.forName("org.postgresql.Driver");
	if(dbms.equals("mysql"))
	Class.forName  ("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection( "jdbc:"+dbms+"://"+host+"/"+BD+"?user="+User+"&password="+Password);
   }
   catch(Exception e)
   {e.printStackTrace();
   }
    }

   
   
   
}
