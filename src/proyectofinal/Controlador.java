package proyectofinal;

public class Controlador {
    private Modelo modelo;
    private Vista vista;
    
    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    
//    public void procesarFormula(int fila, int columna, String formula) {
//        try {
//            if (formula.startsWith("=")) {
//                double resultado = modelo.getHojaActual().evaluarFormula(formula);
//                modelo.getHojaActual().getCeldas()[fila][columna].setValor(String.valueOf(resultado));
//                modelo.getHojaActual().getCeldas()[fila][columna].setFormula(formula);
//            } else {
//                modelo.getHojaActual().getCeldas()[fila][columna].setValor(formula);
//                modelo.getHojaActual().getCeldas()[fila][columna].setFormula("");
//            }
//            vista.actualizarTabla();
//        } catch (Exception e) {
//            vista.mostrarError("Error al procesar fórmula");
//        }
//    }
    
    public void procesarFormula(int fila, int columna, String formula) {
    try {
        if (formula.startsWith("=")) {
            double resultado = modelo.getHojaActual().evaluarFormula(formula);
            modelo.getHojaActual().getCeldas()[fila][columna].setValor(String.valueOf(resultado));
            modelo.getHojaActual().getCeldas()[fila][columna].setFormula(formula);
        } else {
            modelo.getHojaActual().getCeldas()[fila][columna].setValor(formula);
            modelo.getHojaActual().getCeldas()[fila][columna].setFormula("");
        }
        vista.actualizarTabla();
    } catch (Exception e) {
        vista.mostrarError("Error en la fórmula: " + e.getMessage());
    }
}
    
    public void crearNuevaHoja(String nombre) {
        modelo.crearNuevaHoja(nombre);
        vista.actualizarVista();
    }
    
    public void mostrarTablaHash() {
        vista.mostrarDialogoTablaHash();
    }
}
