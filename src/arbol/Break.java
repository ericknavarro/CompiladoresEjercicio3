/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package arbol;

/**
 * Clase de break.
 * @author Erick Navarro
 */
public class Break implements Instruccion{
    /**
     * Método que ejecuta la instrucción break, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param ts tabla de símbolos del ámbito padre de la sentencia
     * @return Esta instrucción retorna la propia instancia de Break
     */    
    @Override
    public Object ejecutar(TablaDeSimbolos ts, Arbol ar) {
        return this;
    }
    
}
