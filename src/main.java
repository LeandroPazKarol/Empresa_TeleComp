
import Controller.LoginController;
import formularios.Login;


public class main {
    public static void main(String[] args) {
     Login vista=new Login();
     LoginController controlador= new LoginController(vista);
     
     vista.setVisible(true);
     vista.setLocationRelativeTo(null);
    }
}
