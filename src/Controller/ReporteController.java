package Controller;

import Dao.ReporteDAO;
import InterfaceDAO.IReporteDAO;
import entity.DetalleReclamo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ReporteController {

    private IReporteDAO dao;

    public ReporteController() {
        this.dao = new ReporteDAO(); 
    }

    public int getTotalReclamos() throws Exception {
        return dao.obtenerTotalReclamos();
    }

    public double getPromedioHorasResolucion() throws Exception {
        return dao.obtenerPromedioHorasResolucion();
    }

    public double getPorcentajePrimerContacto() throws Exception {
        return dao.obtenerPorcentajePrimerContacto();
    }

    /**
     * @param desde fecha inicio (puede ser null)
     * @param hasta fecha fin (puede ser null)
     */
    public List<DetalleReclamo> getDetalleReporte(Date desde, Date hasta) throws Exception {
        Timestamp tDesde = (desde == null) ? null : new Timestamp(desde.getTime());
        Timestamp tHasta = (hasta == null) ? null : new Timestamp(hasta.getTime());
        return dao.listarDetalleReporte(tDesde, tHasta);
    }

}
