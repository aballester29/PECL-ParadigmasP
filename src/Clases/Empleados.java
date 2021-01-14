package Clases;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;


public class Empleados extends Thread{      //Empleado es un hilo (Thread)
    // ATRIBUTOS DE LA CLASE EMPLEADO
    private String id, mensaje;              //Identificador del empleado y mensaje a mostrar en la interfaz
    private boolean conected = false;
    Log evolucionRestaurante;
    JTextPane msg;      // Para mostrar el mensaje en la interfaz
    Paso paso;
    Mostrador most;                 //Mostrador donde se encuentran los pedidos de los clientes
    Mesa mest;                    //Mesa de la cocina donde se pondrán los pedidos para los cocineros
    
    // CONSTRUCTOR EMPLEADO
    public Empleados(String id, Mostrador most, Mesa mest, Log log, Paso paso, JTextPane t) { //Añadir atributo mesa
        this.id = id;
        this.paso=paso;
        this.msg=t;
        this.most = most;
        this.mest=mest;
        this.evolucionRestaurante = log;
    }
    
    public Empleados(String id, Mostrador most, Mesa mest, Log log) {
        this.id = id;
        this.most = most;
        this.mest = mest;
        this.evolucionRestaurante = log;
        this.conected = true;
    }
    
    public void run(){
        while (true){ //Bucle infinito            
            try {
                sleep((int)(300+400*Math.random()));      //CAMBIAR SLEEP PARA QUE SEA ENTRE 300 Y 700 MS
                
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

    public void remote() throws IOException{
         try{
             String pedido=most.recogerPedido();
             mensaje=id + " recoge: "+ pedido;
             evolucionRestaurante.escribirLog("◍ "+ mensaje);
             mest.añadirPedido(pedido);//mesa.añadirPedido();
             mensaje=id + " llevar: "+ pedido;
             evolucionRestaurante.escribirLog("◍ "+ mensaje);
         } catch (InterruptedException e) {}
    }
    
    public void local() throws IOException{
        try{
            paso.mirar(id);
            //Cogemos el pedido del mostrador
            String pedido=most.recogerPedido();
            mensaje=id + " recoge: "+ pedido;
            msg.setText(mensaje);  // Mostramos el mensaje en la interfaz
                                  //LLevar pedido a la mesa de la cocina
            evolucionRestaurante.escribirLog("◍ "+ mensaje);
            mest.añadirPedido(pedido);//mesa.añadirPedido();
            mensaje=id + " lleva el pedido a la mesa: "+ pedido;                
            //mostrar mensaje de empleado añade pedido a la mesa
            msg.setText(mensaje);  // Mostramos el mensaje en la interfaz
            evolucionRestaurante.escribirLog("◍ "+ mensaje);
         } catch (InterruptedException e) {}
    }

    
    /**
     *
     * @return
     */
}
