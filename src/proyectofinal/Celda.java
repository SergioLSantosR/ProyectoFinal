package proyectofinal;


public class Celda {
    private String valor;
    private String formula;
    
    public Celda() {
        this.valor = "";
        this.formula = "";
    }
    
    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }
    
    public String getFormula() { return formula; }
    public void setFormula(String formula) { this.formula = formula; }
}