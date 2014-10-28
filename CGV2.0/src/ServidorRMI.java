import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandra
 */
public class ServidorRMI {
    public static void main(String[] args) {
         try {   
      //RMIREGISTRY
      int puertoRMI=Integer.parseInt(JOptionPane.showInputDialog("Ingrese Puerto RMI"));
      Registry registry = LocateRegistry.createRegistry(puertoRMI);

      //Crear Objeto Servidor
      ConexionBD conbd = new ConexionBD();

      //Registrar Objeto Servidor
      registry.rebind("conex", conbd);
      System.out.println("Servidor Corriendo en el Puerto: "+puertoRMI);
    } catch (Exception e) {
      System.out.println(e);
    }
    }

}