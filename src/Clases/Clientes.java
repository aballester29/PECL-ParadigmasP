package Clases;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Clientes extends Thread{ //Cada cliente es un hilo (thread)
    // ATRIBUTOS DE LA CLASE Clientes
    private String id;              //Identificador del cliente
    Mostrador most;                 //Mostrador donde se dejarán los pedidos para los empleados
    Log evolucionRestaurante;       //Log de actividad
    
    
    // CONSTRUCTOR Clientes
    //Le pasamos por parámetro el id, el mostrador que hayamos creado y el log donde registre su actividad
    public Clientes(String id, Mostrador most, Log log) { 
        this.id = id;
        this.most = most;
        this.evolucionRestaurante = log;
    }
    
    
    // PROGRAMA PRINCIPAL
    // El hilo entra en un bucle en el que creará 2 pedidos. Al crear cada uno, lo añadirá al mostrador y se registrará el evento en el log.
    // Espera entre 500 y 1000 segundos entre la creación de cada pedido.
    // Una vez creados los 2 pedidos, finalizará su ejecución y lo dejará registrado en el log.
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
