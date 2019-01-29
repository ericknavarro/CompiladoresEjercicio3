/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package arbol;

import java.util.LinkedList;

/**
 * Clase de acceso a un arreglo, contiene la lista de indices con los que se 
 * realizará el acceso a cierta celda del arreglo.
 * @author Erick Navarro
 */
public class AccesoArreglo implements Instruccion{
    /**
     * Lista de indices con los que se debe encontrar la celda específica del 
     * arreglo a la que se desea accesar.
     */
    private final LinkedList<Instruccion> indices;
    /**
     * Identificador del arreglo.
     */
    protected final String id;
    /**
     * Constructor de la clase AccesoArreglo
     * @param id Identificador del arreglo
     * @param indices Lista de indices con los que se debe encontrar la celda 
     * específica del arreglo a la que se desea accesar.
     */
    public AccesoArreglo(String id, LinkedList<Instruccion> indices) {
        this.indices = indices;
        this.id = id;
    }
    /**
     * Método que ejecuta la accion de acceder al valor de cierta celda de un 
     * arreglo, es una sobreescritura del método ejecutar que se debe programar 
     * por la implementación de la interfaz instrucción.
     * @param ts Tabla de símbolos del ámbito padre.
     * @return No retorna nada porque no es una sentencia que genere un valor.
     */    
    @Override
    public Object ejecutar(TablaDeSimbolos ts, Arbol ar) {
        LinkedList<Integer> valoresIndices=new LinkedList<>();
        for (Instruccion dim : indices) {
            Object er=dim.ejecutar(ts, ar);
            if(er == null) {
                System.err.println("Una de las dimensiones para el acceso al arreglo es nula");
                return null;
            }
            //Se comprueba que el tipo de la variable sea number
            if(!(er instanceof Double)){
                System.err.println("Una de las dimensiones para el acceso al  arreglo no es numerica");
                return null;
            }
            valoresIndices.add(((Double)er).intValue());
        }
        return ts.getValor(id, valoresIndices);
    }
    
}
