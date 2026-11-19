import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        Generado g = new Generado();
        Metodos m = new Metodos();

        /// LOS 3 arreglos base
        /// 10000
        int[] list1 = g.generate(10000);
        /// 50000
        int[] list2 = g.generate(50000);
        /// 100000
        int[] list3 = g.generate(100000);

        /// Listas clone
        int[] lista1c1 = list1.clone();
        int[] lista1c2 = list1.clone();
        int[] lista1c3 = list1.clone();
        int[] lista1c4 = list1.clone();

        int[] lista2c1 = list2.clone();
        int[] lista2c2 = list2.clone();
        int[] lista2c3 = list2.clone();
        int[] lista2c4 = list2.clone(); 

        int[] lista3c1 = list3.clone();
        int[] lista3c2 = list3.clone();
        int[] lista3c3 = list3.clone();
        int[] lista3c4 = list3.clone();

        /// LISTADO DE RESULTADOS
        List<Results> resultados = new ArrayList<>();

        // M1: Inserción
        Results resultaM1S1 = Benchmark.medir(
            () -> m.ordenarInsercion(lista1c1), "Insercion", "10000");
        resultados.add(resultaM1S1);

        Results resultaM1S2 = Benchmark.medir(
            () -> m.ordenarInsercion(lista2c1), "Insercion", "50000");
        resultados.add(resultaM1S2);

        Results resultaM1S3 = Benchmark.medir(
            () -> m.ordenarInsercion(lista3c1), "Insercion", "100000");
        resultados.add(resultaM1S3);

        // M2: Quicksort
        Results resultaM2S1 = Benchmark.medir(
            () -> m.ordenarQuickSort(lista1c2), "Quicksort", "10000");
        resultados.add(resultaM2S1);

        Results resultaM2S2 = Benchmark.medir(
            () -> m.ordenarQuickSort(lista2c2), "Quicksort", "50000");
        resultados.add(resultaM2S2);

        Results resultaM2S3 = Benchmark.medir(
            () -> m.ordenarQuickSort(lista3c2), "Quicksort", "100000");
        resultados.add(resultaM2S3);

        // M3: Arrays.sort
        Results resultaM3S1 = Benchmark.medir(
            () -> m.ordenarArraysSort(lista1c3), "Arrays.sort", "10000");
        resultados.add(resultaM3S1);

        Results resultaM3S2 = Benchmark.medir(
            () -> m.ordenarArraysSort(lista2c3), "Arrays.sort", "50000");
        resultados.add(resultaM3S2);

        Results resultaM3S3 = Benchmark.medir(
            () -> m.ordenarArraysSort(lista3c3), "Arrays.sort", "100000");
        resultados.add(resultaM3S3);

        // M4: Arrays.sort con Comparator
        Results resultaM4S1 = Benchmark.medir(
            () -> m.ordenarComparatorLambda(lista1c4), "Comparator", "10000");
        resultados.add(resultaM4S1);

        Results resultaM4S2 = Benchmark.medir(
            () -> m.ordenarComparatorLambda(lista2c4), "Comparator", "50000");
        resultados.add(resultaM4S2);

        Results resultaM4S3 = Benchmark.medir(
            () -> m.ordenarComparatorLambda(lista3c4), "Comparator", "100000");
        resultados.add(resultaM4S3);

        for (Results results : resultados) {
            System.out.println(results);    
        }
    }
}
