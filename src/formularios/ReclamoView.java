package formularios;

import entity.Reclamo;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReclamoView extends JFrame {

    private JTable tablaReclamos;
    private JButton btnResolver;
    private DefaultTableModel modelo;

    private JTextArea txtDescripcionResolucionDetalles;
    private JTextField txtResponsableDetalles;
    private JTextField txtFechaResolucionDetalles;

    @FunctionalInterface
    public interface ResolverReclamoListener {

        void accept(int id, String descripcion, String responsable);
    }

    private ResolverReclamoListener resolverListener;
    private List<Reclamo> listaReclamos;

    public ReclamoView() {
        setTitle("Gestión de Reclamos");
        setSize(1000, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        /* ===========================
           TABLA A LA IZQUIERDA
        ============================ */
        modelo = new DefaultTableModel(new Object[]{"ID", "Tipo", "Descripción", "Estado"}, 0);
        tablaReclamos = new JTable(modelo);
        tablaReclamos.setRowHeight(24);

        JScrollPane scrollTabla = new JScrollPane(tablaReclamos);
        scrollTabla.setPreferredSize(new Dimension(500, 600));

        /* ====================================================
           PANEL DERECHO: RESOLVER + DETALLES
        ==================================================== */
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        /* =========================
           FORMULARIO DE RESOLVER
        ========================== */
        JPanel panelResolver = new JPanel();
        panelResolver.setLayout(new GridBagLayout());
        panelResolver.setBorder(BorderFactory.createTitledBorder("Resolver Reclamo"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextArea txtDescripcionResolucion = new JTextArea(3, 25);
        JScrollPane scrollDesc = new JScrollPane(txtDescripcionResolucion);

        JTextField txtResponsable = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelResolver.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        panelResolver.add(scrollDesc, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelResolver.add(new JLabel("Responsable:"), gbc);
        gbc.gridx = 1;
        panelResolver.add(txtResponsable, gbc);

        /* BOTÓN DEBAJO */
        btnResolver = new JButton("Resolver Reclamo");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panelResolver.add(btnResolver, gbc);

        /* ===========================
           PANEL DE DETALLES
        ============================ */
        JPanel panelDetalles = new JPanel(new GridBagLayout());
        panelDetalles.setBorder(BorderFactory.createTitledBorder("Detalles de Resolución"));

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(4, 4, 4, 4);
        gbc2.anchor = GridBagConstraints.WEST;
        gbc2.fill = GridBagConstraints.HORIZONTAL;

        txtFechaResolucionDetalles = new JTextField(20);
        txtFechaResolucionDetalles.setEditable(false);

        txtResponsableDetalles = new JTextField(20);
        txtResponsableDetalles.setEditable(false);

        txtDescripcionResolucionDetalles = new JTextArea(4, 25);
        txtDescripcionResolucionDetalles.setEditable(false);
        JScrollPane scrollDetalles = new JScrollPane(txtDescripcionResolucionDetalles);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        panelDetalles.add(new JLabel("Fecha resolución:"), gbc2);
        gbc2.gridx = 1;
        panelDetalles.add(txtFechaResolucionDetalles, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 1;
        panelDetalles.add(new JLabel("Responsable:"), gbc2);
        gbc2.gridx = 1;
        panelDetalles.add(txtResponsableDetalles, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 2;
        panelDetalles.add(new JLabel("Descripción:"), gbc2);
        gbc2.gridx = 1;
        panelDetalles.add(scrollDetalles, gbc2);

        /* AGREGAR A PANEL DERECHO */
        panelDerecho.add(panelResolver);
        panelDerecho.add(Box.createVerticalStrut(10));
        panelDerecho.add(panelDetalles);

        /* ===========================
           DIVISOR MITAD/MITAD REAL
        ============================ */
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollTabla, panelDerecho);

        split.setResizeWeight(0.5);     // distribuye 50/50 inicial
        split.setDividerLocation(0.5);  // coloca divisor al medio

        add(split, BorderLayout.CENTER);

        /* ===========================
           EVENTO TABLA
        ============================ */
        tablaReclamos.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaReclamos.getSelectedRow();
            if (fila >= 0 && listaReclamos != null) {
                Reclamo r = listaReclamos.get(fila);
                if (r.getResolucion() != null) {
                    txtFechaResolucionDetalles.setText(r.getResolucion().getFechaResolucion().toString());
                    txtResponsableDetalles.setText(r.getResolucion().getResponsable());
                    txtDescripcionResolucionDetalles.setText(r.getResolucion().getDescripcion());
                } else {
                    txtFechaResolucionDetalles.setText("");
                    txtResponsableDetalles.setText("");
                    txtDescripcionResolucionDetalles.setText("");
                }
            }
        });

          /* ===========================
              EVENTO BOTÓN RESOLVER
          ============================ */
        btnResolver.addActionListener(e -> {
            int fila = tablaReclamos.getSelectedRow();
            if (fila < 0) {
                mostrarError("Seleccione un reclamo.");
                return;
            }

            int id = (int) modelo.getValueAt(fila, 0);
            String descripcion = txtDescripcionResolucion.getText().trim();
            String responsable = txtResponsable.getText().trim();

            if (descripcion.isEmpty() || responsable.isEmpty()) {
                mostrarError("Debe completar todos los campos.");
                return;
            }

            if (resolverListener != null) {
                resolverListener.accept(id, descripcion, responsable);
            }

            // Actualiza la vista de detalles con los nuevos datos 
            txtDescripcionResolucionDetalles.setText(descripcion);
            txtResponsableDetalles.setText(responsable);
            txtFechaResolucionDetalles.setText(java.time.LocalDate.now().toString());

            // Limpia campos de ingreso luego d 
            txtDescripcionResolucion.setText("");
            txtResponsable.setText("");
        });
    }

    public void actualizarListaReclamos(List<Reclamo> reclamos) {
        this.listaReclamos = reclamos;
        modelo.setRowCount(0);
        for (Reclamo r : reclamos) {
            modelo.addRow(new Object[]{
                r.getIdReclamo(), r.getTipo(), r.getDescripcion(), r.getEstado()
            });
        }
    }

    public void setResolverReclamoListener(ResolverReclamoListener listener) {
        this.resolverListener = listener;
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
