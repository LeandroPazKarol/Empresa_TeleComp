package Controller;

import Dao.ClienteDao;
import Dao.ReclamoDao;
import TableModel.ReclamoTableModel;
import entity.Cliente;
import entity.Reclamo;
import java.util.List;

public class ReclamoController {

    private ClienteDao clienteDao;
    private ReclamoDao reclamoDao;

    public ReclamoController() {
        clienteDao = new ClienteDao();
        reclamoDao = new ReclamoDao();
    }
    
    public List<Reclamo> ReclamoListar() throws Exception{
        return reclamoDao.readAll();
    }

    public Cliente buscarCliente(String dni) throws Exception {
        return clienteDao.buscarporDNI(dni);
    }

    public boolean registrarReclamo(Reclamo reclamo) throws Exception {
        return reclamoDao.registrarReclamo(reclamo);
    }
    
  
    public ReclamoTableModel obtenerTablaReclamos() throws Exception {
        List<Reclamo> lista = reclamoDao.readAll();
        return new ReclamoTableModel(lista);
    }
}
