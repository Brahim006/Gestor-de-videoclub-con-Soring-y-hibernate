/*
 * Clase utilitaria para la comprobación y validación de ingreso de textos
 */
package com.domain.gui.utils;

/**
 *
 * @author Brahim
 */
public class InputVerifier {
    
    /**
     * Recive una cadena de texto y verifica que esta sea válida para ser
     * insertada en un query SQL, es decir que esta no contenga comillas '' que
     * puedan alterar el resulotado de la sentencia
     * @param texto Texto a verificar
     * @return El texto proesao y libre de comillas
     * @throws NullPointerException Una excepción en caso de haber entregado
     * texto vacío
     */
    public static String verificarTexto(String texto) 
                                                throws NullPointerException {
        
        String aux = "";
        
        if(texto.equals("")){
            throw new NullPointerException("Texto vacío");
        } else {
            for (int i = 0; i < texto.length(); i++) {
                if(texto.charAt(i) != '\'') aux += texto.charAt(i);
            }
        }
        return aux;
    } // fin verificarTexto
    
}