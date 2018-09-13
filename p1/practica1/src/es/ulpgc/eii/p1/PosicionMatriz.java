package es.ulpgc.eii.p1;

/**
 * Class represents position of element in matrix, contains info about column and row index
 * @author David Bohmann
 */
public class PosicionMatriz {

    private int fila;
    private int columna;

    /**
     * Constructor
     * @param fila row index
     * @param columna column index
     */
    public PosicionMatriz(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Getter of row index
     * @return row index
     */
    public int dameFila() {
        return this.fila;
    }

    /**
     * Getter of column index
     * @return column index
     */
    public int dameColumna() {
        return this.columna;
    }

    /**
     * String representation of matrix position
     * @return String representation of matrix position
     */
    @Override
    public String toString() {
        return String.format("[%d] [%d]", dameFila(), dameColumna());
    }

    /**
     * Compare two positions of element in matrix
     * @param p PosicionMatriz
     * @return true, if column and row of elements are the same, false elsewhere
     */
    public boolean equals(PosicionMatriz p) {
        return this.columna == p.columna && this.fila == p.fila;
    }
}
