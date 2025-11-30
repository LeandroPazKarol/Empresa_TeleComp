
package Repository;

import entity.Usuario;
import java.util.List;

public interface IUsuario {
    Usuario validarUsuario(String user, String pass);
    boolean registrar(Usuario u);
    List<Usuario> listar();
    boolean actualizar(Usuario u);
    boolean eliminar(int idUsuario);
}
