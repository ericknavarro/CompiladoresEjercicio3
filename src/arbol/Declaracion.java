/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package arbol;

/**
 * Clase que ejecuta las acciones de una instrucción de declaración y que implementa
 * la interfaz de instrucción
 * @author Erick
 */
public class Declaracion implements Instruccion{
    /**
     * Bandera que indica si lo que se declara es un parámetro o no.
     */
    protected boolean parametro;
    /**
     * Identificador de la variable que será declarada.
     */
    protected final String id;
    /**
     * Tipo de la variable que será declarada.
     */
    protected final Simbolo.Tipo tipo;
    /**
     * Constructor de la clase
     * @param a Identificador de la variable que será declarada
     * @param b Tipo de la clase que será declarada
     */
    public Declaracion(String a, String b) {
        id=a;
        String reservadaTipo=b.toLowerCase();
        switch (reservadaTipo) {
            case "number": tipo=Simbolo.Tipo.NUMERO;
                     break;
            case "string":  tipo=Simbolo.Tipo.CADENA;
                     break;
            case "boolean": tipo=Simbolo.Tipo.BOOLEANO;
                     break;
            default:
                tipo=null;
        }
        parametro=false;
    }
    /**
     * Método que ejecuta la accion de declarar una variable, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param ts Tabla de símbolos del ámbito padre.
     * @return No retorna nada porque no es una sentencia que genere un valor.
     */
    @Override
    public Object ejecutar(TablaDeSimbolos ts,Arbol ar) {
        Simbolo aux=new Simbolo(id,tipo);
        aux.setParametro(this.parametro);
        ts.add(aux);
        return null;
    }
    /**
     * Función que retorna el valor declarado
     * @return 
     */
    public String getIdentificador() {
        return id;
    }
    /**
     * Método que devuelve el valor de la bandera parámetro
     * @return 
     */
    public boolean isParametro() {
        return parametro;
    }
    /**
     * Método con el que se configura el valor de la bandera parámetro.
     * @param parametro 
     */
    public void setParametro(boolean parametro) {
        this.parametro = parametro;
    }
    
}
