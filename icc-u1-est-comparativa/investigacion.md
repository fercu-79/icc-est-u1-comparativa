# Investigación del Algoritmo Implementado (V2): Quick Sort

## 1. Identificación del algoritmo y ubicación en el repositorio

* **Algoritmo elegido:** Quick Sort (Ordenamiento Rápido).
* **Variante implementada:** Quick Sort recursivo *in-place* con el **esquema de partición de Lomuto**, tomando siempre como pivote el último elemento del rango activo (`arr[fin]`).
* **Archivos enlazados en el proyecto:**
  * Implementación del algoritmo: [text](src/Metodos.java)
  * Integración y ejecución en las pruebas de rendimiento: [text](src/App.java)
  * Medición de tiempos: [text](src/Benchmark.java) y [text](src/Results.java)
* **Clases y métodos que participan en V2:**
  * En la clase `Metodos` ([text](src/Metodos.java)):
    * `ordenarQuickSort(int[] arr)`: método público que se invoca desde `App.java`; valida el arreglo y lanza la primera llamada recursiva.
    * `quickSort(int[] arr, int inicio, int fin)`: método privado que divide el arreglo en dos subrangos alrededor del pivote.
    * `particionar(int[] arr, int inicio, int fin)`: método privado que reordena los elementos respecto al pivote y devuelve el índice final donde quedó ubicado.
  * En la clase `App` ([text](src/App.java)): se ejecuta dentro de `Benchmark.medir(...)` sobre copias independientes (`lista1c2`, `lista2c2`, `lista3c2`) de 10.000, 50.000 y 100.000 enteros.

### Qué se tomó de las fuentes y qué se adaptó
* **Base consultada:** Se tomó la estructura clásica de recursión y el esquema de partición de Lomuto descritos en *Introduction to Algorithms* (Cormen et al., 2022) y en el material de *Algorithms* (Sedgewick & Wayne, 2011).
* **Modificaciones realizadas para este proyecto:**
  1. Se tradujo el pseudocódigo (que en Cormen trabaja con arreglos indexados desde 1) a Java con índices desde `0` hasta `arr.length - 1`.
  2. Se separó un método público de entrada (`ordenarQuickSort`) para que desde `App.java` solo haya que pasar el arreglo `int[]`, ocultando los índices `inicio` y `fin` en métodos `private`.
  3. Se agregó una validación inicial (`if (arr == null || arr.length < 2) return;`) para evitar excepciones con arreglos vacíos, nulos o de un solo elemento.
  4. Se integró directamente con el esquema de copias `.clone()` y lambdas de `Benchmark.medir` en `App.java`.

---

## 2. Funcionamiento paso a paso

1. **Inicio (`ordenarQuickSort`):** Recibe el arreglo `arr`. Si es `null` o tiene menos de 2 elementos, termina de inmediato. Caso contrario, llama a `quickSort(arr, 0, arr.length - 1)`.
2. **Parámetros:**
   * `arr`: arreglo de enteros que se modifica directamente en memoria (no se crean arreglos nuevos).
   * `inicio`: límite izquierdo del subarreglo que se está procesando.
   * `fin`: límite derecho del subarreglo actual.
3. **Caso base:** La condición `if (inicio < fin)` controla si todavía hay al menos dos elementos por ordenar. Cuando `inicio >= fin`, el subarreglo tiene 0 o 1 elementos y la llamada retorna sin hacer nada más.
4. **Avance de la recursión:** En cada llamada válida, `particionar` coloca el pivote en su posición definitiva (`pivoteIndex`). A partir de ahí se hacen dos llamadas recursivas sobre los tramos restantes: de `inicio` a `pivoteIndex - 1`, y de `pivoteIndex + 1` a `fin`. Como el pivote queda excluido de ambas llamadas, el tamaño de los subproblemas siempre disminuye y la recursión garantiza llegar al caso base.
5. **Propiedad que se mantiene (invariante):** Al finalizar cada ejecución de `particionar`:
   * Todo elemento entre `inicio` y `pivoteIndex - 1` es menor o igual al pivote (`<= pivote`).
   * El elemento en `pivoteIndex` ya ocupa el lugar exacto que tendrá en el arreglo ordenado.
   * Todo elemento entre `pivoteIndex + 1` y `fin` es estrictamente mayor que el pivote (`> pivote`).

---

## 3. Decisiones concretas de esta versión de Quick Sort

