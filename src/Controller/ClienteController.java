package controller;

import Dao.ClienteDao;
import entity.Cliente;
import java.util.List;

public class ClienteController {

    // Instanciamos el DAO usando el Patrón SINGLETON.
    private ClienteDao dao = ClienteDao.getInstance();

    public boolean registrarCliente(Cliente cliente) throws Exception {
        return dao.registrarCliente(cliente);
    }

    public boolean actualizarCliente(Cliente cliente) throws Exception {
        return dao.actualizarCliente(cliente);
    }

    public boolean eliminarCliente(String dni) throws Exception {
        return dao.eliminarCliente(dni);
    }

    // El controlador arregla la diferencia de nombres.
    // entre buscarporDNI y buscarCliente
    public Cliente buscarCliente(String dni) throws Exception {
        return dao.buscarporDNI(dni);
    }

    public List<Cliente> listarClientes() throws Exception {
        return dao.listarClientes();
    }
}
