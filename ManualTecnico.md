# Manual Técnico

## Hoja Electrónica - Proyecto Final Java

## Descripción Técnica
Este proyecto consiste en una aplicación Java con interfaz gráfica construida con **Swing**, que simula una hoja electrónica con capacidad de manejar múltiples hojas de cálculo. Utiliza una **lista enlazada personalizada**, evaluación de fórmulas y un modelo de celdas propio. La arquitectura del proyecto está contenida en el paquete **proyectofinal**.

## Requisitos Técnicos

### Software Requerido
- **Java Development Kit (JDK)** versión 8 o superior  
- IDE recomendado: **NetBeans**, **IntelliJ IDEA** o **Eclipse**  
- Sistema operativo compatible con Java: Windows, Linux o macOS  

## Estructura del Proyecto

### Paquete: `proyectofinal`

| Archivo/Clase              | Descripción                                                                 |
|---------------------------|-----------------------------------------------------------------------------|
| `ProyectoFinal.java`      | Clase principal que contiene el método `main` para iniciar la aplicación.   |
| `Vista.java`              | Administra la interfaz gráfica del usuario mediante `JFrame` y `JTabbedPane`. |
| `Controlador.java`        | Conecta la vista con la lógica de negocio. Maneja eventos, creación de hojas y fórmulas. |
| `Hoja.java`               | Representa una hoja individual con su propio modelo de datos.               |
| `Celda.java`              | Modelo de celda que almacena valores y fórmulas.                            |
| `Modelo.java`             | Modelo personalizado para la `JTable`, basado en `AbstractTableModel`.     |
| `MiListaEnlazada.java`    | Estructura de datos para manejar dinámicamente las hojas como nodos enlazados. |

## Ejecución del Proyecto

### Desde un IDE como NetBeans
1. Abrir el proyecto desde el IDE.
2. Verificar que el archivo `ProyectoFinal.java` está marcado como clase principal.
3. Ejecutar el proyecto (tecla F6 o botón “Run”).
4. Se abrirá la ventana de interfaz gráfica con una hoja activa por defecto (`Hoja1`).

## Arquitectura del Proyecto

### `ProyectoFinal.java`
- Es el punto de entrada.
- Llama a la clase `Vista` para iniciar la GUI.
- Crea instancias del `Controlador`.

### `Vista.java`
- Hereda de `JFrame`.
- Implementa la interfaz con pestañas (`JTabbedPane`) y una barra de fórmulas (`JTextField`).
- Permite crear nuevas hojas desde un botón.
- Se comunica con `Controlador` para ejecutar acciones.

### `Controlador.java`
- Controlador central del sistema (patrón MVC).
- Procesa eventos desde la `Vista` y gestiona la lógica de evaluación.
- Asigna fórmulas, actualiza modelos, y añade nuevas hojas.

### `Hoja.java`
- Clase que representa una hoja individual.
- Internamente contiene una instancia del modelo (`Modelo`) y tabla (`JTable`).
- Se usa para generar dinámicamente hojas nuevas.

### `Modelo.java`
- Extiende `AbstractTableModel`.
- Personaliza el comportamiento de la tabla.
- Se comunica con objetos `Celda` para extraer y mostrar datos.

### `Celda.java`
- Contiene el valor o fórmula de una celda.
- Tiene métodos para definir, evaluar o mostrar contenido.
- Permite trabajar con texto directo o expresiones como `=4+2`.

### `MiListaEnlazada.java`
- Implementa una lista enlazada para almacenar dinámicamente todas las hojas.
- Cada nodo contiene el nombre de la hoja y su tabla correspondiente.
- Permite recorrer, agregar o buscar hojas.

## Funcionamiento Interno

### Evaluación de Fórmulas
1. El usuario escribe una fórmula en el campo de texto (por ejemplo, `=3+2`).
2. `Controlador` la recibe y analiza.
3. Se pasa a `Celda` para evaluación con métodos auxiliares.
4. El resultado se almacena y actualiza en el modelo (`Modelo`) y en la interfaz (`Vista`).

### Gestión de Hojas
- Cada hoja es un nodo de `MiListaEnlazada`.
- Al agregar una hoja, `Controlador` llama a `Vista` para generar una nueva pestaña.
- Se añade una nueva instancia de `Hoja` con su modelo correspondiente.

### Manejo de Celdas
- Cada celda es un objeto `Celda`, con atributos:
  - `String valor`
  - `String formula`
- Puede contener texto plano o una expresión aritmética válida.
- El resultado se muestra automáticamente si se reconoce una fórmula.

## Validaciones y Control de Errores

| Situación                               | Comportamiento del sistema                            |
|----------------------------------------|--------------------------------------------------------|
| Fórmulas mal escritas (`=5++2`)        | Se evita el cálculo, se muestra un mensaje de error.  |
| Celdas vacías o mal direccionadas      | Se muestran como celdas vacías sin romper el programa.|
| Nombres duplicados de hoja             | Se evita crear hojas repetidas.                       |
| Escritura fuera de contexto            | No se permite sin selección activa de celda.          |

## Recomendaciones Técnicas

- Mantener separación entre `Modelo`, `Vista` y `Controlador` para facilitar mantenimiento.
- Para extender el proyecto se sugiere:
  - Agregar soporte para referencias entre celdas (`=A1+B1`).
  - Incluir funciones básicas (`SUM()`, `RES()`, etc).
  - Exportar hojas a archivos `.csv` o `.xlsx`.
- Agregar validaciones adicionales para mejorar robustez.

## Créditos y Mantenimiento

- **Desarrolladores principales:** Steve Alvizures y Sergio Santos  
- Proyecto Final de Programación 3.
- Se recomienda actualizar este manual si se realizan modificaciones estructurales importantes.


