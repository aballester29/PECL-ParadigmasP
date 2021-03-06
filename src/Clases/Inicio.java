package Clases;
import RMI.Conexion;

public class Inicio {
    // CLASE FUNCIONES: CONTIENE LA FUNCIÓN PRINCIPAL PARA COMENZAR TODA LA SIMULACIÓN
    // ATRIBUTOS:
    public static Mostrador most;
    public static Mesa mesa;
    public static Empleados E1, E2;
    public static Cocineros C1, C2, C3;
    
    private Log evolucionRestaurante = new Log();
    private Paso paso = new Paso(evolucionRestaurante);
    
    
    
    // FUNCIÓN CONSTRUCTOR: CREA LAS VARIABLES COMPARTIDAS Y LOS HILOS Y LOS INICIA
    public Inicio(Simulacion s, Conexion con){
        this.most = new Mostrador(10, s.textMost, con);
        this.mesa = new Mesa(20, s.textMesa, con);
       
        this.E1 = new Empleados("Empleado1", most, mesa, evolucionRestaurante, paso,s.textFieldE1);
        this.E2 = new Empleados("Empleado2", most, mesa, evolucionRestaurante, paso,s.textFieldE2);        
        
        this.C1 = new Cocineros("Cocinero1", mesa, evolucionRestaurante, paso,s.textFieldC1);
        this.C2 = new Cocineros("Cocinero2", mesa, evolucionRestaurante, paso,s.textFieldC2);
        this.C3 = new Cocineros("Cocinero3", mesa, evolucionRestaurante, paso,s.textFieldC3);
        
        for(int i=1; i<=200;i++){
            Clientes Ci= new Clientes("Cliente"+i,  most, evolucionRestaurante);
            Ci.start();
        }
        
        E1.start();
        E2.start();
        
        C1.start();
        C2.start();
        C3.start();
    }

    // FUNCIÓN GETPASO: Permite a la interfaz de conexión local obtener la clase creada aqui para controlar la ejecución de la simulación.
    public Paso getPaso() {
        return paso;
    } 
}
