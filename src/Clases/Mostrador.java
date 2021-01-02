package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JList;
import javax.swing.JTextArea;



public class Mostrador {
    // ATRIBUTOS DE LA CLASE Mostrador
    public final List<String> most;
    private int cont=0, max;
    private final Lock control = new ReentrantLock();
    private final Condition llena = control.newCondition();
    private final Condition vacia = control.newCondition();
    JList text;
    // CONSTRUCTOR DEL MOSTRADOR    
    public Mostrador(int maxi, JList t){
        this.max=maxi;
        this.text=t;
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
            text.addElement(pedido);
            //setMost();
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
            //setMost();
            llena.signal();
            return pedido;
        } finally{
            control.unlock();       // Quitamos el cerrojo
        }          
    }

    /*public void setMost() {
        for (int i=0; i<most.size(); i++){
            text.setText(most.get(i));
        }
    }*/
    
}
