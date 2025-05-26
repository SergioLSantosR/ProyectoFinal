package proyectofinal;


import javax.swing.SwingUtilities;

public class ProyectoFinal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Modelo modelo = new Modelo();
            Vista vista = new Vista(modelo, null);
            Controlador controlador = new Controlador(modelo, vista);
            vista.setControlador(controlador);
            vista.setVisible(true);
        });
    }
}