/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package arbol;

/**
 * Clase de retorno.
 * @author erick
 */
public class Return implements Instruccion{
    /**
     * Lista de las instrucciones (Funciones y declaraciones de variables globales) que componen el archivo.
     */
    private final Instruccion valorRetorno;
    /**
     * Constructor para un retorno que no es de tipo VOID
     * @param a Instrucción que contiene el valor de retorno de la función
     */
    public Return(Instruccion a) {
        valorRetorno=a;
    }
    /**
     * Constructor para un retorno que es de tipo VOID
     */
    public Return() {
        valorRetorno=null;
    }
    /**
     * Método que ejecuta la instrucción retorno, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param ts tabla de símbolos del ámbito padre de la sentencia
     * @return Esta instrucción retorna el valor producido por la operación que se ejecutó
     */    
    @Override
    public Object ejecutar(TablaDeSimbolos ts, Arbol ar) {
        if(valorRetorno==null){
            return this;
        }else{
            return valorRetorno.ejecutar(ts, ar);
        }
    }
    
}
