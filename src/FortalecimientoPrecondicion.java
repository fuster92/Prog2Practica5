public class FortalecimientoPrecondicion {

    /**
     * Pre: vector.length > 0
     * Post: sumaImpares (vector) =
     *      SUMA alfa EN [0, vector.length - 1] AND vector[alfa]%2=1.vector[alfa]
     */
    public static int sumaImpares(int[] vector){
        return sumaImparesMitades(vector, 0, vector.length - 1);
    }

    /**
     * Pre: vector.length > 0 AND desde >= 0 AND hasta < vector.length
     * Post: sumaImparesMitad (vector, desde, hasta) =
     *       SUMA alfa EN [desde, hasta] AND vector[alfa] % 2 = 1.vector[alfa]
     */
    private static int sumaImparesMitades(int[] vector,
                                         int desde,
                                         int hasta){
        if (desde == hasta){
            return (vector[desde] % 2 == 1) ? vector[desde] : 0;
        } else {

            return sumaImparesMitades(vector, desde, desde + (hasta - desde ) / 2) +
                    sumaImparesMitades(vector, desde + ((hasta - desde) / 2) + 1 , hasta);
        }
    }
}
