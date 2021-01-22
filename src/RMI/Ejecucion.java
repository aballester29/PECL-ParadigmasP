
package RMI;

import Clases.Simulacion;
import Clases.Inicio;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Ejecucion  extends UnicastRemoteObject implements InterfazRemota
{
    public Inicio f;
    public InterfazCliente in;
    Conexion con = new Conexion (this) ;
    public Ejecucion() throws RemoteException {}                 //Constructor
    

    public void iniciar() throws RemoteException  // Implementación del método remoto
    {
        Simulacion s = new Simulacion(con);
        this.f = s.f;
        s.setVisible(true);       
    }
    
    public void iniCliente() throws RemoteException
    {
        this.in = new InterfazCliente();
        con.conx =true;
        in.setVisible(true);
    }
}
