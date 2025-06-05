package proyectofinal;

/*
Esta clase representa una celda de la hoja de cálculo.
en cada celda se puede guardar un valor (lo que el usuario ve)
 y también una fórmula (lo que el usuario escribe para que se calcule algo).
 */

public class Celda {
    // Aquí se guarda el valor final de la celda, puede ser un número o texto
    private String valor;
    // Aquí se guarda la fórmula escrita por el usuario
    private String formula;

    /*
    Constructor de la celda.
    Cuando se crea una celda nueva, se deja vacía, sin valor ni fórmula.
    */
    public Celda() {
        this.valor = "";
        this.formula = "";
    }

    //Devuelve el valor que tiene actualmente la celda.
    public String getValor() { return valor; }
    
    //Permite asignarle un nuevo valor a la celda.
    public void setValor(String valor) { this.valor = valor; }

    //Devuelve la fórmula que se escribió en la celda.
    public String getFormula() { return formula; }

    //Permite escribir o cambiar la fórmula de la celda.
    public void setFormula(String formula) { this.formula = formula; }
}
