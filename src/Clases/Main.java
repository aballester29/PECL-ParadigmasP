/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Azahara
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mostrador most = new Mostrador(10);
        Empleados E1 = new Empleados("Empleado1", most);
        Empleados E2 = new Empleados("Empleado2", most);
    }
    
}
