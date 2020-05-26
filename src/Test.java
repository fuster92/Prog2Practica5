/*
 * Main.java
 *
 * 09/05/2020
 * Javier Fuster Trallero
 * Versión 1.0 Inicial
 *
 */

import java.util.Random;

/**
 * Clase de Test
 */
public class Test {
    /*
        Strings para indicar a la terminal un cambio de color
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private static final String TEXTO_MUESTRA_RESULTADO =
            "Suma debilitamiento = "+ ANSI_YELLOW + " %d " + ANSI_RESET +
            "| Suma division = "+ ANSI_YELLOW + "%d" + ANSI_RESET +
            "| Suma = "+ ANSI_YELLOW + "%d" + ANSI_RESET + "\n Suma correcta: %s";
    private static final String TEXTO_MUESTRA_TIEMPO =
            "Numero de intentos : %d\n" +
            "Longitud del vector : %d\n" +
            "Tiempo deb = "+ ANSI_YELLOW + "%dns \n" + ANSI_RESET +
            "Tiempo div = "+ ANSI_YELLOW + "%dns \n" + ANSI_RESET +
            "Tiempo = "+ ANSI_YELLOW + "%dns" + ANSI_RESET;
    private static final String MENSAJE_OVERFLOW = "Stack Overflow";
    private static final String MENSAJE_AYUDA =
            "numeroTest longitudVector maxValorNumero mostrarResultados";

    private static long sumaTiempoDeb = 0;
    private static long sumaTiempoIterativa = 0;
    private static long sumaTiempoFort = 0;

    private static int maxNumTest;
    private static int maxLongitudVector;
    private static int maxValorNumero;
    private static boolean mostrarResultados;

    /**
     * Inicializa un vector del tamaño introducido con valores aleatorios
     */
    public static int[] inicializarVector(int tamanho, int valorMaximo){
        Random generador = new Random();
        int[] vector = new int[tamanho];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = generador.nextInt(valorMaximo);
        }
        return vector;
    }

    /**
     * Método main
     */
    public static void main(String[] args){

        procesarArgs(args);

        int [] vector;
        int numeroTest = 0;

        while (numeroTest < maxNumTest) {
            vector = inicializarVector(maxLongitudVector, maxValorNumero);
            try {
                testMetodosContarImpares(vector, mostrarResultados);
            } catch (StackOverflowError stackOverflowError) {
                System.out.println(MENSAJE_OVERFLOW);
            }
            numeroTest++;
        }
        if (mostrarResultados){
            System.out.println(
                    (String.format(
                            TEXTO_MUESTRA_TIEMPO,
                            maxNumTest,
                            maxLongitudVector,
                            (sumaTiempoDeb / (maxNumTest)),
                            (sumaTiempoFort / (maxNumTest)),
                            (sumaTiempoIterativa / (maxNumTest)))));
        } else {
            System.out.println(
                    (sumaTiempoIterativa / (maxNumTest)) + "," +
                    (sumaTiempoFort / (maxNumTest)) + "," +
                    (sumaTiempoDeb / (maxNumTest)));
        }

    }

    /**
     * Procesamos de manera muy simple los argumentos al programa
     */
    private static void procesarArgs(String[] args) {
        if (args.length == 4) {
            maxNumTest = Integer.parseInt(args[0]);
            maxLongitudVector = Integer.parseInt(args[1]);
            maxValorNumero = Integer.parseInt(args[2]);
            mostrarResultados = args[3].equals("mostrar");

        } else if (args.length == 1) {
            //Imprime la ayuda
            System.out.println(MENSAJE_AYUDA);
            System.exit(0);
        } else {
            //Valores por defecto
            maxNumTest = 1;
            maxLongitudVector = 5000;
            maxValorNumero = 100;
            mostrarResultados  = false;
        }
    }

    /**
     * Test de los métodos de contar impares
     * @param vector Vector sobre el que probar los métodos
     */
    private static void testMetodosContarImpares(int[] vector, boolean mostrarResultados)
    throws StackOverflowError{
        long start;

        int sumaRecursivaDeb;
        int sumaIterativa;
        int sumaRecursivaFort;
        start = System.nanoTime();
        sumaRecursivaDeb = DebilitamientoPostcondicion.sumaImpares(vector);
        sumaTiempoDeb += System.nanoTime() - start;

        start = System.nanoTime();
        sumaIterativa = Iterativo.sumaImpares(vector);
        sumaTiempoIterativa += System.nanoTime() - start;

        start = System.nanoTime();
        sumaRecursivaFort = FortalecimientoPrecondicion.sumaImpares(vector);
        sumaTiempoFort += System.nanoTime() - start;

        //Si se ha pasado el parámetro para mostrar los resultados.
        if (mostrarResultados) {
            String sumaEsCorrecta = ANSI_RED + "no" + ANSI_RESET;
            if (resultadosIguales(sumaRecursivaFort, sumaRecursivaDeb, sumaIterativa)){
                sumaEsCorrecta = ANSI_GREEN + "si" + ANSI_RESET;
            }
            System.out.println(String.format(
                            TEXTO_MUESTRA_RESULTADO,
                            sumaRecursivaDeb,
                            sumaRecursivaFort,
                            sumaIterativa,
                            sumaEsCorrecta));
        }
    }

    /**
     * Devuelve verdadero si los 4 digitos son iguales
     */
    private static boolean resultadosIguales(int a, int b, int c){
        return (a == b) &&
               (b == c);
    }
}
