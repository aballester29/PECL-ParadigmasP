package Clases;
import RMI.Conexion;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JTextArea;


public class Mesa {
    // ATRIBUTOS DE LA CLASE Mesa
    public final List<String> mes;
    private int cont=0, max;
    private final Lock control = new ReentrantLock();
    private final Condition llena = control.newCondition();
    private final Condition vacia = control.newCondition();
    private Conexion con;
    JTextArea textS; // Para mostrar el contenido en la interfaz
    
    // CONSTRUCTOR DE LA MESA    
    // Le pasamos por parámetros el maximo de tamaño de la mesa, y el cuadro de la interfaz donde mostrará todo su contenido.
    // También pasamos objeto de conexion para saber si tenemos una interfaz de cliente o no.
    public Mesa(int maxi, JTextArea t, Conexion con){
        this.max=maxi;
        this.textS=t;
        this.con = con;
        mes = new ArrayList<>(max);        
    }

        
    // FUNCIÓN PARA AÑADIR PEDIDOS A LA LISTA (mes). USADA POR Empleados.
    public void añadirPedido(String pedido) throws InterruptedException{
        control.lock();             // Ponemos el cerrojo        
        while (cont == max){        // Controlamos si la lista está llena.
            llena.await();
        }        
        try{
            mes.add(pedido);       // Añadimos el pedido
            cont ++;                // Sumamos 1 al contador
            
            textS.setText(null);         // Borramos el contenido del texto de la interfaz
            
            for(String a : mes){       // Recorremos el array y mostramos todos los pedidos en la interfaz
                textS.append(a + "\n");
            }
            con.mirar("mesa");
            vacia.signal();         
        } finally{
            control.unlock();       // Quitamos el cerrojo
        }     
    }
    
    // FUNCIÓN PARA RECOGER PEDIDOS DE LA LISTA (mes). USADA POR Cocineros
    public String recogerPedido() throws InterruptedException{
        control.lock();             // Ponemos el cerrojo
        while (cont == 0){          // Controlamos si la lista está vacia.
            vacia.await();
        }
        try{
            String pedido = mes.get(0);    // Obtenemos el pedido de la lista
            mes.remove(0);                 // Eliminamos el pedido de la lista
            cont --;                        // Disminuimos el contador
            
            textS.setText(null);         // Borramos el contenido del texto de la interfaz
            
            for(String a : mes){       // Recorremos el array y mostramos todos los pedidos en la interfaz
                textS.append(a + "\n");
            }
            con.mirar("mesa");
            llena.signal();
            return pedido;
        } finally{
            control.unlock();       // Quitamos el cerrojo
        }
    }

}
