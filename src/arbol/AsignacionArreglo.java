/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package arbol;

import java.util.LinkedList;

/**
 * Clase de asignación de arreglo, que hereda de la clase asignación pues tiene
 * los mismos atributos pero además contiene la lista de indices con los que se 
 * realizará la asignación a cierta celda del arreglo.
 * @author Erick Navarro
 */
public class AsignacionArreglo extends Asignacion implements Instruccion{
    /**
     * Lista de indices con los que se realizará la asignación a cierta celda del arreglo.
     */
    private final LinkedList<Instruccion> indices;
    /**
     * Construcción de la clase AsignacionArreglo
     * @param a Identificador del arreglo
     * @param b Instrucción o expresión que contiene el valor que se le va a asignar a la celda
     * @param c Lista de indices con la posición de la celda a la que se hará la asignación
     */
    public AsignacionArreglo(String a, Instruccion b, LinkedList<Instruccion> c) {
        super(a,b);
        indices=c;
    }
    /**
     * Método que ejecuta la accion de asignar un valor a un arreglo, es una 
     * sobreescritura del método ejecutar que se debe programar por la 
     * implementación de la interfaz instrucción.
     * @param ts Tabla de símbolos del ámbito padre.
     * @return No retorna nada porque no es una sentencia que genere un valor.
     */
    @Override
    public Object ejecutar(TablaDeSimbolos ts,Arbol ar) {
        LinkedList<Integer> valoresIndices=new LinkedList<>();
        for (Instruccion dim : indices) {
            Object er=dim.ejecutar(ts, ar);
            valoresIndices.add(((Double)er).intValue());
        }
        ts.setValor(id,valor.ejecutar(ts,ar),valoresIndices);
        return null;
    }
}
