package Controller;

import Dao.ClienteDao;
import Dao.ReclamoDao;
import entity.Cliente;
import entity.Reclamo;

public class ReclamoController {

    private ClienteDao clienteDao;
    private ReclamoDao reclamoDao;

    public ReclamoController() {
        clienteDao = new ClienteDao();
        reclamoDao = new ReclamoDao();
    }

    public Cliente buscarCliente(String dni) throws Exception {
        return clienteDao.buscarporDNI(dni);
    }

    public boolean registrarReclamo(Reclamo reclamo) throws Exception {
        return reclamoDao.registrarReclamo(reclamo);
    }
}
