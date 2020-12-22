package Clases;

public class Clientes extends Thread{ //Cada cliente es un hilo (thread)
    // ATRIBUTOS DE LA CLASE Clientes
    private String id;              //Identificador del cliente 
    Mostrador most;                 //Mostrador donde se dejarán los pedidos para los empleados
    
    
    // CONSTRUCTOR Clientes
    public Clientes(String id, Mostrador most) { //Añadir atributo mesa
        this.id = id;
        this.most = most;
    }
    
    public void run(){
        for(int i=1; i<=2; i++){ //Bucle para crear 2 pedidos            
            try {                
                //Creamos un pedido
                String pedido=(id+"-pedido-"+i);                

                //LLevar pedido a la mesa de la cocina
                most.añadirPedido(pedido);
                System.out.println("Pedido dejado: "+ pedido);
                sleep((int)(500+500*Math.random()));
            } catch (InterruptedException e) {}            
        }
        System.out.println("ACABÉ "+ id);
    }    
}
