package Clases;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Clientes extends Thread{ //Cada cliente es un hilo (thread)
    // ATRIBUTOS DE LA CLASE Clientes
    private String id;              //Identificador del cliente
    Mostrador most;                 //Mostrador donde se dejarán los pedidos para los empleados
    Log evolucionRestaurante;
    
    
    // CONSTRUCTOR Clientes
    public Clientes(String id, Mostrador most, Log log) { //Añadir atributo mesa
        this.id = id;
        this.most = most;
        this.evolucionRestaurante = log;
    }
    
    public void run(){
        for(int i=1; i<=2; i++){ //Bucle para crear 2 pedidos            
            try {                
                //Creamos un pedido
                String pedido=(id+"-P"+i);                
                //LLevar pedido a la mesa de la cocina
                most.añadirPedido(pedido);
                String mensaje=id + " lleva: "+ pedido;
                evolucionRestaurante.escribirLog("◉ "+ mensaje);
                sleep((int)(500+500*Math.random()));
            } catch (InterruptedException e) {} catch (IOException ex) {            
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
        String mensaje = "El "+ id +" ha acabado su ejecución";
        System.out.println(mensaje);        
        try {
            evolucionRestaurante.escribirLog("◉ "+ mensaje);
        } catch (IOException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
