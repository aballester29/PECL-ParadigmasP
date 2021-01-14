/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaz.Simulacion;
import RMI.InterfazR;

/**
 *
 * @author Azahara
 */
public class Funciones {
    // CLASE FUNCIONES: CONTIENE LA FUNCIÓN PRINCIPAL PARA COMENZAR TODA LA SIMULACIÓN
    // ATRIBUTOS:
    public static Mostrador most;
    public static Mesa mesa;
    public static Empleados E1, E2;
    public static Cocineros C1, C2, C3;
    
    private Log evolucionRestaurante = new Log();
    private Paso paso = new Paso(evolucionRestaurante);
    
    
    // FUNCIÓN CONSTRUCTOR: CREA LOS HILOS Y LOS INICIA
    public Funciones(Simulacion s){
        this.most = new Mostrador(10, s.textMost);
        this.mesa = new Mesa(20, s.textMesa);
        
        this.E1 = new Empleados("Empleado1", most, mesa, evolucionRestaurante, paso,s.textFieldE1);
        this.E2 = new Empleados("Empleado2", most, mesa, evolucionRestaurante, paso,s.textFieldE2);        
        
        //cocineros--
        this.C1 = new Cocineros("Cocinero1", mesa, evolucionRestaurante, paso,s.textFieldC1);
        this.C2 = new Cocineros("Cocinero2", mesa, evolucionRestaurante, paso,s.textFieldC2);
        this.C3 = new Cocineros("Cocinero3", mesa, evolucionRestaurante, paso,s.textFieldC3);
        
        for(int i=1; i<=200;i++){
            Clientes Ci= new Clientes("Cliente"+i,  most, evolucionRestaurante);
            Ci.start();
        }
        
        hilos();
    }
    
    public Funciones(InterfazR r){
        this.most = new Mostrador(10, r.textMost);
        this.mesa = new Mesa(20, r.textMesa);
        
        this.E1 = new Empleados("Empleado1", most, mesa, evolucionRestaurante);
        this.E2 = new Empleados("Empleado2", most, mesa, evolucionRestaurante);        
        
        //cocineros--
        this.C1 = new Cocineros("Cocinero1", mesa, evolucionRestaurante);
        this.C2 = new Cocineros("Cocinero2", mesa, evolucionRestaurante);
        this.C3 = new Cocineros("Cocinero3", mesa, evolucionRestaurante);
        
        for(int i=1; i<=200;i++){
            Clientes Ci= new Clientes("Cliente"+i,  most, evolucionRestaurante);
            Ci.start();
        }
        
        hilos();
    }
      
    public void hilos(){      
        E1.start();
        E2.start();
        
        //cocineros--
        C1.start();
        C2.start();
        C3.start();
    }

    public Paso getPaso() {
        return paso;
    }
    
    
    
}
