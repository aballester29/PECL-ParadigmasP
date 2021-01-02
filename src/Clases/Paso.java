/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Azahara
 */
public class Paso {
    private boolean cerrado=false;
    private Lock cerrojo = new ReentrantLock();
    private Condition parar = cerrojo.newCondition();
    private boolean cerradoE1=false;
    private boolean cerradoE2=false;

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

    public void abrir()
    {
        try
        {
            cerrojo.lock();
            cerrado=false; //Se cambia la condición por la que otros hilos podrían estar esperando
            parar.signalAll();
        }
        finally
        {
            cerrojo.unlock();
        }
    }

    public void cerrar()
    {
        try
        {
            cerrojo.lock();
            cerrado=true;
        }
        finally
        {
            cerrojo.unlock();
        }
    }
    
    public void abrirE(String id)
    {
        if (id == "Empleado1"){
            System.out.println("adios "+id);
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
            System.out.println("adios "+id);
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
               
    }

    public void cerrarE(String id)
    {
        if (id == "Empleado1"){
            System.out.println("hola "+id);
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
            System.out.println("hola "+id);
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

    }
}
