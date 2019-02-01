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
        String tipoFuncion = ar.getFuncionPre().getTipo().toString(); // Get tipo de la funcion
        String idFuncion = ar.getFuncionPre().getIdentificador().toString(); // Get indentificador de la funcion
        if(valorRetorno==null){
            /*
                Condicion cuando el return contiene un dato que retornar
                se verifica que la funcion no tenga VOID si no salta un error
            */
            if (tipoFuncion.toLowerCase().equals("VOID".toLowerCase()) == false) {
                System.err.println("ERROR:Se tiene que retornar un tipo de variable que indica el retorno la funcion: " + idFuncion);
            }
            return this;
        }else{
            /*
                Condicion cuando el return NO contiene un dato que retornar
                se verifica que la funcion TENGA VOID si no salta un error
             */
            if (tipoFuncion.toLowerCase().equals("VOID".toLowerCase())) {
                System.err.println("ERROR:No se puede retornar el tipo de variable dado que la funcion es VOID o equivalente un procedimiento, error en la funcion: "+ idFuncion);
            }
            return valorRetorno.ejecutar(ts, ar);
        }
    }
    
}
