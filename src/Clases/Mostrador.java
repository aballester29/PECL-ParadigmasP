package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mostrador {
    // ATRIBUTOS DE LA CLASE Mostrador
    private final List<String> most;
    private int cont=0, max;
    private final Lock control = new ReentrantLock();
    private final Condition llena = control.newCondition();
    private final Condition vacia = control.newCondition();
    
    // CONSTRUCTOR DEL MOSTRADOR    
    public Mostrador(int maxi){
        this.max=maxi;
        most = new ArrayList<String>(max);
        
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
            llena.signal();
            return pedido;
        } finally{
            control.unlock();       // Quitamos el cerrojo
        }          
    }
}
