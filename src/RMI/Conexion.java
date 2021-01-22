/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import Clases.Funciones;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JTextArea;

/**
 *
 * @author Azahara
 */
public class Conexion {
    boolean conx=false;
    Funcion fun;

    
    public Conexion(Funcion fun){
        this.fun = fun;
    }
    public void mirar(String tipo)
    {

            if(conx)
            {
                if (tipo == "mesa"){
                    escribir(fun.in.textMesa, fun.f.mesa.mes);
                }
                else if (tipo == "most"){
                    escribir(fun.in.textMost, fun.f.most.most);
                }  
            }
    }
    public void escribir(JTextArea text, List<String> lista){
       
        text.setText(null);         // Borramos el contenido del texto de la interfaz
            
            for(String a : lista){       // Recorremos el array y mostramos todos los pedidos en la interfaz
                text.append(a + "\n");
            }

    }
    
}