* **Elección del pivote:** Se elige siempre el elemento del extremo derecho del subarreglo (`int pivote = arr[fin]`). Es una decisión directa y sin costo extra de cálculo, aunque sensible a entradas ya ordenadas.
* **Partición (Lomuto):** El índice `i` arranca en `inicio - 1` y delimita hasta dónde llegan los números menores o iguales al pivote. El bucle con `j` recorre desde `inicio` hasta `fin - 1`; cada vez que encuentra un valor `arr[j] <= pivote`, avanza `i` e intercambia `arr[i]` con `arr[j]`. Al salir del bucle, se intercambia el pivote (`arr[fin]`) con `arr[i + 1]` para dejarlo en medio de ambas zonas.
* **Límites de las llamadas:** Las llamadas usan `pivoteIndex - 1` y `pivoteIndex + 1`. Dejar fuera `pivoteIndex` evita trabajo repetido y asegura que no se produzca recursión infinita.
* **Manejo de elementos repetidos:** Al comparar con `<=`, los elementos iguales al pivote se agrupan en la partición izquierda. El algoritmo ordena correctamente aunque haya muchos duplicados, pero si un mismo valor se repite demasiado, las particiones se inclinan hacia la izquierda y pierden balance.

---

## 4. Traza manual sobre `[7, 2, 7, 0, 4, 2]`

Arreglo inicial (índices `0` a `5`): **`[7, 2, 7, 0, 4, 2]`**

1. **Llamada `quickSort(arr, 0, 5)`**
   * `particionar(0, 5)` → `pivote = arr[5] = 2`, `i = -1`:
     * `j = 0`: `7 <= 2` (falso)
     * `j = 1`: `2 <= 2` (verdadero) → `i = 0`, intercambio `arr[0]` y `arr[1]` → `[2, 7, 7, 0, 4, 2]`
     * `j = 2`: `7 <= 2` (falso)
     * `j = 3`: `0 <= 2` (verdadero) → `i = 1`, intercambio `arr[1]` y `arr[3]` → `[2, 0, 7, 7, 4, 2]`
     * `j = 4`: `4 <= 2` (falso)
     * Cierre: intercambio `arr[2]` y `arr[5]` → **`[2, 0, 2, 7, 4, 7]`**. Devuelve `pivoteIndex = 2`.
   * Se divide en izquierda `(0, 1)` y derecha `(3, 5)`.

2. **Rama izquierda: `quickSort(arr, 0, 1)`** sobre `[2, 0]`
   * `particionar(0, 1)` → `pivote = arr[1] = 0`, `i = -1`:
     * `j = 0`: `2 <= 0` (falso)
     * Cierre: intercambio `arr[0]` y `arr[1]` → **`[0, 2, 2, 7, 4, 7]`**. Devuelve `pivoteIndex = 0`.
   * Llamadas hijas: `quickSort(0, -1)` y `quickSort(1, 1)` → ambas cumplen caso base y terminan.

3. **Rama derecha: `quickSort(arr, 3, 5)`** sobre `[7, 4, 7]`
   * `particionar(3, 5)` → `pivote = arr[5] = 7`, `i = 2`:
     * `j = 3`: `7 <= 7` (verdadero) → `i = 3`, intercambio `arr[3]` consigo mismo
     * `j = 4`: `4 <= 7` (verdadero) → `i = 4`, intercambio `arr[4]` consigo mismo
     * Cierre: intercambio `arr[5]` consigo mismo → **`[0, 2, 2, 7, 4, 7]`**. Devuelve `pivoteIndex = 5`.
   * Llamadas hijas: `quickSort(3, 4)` y `quickSort(6, 5)` (caso base).

4. **Sub-rama: `quickSort(arr, 3, 4)`** sobre `[7, 4]`
   * `particionar(3, 4)` → `pivote = arr[4] = 4`, `i = 2`:
     * `j = 3`: `7 <= 4` (falso)
     * Cierre: intercambio `arr[3]` y `arr[4]` → **`[0, 2, 2, 4, 7, 7]`**. Devuelve `pivoteIndex = 3`.
   * Llamadas hijas: `quickSort(3, 2)` y `quickSort(4, 4)` → ambas cumplen caso base.

* **Resultado final:** **`[0, 2, 2, 4, 7, 7]`**

---

## 5. Análisis de complejidad y estabilidad


### Complejidad en tiempo
* **Mejor caso — $\Omega(n \log n)$:** Se da cuando el pivote parte el arreglo casi exactamente por la mitad en cada paso, logrando una profundidad de recursión de $\log_2 n$ y un recorrido lineal de $n$ elementos por nivel.
* **Caso promedio — $\Theta(n \log n)$:** Asumiendo que los valores de entrada están distribuidos aleatoriamente (todas las permutaciones tienen igual probabilidad, que es el caso de los arreglos generados por `Generado`), las particiones suelen quedar razonablemente equilibradas y el costo esperado se mantiene en $\Theta(n \log n)$.
* **Peor caso — $O(n^2)$:** Aparece cuando el pivote elegido es siempre el mayor o el menor del rango actual. En cada llamada el subarreglo se reduce solo en un elemento (`n - 1` por un lado y `0` por el otro), obligando a hacer del orden de $n^2 / 2$ comparaciones.

