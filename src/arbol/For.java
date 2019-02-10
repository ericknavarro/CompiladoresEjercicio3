/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package arbol;

import java.util.LinkedList;

/**
 * Clase que ejecuta las acciones de una instrucción mientras y que implementa
 * la interfaz de instrucción
 * @author Erick
 */
public class For implements Instruccion{
    /**
     * Inicializador de la sentencia For.
     */
    private final Asignacion inicializador;
    /**
     * Condición de la sentencia For.
     */
    private final Instruccion condicion;
    /**
     * Incrementador de la sentencia For.
     */
    private final Asignacion incrementador;
    /**
     * Lista de las instrucciones que deben ejecutarse si la condición se cumple.
     */
    private final LinkedList<Instruccion> listaInstrucciones;
    /**
     * Constructor de la instruccion For
     * @param a
     * @param b
     * @param c
     * @param d 
     */
    public For(Asignacion a, Instruccion b, Asignacion c, LinkedList<Instruccion> d) {
        inicializador=a;
        condicion=b;
        incrementador=c;
        listaInstrucciones=d;
    }
    /**
     * Método que ejecuta la instrucción For, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param ts tabla de símbolos del ámbito padre de la sentencia
     * @return Esta instrucción retorna nulo porque no produce ningun valor
     */
    @Override
    public Object ejecutar(TablaDeSimbolos ts,Arbol ar) {
        inicializador.ejecutar(ts,ar);
        while((Boolean)condicion.ejecutar(ts,ar)){
            incrementador.ejecutar(ts,ar);
            TablaDeSimbolos tablaLocal=new TablaDeSimbolos();
            tablaLocal.addAll(ts);
            for(Instruccion ins:listaInstrucciones){
                Object r;
                r=ins.ejecutar(tablaLocal,ar);
                if(r!=null){
                    if(r instanceof Break){
                        return null;
                    }else{
                        return r;
                    }
                }
            }
        }
        return null;
    }   
}