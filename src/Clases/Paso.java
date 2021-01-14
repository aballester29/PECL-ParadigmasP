/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Azahara
 */
public class Paso {
    // CLASE PASO: SE ENCARGA DE CONTROLAR LA EJECUCIÓN DE LA SIMULACIÓN Y DE LOS EMPLEADOS
    
    // ATRIBUTOS 
    private Lock cerrojo = new ReentrantLock();
    private Condition parar = cerrojo.newCondition();
    private boolean cerrado=false;
    private boolean cerradoE1=false;
    private boolean cerradoE2=false;
    Log evolucionRestaurante;

    public Paso(Log log) {
        this.evolucionRestaurante = log;
    }

    
    // FUNCIONES
    // FUNCIÓN MIRAR: SIRVE PARA QUE LOS OTROS OBJETOS MIREN COMO ESTÁ EL CERROJO Y SI PUEDEN O NO SEGUIR UTILIZANDOSE
    public void mirar(String id)
    {
        try
        {
            cerrojo.lock();
            while(cerrado)
            {
                try
                {
                    parar.await();
                } catch(InterruptedException ie){ }
            }
            while (id=="Empleado1" && cerradoE1) {
                try {
                    parar.await();
                } catch (InterruptedException ie){ } 
            }
            while (id=="Empleado2" && cerradoE2) {
                try {
                    parar.await();
                } catch (InterruptedException ie){ } 
            }
        }
        finally
        {
            cerrojo.unlock();
        }
    }

    // FUNCIÓN PARA ABRIR EL CERROJO PARA CONTINUAR CON TODA LA SIMULACIÓN
    public void abrir() throws IOException
    {
        try
        {
            cerrojo.lock();
            cerrado=false; //Se cambia la condición por la que otros hilos podrían estar esperando
            String mensaje="Simulacion reanudada";
            evolucionRestaurante.escribirLog("+ "+ mensaje);
            parar.signalAll();
        }
        finally
        {
            cerrojo.unlock();
        }
    }

    // FUNCIÓN PARA CERRAR EL CERROJO PARA PARAR TODA LA SIMULACIÓN
    public void cerrar() throws IOException
    {
        try
        {
            cerrojo.lock();
            cerrado=true;
            String mensaje="Simulacion parada";
            evolucionRestaurante.escribirLog("- "+ mensaje);
        }
        finally
        {
            cerrojo.unlock();
        }
    }
    
    // FUNCIÓN PARA ABRIR EL CERROJO PARA QUE SE REANUDE LA EJECUCIÓN DE UN EMPLEADO PARADO
    public void abrirE(String id) throws IOException
    {
        if (id == "Empleado1"){
            try
            {
                cerrojo.lock();
                cerradoE1=false; //Se cambia la condición por la que otros hilos podrían estar esperando
                parar.signal();
            }
            finally
            {
                cerrojo.unlock();
            }
        }
        else if (id == "Empleado2"){
            try
            {
                cerrojo.lock();
                cerradoE2=false; //Se cambia la condición por la que otros hilos podrían estar esperando
                parar.signal();
            }
            finally
            {
                cerrojo.unlock();
            }
        }
        String mensaje="Simulacion reanudada para el "+id;
        evolucionRestaurante.escribirLog("+ "+ mensaje);
               
    }

    // FUNCIÓN PARA CERRAR EL CERROJO Y PARAR LA EJECUCIÓN DE UN EMPLEADO
    public void cerrarE(String id) throws IOException
    {
        if (id == "Empleado1"){
                try
                {
                    cerrojo.lock();
                    cerradoE1=true;
                }
                finally
                {
                    cerrojo.unlock();
                }
        }
        else if (id == "Empleado2"){
                try
                {
                    cerrojo.lock();
                    cerradoE2=true;
                }
                finally
                {
                    cerrojo.unlock();
                }
        }
        String mensaje="Simulacion pausada para el "+id;
        evolucionRestaurante.escribirLog("- "+ mensaje);

    }
}
