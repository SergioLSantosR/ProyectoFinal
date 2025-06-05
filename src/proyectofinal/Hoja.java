package proyectofinal;

import java.util.regex.*;

public class Hoja {

    /* Esta clase representa una hoja de cálculo.
       Cada hoja tiene un nombre y una matriz de celdas que puede 
       almacenar valores o fórmulas. */

    private String nombre;
    private Celda[][] celdas;
    //Se define el tamaño máximo de la hoja: 100 filas y 26 columnas (de la A a la Z).
    public static final int FILAS = 100;
    public static final int COLUMNAS = 26;

    /* Constructor de la hoja. Se asigna el nombre y se inicializa 
    la matriz de celdas vacías. */
    public Hoja(String nombre) {
        this.nombre = nombre;
        this.celdas = new Celda[FILAS][COLUMNAS];
        inicializarCeldas();
    }

    // Este método llena toda la matriz con objetos Celda nuevos y vacíos. 
    private void inicializarCeldas() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                celdas[i][j] = new Celda();
            }
        }
    }


/* Este método se encarga de evaluar una fórmula que el usuario haya escrito 
en una celda. Puede manejar funciones como suma o multiplicación, o una operación 
simple entre dos celdas. */
    
    public double evaluarFormula(String formula) {
    try {
        formula = formula.replaceAll("\\s+", "").substring(1);
        
        // Verificar si es una función (suma, resta, etc.)
        if (formula.matches("(suma|resta|multi|div)\\([A-Za-z]+\\d+:[A-Za-z]+\\d+\\)")) {
            return evaluarFuncion(formula);
        }
        // Verificar si es una operación básica (A1+B2)
        else if (formula.matches("([A-Za-z]+\\d+)([+\\-*/])([A-Za-z]+\\d+)")) {
            return evaluarOperacionBasica(formula);
        }
    } catch (Exception e) {
        throw new RuntimeException("Formato de fórmula no válido");
    }
    return 0;
}
    
    //Este método evalúa fórmulas simples como A1+B2, A1*B2, etc.
    private double evaluarOperacionBasica(String formula) {
        Pattern pattern = Pattern.compile("([A-Za-z]+\\d+)([+\\-*/])([A-Za-z]+\\d+)");
        Matcher matcher = pattern.matcher(formula);
        
        if (matcher.find()) {
            int[] coord1 = convertirReferencia(matcher.group(1));
            int[] coord2 = convertirReferencia(matcher.group(3));
            String operador = matcher.group(2);
            
            double num1 = obtenerValorNumerico(coord1);
            double num2 = obtenerValorNumerico(coord2);
            
            switch (operador) {
                case "+": return num1 + num2;
                case "-": return num1 - num2;
                case "*": return num1 * num2;
                case "/": return num2 != 0 ? num1 / num2 : 0;
            }
        }
        return 0;
    }

    /* Este método evalúa funciones como suma(A1:B2), resta(A1:B2), etc. 
    dentro de un rango de celdas. */
    
    private double evaluarFuncion(String formula) {
        Pattern pattern = Pattern.compile("(suma|resta|multi|div)\\(([A-Za-z]+\\d+):([A-Za-z]+\\d+)\\)");
        Matcher matcher = pattern.matcher(formula);
        
        if (matcher.find()) {
            String funcion = matcher.group(1);
            int[] coordInicio = convertirReferencia(matcher.group(2));
            int[] coordFin = convertirReferencia(matcher.group(3));
            
            double resultado = 0;
            boolean primerValor = true;
            
            for (int i = coordInicio[0]; i <= coordFin[0]; i++) {
                for (int j = coordInicio[1]; j <= coordFin[1]; j++) {
                    double valor = obtenerValorNumerico(new int[]{i, j});
                    
                    switch (funcion) {
                        case "suma":
                            resultado += valor;
                            break;
                        case "resta":
                            if (primerValor) {
                                resultado = valor;
                                primerValor = false;
                            } else {
                                resultado -= valor;
                            }
                            break;
                        case "multi":
                            if (primerValor) {
                                resultado = valor;
                                primerValor = false;
                            } else {
                                resultado *= valor;
                            }
                            break;
                        case "div":
                            if (primerValor) {
                                resultado = valor;
                                primerValor = false;
                            } else if (valor != 0) {
                                resultado /= valor;
                            }
                            break;
                    }
                }
            }
            return resultado;
        }
        return 0;
    }

        /* Este método intenta convertir el contenido de una celda a número.
       Si no es posible (por ejemplo, está vacía o es texto), devuelve 0. */
    
    private double obtenerValorNumerico(int[] coordenadas) {
        try {
            return Double.parseDouble(celdas[coordenadas[0]][coordenadas[1]].getValor());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /* Este método convierte una referencia tipo "A1", "C5", etc. a coordenadas 
    numéricas de la matriz. Devuelve un arreglo con la fila y la columna correspondientes.*/
    
    public static int[] convertirReferencia(String referencia) {
        referencia = referencia.toUpperCase();
        String letras = referencia.replaceAll("[^A-Z]", "");
        String numeros = referencia.replaceAll("[^0-9]", "");
        
        int columna = 0;
        for (int i = 0; i < letras.length(); i++) {
            columna = columna * 26 + (letras.charAt(i) - 'A' + 1);
        }
        
        return new int[]{Integer.parseInt(numeros) - 1, columna - 1};
    }

    // Devuelve el nombre de la hoja.
    public String getNombre() { return nombre; }

    //Devuelve la matriz completa de celdas de la hoja.
    public Celda[][] getCeldas() { return celdas; }

}

