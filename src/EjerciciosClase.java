/*
 * EjerciciosClase.java
 *
 * 11/05/2020
 * Javier Fuster Trallero
 * Versión 0.0
 */

/**
 * Clase de EjerciciosClase
 */
public class EjerciciosClase {

    /**
     * Construye un EjerciciosClase
     */
    public EjerciciosClase() {

    }

    /**
     * Pre: n >= 0
     * Post: (n=0 -> numCifras(n) = 1) AND
     *       (n > 0 -> 10 ^(numCifras(n) - 1) <= n AND n < 10 ^(numCifras(n))
     */
    public static int numCifras (int n){
        if(n <= 9){
            return 1;
        } else {
            return 1 + numCifras(n/10);
        }
    }

    /**
     * Pre: n >= AND b >= 2
     * Post: (n = 0 -> numbCifras(n) = 1) AND
 *           (n > 0 -> b^(numCifras(n) - 1) <= n AND n < b^(numCifras(n))
     */
    public static int numCifras(int n, int b){
        if (n < b){
            return 1;
        } else{
            return numCifras(n / b, b);
        }
    }


    public static int cifra (int n, int i){
        if (i == 1){
            return n%10;
        } else{
            return cifra(n/10, i - 1);
        }
    }

    /**
     * Inmersion por fortalecimiento precondicion
     */
    public static double sumaDatos(double[] datos){
        return sumaDatos(datos, datos.length);
    }

    /**
     * Inmersion por fortalecimiento precondicion
     */
    public static double sumaDatos(double[] datos, int hasta){
        if (hasta == 0){
            return datos[hasta];
        } else {
            return datos[hasta] + sumaDatos(datos, hasta - 1);
        }
    }

    public static int posicionPositivo(double[] valores){
        return posicionPositivo(valores, valores.length - 1);
    }

    private static int posicionPositivo(double[] valores, int desde){
        if (valores[desde] > 0.0){
            return desde;
        } else {
            return posicionPositivo(valores, desde + 1);
        }
    }

    public <A> void  invertir(A[] T){
        invertir(T, 0);
    }

    private <A> void  invertir(A[] T, int indice){

    }
}
