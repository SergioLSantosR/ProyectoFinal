package proyectofinal;

public class Controlador {
    
    /* Esta clase se encarga de conectar el modelo con la vista.
       Es decir, controla lo que pasa cuando el usuario interactúa 
       con la hoja. */
    
    private Modelo modelo;
    private Vista vista;

    /* Constructor del controlador.
       Aquí se reciben el modelo y la vista ya creados, y se almacenana 
       para su uso. */
    
    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    /* Este método procesa lo que el usuario escribe en una celda.
       Si lo que se escribe es una fórmula (empieza con "="), se 
       evalúa y se guarda el resultado. Si no, simplemente se guarda 
       el texto como está. Luego de eso, se actualiza la tabla en la vista. */
    
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

    /* Este método permite crear una nueva hoja de cálculo con el nombre que 
    el usuario indique. Luego de crearla, se actualiza la vista para que muestre 
    la nueva hoja. */
    
    
    public void crearNuevaHoja(String nombre) {
        modelo.crearNuevaHoja(nombre);
        vista.actualizarVista();
    }

    //Este método muestra en pantalla el contenido de la tabla hash
    
    public void mostrarTablaHash() {
        vista.mostrarDialogoTablaHash();
    }
}
