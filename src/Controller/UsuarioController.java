package Controller;

import Dao.IAreaDao;
import Dao.IUsuarioDao;
import entity.Area;
import entity.Usuario;
import factory.DaoFactory;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class UsuarioController {

    private final IUsuarioDao usuarioDao;
    private final IAreaDao areaDao;

    public UsuarioController() {
        this.usuarioDao = DaoFactory.getUsuarioDao();
        this.areaDao=DaoFactory.getAreaDao();
    }

    //metodo para login
    public Usuario login(String user, String pass) {
        return usuarioDao.validarUsuario(user, pass);
    }

    //metodo para registrar
    public boolean registrarUsuario(String nombre, String pass, String rol, Area area) {
        Usuario u = new Usuario();
        u.setNombreUsuario(nombre);
        u.setPassword(pass);
        u.setRol(rol);
        u.setIdArea(area.getIdArea());
        return usuarioDao.registrar(u);
    }

    //metodo para llenar la tabla
    public void llenarTabal(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        List<Usuario> lista = usuarioDao.listar();

        for (Usuario u : lista) {
            modelo.addRow(new Object[]{
                u.getIdUsuario(),
                u.getNombreUsuario(),
                u.getRol(),
                u.getIdArea()
            });
        }
    }

    //lenar combobox 
    public void llenarComboArea(DefaultComboBoxModel modelo){
   modelo.removeAllElements();
        List<Area> lista=areaDao.listar();
    for(Area a: lista){
    modelo.addElement(a);
    }
    }
    
    public boolean actualizarUsuario(int id, String nombre, String pass, String rol, Area area){
    Usuario u = new Usuario();
    u.setIdUsuario(id);
    u.setNombreUsuario(nombre);
    u.setPassword(pass);
    u.setRol(rol);
    u.setIdArea(area.getIdArea());
    return usuarioDao.actualizar(u);
    }
    
    public boolean eliminarUsuario(int id){
    return usuarioDao.eliminar(id);
    }
    
}
