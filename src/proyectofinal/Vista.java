package proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

public class Vista extends JFrame {
    private JTabbedPane tabbedPane;
    private JTable tabla;
    private JTextField formulaField;
    private Modelo modelo;
    private Controlador controlador;
    
    public Vista(Modelo modelo, Controlador controlador) {
        this.modelo = modelo;
        this.controlador = controlador;
        
        configurarVentanaPrincipal();
        crearMenu();
        crearBarraFormulas();
        crearAreaTrabajo();
    }
    
    private void configurarVentanaPrincipal() {
        setTitle("Hoja Electrónica");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu archivoMenu = new JMenu("Archivo");
        JMenuItem nuevaHojaItem = new JMenuItem("Nueva Hoja");
        JMenuItem hashTableItem = new JMenuItem("Tabla hash");
        
        nuevaHojaItem.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Nombre de la nueva hoja:");
            if (nombre != null && !nombre.trim().isEmpty()) {
                controlador.crearNuevaHoja(nombre);
            }
        });
        
        hashTableItem.addActionListener(e -> controlador.mostrarTablaHash());
        
        archivoMenu.add(nuevaHojaItem);
        archivoMenu.add(hashTableItem);
        menuBar.add(archivoMenu);
        
        setJMenuBar(menuBar);
    }
    
    private void crearBarraFormulas() {
        JPanel formulaPanel = new JPanel(new BorderLayout());
        formulaPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JLabel fxLabel = new JLabel("f(x):");
        formulaField = new JTextField();
        
        formulaField.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            int col = tabla.getSelectedColumn();
            if (fila >= 0 && col >= 0) {
                controlador.procesarFormula(fila, col, formulaField.getText());
            }
        });
        
        formulaPanel.add(fxLabel, BorderLayout.WEST);
        formulaPanel.add(formulaField, BorderLayout.CENTER);
        
        add(formulaPanel, BorderLayout.NORTH);
    }
    
   
    private void crearAreaTrabajo() {
    tabbedPane = new JTabbedPane();
    actualizarVista();
    add(tabbedPane, BorderLayout.CENTER);
}
    
    public void mostrarDialogoTablaHash() {
        JDialog dialog = new JDialog(this, "Tabla Hash", true);
        dialog.setSize(600, 400);
        dialog.setLayout(new BorderLayout());
        
        DefaultTableModel model = new DefaultTableModel(new String[]{"Datos", "Hash", "Índice"}, 0);
        JTable tablaHash = new JTable(model);
        
        JPanel panelControles = new JPanel();
        JButton btnAgregar = new JButton("Agregar Dato");
        JButton btnGenerar = new JButton("Generar Hash");
        JTextField txtDato = new JTextField(20);
        
        btnAgregar.addActionListener(e -> {
            String dato = txtDato.getText().trim();
            if (!dato.isEmpty()) {
                model.addRow(new Object[]{dato, "", ""});
                txtDato.setText("");
            }
        });
        
        btnGenerar.addActionListener(e -> generarHash(model));
        
        panelControles.add(new JLabel("Dato:"));
        panelControles.add(txtDato);
        panelControles.add(btnAgregar);
        panelControles.add(btnGenerar);
        
        dialog.add(panelControles, BorderLayout.NORTH);
        dialog.add(new JScrollPane(tablaHash), BorderLayout.CENTER);
        
        dialog.setVisible(true);
    }
    
    private void generarHash(DefaultTableModel model) {
        MiListaEnlazada[] tablaHash = new MiListaEnlazada[10];
        for (int i = 0; i < 10; i++) {
            tablaHash[i] = new MiListaEnlazada();
        }
        
        for (int i = 0; i < model.getRowCount(); i++) {
            String dato = (String) model.getValueAt(i, 0);
            if (dato != null && !dato.isEmpty()) {
                int hash = funcionHashPersonalizada(dato);
                int indice = hash % 10;
                
                model.setValueAt(hash, i, 1);
                model.setValueAt(indice, i, 2);
                
                tablaHash[indice].agregar(dato);
            }
        }
        
        mostrarEstructuraHash(tablaHash);
    }
    
    private int funcionHashPersonalizada(String clave) {
        int hash = 0;
        for (int i = 0; i < clave.length(); i++) {
            hash = (hash * 31 + clave.charAt(i)) % 1000000;
        }
        return Math.abs(hash);
    }
    
    private void mostrarEstructuraHash(MiListaEnlazada[] tablaHash) {
        StringBuilder sb = new StringBuilder();
        sb.append("Estructura de la Tabla Hash:\n");
        sb.append("----------------------------\n");
        
        for (int i = 0; i < tablaHash.length; i++) {
            sb.append("Bucket ").append(i).append(": ");
            if (tablaHash[i].tamaño() == 0) {
                sb.append("Vacío");
            } else {
                for (int j = 0; j < tablaHash[i].tamaño(); j++) {
                    sb.append(tablaHash[i].obtener(j));
                    if (j < tablaHash[i].tamaño() - 1) {
                        sb.append(" -> ");
                    }
                }
            }
            sb.append("\n");
        }
        
        JOptionPane.showMessageDialog(this, sb.toString(), "Estructura Hash", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
    
    public void actualizarTabla() {
        if (tabla != null) {
            ((AbstractTableModel)tabla.getModel()).fireTableDataChanged();
        }
    }
    
    public void actualizarVista() {
//        if (tabbedPane == null) return;
//        
//        tabbedPane.removeAll();
//        
//        for (int i = 0; i < modelo.getHojas().tamaño(); i++) {
//            Hoja hoja = (Hoja) modelo.getHojas().obtener(i);
//            
//            HojaTableModel model = new HojaTableModel(hoja);
//            JTable tablaHoja = new JTable(model);
//            
//            // Configurar la tabla
//            tablaHoja.setRowHeight(25);
//            tablaHoja.setGridColor(Color.LIGHT_GRAY);
//            
//            // Configurar el renderizado de celdas
//            tablaHoja.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//                @Override
//                public Component getTableCellRendererComponent(JTable table, Object value,
//                        boolean isSelected, boolean hasFocus, int row, int column) {
//                    Component c = super.getTableCellRendererComponent(table, value, 
//                            isSelected, hasFocus, row, column);
//                    
//                    // Resaltar celdas con fórmulas
//                    if (!hoja.getCeldas()[row][column].getFormula().isEmpty()) {
//                        c.setBackground(new Color(220, 240, 255));
//                    } else {
//                        c.setBackground(Color.WHITE);
//                    }
//                    
//                    return c;
//                }
//            });

            if (tabbedPane == null) return;
    
    tabbedPane.removeAll();
    
    for (int i = 0; i < modelo.getHojas().tamaño(); i++) {
        Hoja hoja = (Hoja) modelo.getHojas().obtener(i);
        
        HojaTableModel model = new HojaTableModel(hoja);
        JTable tablaHoja = new JTable(model);
        
        // Configurar editor para las celdas
        tablaHoja.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean stopCellEditing() {
                try {
                    // Obtener fila y columna
                    int row = tablaHoja.getEditingRow();
                    int col = tablaHoja.getEditingColumn();
                    String value = (String) getCellEditorValue();
                    
                    // Procesar el valor
                    if (value != null && !value.isEmpty()) {
                        controlador.procesarFormula(row, col, value);
                    }
                    
                    return super.stopCellEditing();
                } catch (Exception e) {
                    mostrarError("Error al procesar la celda");
                    return false;
                }
            }
        });
            
            // Listener para selección de celdas
            tablaHoja.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int row = tablaHoja.getSelectedRow();
                    int col = tablaHoja.getSelectedColumn();
                    if (row >= 0 && col >= 0) {
                        formulaField.setText(hoja.getCeldas()[row][col].getFormula());
                    }
                }
            });
            
            JScrollPane scrollPane = new JScrollPane(tablaHoja);
            tabbedPane.addTab(hoja.getNombre(), scrollPane);
            
            if (hoja == modelo.getHojaActual()) {
                this.tabla = tablaHoja;
                tabbedPane.setSelectedIndex(i);
            }
        }
    }
    
    private class HojaTableModel extends AbstractTableModel {
        private Hoja hoja;
        
        public HojaTableModel(Hoja hoja) {
            this.hoja = hoja;
        }
        
        @Override 
        public int getRowCount() { 
            return Hoja.FILAS; 
        }
        
        @Override 
        public int getColumnCount() { 
            return Hoja.COLUMNAS; 
        }
        
        @Override
        public String getColumnName(int col) {
            return String.valueOf((char)('A' + col));
        }
        
        @Override
        public Object getValueAt(int row, int col) {
            // Obtener el valor de la celda correspondiente
            return hoja.getCeldas()[row][col].getValor();
        }
        
        @Override
        public boolean isCellEditable(int row, int col) { 
            return true; 
        }
        
        @Override
        public void setValueAt(Object value, int row, int col) {
            // Actualizar el valor de la celda
            hoja.getCeldas()[row][col].setValor(value != null ? value.toString() : "");
            fireTableCellUpdated(row, col);
        }
    }
}
