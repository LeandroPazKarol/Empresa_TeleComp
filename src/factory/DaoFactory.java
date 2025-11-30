package factory;

//para una isntancia generica
import Dao.AreaDaoImpl;
import Dao.UsuarioDaoImpl;
import Repository.IArea;
import Repository.IUsuario;

public class DaoFactory {

    //meotodo factory estatico
    public static IUsuario getUsuarioDao() {
        return new UsuarioDaoImpl();
    }
    
    public static IArea getAreaDao(){
    return new AreaDaoImpl();
    }
}
