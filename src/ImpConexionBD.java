
/**
 *
 * @author Alexandra
 */
import java.rmi.*;
public interface ImpConexionBD  extends Remote{
    public String EjecutarQuery(String query) throws RemoteException;
    public void Conectar(String host, String BD, String User, String Password, String dbms) throws RemoteException;
}
