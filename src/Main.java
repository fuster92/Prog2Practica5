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
            "Suma recursiva debilitamiento = "+ ANSI_RED + " %d |" + ANSI_RESET +
            "Suma recursiva fortalecimiento = "+ ANSI_RED + "%d |" + ANSI_RESET +
            "Suma recursiva division = "+ ANSI_RED + "%d |" + ANSI_RESET +
            "Suma secuencial = "+ ANSI_RED + "%d" +
            ANSI_RESET;
    private static final String TEXTO_MUESTRA_TIEMPO =
            "Numero de intentos : %d\n" +
            "Tiempo recursivo deb = "+ ANSI_RED + "%dns \n" + ANSI_RESET +
            "Tiempo recursivo fort = "+ ANSI_RED + "%dns \n" + ANSI_RESET +
            "Tiempo recursivo div = "+ ANSI_RED + "%dns \n" + ANSI_RESET +
            "Tiempo secuencia = "+ ANSI_RED + "%dns" + ANSI_RESET;

    private static long sumaTiempoFort = 0;
    private static long sumaTiempoDeb = 0;
    private static long sumaTiempoSec = 0;
    private static int testRealizados = 0;
    private static long sumaTiempoDiv = 0;

    private static int maxNumTest;
    private static int maxLongitudVector;
    private static int maxValorNumero;

    /**
     * Construye un Main
     */
    public Main() {

    }

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

    public static int sumaImparesMitades(int[] vector){
        int suma = 0;
        int puntoMedio = vector.length / 2;
        //Caso base longitud 1
        if (puntoMedio == 0 || (puntoMedio == 1 && vector.length == 2)){
            if (puntoMedio == 0) {
                //Si ha quedado un trozo de longitud 1
                suma += ((vector[0] % 2 == 0) ? 0:vector[0]);
            }
            if(vector.length == 2){
                //Si ha quedado un trozo de longitud 2
                suma += (vector[1] % 2 == 1)? vector[1]:0;
            }
            return suma;

        }else {
            return sumaImparesMitades(Arrays.copyOfRange(vector, 0, puntoMedio)) +
                    sumaImparesMitades(Arrays.copyOfRange(vector, puntoMedio, vector.length));
        }
    }

    /**
     * Suma los impares en un bucle
     */
    public static int sumaImparesBucle(int[] vector){
        int suma = 0;
        for (int i = 0; i < vector.length; i++){
            suma += (vector[i] % 2 != 0) ? vector[i] : 0;
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
            maxLongitudVector = 1000;
            maxValorNumero = 100;
        }


        int [] vector;
        int numeroTest = 0;
        while (numeroTest < maxNumTest) {
            vector = inicializarVector(maxLongitudVector, maxValorNumero);
            //System.out.println(Arrays.toString(vector));
            testMetodosCuentaImpares(vector, true);
            numeroTest++;
        }
        System.out.println(
                (String.format(
                        TEXTO_MUESTRA_TIEMPO,
                        maxNumTest,
                        (sumaTiempoDeb / (maxNumTest)),
                        (sumaTiempoFort / (maxNumTest)),
                        (sumaTiempoDiv / (maxNumTest)),
                        (sumaTiempoSec / (maxNumTest)))));
    }

    /**
     * Test de los métodos cuenta impares
     * @param vector
     */
    private static void testMetodosCuentaImpares(int[] vector, boolean mostrarResultados) {
        long start;

        int sumaRecursivaDeb;
        int sumaRecursivaFort;
        int sumaSecuencial;
        int sumaDivision;

        testRealizados++;
        start = System.nanoTime();
        sumaRecursivaDeb = sumaImparesDebPost(vector, 0);
        sumaTiempoDeb = System.nanoTime() - start;

        start = System.nanoTime();
        sumaRecursivaFort = sumaImparesFortPre(vector, 0, 0);
        sumaTiempoFort = System.nanoTime() - start;

        start = System.nanoTime();
        sumaSecuencial = sumaImparesBucle(vector);
        sumaTiempoSec = System.nanoTime() - start;

        start = System.nanoTime();
        sumaDivision = sumaImparesMitades(vector);
        sumaTiempoDiv += System.nanoTime() - start;
        if (mostrarResultados) {
            System.out.println(String.format(
                            TEXTO_MUESTRA_RESULTADO,
                            sumaRecursivaDeb,
                            sumaRecursivaFort,
                            sumaDivision,
                            sumaSecuencial));
        }
    }
}
