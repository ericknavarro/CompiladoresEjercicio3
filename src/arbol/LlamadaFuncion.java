/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package arbol;

import java.util.LinkedList;

/**
 * Clase llamada a función, esta implementa la interfaz instrucción ya que puede 
 * ejecutarse como el resto de las sentencias y además podría retornar un valor
 * en caso no se trate de una función de tipo void.
 * @author erick
 */
public class LlamadaFuncion implements Instruccion{
    /**
     * Identificador de la función que se llama
     */
    private final String identificador;
    /**
     * Lista de parámetros de la función que se llama
     */
    private final LinkedList<Instruccion> parametros;
    /**
     * Constructor de la clase llamada a función
     * @param a Identificador de la función que se está llamando
     * @param b Lista de parámetros que se le están enviando a la función que se llama
     */
    public LlamadaFuncion(String a, LinkedList<Instruccion> b) {
        identificador=a;
        parametros=b;
    }
    /**
     * Método que ejecuta la accion de llamar a una función, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param ts Tabla de símbolos del ámbito padre de la sentencia.
     * @return Esta instrucción retorna nulo porque no produce ningun valor al ser
     * ejecutada.
     */
    @Override
    public Object ejecutar(TablaDeSimbolos ts,Arbol ar) {
        Function f=ar.getFunction(identificador);
        if(null!=f){
            f.setValoresParametros(parametros);
            Object rFuncion=f.ejecutar(ts, ar); //Objeto que almacena el resultado de la ejecución del proceso
            if(f.getTipo()==Simbolo.Tipo.VOID && !(rFuncion instanceof Return || rFuncion == null)){
                System.err.println("Una función de tipo Void no puede retornar valores, solamente puede retornar vacío.");
                return null;
            }else if(f.getTipo()==Simbolo.Tipo.NUMERO && !(rFuncion instanceof Double)){
                System.err.println("Una función de tipo Number no puede retornar un valor que no sea numérico.");
                return null;
            }else if(f.getTipo()==Simbolo.Tipo.BOOLEANO && !(rFuncion instanceof Boolean)){
                System.err.println("Una función de tipo Boolean no puede retornar un valor que no sea verdadero o falso.");
                return null;
            }else if(f.getTipo()==Simbolo.Tipo.CADENA && !(rFuncion instanceof String)){
                System.err.println("Una función de tipo Cadena no puede retornar un valor que no sea una cadena de caracteres.");
                return null;
            }
            if(rFuncion instanceof Return){
                return null;
            }else{
                return rFuncion;
            }
        } else {
            System.err.println("La función " + identificador + " no existe.");    
        }
        return null;
    }
}
