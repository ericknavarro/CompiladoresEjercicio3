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
        
        // creando identificador único
        int numParametros = 0;
        if (parametros != null) {
            numParametros = parametros.size();
        }
        
        // para llamar a la función es necesario construir su identificador único
        String id = "_" + identificador.toLowerCase() + "(";
        for(Instruccion parametro: parametros) {
            // es necesario evaluar los parametros de la función para saber sus tipo
            // y así poder completar el id
            Object resultado = parametro.ejecutar(ts, ar);
            
            if (resultado instanceof Double) {
                id += "_" + Simbolo.Tipo.NUMERO;
            } else if(resultado instanceof String) {
                id += "_" + Simbolo.Tipo.CADENA;
            } else if(resultado instanceof Boolean){
                id += "_" + Simbolo.Tipo.BOOLEANO;
            }
        }
        id += ")";
        
        Function f=ar.getFunction(id);
        if(null!=f){
            f.setValoresParametros(parametros);
            return f.ejecutar(ts, ar);
        }
        return null;
    }
}
