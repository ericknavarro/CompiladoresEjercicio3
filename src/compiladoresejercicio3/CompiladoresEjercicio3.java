/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package compiladoresejercicio3;

import arbol.Arbol;
import arbol.TablaDeSimbolos;
import java.io.FileInputStream;

/**
 * Clase principal del ejercicio 3
 * @author erick
 */
public class CompiladoresEjercicio3 {

    /**
     * @param args argumentos de la linea de comando
     */
    public static void main(String[] args) {
        interpretar("testReturnBreak.txt");
    }
    /**
     * Método que lee el contenido de un archivo de texto y ejecuta las 
     * instrucciones que contiene.
     * @param path ruta del archivo de texto que se desea interpretar
     */
    private static void interpretar(String path) {
        analizadores.Sintactico pars;
        Arbol AST_arbolSintaxisAbstracta=null;
        try {
            pars=new analizadores.Sintactico(new analizadores.Lexico(new FileInputStream(path)));
            pars.parse();        
            AST_arbolSintaxisAbstracta=pars.getAST();
        } catch (Exception ex) {
            System.err.println("Error fatal en compilación de entrada.");
        } 
        if(AST_arbolSintaxisAbstracta==null){
            System.err.println("No es posible ejecutar las instrucciones porque\r\n"
                    + "el árbol no fue cargado de forma adecuada por la existencia\r\n"
                    + "de errores léxicos o sintácticos.");
            return;
        }
        //Se crea una tabla de símbolos global para ejecutar las instrucciones.
        TablaDeSimbolos ts=new TablaDeSimbolos();
        //Se ejecuta el árbol
        AST_arbolSintaxisAbstracta.ejecutar(ts, AST_arbolSintaxisAbstracta);
    }
}
