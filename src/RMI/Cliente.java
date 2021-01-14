/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;
import Clases.*;
import java.rmi.*;

/**
 *
 * @author Azahara
 */
public class Cliente {
    public static void main(String args[])
    {
        try {
             InterfazRemota obj = (InterfazRemota) Naming.lookup("//127.0.0.1/ObjetoRemoto"); //Localiza el objeto distribuido
             obj.iniciar();
        }
        catch (Exception e)
        {
            System.out.println("Excepción : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
