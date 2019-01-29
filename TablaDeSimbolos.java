/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package arbol;

import arbol.Simbolo.Tipo;
import java.util.LinkedList;

/**
 * Clase tabla de símbolos, que realiza todas las operaciones habituales de una 
 * tabla de símbolos, hereda de la clase lista enlazada porque básicamente la tabla
 * es una lista de símbolos.
 * @author Erick
 */
public class TablaDeSimbolos extends LinkedList<Simbolo>{
    /**
     * Constructor de la clase que lo único que hace es llamar al constructor de 
     * la clase padre, es decir, el constructor de LinkedList.
     */
    public TablaDeSimbolos() {
        super();
    }
    /**
     * Método que busca una variable en la tabla de símbolos y devuelve su valor.
     * @param id Identificador de la variable que quiere buscarse
     * @return Valor de la variable que se buscaba, si no existe se devuelve nulo
     */
    Object getValor(String id) {
        for (int i = this.size()-1; i >= 0; i--) {
            Simbolo s=this.get(i);
            if(s.isParametro() && s.isParametroInicializado() || !s.isParametro()){
                if(s.getId().equals(id)){
                    Object aux=s.getValor();
                    return aux;
                }
            }
        }
        System.out.println("La variable "+id+" no existe en este ámbito.");
        return null;
    }
    /**
     * Método que busca cierta celda en un arreglo dentro de la tabla de símbolos
     * y devuelve su valor
     * @param id Identificador del arreglo
     * @param indices Indices con los que se desea acceder al arreglo
     * @return Si el arreglo y su celda existe devuelve el valor, de lo contrario devuelve nulo
     */
    Object getValor(String id, LinkedList<Integer> indices) {
        for(Simbolo s:this){
            if(s.getId().equals(id)){
                return s.getValor(id, indices);
            }
        }
        System.out.println("La variable "+id+" no existe en este ámbito.");
        return null;
    }
    
    /**
     * Método que asigna un valor a una variable específica, si no la encuentra 
     * no realiza la asignación y despliega un mensaje de error.
     * @param id Identificador de la variable que quiere buscarse
     * @param valor Valor que quiere asignársele a la variable buscada
     */
    void setValor(String id, Object valor) {
        for (int i = this.size()-1; i >= 0; i--) {
            Simbolo s=this.get(i);
            if(s.getId().equals(id)){
                Tipo t = s.getTipo();
                Tipo tvalor = getValorTipo(valor);
                if(t == tvalor){
                    s.setValor(valor);
                } else {
                System.out.println("La variable "+id+" no es del mismo tipo \""+ t.name() +"\" del valor. \"" + tvalor.name()+"\"");
              
                }
                return;
            }
        }
        System.out.println("La variable "+id+" no existe en este ámbito, por lo "
                + "que no puede asignársele un valor.");
    }
    
    /**
     * Método que devuelve el tipo del objeto valor a asignar a las variables
     * @param valor Valor que se desea averiguar el tipo
     */
    Tipo getValorTipo (Object valor){
        Tipo tipo = null;
        if(valor.getClass()==Integer.class){
            tipo = Tipo.NUMERO;
            return tipo;
        } else if(valor.getClass()==String.class){
            tipo = Tipo.CADENA;
            return tipo;
        } else if(valor.getClass()==boolean.class){
            tipo = Tipo.BOOLEANO;
            return tipo;
        }
        return tipo;
    }
    
    /**
     * Método que asigna un valor a cierta celda de un arreglo.
     * @param id Identificador del arreglo
     * @param valor Valor que se desea asignar a la celda del arreglo
     * @param indices Indices para acceder a la celda del arreglo
     */
    void setValor(String id, Object valor, LinkedList<Integer> indices) {
        for(Simbolo s:this){
            if(s.getId().equals(id)){
                s.setValor(valor,indices);
                return;
            }
        }
        System.out.println("La variable "+id+" no existe en este ámbito, por lo "
                + "que no puede asignársele un valor.");
    }
    /**
     * Méotodo que marca como inicializado el último parámetro agregado con el nombre de identificador indicado.
     * @param id 
     */
    void setParametroInicializado(String id) {
        for (int i = this.size()-1; i >= 0; i--) {
            Simbolo s=this.get(i);
            if(s.getId().equals(id)){
                s.setParametroInicializado(true);
                return;
            }
        }
        System.out.println("El parámtro "+id+" que quiere marcar como inicializado no existe en este ámbito, por lo "
                + "que no puede marcar.");
    }
}