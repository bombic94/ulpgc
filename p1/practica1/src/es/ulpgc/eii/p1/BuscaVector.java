package es.ulpgc.eii.p1;

/**
 * Class BuscaVector serves for finding a vector in a matrix
 * @author David Bohmann
 */
public class BuscaVector {

    /**
     * Return position of first element of vector found in matriz
     * @param m Matrix of elements
     * @param v Vector we want to find in matrix
     * @return new PosicionMatriz object, if found, else null
     */
    public static PosicionMatriz buscaVectorEnMatriz(int[][] m, int[] v) {
        PosicionMatriz posicionMatriz = null;

        outerloop:
        for (int i = 0; i < m.length; i++) { //go through matrix by lines
            for (int j = 0; j < m[i].length; j++) { //go line matrix by columns
                if (v[0] == m[i][j]) {
                    posicionMatriz = buscaVectorEnFila(m[i], v, j, i);
                    if (posicionMatriz != null) {
                        break outerloop;
                    }
                }
            }
        }

        return posicionMatriz;
    }

    /**
     * Compare line of matrix with given vector
     * @param fila row of matrix
     * @param v searched vector
     * @param posicionInMatrizColumna Column index in matrix
     * @param posicionInMatrizFila Row index in matrix
     * @return new PosicionMatriz object, if found, else null
     */
    private static PosicionMatriz buscaVectorEnFila(int[] fila, int[] v, int posicionInMatrizColumna, int posicionInMatrizFila) {

        //rest of line is shorter than vector
        if (fila.length - posicionInMatrizColumna < v.length) {
            return null;
        }

        //go through vector and check if its the same as the matrix line
        for (int i = posicionInMatrizColumna, j = 0; i < fila.length; i++, j++) {
            // numbers don't match, return null
            if (fila[i] != v[j]) {
                return null;
            }
            // end of vector
            if (j == v.length - 1) {
                break;
            }
        }
        return new PosicionMatriz(posicionInMatrizFila, posicionInMatrizColumna);
    }

    /**
     * Main method of program
     * @param args Arguments of program
     */
    public static void main(String[] args) {
        int[][] m = {{1,2,3,4,5,6,7,8},{1,2,5,6,7,8},{1,2,3,7,8},{1,6,7,8}};
        int[]   v = {3,7,8};
        PosicionMatriz res = buscaVectorEnMatriz(m,v);
        if(res == null){
            System.out.println("Vector no encontrado");
        }else{
            System.out.println("El vector se ha encontrado en la posición " + res);
        }
    }
}
