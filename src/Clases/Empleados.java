package Clases;


public class Empleados extends Thread{      //Empleado es un hilo (Thread)
    // ATRIBUTOS DE LA CLASE EMPLEADO
    private String id;              //Identificador del empleado 
    Mostrador most;                 //Mostrador donde se encuentran los pedidos de los clientes
    //Mesa mesa;                    //Mesa de la cocina donde se pondrán los pedidos para los cocineros
    
    // CONSTRUCTOR EMPLEADO
    public Empleados(String id, Mostrador most) { //Añadir atributo mesa
        this.id = id;
        this.most = most;
        //this.mesa=mesa
    }
    
    public void run(){
        while (true){ //Bucle infinito            
            try {
                sleep((int)(300+400*Math.random()));      //CAMBIAR SLEEP PARA QUE SEA ENTRE 300 Y 700 MS
                //Cogemos el pedido del mostrador
                String pedido=most.recogerPedido();
                System.out.println(id + " recoge: "+ pedido);

                //LLevar pedido a la mesa de la cocina
                //mesa.añadirPedido();
            } catch (InterruptedException e) {}            
        }
    }
}
