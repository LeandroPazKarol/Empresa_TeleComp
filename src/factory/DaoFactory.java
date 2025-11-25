package factory;

//para una isntancia generica
import Dao.AreaDaoImpl;
import Dao.IAreaDao;
import Dao.IUsuarioDao;
import Dao.UsuarioDaoImpl;

public class DaoFactory {

    //meotodo factory estatico
    public static IUsuarioDao getUsuarioDao() {
        return new UsuarioDaoImpl();
    }
    
    public static IAreaDao getAreaDao(){
    return new AreaDaoImpl();
    }
}
