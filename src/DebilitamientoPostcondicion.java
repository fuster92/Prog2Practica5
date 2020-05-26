/**
 * Clase de encapsulación del método por debilitamiento
 */
public class DebilitamientoPostcondicion {

    /**
     * Pre: vector.length > 0
     * Post: sumaImpares (vector) =
     *      SUMA alfa EN [0, vector.length - 1] AND vector[alfa]%2=1.vector[alfa]
     */
    public static int sumaImpares(int[] vector){
        return sumaImparesDebPost(vector, 0);
    }

    /**
     * Pre: vector.length > 0 AND indice < vector.length AND indice >= 0
     * Post: sumaImpares (vector, indice) =
     *      SUMA alfa EN [indice, vector.length - 1] AND vector[alfa]%2=1.vector[alfa]
     */
    private static int sumaImparesDebPost(int[] vector, int indice){
        if ( indice < vector.length) {
            int valor = vector[indice];
            //Si es impar
            if( valor % 2 != 0){
                return valor + sumaImparesDebPost(vector, indice + 1); //c
            } else return sumaImparesDebPost(vector, indice + 1);
        }
        return 0;
    }
}
