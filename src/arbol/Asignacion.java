/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package arbol;

/**
 * Clase que ejecuta las acciones de una instrucción de asignación y que implementa
 * la interfaz de instrucción
 * @author Erick
 */
public class Asignacion implements Instruccion{
    
    /**
     * Identificador de la tabla de simbolos padre de la asignación.
     */
    protected TablaDeSimbolos tablaPadre;
    
    /**
     * Identificador de la variable a la que se le asigna el valor.
     */
    protected final String id;
    /**
     * Valor que se le asigna a la variable.
     */
    protected final Instruccion valor;
    /**
     * Constructor de la clase asignación
     * @param a identificador de la variable
     * @param b valor que se le va a asignar
     */
    public Asignacion(String a, Instruccion b) {
        this.id=a;
        this.valor=b;
    }
    
    /**
     * Método que agrega una referencia a la tabla de simbolos padre
     * @param ts tabla de símbolos del ámbito padre de la sentencia asignación
     */
    
    public void setTablaDeSimbolosPadre(TablaDeSimbolos ts) {
        this.tablaPadre = ts;
    }
    
    /**
     * Método que ejecuta la accion de asignar un valor, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param ts tabla de símbolos del ámbito padre de la sentencia asignación
     * @return En este caso retorna nulo porque no es una sentencia que genere un valor.
     */
    @Override
    public Object ejecutar(TablaDeSimbolos ts, Arbol ar) {
        
        if(tablaPadre != null) // Si se definió una tabla padre, se obtendrá de ahí el valor a asignar.
            ts.setValor(id,valor.ejecutar(tablaPadre,ar));
        else 
            ts.setValor(id,valor.ejecutar(ts,ar));
        
        return null;
    }
    
}
