package proyectofinal;

public class Modelo {
    private MiListaEnlazada hojas;
    private Hoja hojaActual;
    
    public Modelo() {
        hojas = new MiListaEnlazada();
        crearNuevaHoja("Hoja1");
    }
    
    public void crearNuevaHoja(String nombre) {
        Hoja nuevaHoja = new Hoja(nombre);
        hojas.agregar(nuevaHoja);
        hojaActual = nuevaHoja;
    }
    
    public Hoja getHojaActual() { return hojaActual; }
    public MiListaEnlazada getHojas() { return hojas; }
    
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