/*
 * Clase ejecutable que crea los recursos a ser utilizados en el resto de la
 * ejecución del programa.
 */
package com.domain.exe;

import com.domain.gui.Gui;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author Brahim
 */
public class Ejecutable {
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ulafe) {
            
        } finally {
            Gui gui = new Gui();
        }
    }
    
}
