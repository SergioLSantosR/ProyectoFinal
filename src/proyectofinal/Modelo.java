package proyectofinal;

public class Modelo {

    /*Esta clase representa el modelo del proyecto, según el patrón MVC.
       Se encarga de manejar la lógica de las hojas de cálculo y su almacenamiento. */

   
    private MiListaEnlazada hojas; // Lista que guarda todas las hojas creadas
    private Hoja hojaActual; // Hoja que está siendo usada actualmente

    /* Al crear el modelo, se inicializa la lista y se crea una hoja por defecto 
    llamada "Hoja1". */
   
    public Modelo() {
        hojas = new MiListaEnlazada();
        crearNuevaHoja("Hoja1");
    }

    /* Este método permite crear una nueva hoja con el nombre que se indique.
       La nueva hoja se agrega a la lista y se establece como la hoja activa. */
    
    public void crearNuevaHoja(String nombre) {
        Hoja nuevaHoja = new Hoja(nombre);
        hojas.agregar(nuevaHoja);
        hojaActual = nuevaHoja;
    }

    // Devuelve la hoja actual que se está utilizando.
    public Hoja getHojaActual() { return hojaActual; }

    //Devuelve la lista completa de hojas creadas.
    public MiListaEnlazada getHojas() { return hojas; }

    /* Cambia la hoja actual a otra, según el nombre que se reciba.
       Busca dentro de la lista y, si encuentra una hoja con ese nombre, 
       la establece como activa. */
   
    public void setHojaActual(String nombre) {
        for (int i = 0; i < hojas.tamaño(); i++) {
            Hoja hoja = (Hoja) hojas.obtener(i);
            if (hoja.getNombre().equals(nombre)) {
                hojaActual = hoja;
                break;
            }
        }
    }

}

