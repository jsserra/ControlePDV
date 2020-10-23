/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pdv.util;

/**
 *
 * @author jsser
 */
public class pdvException extends Exception{

    public pdvException(){
    }
    
    public pdvException(String message) {
        super(message);
    }

    public pdvException(String message, Throwable cause) {
        super(message, cause);
    }

    public pdvException(Throwable cause) {
        super(cause);
    }
    
    
}
