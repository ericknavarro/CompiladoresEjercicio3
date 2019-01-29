/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package arbol;

import java.util.LinkedList;

/**
 * Clase que ejecuta las acciones de una función y que implementa
 * la interfaz instrucción
 * @author Erick
 */
public class Function implements Instruccion {
    /**
     * Tipo de la función.
     */
    private final Simbolo.Tipo tipo;
    /**
     * Identificador de la función.
     */
    private final String identificador;
    /**
     * Parámetros de la función.
     */
    private final LinkedList<Declaracion> parametros;
    /**
     * Valores que se reciben como parámetros para ejecutar la función.
     */
    private LinkedList<Instruccion> valoresParametros;
    /**
     * Lista de las instrucciones que deben ejecutarse si se llama a la función.
     */
    private final LinkedList<Instruccion> instrucciones;
    /**
     * Constructor de la clase Function cuando esta recibe parámetros.
     * @param a Tipo de la función
     * @param b Identificador de la función
     * @param c Lista de parámetros definidos
     * @param d Lista de instrucciones contenidas por la función
     */
    public Function(String a, String b, LinkedList<Declaracion> c, LinkedList<Instruccion> d) {
        switch (a.toLowerCase()) {
            case "number": tipo=Simbolo.Tipo.NUMERO;
                     break;
            case "string":  tipo=Simbolo.Tipo.CADENA;
                     break;
            case "boolean": tipo=Simbolo.Tipo.BOOLEANO;
                     break;
            case "void": tipo=Simbolo.Tipo.VOID;
                     break;
            default:
                tipo=null;
        }
        identificador=b;
        parametros=c;
        instrucciones=d;
    }
    /**
     * Constructor de la clase Function cuando esta no recibe parámetros.
     * @param a Tipo de la función
     * @param b Identificador de la función
     * @param c Lista de instrucciones contenidas por la función
     */
    public Function(String a, String b, LinkedList<Instruccion> c) {
        switch (a.toLowerCase()) {
            case "number": tipo=Simbolo.Tipo.NUMERO;
                     break;
            case "string":  tipo=Simbolo.Tipo.CADENA;
                     break;
            case "boolean": tipo=Simbolo.Tipo.BOOLEANO;
                     break;
            case "void": tipo=Simbolo.Tipo.VOID;
                     break;
            default:
                tipo=null;
        }
        identificador=b;
        parametros=new LinkedList<>();
        instrucciones=c;
    }
    /**
     * Método que ejecuta la una función, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param ts tabla de símbolos del ámbito padre de la sentencia.
     * @return Estra instrucción retorna el valor de retorno de la función o bien
     * una instancia de retorno para el caso de las funciones void.
     */
    @Override
    public Object ejecutar(TablaDeSimbolos ts,Arbol ar) {
        TablaDeSimbolos tablaLocal=new TablaDeSimbolos(); // Creamos una nueva tabla local para la función.
        tablaLocal.addAll(ar.tablaDeSimbolosGlobal); // Agregamos a la tabla local las referencias a las variables globales.
        if(parametros.size()==valoresParametros.size()){
            for(int i=0;i<parametros.size();i++){
                
                Declaracion d=parametros.get(i); 
                d.setParametro(true);
                d.ejecutar(tablaLocal, ar);
                
                Asignacion a=new Asignacion(d.getIdentificador(), valoresParametros.get(i));
                a.setTablaDeSimbolosPadre(ts); // Indicamos que los valores de los parámetros los debe obtener de la tabla padre de esta función.
                
                a.ejecutar(tablaLocal, ar);
                tablaLocal.setParametroInicializado(d.getIdentificador());
            }
            for(Instruccion ins:instrucciones){
                Object r;
                r=ins.ejecutar(tablaLocal,ar);
                if(r!=null){
                    if(r instanceof Return){
                        return null;
                    }else{
                        return r;
                    }
                }
            }            
        }else{
            System.err.println("La cantidad de parámetros enviada a la función " + identificador + " no es correcta.");
        }
        return null;
    }
    /**
     * Méotodo que devuelve el identificador de la función
     * @return Identificador de la función
     */
    public String getIdentificador() {
        return identificador.toLowerCase();
    }
    /**
     * Método que configura el set de parámetros que la función debe recibir
     * @param a Lista de parámetros que se desea asignar a la función.
     */
    public void setValoresParametros(LinkedList<Instruccion> a) {
        valoresParametros=a;
    }
    
}
