import java.rmi.*;

public interface IntConexionBD  extends Remote
{
    public String EjecutarQuery(String query) throws RemoteException;
    public void Conectar(String host, String BD, String User, String Password, String dbms) throws RemoteException;
}

