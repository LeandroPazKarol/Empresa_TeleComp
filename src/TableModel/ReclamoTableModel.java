/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableModel;

/**
 *
 * @author Sebastián
 */
import java.util.List;
import javax.swing.table.AbstractTableModel;
import entity.Reclamo;

public class ReclamoTableModel extends AbstractTableModel {
    private final String[] columnas = {
        "ID", "Fecha", "Tipo", "Descripción", "Estado",
        "Canal", "Cliente", "ÁreaAsignada", "UsuarioRegistra"
    };
    private List<Reclamo> lista;
    public ReclamoTableModel(List<Reclamo> lista) {
        this.lista = lista;
    }
    @Override
    public int getRowCount() {
        return lista == null ? 0 : lista.size();
    }
    @Override
    public int getColumnCount() {
        return columnas.length;
    }
    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Reclamo r = lista.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> r.getIdReclamo();
            case 1 -> r.getFechaRegistro();
            case 2 -> r.getTipo();
            case 3 -> r.getDescripcion();
            case 4 -> r.getEstado();
            case 5 -> r.getCanalIngreso();
            case 6 -> r.getIdCLiente();
            case 7 -> r.getIdAreaAsignada();
            case 8 -> r.getIdUsuarioRegistra();
            default -> null;
        };
    }

    public Reclamo getReclamoAt(int row) {
        return lista.get(row);
    }

    public void setLista(List<Reclamo> nuevaLista) {
        this.lista = nuevaLista;
        fireTableDataChanged();
    }
}