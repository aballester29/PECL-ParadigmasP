package Clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Log {
    //Crea el archivo en disco.
    String ruta = "./evolucionRestaurante.txt";
    File archivo = new File(ruta);
    BufferedWriter bw;

    // CONSTRUCTOR DEL MOSTRADOR    
    public Log(){
    }

    // Función para crear el log
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
	
    //Función para añadir eventos al archivo.
    public void escribirLog(String evento) throws IOException {
            java.util.Date fecha = new Date();
            FileWriter flwriter = null;
            if(archivo.exists()) {
            try {//además de la ruta del archivo recibe un parámetro de tipo boolean, que le indican que se va añadir más registros 
                    flwriter = new FileWriter(ruta, true);
                    BufferedWriter bfwriter = new BufferedWriter(flwriter);
                bfwriter.write(fecha+ " ------- "+evento+"\n");
                    bfwriter.close();

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
