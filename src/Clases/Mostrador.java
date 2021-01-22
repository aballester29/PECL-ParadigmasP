package Clases;
import RMI.Conexion;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JTextArea;



public class Mostrador {
    // ATRIBUTOS DE LA CLASE Mostrador
    public final List<String> most;
    private int cont=0, max;
    private final Lock control = new ReentrantLock();
    private final Condition llena = control.newCondition();
    private final Condition vacia = control.newCondition();
    JTextArea textS; // Para mostrar el contenido en la interfaz
    private Conexion con;
    
    // CONSTRUCTOR DEL MOSTRADOR 
    // Le pasamos por parámetros el maximo de tamaño del mostrador, y el cuadro de la interfaz donde mostrará todo su contenido.
    // También pasamos objeto de conexion para saber si tenemos una interfaz de cliente o no.
    public Mostrador(int maxi, JTextArea t, Conexion con){
        this.con = con;
        this.max=maxi;
        this.textS=t;
        most = new ArrayList<>(max);
        
    }
        
    // FUNCIÓN PARA AÑADIR PEDIDOS A LA LISTA (MOST). USADA POR CLIENTES.
    public void añadirPedido(String pedido) throws InterruptedException{
        control.lock();             // Ponemos el cerrojo        
        while (cont == max){        // Controlamos si la lista está llena.
            llena.await();
        }        
        try{
            most.add(pedido);       // Añadimos el pedido
            cont ++;                // Sumamos 1 al contador
            
            textS.setText(null);         // Borramos el contenido del texto de la interfaz
            
            for(String a : most){       // Recorremos el array y mostramos todos los pedidos en la interfaz
                textS.append(a + "\n");
            }
            
            con.mirar("most");
            vacia.signal();         
        } finally{
            control.unlock();       // Quitamos el cerrojo
        }     
    }
    
    // FUNCIÓN PARA RECOGER PEDIDOS DE LA LISTA (MOST). USADA POR EMPLEADOS
    public String recogerPedido() throws InterruptedException{
        control.lock();             // Ponemos el cerrojo
        while (cont == 0){          // Controlamos si la lista está vacia.
            vacia.await();
        }
        try{
            String pedido = most.get(0);    // Obtenemos el pedido de la lista
            most.remove(0);                 // Eliminamos el pedido de la lista
            cont --;                        // Disminuimos el contador
            
            textS.setText(null);         // Borramos el contenido del texto de la interfaz
            
            for(String a : most){       // Recorremos el array y mostramos todos los pedidos en la interfaz
                textS.append(a + "\n");
            }
            
            con.mirar("most");
            llena.signal();
            return pedido;
        } finally{
            control.unlock();       // Quitamos el cerrojo
        }          
    }

   
}
