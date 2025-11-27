/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfaceDAO;

import entity.DetalleReclamo;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface IReporteDAO {
    int obtenerTotalReclamos() throws SQLException;
    double obtenerPromedioHorasResolucion() throws SQLException;
    double obtenerPorcentajePrimerContacto() throws SQLException;
    List<DetalleReclamo> listarDetalleReporte(Timestamp desde, Timestamp hasta) throws SQLException;
}