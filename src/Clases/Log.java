/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Azahara
 */
public class Log {
    //Crea el archivo en disco.
    String ruta = "./evolucionRestaurante.txt";
    File archivo = new File(ruta);
    BufferedWriter bw;

    // CONSTRUCTOR DEL MOSTRADOR    
    public Log(){
    }

    public void crearArchivo() throws IOException {
    if(archivo.exists()) {
        bw = new BufferedWriter(new FileWriter(archivo));
        bw.write("El fichero de texto ya estaba creado."+"\n");
    } else {
        bw = new BufferedWriter(new FileWriter(archivo));
        bw.write("Acabo de crear el fichero de texto."+"\n");
    }
    bw.close();
    }
	
    //Añadir más eventos al archivo.
    public void escribirLog(String evento) throws IOException {
            java.util.Date fecha = new Date();
            FileWriter flwriter = null;
            if(archivo.exists()) {
            try {//además de la ruta del archivo recibe un parámetro de tipo boolean, que le indican que se va añadir más registros 
                    flwriter = new FileWriter(ruta, true);
                    BufferedWriter bfwriter = new BufferedWriter(flwriter);
                bfwriter.write(fecha+ " ------- "+evento+"\n");
                    bfwriter.close();
                    System.out.println("Archivo modificado satisfactoriamente..."+"\n");

            } catch (IOException e) {
                    e.printStackTrace();
            } finally {
                    if (flwriter != null) {
                            try {
                                    flwriter.close();
                            } catch (IOException e) {
                                    e.printStackTrace();
                            }
                    }
            }
            } else {
            crearArchivo();
            }
    }
}
