/*
 * TableModel que hereda de DefaultTableModel adaptándose a las necesidades de
 * las tablas de la aplicación
 */
package com.domain.gui.utils;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Brahim
 */
public class TableModelNoEditable extends DefaultTableModel {

    // Constructores que llaman a los de la superclase
    public TableModelNoEditable() {
    }

    public TableModelNoEditable(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    public TableModelNoEditable(Vector columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public TableModelNoEditable(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public TableModelNoEditable(Vector data, Vector columnNames) {
        super(data, columnNames);
    }

    public TableModelNoEditable(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
    
    // Métodos sobreescritos
    
    @Override
    public boolean isCellEditable(int row, int column){
        return false; // Elimina la posibilidad de editar celdas
    }
    
}
