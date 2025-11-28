package Controller;

import Dao.IUsuarioDao;
import Dao.UsuarioDaoImpl;
import Dao.UsuarioDaoImpl;
import entity.Usuario;
import factory.DaoFactory;
import formularios.Login;
import formularios.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LoginController implements ActionListener {

    private Login view;
    private IUsuarioDao usaurioDAO;

    public LoginController(Login view) {
        this.view = view;
        this.usaurioDAO = DaoFactory.getUsuarioDao();
        this.view.btnIngresa.addActionListener(this);
        this.view.btnSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnIngresa || e.getSource() == view.btnSalir) {
            String usuario = view.txtUsuario.getText().trim();
            String contraseña = new String(view.passPassword.getPassword());

            if (usuario.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Debes ingresar sus contraseña correcta");
                return;
            }

            Usuario usuarioLogueado = null;
            usuarioLogueado = usaurioDAO.validarUsuario(usuario, contraseña);

            if (usuarioLogueado != null) {
                JOptionPane.showMessageDialog(view, "Bienvenido, " + usuario);
                Menu menu = new Menu();

                MenuControlador menuControlador = new MenuControlador();
                menu.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
                menu.setVisible(true);

                this.view.dispatchEvent(e);
            } else {
                JOptionPane.showMessageDialog(view, "Usuario o contraseña invalido");
            }
        }
    }

}