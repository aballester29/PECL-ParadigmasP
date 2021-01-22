
package RMI;

import Interfaz.Simulacion;
import Clases.Funciones;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Funcion  extends UnicastRemoteObject implements InterfazRemota
{
    public Funciones f;
    public InterfazR in;
    Conexion con = new Conexion (this) ;
    public Funcion() throws RemoteException {}                 //Constructor
    

    public void iniciar() throws RemoteException  // Implementación del método remoto
    {
        Simulacion s = new Simulacion(con);
        this.f = s.f;
        s.setVisible(true);       
    }
    
    public void iniCliente() throws RemoteException
    {
        this.in = new InterfazR();
        con.conx =true;
        in.setVisible(true);
    }
}
