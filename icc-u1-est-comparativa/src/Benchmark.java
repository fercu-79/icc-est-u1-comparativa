public class Benchmark {
    
    public static Results medir(Runnable accion, String nombre, String sample) {
        // timepo actual
        long timepoActual = System.nanoTime();
        // accion
        accion.run();
        // tiempo final
        long timepoFinal = System.nanoTime();
        // retornar el tiempo de duración

        Results result = new Results(nombre, timepoFinal - timepoActual, sample);
        return result;
    }
}
