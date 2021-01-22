package RMI;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    // Programa de conexión distribuida, servicio de servidor. Crea el servidor y espera a que un cliente se conecte a el.    
    public static void main(String args[])
    {
        try
        {
            Funcion obj = new Funcion(); //Crea una instancia del objeto que implementa la interfaz, como objeto a registrar 
            Naming.rebind("//127.0.0.1/ObjetoRemoto",obj);   //rebind sólo funciona sobre una url del equipo local 
            System.out.println("El Objeto Remoto ha quedado registrado");
            obj.iniciar();
        }
        catch (Exception e)
        {
            System.out.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
