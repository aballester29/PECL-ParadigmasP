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
    public static Mostrador m;
    public static Empleados E1, E2;
    private Simulacion s;
    
    
    public Funciones(Paso paso, Simulacion s){
        this.s=s;
        this.m = new Mostrador(10, s.textList);
        this.E1 = new Empleados("Empleado1", m, paso,s.textFieldE1);
        this.E2 = new Empleados("Empleado2", m, paso,s.textFieldE2);        
        
        //String[] mostrador= new String[m.most.size()];
        //mostrador = m.most.toArray(mostrador);
        //s.setMostrador(mostrador);
        
        for(int i=1; i<=200;i++){
            Clientes Ci= new Clientes("C"+i,  m, paso);
            Ci.start();
        }
        
        E1.start();
        E2.start();

    }
      
    
}
