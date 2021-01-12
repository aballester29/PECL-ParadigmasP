/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaz.Simulacion;

/**
 *
 * @author Azahara
 */
public class Funciones {
    // CLASE FUNCIONES: CONTIENE LA FUNCIÓN PRINCIPAL PARA COMENZAR TODA LA SIMULACIÓN
    // ATRIBUTOS:
    public static Mostrador m;
    public static Mesa mesa;
    public static Empleados E1, E2;
    public static Cocineros C1, C2, C3;
    private Simulacion s;
    
    
    // FUNCIÓN CONSTRUCTOR: CREA LOS HILOS Y LOS INICIA
    public Funciones(Paso paso, Simulacion s){
        this.s=s;
        this.m = new Mostrador(10, s.textMost);
        this.mesa = new Mesa(20, s.textMesa);
        
        this.E1 = new Empleados("Empleado1", m, mesa, paso,s.textFieldE1);
        this.E2 = new Empleados("Empleado2", m, mesa, paso,s.textFieldE2);        
        
        //cocineros--
        this.C1 = new Cocineros("Cocinero1", mesa, paso,s.textFieldC1);
        this.C2 = new Cocineros("Cocinero2", mesa, paso,s.textFieldC2);
        this.C3 = new Cocineros("Cocinero3", mesa, paso,s.textFieldC3);
        
        for(int i=1; i<=200;i++){
            Clientes Ci= new Clientes("C"+i,  m, paso);
            Ci.start();
        }
        
        E1.start();
        E2.start();
        
        //cocineros--
        C1.start();
        C2.start();
        C3.start();

    }
      
    
}
