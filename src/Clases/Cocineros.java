package Clases;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;

public class Cocineros extends Thread{
    // ATRIBUTOS DE LA CLASE COCINERRO
    private String id, mensaje;             //Identificador del cocinero y mensaje a mostrar en la interfaz
    Log evolucionRestaurante;               // Log de actividad
    JTextPane msg;                          // Para mostrar el mensaje en la interfaz
    Paso paso;                              // Clase usada para controlar la pausa y reanudación de la simulación.
    Mesa mest;                              // Mesa de la cocina donde se pondrán los pedidos para los cocineros
    
    // CONSTRUCTOR cocinero
    
    // Necesitamos el id, la mesa donde va a recoger los pedidos, el log de registro,
    // la clase controladora de la pausa de la simulación y el cuadro de la interfaz donde mostrará los eventos por pantalla.
    public Cocineros(String id, Mesa mest, Log log, Paso paso, JTextPane t) { 
        this.id = id;
        this.paso=paso;
        this.msg=t;
        this.mest = mest;
        this.evolucionRestaurante = log;
    }
    
    // PROGRAMA PRINCIPAL:
    // Bucle infinito  que contiene una espera entre 1500 y 2000 ms y la ejecución dependerá del tipo de conexión.   
    public void run(){
        while (true){ //Bucle infinito            
            try {
                sleep((int)(1500+500*Math.random()));                
                paso.mirar(id);
                //Cogemos el pedido de la mesa
                String pedido=mest.recogerPedido();

                mensaje=id + " cocina: "+ pedido;
                msg.setText(mensaje);  // Mostramos el mensaje en la interfaz
                evolucionRestaurante.escribirLog("◎ "+ mensaje); // Escribimos en el log
            } catch (InterruptedException e) {} catch (IOException ex) {            
                Logger.getLogger(Cocineros.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }
}
