/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Azahara
 */
public class Mostrador {
    private List<String> most;
    private int cont=0, max;
    private Lock control = new ReentrantLock();
    private Condition llena = control.newCondition();
    private Condition vacia = control.newCondition();
    
        
    public Mostrador(int max){
        most = new ArrayList<String>(max);
        this.max = max;
    }
    
    public void añadirPedido(String pedido) throws InterruptedException{
        control.lock();
        
        while (cont == max){
            vacia.await();
        }
        
        try{
            most.add(pedido);
            cont ++;
        } finally{
            control.unlock();
        }
        
        
    }
    
    public String recogerPedido(){
        control.lock();
        
        while (cont == 0){
            
        }
        
        String pedido = most.get(0);
        most.remove(0);
          
        return pedido;
    }
}
