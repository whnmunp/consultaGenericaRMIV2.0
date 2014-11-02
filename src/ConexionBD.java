
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
 import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author Alexandra
 */
public class ConexionBD extends UnicastRemoteObject implements ImpConexionBD {

    Connection con;
    
    public ConexionBD() throws RemoteException {
   
    }

   //este es un metodo que sirve para .... probando repositorio
    public String EjecutarQuery(String query,JTable jtResultdos) throws RemoteException {
                String respuesta="Todo esta correcto";
                DefaultTableModel modelo = (DefaultTableModel)jtResultdos.getModel();//recupero el modelo de la tabla
                try{
            	Statement stmt = con.createStatement ();
            	ResultSet rs = stmt.executeQuery (query);

            	int numCols = rs.getMetaData().getColumnCount ();//obtengo el numero de columnas de la consulta
//                jtResultdos.setNumeroColumnas(numCols);//supestamente obtengo el numero de columnas de la consulta
                
                //String []Column=new String[numCols];//creo este array para almacenar el nombre de cada columna
//                int j=0;//esta variable me ayudara a controlar el numero de columna
                
                //obtenemos los nombre de las columnas de la consulta
                for (int i=1; i<=numCols; i++) {
                    modelo.addColumn(rs.getMetaData().getColumnName(i));//le añado el nombre de cada columna al modelo
                    //Column[i]=rs.getMetaData().getColumnName(i+1);//con esto obtengo el nombre de la columna y la almaceno
                }
                
                //creo un object para los datos
                Object []fila=new Object[numCols];
                while ( rs.next() ) {
		  for (int i=1; i<=numCols; i++) {
                    fila[i-1]=rs.getString(i);//
                  }
                } 
                modelo.addRow(fila);
            	rs.close();
            	stmt.close();
            	con.close();
                }
                catch(Exception e)
                {e.printStackTrace();
                } 
                return respuesta;
    }

    //este metodo ayuda a conectar las bases de datos abcddkkdkdk
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
