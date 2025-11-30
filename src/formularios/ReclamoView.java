package formularios;

import entity.Reclamo;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReclamoView extends JFrame {

    private JTable tablaReclamos;
    private DefaultTableModel modelo;
    private JButton btnResolver;
    private JTextArea txtDescripcionResolucion;
    private JTextField txtResponsable;
    private JCheckBox chkSMS;
    private JCheckBox chkEmail;
    private JCheckBox chkEncuesta;

    private JTextField txtFechaDetalles;
    private JTextField txtResponsableDetalles;
    private JTextArea txtDescripcionDetalles;

    private ResolverReclamoListener resolverListener;
    private List<Reclamo> listaReclamos;

    public ReclamoView() {
        setTitle("Gestión de Reclamos");
        setSize(1000, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Tabla
        modelo = new DefaultTableModel(new Object[]{"ID", "Tipo", "Descripción", "Estado", "Cliente"}, 0);
        tablaReclamos = new JTable(modelo);
        tablaReclamos.setRowHeight(24);
        JScrollPane scrollTabla = new JScrollPane(tablaReclamos);
        scrollTabla.setPreferredSize(new Dimension(500, 600));

        // Panel derecho
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel Resolver
        JPanel panelResolver = new JPanel(new GridBagLayout());
        panelResolver.setBorder(BorderFactory.createTitledBorder("Resolver Reclamo"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Descripción
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelResolver.add(new JLabel("Descripción:"), gbc);
        txtDescripcionResolucion = new JTextArea(3, 25);
        JScrollPane scrollDesc = new JScrollPane(txtDescripcionResolucion);
        gbc.gridx = 1;
        panelResolver.add(scrollDesc, gbc);

        // Responsable
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelResolver.add(new JLabel("Responsable:"), gbc);
        txtResponsable = new JTextField(20);
        gbc.gridx = 1;
        panelResolver.add(txtResponsable, gbc);

        // Checkboxes notificación
        chkSMS = new JCheckBox("Enviar SMS");
        chkSMS.setSelected(true);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelResolver.add(chkSMS, gbc);

        chkEmail = new JCheckBox("Enviar Email");
        chkEmail.setSelected(true);
        gbc.gridx = 1;
        panelResolver.add(chkEmail, gbc);

        // Encuesta obligatoria
        chkEncuesta = new JCheckBox("Enviar Encuesta de Satisfacción");
        chkEncuesta.setSelected(true);
        chkEncuesta.setEnabled(false); // no se puede desmarcar
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelResolver.add(chkEncuesta, gbc);
        gbc.gridwidth = 1;

        // Botón Resolver
        btnResolver = new JButton("Resolver Reclamo");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panelResolver.add(btnResolver, gbc);

        // Panel Detalles
        JPanel panelDetalles = new JPanel(new GridBagLayout());
        panelDetalles.setBorder(BorderFactory.createTitledBorder("Detalles de Resolución"));
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(4, 4, 4, 4);
        gbc2.anchor = GridBagConstraints.WEST;
        gbc2.fill = GridBagConstraints.HORIZONTAL;

        txtFechaDetalles = new JTextField(20);
        txtFechaDetalles.setEditable(false);
        txtResponsableDetalles = new JTextField(20);
        txtResponsableDetalles.setEditable(false);
        txtDescripcionDetalles = new JTextArea(4, 25);
        txtDescripcionDetalles.setEditable(false);
        JScrollPane scrollDetalles = new JScrollPane(txtDescripcionDetalles);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        panelDetalles.add(new JLabel("Fecha resolución:"), gbc2);
        gbc2.gridx = 1;
        panelDetalles.add(txtFechaDetalles, gbc2);

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

        // Agregar a panel derecho
        panelDerecho.add(panelResolver);
        panelDerecho.add(Box.createVerticalStrut(10));
        panelDerecho.add(panelDetalles);

        // SplitPane
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollTabla, panelDerecho);
        split.setResizeWeight(0.5);
        split.setDividerLocation(0.5);
        add(split, BorderLayout.CENTER);

        // Evento tabla
        tablaReclamos.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaReclamos.getSelectedRow();
            if (fila >= 0 && listaReclamos != null) {
                Reclamo r = listaReclamos.get(fila);
                if (r.getResolucion() != null) {
                    txtFechaDetalles.setText(r.getResolucion().getFechaResolucion().toString());
                    txtResponsableDetalles.setText(r.getResolucion().getResponsable());
                    txtDescripcionDetalles.setText(r.getResolucion().getDescripcion());
                } else {
                    txtFechaDetalles.setText("");
                    txtResponsableDetalles.setText("");
                    txtDescripcionDetalles.setText("");
                }
            }
        });

        // Botón Resolver
        btnResolver.addActionListener(e -> {
            Reclamo reclamo = getReclamoSeleccionado();
            if (reclamo == null) {
                mostrarError("Seleccione un reclamo de la lista");
                return;
            }

            String medio = chkSMS.isSelected() && chkEmail.isSelected() ? "AMBOS" :
                           chkSMS.isSelected() ? "SMS" :
                           chkEmail.isSelected() ? "EMAIL" : "NINGUNO";

            if (resolverListener != null) {
                resolverListener.accept(reclamo.getIdReclamo(),
                                        txtDescripcionResolucion.getText().trim(),
                                        txtResponsable.getText().trim(),
                                        medio);
            }

            // Actualizar detalles
            txtDescripcionDetalles.setText(txtDescripcionResolucion.getText().trim());
            txtResponsableDetalles.setText(txtResponsable.getText().trim());
            txtFechaDetalles.setText(java.time.LocalDate.now().toString());

            // Limpiar campos
            txtDescripcionResolucion.setText("");
            txtResponsable.setText("");
        });
    }

    public void actualizarListaReclamos(List<Reclamo> reclamos) {
        this.listaReclamos = reclamos;
        modelo.setRowCount(0);
        for (Reclamo r : reclamos) {
            modelo.addRow(new Object[]{r.getIdReclamo(), r.getTipo(), r.getDescripcion(), r.getEstado(), r.getIdCLiente()});
        }
    }

    public Reclamo getReclamoSeleccionado() {
        int fila = tablaReclamos.getSelectedRow();
        if (fila >= 0) {
            Reclamo r = new Reclamo();
            r.setIdReclamo((int) modelo.getValueAt(fila, 0));
            r.setTipo((String) modelo.getValueAt(fila, 1));
            r.setDescripcion((String) modelo.getValueAt(fila, 2));
            r.setEstado((String) modelo.getValueAt(fila, 3));
            r.setIdCLiente((int) modelo.getValueAt(fila, 4));
            return r;
        }
        return null;
    }

    public void setResolverReclamoListener(ResolverReclamoListener listener) {
        this.resolverListener = listener;
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @FunctionalInterface
    public interface ResolverReclamoListener {
        void accept(int idReclamo, String descripcion, String responsable, String medio);
    }
}
