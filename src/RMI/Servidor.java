/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Azahara
 */
public class Servidor {
    
    
    public static void main(String args[])
    {
        try
        {
            Funcion obj = new Funcion(); //Crea una instancia del objeto que implementa la interfaz, como objeto a registrar 
            Registry registry = LocateRegistry.createRegistry(1099); //Arranca rmiregistry local en el puerto 1099
            Naming.rebind("//localhost/ObjetoRemoto",obj);   //rebind sólo funciona sobre una url del equipo local 
            System.out.println("El Objeto Remoto ha quedado registrado");
        }
        catch (Exception e)
        {
            System.out.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
