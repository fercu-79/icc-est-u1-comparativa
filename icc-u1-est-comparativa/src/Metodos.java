import java.util.Arrays;
import java.util.Comparator;

public class Metodos {

    // V1: Método básico (Inserción)
    public void ordenarInsercion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int clave = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > clave) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = clave;
        }
    }

    // V2: Método investigado (Quick Sort)
    public void ordenarQuickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            int pivoteIndex = particionar(arr, inicio, fin);
            quickSort(arr, inicio, pivoteIndex - 1);
            quickSort(arr, pivoteIndex + 1, fin);
        }
    }

    private int particionar(int[] arr, int inicio, int fin) {
        int pivote = arr[fin];
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (arr[j] <= pivote) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[fin];
        arr[fin] = temp;
        return i + 1;
    }

    // V3: Arrays.sort nativo para int[]
    public void ordenarArraysSort(int[] arr) {
        Arrays.sort(arr);
    }

    // V4 (Opción A): Arrays.sort con Comparator usando lambda
    public void ordenarComparatorLambda(int[] arr) {
        Integer[] temp = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }

        Comparator<Integer> comp = (a, b) -> Integer.compare(a, b);
        Arrays.sort(temp, comp);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp[i];
        }
    }
    // V4 (Opción B): Arrays.sort con constructor de comparadores
    public void ordenarComparatorConstructor(int[] arr) {
        Integer[] temp = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }

        Comparator<Integer> comp = Comparator.comparingInt(Integer::intValue);
        Arrays.sort(temp, comp);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp[i];
        }
    }
}