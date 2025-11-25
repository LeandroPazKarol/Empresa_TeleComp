
package Dao;

import entity.Usuario;
import java.util.List;

public interface IUsuarioDao {
    Usuario validarUsuario(String user, String pass);
    boolean registrar(Usuario u);
    List<Usuario> listar();
    boolean actualizar(Usuario u);
    boolean eliminar(int idUsuario);
}
