package proyectofinal;

import javax.swing.SwingUtilities;

public class ProyectoFinal {

    /* Este es el punto de entrada principal del programa. 
       Aquí se inicializa todo el sistema (modelo, vista y controlador). */
    
    public static void main(String[] args) {

        /* Usamos SwingUtilities para asegurarnos de que toda la interfaz gráfica 
           se cargue correctamente.*/
        
        SwingUtilities.invokeLater(() -> {

            //Se crea una instancia del modelo, que maneja los datos
            Modelo modelo = new Modelo();

            /* Se crea la vista, que es la interfaz gráfica del usuario.
               Por ahora el controlador se pasa como null porque todavía 
               no lo hemos creado. */
           
            Vista vista = new Vista(modelo, null);

            /* Se crea el controlador, que conecta la vista con el modelo
            y gestiona la lógica. */
            
            Controlador controlador = new Controlador(modelo, vista);

            // Ahora que ya tenemos el controlador, se lo asignamos a la vista.
           
            vista.setControlador(controlador);

            //Finalmente, mostramos la ventana. 
            vista.setVisible(true);
        });
    }
}
