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
public class DoWhile implements Instruccion{
    /**
     * Condición de la sentencia mientras.
     */
    private final Instruccion condicion;
    /**
     * Lista de las instrucciones que deben ejecutarse si la condición se cumple.
     */
    private final LinkedList<Instruccion> listaInstrucciones;
    /**
     * Constructor de la clase Mientras
     * @param a condición que debe evaluarse para ejecutar el ciclo
     * @param b instrucciones que deben ejecutarse si la condición se cumpliera
     */
    public DoWhile(Instruccion a, LinkedList<Instruccion> b) {
        this.condicion=a;
        this.listaInstrucciones=b;
    }
    /**
     * Método que ejecuta la instrucción mientras, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param ts tabla de símbolos del ámbito padre de la sentencia
     * @return Esta instrucción retorna nulo porque no produce ningun valor
     */
    @Override
    public Object ejecutar(TablaDeSimbolos ts,Arbol ar) {
        Object resultado;
        
        do{
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
            
            resultado = condicion.ejecutar(ts, ar);
            
            if(!(resultado instanceof Boolean)){
                System.err.println("Se esperaba un valor booleano");
                return null;
            }
            
        }while((Boolean)resultado);
        
        return null;
    }   
}