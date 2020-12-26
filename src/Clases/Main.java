package Clases;

import java.util.Date;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mostrador most = new Mostrador(10);
        Empleados E1 = new Empleados("Empleado1", most);
        Empleados E2 = new Empleados("Empleado2", most);
        
        for(int i=1; i<=200;i++){
            Clientes Ci= new Clientes("C"+i,  most);
            Ci.start();
        }
        
        E1.start();
        E2.start();
       
    }
    
}
