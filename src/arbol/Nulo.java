/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

/**
 *
 * @author Pavel
 */
public class Nulo implements Instruccion {

    @Override
    public Object ejecutar(TablaDeSimbolos ts, Arbol ar) {
        return this;
    }

}
