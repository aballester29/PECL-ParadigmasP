package RMI;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazRemota extends Remote {
    void iniCliente() throws RemoteException;
}
