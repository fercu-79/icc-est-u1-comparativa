import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class App {
    public static void main(String[] args) throws Exception {

        Generado g = new Generado();
        Benchmark b = new Benchmark();

        List<Integer> list = g.generate(100000);
        System.out.println("Lista generada: " + list);

        Results benGenList = b.medir(
            () -> g.generate(100000),
            "Generar listado", "100000");
        System.out.println(benGenList.toString());
        // long nanoTime = System.nanoTime();

        // long currentTimeMillis = System.currentTimeMillis();
        
        // System.out.println("Nano Time: " + nanoTime);
        // System.out.println("Current Time (ms): " + currentTimeMillis);

        //Runnable funcion = () -> {
        //    System.out.println("Ejecutando tarea 1");
        //    System.out.println("Ejecutando tarea 2");
        //};

        // Forma de hacerlo con Runnable, suponiendo que tengamos la clase metodo creada

        // List<Integer> list = new ArrayList<>();
        // MetodosOrd m0 = new MetodosOrd(list);
        // Ejecuta la acción de ordenarBurbuja en un hilo separado
        // m0.ordenarBurbuja(list);
        // Guarda la acción de ordenarBurbuja en un Runnable
        // Runnable burbuja orden = () -> m0.ordenarBurbuja(list);


        // Callable<T> x -> es otra forma de hacerlo, pero no es necesario para este caso,
        // ya que no necesitamos retornar un valor.
        // System.out.println("Ejecución de la función Runnable");
        // funcion.run();
    }
}
