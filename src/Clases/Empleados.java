package Clases;

import javax.swing.JTextPane;


public class Empleados extends Thread{      //Empleado es un hilo (Thread)
    // ATRIBUTOS DE LA CLASE EMPLEADO
    public String id, mensaje;              //Identificador del empleado
    JTextPane msg;
    Paso paso;
    Mostrador most;                 //Mostrador donde se encuentran los pedidos de los clientes
    //Mesa mesa;                    //Mesa de la cocina donde se pondrán los pedidos para los cocineros
    
    // CONSTRUCTOR EMPLEADO
    public Empleados(String id, Mostrador most, Paso paso, JTextPane t) { //Añadir atributo mesa
        this.id = id;
        this.paso=paso;
        this.msg=t;
        this.most = most;
        //this.mesa=mesa
    }
    
    public void run(){
        while (true){ //Bucle infinito            
            try {
                sleep((int)(300+400*Math.random()));      //CAMBIAR SLEEP PARA QUE SEA ENTRE 300 Y 700 MS
                paso.mirar();
                //Cogemos el pedido del mostrador
                String pedido=most.recogerPedido();
                mensaje=id + " recoge: "+ pedido;
                msg.setText(mensaje);

                //LLevar pedido a la mesa de la cocina
                //mesa.añadirPedido();
            } catch (InterruptedException e) {}            
        }
    }

    public String getMensaje() {
        return mensaje;
    }
    
    
}