### Memoria auxiliar y pila recursiva
* **Memoria de datos:** $O(1)$, porque todo el intercambio ocurre sobre el mismo arreglo `arr` usando solo variables primitivas (`pivote`, `i`, `j`, `temp`).
* **Pila de llamadas (stack):** $O(\log n)$ en mejor y promedio caso, pero puede escalar hasta **$O(n)$** en el peor caso si la recursión se degrada en una cadena lineal de llamadas.

### Estabilidad
Esta implementación es **inestable**. Los intercambios en `particionar` saltan posiciones sin preservar el orden previo entre elementos con el mismo valor. En la traza de `[7, 2, 7, 0, 4, 2]`, el `7` que estaba originalmente en el índice `0` se intercambia con el `2` del índice `1` y luego termina desplazado al final, rompiendo el orden relativo respecto al otro `7`.

---

## 6. Caso desfavorable y comportamiento en prueba real

El escenario más desfavorable para este Quick Sort con pivote fijo al final (`arr[fin]`) es recibir un **arreglo ya ordenado** (de menor a mayor o de mayor a menor) o un arreglo lleno de valores iguales.

* **Qué ocurre al probarlo:** Al ejecutar `ordenarQuickSort` sobre un arreglo ya ordenado de tamaño grande (como `50.000` o `100.000` elementos), `particionar` siempre devuelve `fin` como `pivoteIndex`, generando llamadas encadenadas `quickSort(arr, 0, n-2)`, `quickSort(arr, 0, n-3)`, etc. Con el tamaño de pila por defecto de la JVM, la recursión alcanza miles de marcos activos y el programa se detiene lanzando **`java.lang.StackOverflowError`**.
* **Cómo se corrige en caso de necesitar soportar entradas ordenadas:** Basta con elegir un pivote aleatorio (o la mediana entre `inicio`, la mitad y `fin`) e intercambiarlo con `arr[fin]` justo antes de empezar el ciclo en `particionar`, rompiendo así el patrón de particiones desbalanceadas.

---

## 7. Relación con los resultados del benchmarking y fuentes

### Tiempos registrados para Quick Sort (V2) en la ejecución del proyecto

| Tamaño de muestra (`sample`) | Duración (ns) | Duración (s) |
| :--- | :--- | :--- |
| `10000` | 1.419.100 ns | 0.0014191 s |
| `50000` | 4.035.500 ns | 0.0040355 s |
| `100000` | 7.327.000 ns | 0.0073270 s |

### Observaciones a partir de la tabla de benchmarking
1. **Confirmación empírica del crecimiento $O(n \log n)$:** Al pasar de `10.000` a `100.000` elementos (10 veces más datos), el tiempo de Quick Sort solo creció aproximadamente **5.16 veces** (de `0.0014191 s` a `0.007327 s`). Si lo contrastamos con el método básico implementado en V1 (Inserción), que en esa misma corrida pasó de `0.0295734 s` a `2.639324 s` (casi 90 veces más lento por su complejidad $O(n^2)$), Quick Sort resultó alrededor de **360 veces más rápido** con 100.000 datos, comprobando el comportamiento promedio esperado sobre entradas aleatorias.
2. **Comportamiento frente a la referencia de biblioteca (`Arrays.sort`) y el efecto de la JVM:** En las mediciones obtenidas, nuestra implementación de Quick Sort (`0.007327 s` en 100k) incluso marcó tiempos menores que `Arrays.sort` (`0.0121823 s` en 100k) y que la variante con `Comparator` (`0.0704644 s` en 100k). Además de operar directamente sobre primitivos `int[]` sin el costo de *boxing* a `Integer[]` que penaliza a V4, el hecho de que `Arrays.sort` tardara más en `10.000` (`0.0072483 s`) que en `50.000` (`0.0059601 s`) refleja el calentamiento (*warm-up*) del compilador JIT de Java en las primeras invocaciones, mientras que Quick Sort ya se beneficiaba de la optimización activa de la JVM.

### Fuentes técnicas consultadas
1. Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2022). *Introduction to Algorithms* (4th ed., Cap. 7: Quicksort, pp. 182–204). MIT Press. Consultado el 21 de noviembre de 2026.
2. Sedgewick, R., & Wayne, K. (2011). *Algorithms* (4th ed., Sec. 2.3: Quicksort). Addison-Wesley. Disponible en: https://algs4.cs.princeton.edu/23quicksort/. Consultado el 21 de noviembre de 2026.
3. Oracle. (2026). *Class Arrays — Java Platform, Standard Edition API Specification*. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Arrays.html. Consultado el 21 de noviembre de 2026.