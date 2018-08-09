/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package arbol;

import java.util.LinkedList;

/**
 * Clase declaración de arreglo, que hereda de la clase declaración, pues tiene 
 * los mismos atributos y además la información de sus dimensiones.
 * @author Erick Navarro
 */
public class DeclaracionArreglo extends Declaracion implements Instruccion{
    /**
     * Lista de valores que definen los tamaños de cada dimension del arreglo.
     */
    private final LinkedList<Instruccion> dimensiones;
    /**
     * Constructor de la declaración de un arreglo.
     * @param a Identificador del arreglo
     * @param b Tipo del arreglo
     * @param c Dimensiones del arreglo
     */
    public DeclaracionArreglo(String a, String b, LinkedList<Instruccion> c) {
        super(a,b);
        dimensiones=c;
    }
    /**
     * Método que ejecuta la accion de declarar un arreglo, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param ts Tabla de símbolos del ámbito padre.
     * @return No retorna nada porque no es una sentencia que genere un valor.
     */
    @Override
    public Object ejecutar(TablaDeSimbolos ts,Arbol ar) {
        LinkedList<Integer> tamaniosDimensiones=new LinkedList<>();
        for (Instruccion dim : dimensiones) {
            Object er=dim.ejecutar(ts, ar);
            tamaniosDimensiones.add(((Double)er).intValue());
        }
        ts.add(new Simbolo(id,tipo,tamaniosDimensiones));
        return null;
    }
}
