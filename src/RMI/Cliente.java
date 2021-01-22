package RMI;
import java.rmi.*;

public class Cliente {
    // Programa de conexión distribuida, servicio de cliente. Localiza el servidor y se conecta a el.
    Boolean conected =true;
    public static void main(String args[])
    {
        try {
             InterfazRemota obj = (InterfazRemota) Naming.lookup("//127.0.0.1/ObjetoRemoto"); //Localiza el objeto distribuido
             System.out.println("Conexión iniciada...");             
             obj.iniCliente(); // Inicia la ejecución de la simulación.
        }
        catch (Exception e)
        {
            System.out.println("Excepción : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Boolean getConected() {
        return conected;
    }
    
    
}
