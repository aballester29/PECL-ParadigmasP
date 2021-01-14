/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


import javax.swing.JTextPane;

/**
 *
 * @author Azahara
 */
public class Cocineros extends Thread{
     // ATRIBUTOS DE LA CLASE COCINERRO
    private String id, mensaje;              //Identificador del cocinero y mensaje a mostrar en la interfaz
    private boolean conected = false;
    JTextPane msg;      // Para mostrar el mensaje en la interfaz
    Paso paso;                 
    Mesa mest;//Mesa mesa;                    //Mesa de la cocina donde se pondrán los pedidos para los cocineros
    
    // CONSTRUCTOR EMPLEADO
    public Cocineros(String id, Mesa mest, Paso paso, JTextPane t) { 
        this.id = id;
        this.paso=paso;
        this.msg=t;
        this.mest = mest;
    }

    public Cocineros(String id, Mesa mest) {
        this.id = id;
        this.mest = mest;
        this.conected = true;
    }
    
    
    
    public void run(){
        while (true){ //Bucle infinito            
            try {
                sleep((int)(1500+2000*Math.random()));      //CAMBIAR SLEEP PARA QUE SEA ENTRE 300 Y 700 MS
                
                if (conected) {
                    rmi();
                }
                else {
                    paso.mirar(id);
                    //Cogemos el pedido del mostrador
                    String pedido=mest.recogerPedido();

                    mensaje=id + " cocina: "+ pedido;
                    msg.setText(mensaje);  // Mostramos el mensaje en la interfaz
                    //mostrar mensaje de empleado añade pedido a la mesa
                }
            } catch (InterruptedException e) {}            
        }
    }
    
    public void rmi(){
         try{
             String pedido=mest.recogerPedido();
         } catch (InterruptedException e) {}
    }
   
}
