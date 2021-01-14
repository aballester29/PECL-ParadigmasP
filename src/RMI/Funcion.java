
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Funcion  extends UnicastRemoteObject implements InterfazRemota
{
    boolean conected = false;
    public Funcion() throws RemoteException {}                 //Constructor
    

    @Override
    public void iniciar() throws RemoteException  // Implementación del método remoto
    {   
        InterfazR in = new InterfazR();
        in.setVisible(true);
    }

    
}
