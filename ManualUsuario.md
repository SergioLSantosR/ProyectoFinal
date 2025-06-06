# Manual de Usuario

## Hoja Electrónica

## Descripción General
Este software es una hoja electrónica que permite al usuario trabajar con múltiples hojas de cálculo,
ingresar fórmulas, visualizar resultados en tablas y simular una tabla hash. La interfaz gráfica está 
diseñada con `Java Swing` para facilitar su uso.

---

## Requisitos del Sistema

- **Java JDK 8 o superior** instalado.
- Sistema operativo compatible con Java (Windows, macOS o Linux).
- Entorno de desarrollo recomendado: **NetBeans**, **Eclipse** o **IntelliJ IDEA**.

---

## Inicio del Programa

1. Compile y ejecute el archivo `ProyectoFinal.java`.
2. La ventana principal aparecerá con una hoja predeterminada llamada `Hoja1`.
3. El menú principal contiene las opciones: `Archivo > Nueva Hoja` y `Archivo > Tabla hash`.

| Menú    | Opción        | Descripción                                    |
|---------|---------------|------------------------------------------------|
| Archivo | Nueva Hoja    | Crear una nueva hoja solicitando el nombre.    |
| Archivo | Tabla hash    | Abrir ventana para ingresar datos y generar hash.|

---

## Interfaz de Usuario

### Barra de Fórmulas

- Ubicada en la parte superior.
- Permite ingresar fórmulas o valores que se colocarán en la celda seleccionada.
- El campo se activa al presionar **Enter** y actualiza la celda activa.

### Área de Tablas (Hojas)

- Representa una hoja electrónica en formato de tabla.
- Cada hoja se muestra en una pestaña diferente.
- Las celdas son editables manualmente o mediante la barra de fórmulas.

### Menú Superior

| Opción      | Función                                                      |
|-------------|--------------------------------------------------------------|
| Nueva Hoja  | Crear hoja nueva con nombre único ingresado por el usuario.  |
| Tabla hash  | Abrir ventana para ingresar datos y visualizar estructura.   |.

---

## Funcionalidades Principales

### Crear una Nueva Hoja

1. Hacer clic en `Archivo > Nueva Hoja`.
2. Ingresar un nombre único en el cuadro de texto.
3. Se agregará una nueva pestaña con la hoja creada.

### Editar Celdas

- Selecciona una celda con el mouse.
- Escribe directamente en la celda o usa la barra de fórmulas.
- Presiona **Enter** para aplicar el valor.

### Usar Fórmulas

- Las fórmulas pueden escribirse en formato sencillo.
- Ejemplo: `=3+2` se interpreta como valor 5.
- Las celdas pueden guardar expresiones para cálculo simple.

### Visualizar la Tabla Hash

1. Ir al menú `Archivo > Tabla hash`.
2. Escribir datos en el campo de texto.
3. Presionar `Agregar Dato` para agregarlos a la tabla.
4. Hacer clic en `Generar Hash` para calcular y mostrar:
   - Valor hash de cada dato.
   - Índice de dispersión.
   - Estructura de los buckets.

---

## Ejemplo de Uso

1. Iniciar el programa.
2. Crear una nueva hoja llamada `Gastos2025`.
3. Seleccionar celda A1 y escribir `=500+250`.
4. Presionar Enter y ver el resultado `750` en la celda.
5. Abrir `Tabla hash`, ingresar valores como `nombre`, `correo`, `123`, etc.
6. Presionar `Generar Hash` y visualizar el índice de dispersión correspondiente.

---

## Consejos de Uso

- No ingresar fórmulas vacías o inválidas, pueden causar errores al procesarse.
- Evitar repetir nombres de hoja para evitar confusión.
- Verifique que las celdas están seleccionadas antes de usar la barra de fórmulas.

---

## Manejo de Errores

- Si se deja vacío un nombre de hoja, el sistema no creará la hoja.
- Si se ingresa una fórmula con error de sintaxis, se mostrará un mensaje de error.

---

## Cierre del Programa

- Para salir del sistema, cerrar la ventana principal desde el botón `X` (cerrar ventana) o usar el menú del sistema operativo.

---

## Créditos

- Proyecto desarrollado como proyecto final del curso de Programación 3.
- Autores: Steve Alvizures y Sergio Santos.
- Universidad Mariano Gálvez de Guatemala.
