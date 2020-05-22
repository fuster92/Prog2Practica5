/*
 * Main.java
 *
 * 09/05/2020
 * Javier Fuster Trallero
 * Versión 1.0 Inicial
 *
 */

import java.util.Arrays;
import java.util.Random;

/**
 * Clase de Main
 */
public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static final String TEXTO_MUESTRA_RESULTADO =
            "Suma debilitamiento = "+ ANSI_YELLOW + " %d " + ANSI_RESET +
            "| Suma fortalecimiento = "+ ANSI_YELLOW + "%d" + ANSI_RESET +
            "| Suma division = "+ ANSI_YELLOW + "%d" + ANSI_RESET +
            "| Suma = "+ ANSI_YELLOW + "%d" + ANSI_RESET + "\n Suma correcta: %s";
    private static final String TEXTO_MUESTRA_TIEMPO =
            "Numero de intentos : %d\n" +
            "Longitud del vector : %d\n" +
            "Tiempo deb = "+ ANSI_YELLOW + "%dns \n" + ANSI_RESET +
            "Tiempo fort = "+ ANSI_YELLOW + "%dns \n" + ANSI_RESET +
            "Tiempo div = "+ ANSI_YELLOW + "%dns \n" + ANSI_RESET +
            "Tiempo = "+ ANSI_YELLOW + "%dns" + ANSI_RESET;
    private static final String MENSAJE_OVERFLOW = "Stack Overflow";

    private static long sumaTiempoFort = 0;
    private static long sumaTiempoDeb = 0;
    private static long sumaTiempoSec = 0;
    private static int testRealizados = 0;
    private static long sumaTiempoDiv = 0;

    private static int maxNumTest;
    private static int maxLongitudVector;
    private static int maxValorNumero;

    /**
     * Pre: vector.length > 0
     * Post: sumaImpares (vector) =
     *      SUMA alfa EN [0, vector.length - 1] AND vector[alfa]%2=1.vector[alfa]
     */
    public static int sumaImpares(int[] vector){
        return 0;
    }

    /**
     * Pre: vector.length > 0 AND indice < vector.length AND indice >= 0
     * Post: sumaImpares (vector, indice) =
     *      SUMA alfa EN [indice, vector.length - 1] AND vector[alfa]%2=1.vector[alfa]
     */
    public static int sumaImparesDebPost(int[] vector, int indice){
        if ( vector.length > 0 && indice < vector.length){
            if (vector[indice] % 2 != 0){
                //Si es impar
                return vector[indice] + sumaImparesDebPost(vector, indice + 1);
            } else return sumaImparesDebPost(vector, indice + 1);
        } else return 0; // Si hemos llegado al fin del vector
    }

    /**
     * Pre: vector.length > 0 AND indice < vector.length AND indice >= 0 AND
     *      sumaParcial =
     *      SUMA (alfa EN [0, indice] AND vector[alfa] % 2 = 1).vector[alfa]
     * Post: sumaImpares (vector, indice, sumaParcial) =
     *      SUMA alfa EN [0, vector.length - 1] AND vector[alfa]%2=1.vector[alfa]
     */
    public static int sumaImparesFortPre(int[] vector, int indice, int sumaParcial){
        if (vector.length > 0 && indice < vector.length){
            if (vector[indice] % 2 != 0){
                //Si es impar
                return sumaImparesFortPre(vector, indice + 1, sumaParcial + vector[indice]);
            } else return sumaImparesFortPre(vector, indice + 1, sumaParcial);
        }
        return sumaParcial;
    }

    /**
     * Pre: vector.length > 0 AND desde >= 0 AND hasta < vector.length
     * Post: sumaImparesMitad (vector, desde, hasta) =
     *       SUMA alfa EN [desde, hasta] AND vector[alfa] % 2 = 1.vector[alfa]
     */
    public static int sumaImparesMitades(int[] vector,
                                         int desde,
                                         int hasta){
        if (desde == hasta){
            return (vector[desde] % 2 == 1) ? vector[desde] : 0;
        } if (hasta - desde == 1){
            return ((vector[desde] % 2 == 1) ? vector[desde] : 0 )+
                    ((vector[hasta] % 2 == 1) ? vector[hasta] : 0);
        } else {

            return sumaImparesMitades(vector, desde, desde + (hasta - desde ) / 2) +
                    sumaImparesMitades(vector, desde + ((hasta - desde) / 2) + 1 , hasta);
        }
    }

    /**
     * Suma los impares en un bucle
     */
    public static int sumaImparesBucle(int[] vector){
        int suma = 0;
        for (int numero : vector) {
            suma += (numero % 2 != 0) ? numero : 0;
        }
        return suma;
    }

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

        if (args.length == 3 ){
            maxNumTest = Integer.parseInt(args[0]);
            maxLongitudVector = Integer.parseInt(args[1]);
            maxValorNumero = Integer.parseInt(args[2]);
        } else if (args.length == 1){
            //Imprime la ayuda
            System.out.println("numeroTest longitudVector maxValorNumero");
            System.exit(0);
        } else {
            //Valores por defecto
            maxNumTest = 100;
            maxLongitudVector = 16000;
            maxValorNumero = 100;
        }
        int [] vector;
        int numeroTest = 0;
        while (numeroTest < maxNumTest) {
            vector = inicializarVector(maxLongitudVector, maxValorNumero);
            try {
                testMetodosCuentaImpares(vector, true);
            } catch (StackOverflowError stackOverflowError) {
                System.out.println(MENSAJE_OVERFLOW);
            }
            numeroTest++;
        }
        System.out.println(
                (String.format(
                        TEXTO_MUESTRA_TIEMPO,
                        maxNumTest,
                        maxLongitudVector,
                        (sumaTiempoDeb / (maxNumTest)),
                        (sumaTiempoFort / (maxNumTest)),
                        (sumaTiempoDiv / (maxNumTest)),
                        (sumaTiempoSec / (maxNumTest)))));
    }

    /**
     * Test de los métodos cuenta impares
     * @param vector
     */
    private static void testMetodosCuentaImpares(int[] vector, boolean mostrarResultados)
    throws StackOverflowError{
        long start;

        int sumaRecursivaDeb;
        int sumaRecursivaFort;
        int sumaSecuencial;
        int sumaDivision;

        testRealizados++;
        start = System.nanoTime();
        sumaRecursivaDeb = sumaImparesDebPost(vector, 0);
        sumaTiempoDeb += System.nanoTime() - start;

        start = System.nanoTime();
        sumaRecursivaFort = sumaImparesFortPre(vector, 0, 0);
        sumaTiempoFort += System.nanoTime() - start;

        start = System.nanoTime();
        sumaSecuencial = sumaImparesBucle(vector);
        sumaTiempoSec += System.nanoTime() - start;

        start = System.nanoTime();
        sumaDivision = sumaImparesMitades(vector, 0, vector.length - 1);
        sumaTiempoDiv += System.nanoTime() - start;

        if (mostrarResultados) {
            String mensaje = ANSI_RED + "no" + ANSI_RESET;
            if (resultadosIguales(sumaDivision, sumaRecursivaDeb, sumaRecursivaFort, sumaSecuencial)){
                mensaje = ANSI_GREEN + "si" + ANSI_RESET;
            }
            System.out.println(String.format(
                            TEXTO_MUESTRA_RESULTADO,
                            sumaRecursivaDeb,
                            sumaRecursivaFort,
                            sumaDivision,
                            sumaSecuencial,
                            mensaje));
        }
    }

    /**
     * Devuelve verdadero si los 4 digitos son iguales
     */
    private static boolean resultadosIguales(int a, int b, int c, int d){
        return (a == b) &&
               (b == c) &&
               (c == d);
    }
}
