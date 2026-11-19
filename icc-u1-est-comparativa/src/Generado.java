    public class Generado {


    // genera un listado de tamaño size con valores aleatorios entre 0 y 100
    public static int[] generate(int size){
        int [] list = new int[size];
        for(int i = 0; i< size; i++){
            list[i] = (int) (Math.random()*size);
        }
        return list;
    }
}
