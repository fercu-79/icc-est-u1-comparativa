public class Benchmark {
    
    public static long medir(){
        // timepo actual
        long startTime = System.nanoTime();
        // accion
        
        // tiempo final
        long endTime = System.nanoTime();
        // retornar el tiempo de duración
        return endTime - startTime;
    }
}
