package Repository;

import entity.Reclamo;
import java.util.Date;
import java.util.List;

//ISP, DIP Y OCP
public interface IReclamo {

    void resolverReclamo(int id) throws Exception;

    List<Reclamo> listarReclamosAbiertos() throws Exception;

    void resolverReclamoConResolucion(int idReclamo, Date fechaResolucion, String descripcion, String responsable) throws Exception;

    void actualizarResolucion(int idReclamo, String descripcion, String responsable) throws Exception;

}
