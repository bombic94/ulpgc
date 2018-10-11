package es.ulpgc.eii.p1;

/**
 * @author David Bohmann
 */
public class UsaAuxiliarString {

    /**
     * Main method of program, passes the String to AuxiliarString methods
     * and then prints array of words.
     * @param args
     */
    public static void main(String[] args) {
        String frase = "En un lugar de la Mancha, de cuyo nombre no quiero acordarme" +

                ", no ha mucho tiempo que vivía un hidalgo de los de lanza" +

                " en astillero, adarga antigua, rocín flaco y galgo corredor.";


        System.out.println("Todas las palabras:");

        String[] words = AuxiliarString.palabras(frase);

        // Se muestran todas las palabras de la frase
        for (String word : words) {
            System.out.println(word);
        }

        System.out.println("Todas las palabras sin repetición");

        String[] differentsWords = AuxiliarString.palabrasDistintas(frase);

        // Se muestran todas las palabras sin repetición
        for (String word : differentsWords) {
            System.out.println(word);
        }
    }
}
