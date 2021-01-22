package RMI;
import java.util.List;
import javax.swing.JTextArea;

public class Conexion {
    boolean conx=false;
    Ejecucion fun;

    
    public Conexion(Ejecucion fun){
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

