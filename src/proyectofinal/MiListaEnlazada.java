package proyectofinal;


public class MiListaEnlazada {

    /* Esta clase implementa una lista enlazada simple.
       Puede guardar cualquier tipo de dato como objetos, y permite 
       agregar, buscar y obtener elementos por índice. */

    private Nodo cabeza;
    private int tamaño;

    /* Clase interna que representa cada nodo de la lista.
       Cada nodo guarda un dato y una referencia al siguiente nodo. */
    
    private class Nodo {
        Object dato;
        Nodo siguiente;
        
        Nodo(Object dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    //Constructor de la lista. Al principio está vacía.
    
    public MiListaEnlazada() {
        cabeza = null;
        tamaño = 0;
    }

    // Este método agrega un nuevo elemento al final de la lista.
    public void agregar(Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        //Si la lista está vacía, el nuevo nodo será la cabeza.
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            //Si no, se recorre hasta el último nodo para agregarlo al final.
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
    }

    //Este método devuelve el dato que está en una posición específica de la lista.
   
    public Object obtener(int indice) {
        //Si el índice es inválido, se lanza una excepción.
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        //Se recorre la lista hasta llegar a la posición deseada.
        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    //Este método devuelve cuántos elementos hay actualmente en la lista.
    public int tamaño() {
        return tamaño;
    }

    //Este método revisa si un cierto dato ya existe dentro de la lista. 
   
    public boolean contiene(Object dato) {
        Nodo actual = cabeza;
        //Se recorre la lista nodo por nodo hasta encontrar el dato
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return true;
            }
            actual = actual.siguiente;
        }
        //Si llega al final y no lo encontró, devuelve false.
        return false;
    }
}
