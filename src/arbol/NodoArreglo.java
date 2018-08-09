/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package arbol;

import java.util.LinkedList;

/**
 * Clase NodoArreglo, que es un nodo en el que se almacena el valor de cierta 
 * celda de un arreglo.
 * @author Erick
 */
public class NodoArreglo {
    /**
     * Lista de celdas vecinas en el arreglo.
     */
    private final LinkedList<NodoArreglo> celdasVecinas;
    /**
     * Valor que alberga esta celda del arreglo.
     */
    private Object valor;
    /**
     * Constructor para crear una celda de arreglo vacía
     */
    public NodoArreglo() {
        this.celdasVecinas = new LinkedList<>();
        this.valor = null;
    }
    /**
     * Método que inicializa todas las celdas de un arreglo, esta inicialización
     * se propaga a lo largo de todas las celdas del arreglo según lo que se le 
     * indica en sus parámetros.
     * @param cantDimensiones Cantidad de dimensiones del arreglo
     * @param dimensionActual Dimensión que se está analizando en la propagación actual
     * @param tamaniosDimensiones Lista que contienen los tamaños de todas las dimensiones del arreglo
     */
    public void inicializarNodo(int cantDimensiones, int dimensionActual, LinkedList<Integer> tamaniosDimensiones){
        if(dimensionActual>cantDimensiones){
            return;
        }
        for (int i = 0; i < tamaniosDimensiones.get(dimensionActual-1) ; i++) {
            NodoArreglo arr=new NodoArreglo();
            celdasVecinas.add(arr);
            arr.inicializarNodo(cantDimensiones, dimensionActual+1, tamaniosDimensiones);            
        }
    }
    /**
     * Método que configura cierto valor en una celda específica del arreglo
     * @param cantIndices Cantidad de indices que se reciben para el acceso al arreglo
     * @param indiceActual Indice que se está analizando en la propagación actual
     * @param indices Lista de los indices con los que se accederá al arreglo para asignar el valor
     * @param val Valor que se le quiere asignar a cierta celda del arreglo
     * @param id Identificador del arreglo
     */
    public void setValor(int cantIndices, int indiceActual, LinkedList<Integer> indices, Object val, String id){
        int valIndiceActual=indices.get(indiceActual-1);
        if(valIndiceActual<celdasVecinas.size() && valIndiceActual>=0){
            NodoArreglo arr=celdasVecinas.get(valIndiceActual);
            if(indiceActual==cantIndices){
                arr.valor=val;
            }else{
                arr.setValor(cantIndices, indiceActual+1, indices, val, id);
            }
        }else{
            System.err.println("La asignación al arreglo "+id+" no puede "
                    + "realizarse porque uno o más de los indices exceden "
                    + "los límites del arreglo.");
        }
    }
    /**
     * Método que recoge cierto valor en una celda específica del arreglo y 
     * devuelve nulo cuando no lo encuentra
     * @param cantIndices Cantidad de indices que se reciben para el acceso al arreglo
     * @param indiceActual Indice que se está analizando en la propagación actual
     * @param indices Lista de los indices con los que se accederá al arreglo para asignar el valor
     * @param id Identificador del arreglo
     * @return El valor almacenado por la celda específica o null en caso no lo encuentre
     */
    Object getValor(int cantIndices, int indiceActual, LinkedList<Integer> indices, String id) {
        int valIndiceActual=indices.get(indiceActual-1);
        if(valIndiceActual<celdasVecinas.size() && valIndiceActual>=0){
            NodoArreglo arr=celdasVecinas.get(valIndiceActual);
            if(indiceActual==cantIndices){
                return arr.valor;
            }else{
                return arr.getValor(cantIndices, indiceActual+1, indices, id);
            }
        }else{
            System.err.println("El acceso al arreglo "+id+" no puede "
                    + "realizarse porque uno o más de los indices exceden "
                    + "los límites del arreglo.");
        }
        return null;
    }
}
