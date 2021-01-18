package Clases;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;


public class Empleados extends Thread{      //Empleado es un hilo (Thread)
    // ATRIBUTOS DE LA CLASE EMPLEADO
    private String id, mensaje;              //Identificador del empleado y mensaje a mostrar en la interfaz
    private boolean conected = false;       // Variable booleana que usaremos para saber si nos encontramos en una conexión local o distribuida.
    Log evolucionRestaurante;              // Log de actividad
    JTextPane msg;                        // Para mostrar el mensaje en la interfaz
    Paso paso;                           // Clase usada para controlar la pausa y reanudación de la simulación.
    Mostrador most;                     //Mostrador donde se encuentran los pedidos de los clientes
    Mesa mest;                         //Mesa de la cocina donde se pondrán los pedidos para los cocineros
    
    // CONSTRUCTOR EMPLEADO
    // Tendremos dos constructores de empleado dependiendo del tipo de conexión que tengamos.
    
    // 1. Constructor conexión local: Necesitamos el id, el mostrador donde recogemos los pedidos, la mesa donde los dejamos, el log de registro,
    // la clase controladora de la pausa de la simulación y el cuadro de la interfaz donde mostrará los eventos por pantalla.
    public Empleados(String id, Mostrador most, Mesa mest, Log log, Paso paso, JTextPane t) { 
        this.id = id;
        this.paso=paso;
        this.msg=t;
        this.most = most;
        this.mest=mest;
        this.evolucionRestaurante = log;
    }
    
    // 2. Constructor conexión distribuida mediante rmi: Necesitamos el id, el mostrador donde recogemos los pedidos, la mesa donde los dejamos y el log de registro.
    // No controlaremos el paso en esta interfaz ni mostraremos los eventos de cada empleado.  
    public Empleados(String id, Mostrador most, Mesa mest, Log log) {
        this.id = id;
        this.most = most;
        this.mest = mest;
        this.evolucionRestaurante = log;
        this.conected = true;
    }
    
    // PROGRAMA PRINCIPAL:
    // Bucle infinito que contiene una espera entre 300 y 700 ms y la ejecución dependerá del tipo de conexión.    
    public void run(){
        while (true){ //Bucle infinito            
            try {
                sleep((int)(300+400*Math.random())); 
                
                if (conected) {
                    remote();

                }
                else {
                    local();
                }
                
            } catch (InterruptedException e) {} catch (IOException ex) {            
                Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }

    // PROGRAMAS AUXILIARES: Funcionamiento del hilo según la conexión.
    
    // 1. Conexión local: Primero miraremos si el paso está permitido y, en caso de que sí, cogeremos el pedido del mostrador
    // y registaremos esta acción en el log y en la interfaz. A continuación, llevamos el pedido a la mesa y lo volvenos a registrar.     
    public void local() throws IOException{
        try{
            paso.mirar(id);
            //Cogemos el pedido del mostrador
            String pedido=most.recogerPedido();
            mensaje=id + " recoge: "+ pedido;
            msg.setText(mensaje);  // Mostramos el mensaje en la interfaz                                  
            evolucionRestaurante.escribirLog("◍ "+ mensaje); // Escribimos en el log
            
            //LLevar pedido a la mesa de la cocina
            mest.añadirPedido(pedido);
            mensaje=id + " lleva el pedido a la mesa: "+ pedido;                
            //mostrar mensaje de empleado añade pedido a la mesa
            msg.setText(mensaje);  // Mostramos el mensaje en la interfaz
            evolucionRestaurante.escribirLog("◍ "+ mensaje); // Escribimos en el log
         } catch (InterruptedException e) {}
    }

    // 2. Conexión remota: El hilo entra en el mostrador y coge el pedido que le toque para llevarlo a la mesa. Registra cada acción en el log.
    public void remote() throws IOException{
         try{
             String pedido=most.recogerPedido();
             mensaje=id + " recoge: "+ pedido;
             evolucionRestaurante.escribirLog("◍ "+ mensaje);
             mest.añadirPedido(pedido);
             mensaje=id + " llevar: "+ pedido;
             evolucionRestaurante.escribirLog("◍ "+ mensaje);
         } catch (InterruptedException e) {}
    }
}
