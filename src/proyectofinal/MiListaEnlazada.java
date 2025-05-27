package proyectofinal;


public class MiListaEnlazada {
    private Nodo cabeza;
    private int tamaño;
    
    private class Nodo {
        Object dato;
        Nodo siguiente;
        
        Nodo(Object dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }
    
    public MiListaEnlazada() {
        cabeza = null;
        tamaño = 0;
    }
    
    public void agregar(Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
    }
    
    public Object obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        
        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }
    
    public int tamaño() {
        return tamaño;
    }
    
    public boolean contiene(Object dato) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
}