import java.util.ArrayList;
import java.util.List;

public class Generado {


    // genera un listado de tamaño size con valores aleatorios entre 0 y 100
    public static List<Integer> generate(int size){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i< size; i++){
            list.add((int) (Math.random() * size));
        }
        return list;
    }
}
