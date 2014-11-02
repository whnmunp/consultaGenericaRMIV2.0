
/**
 *
 * @author Wilson&Carlos
 */
import java.rmi.*;
import javax.swing.JTable;
//esta es una interface muy problematica
public interface ImpConexionBD  extends Remote{
    public String EjecutarQuery(String query,JTable jtResultado) throws RemoteException;
    public void Conectar(String host, String BD, String User, String Password, String dbms) throws RemoteException;
}
