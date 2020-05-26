/**
 * Clase de encapsulación del método por iteración
 */
public class Iterativo {

    /**
     * Pre: vector.length > 0
     * Post: sumaImpares (vector) =
     *      SUMA alfa EN [0, vector.length - 1] AND vector[alfa]%2=1.vector[alfa]
     */
    public static int sumaImpares(int[] vector){
        return sumaImparesSecuencial(vector);
    }

    /**
     * Pre: vector.length > 0
     * Post: sumaImpares (vector) =
     *      SUMA alfa EN [0, vector.length - 1] AND vector[alfa]%2=1.vector[alfa]
     */
    private static int sumaImparesSecuencial(int[] vector){
        int suma = 0;
        for (int numero : vector) {
            suma += (numero % 2 != 0) ? numero : 0;
        }
        return suma;
    }
}
