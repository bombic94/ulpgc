package es.ulpgc.eii.p1;

import java.util.ArrayList;
import java.util.List;

public class Album implements Comparable<Album> {

    private String nombre;
    private List<Cancion> cancions;

    /**
     * Constructor que inicializa un álbum al que se le pasa una String con el identificador.
     * @param nombre
     */
    public Album(String nombre) {
        this.nombre = nombre;
        this.cancions = new ArrayList<>();
    }

    /**
     *
     * Devuelve el identificador del álbum.
     * @return
     */
    public String dameNombre() {
        return this.nombre;
    }

    /**
     * Devuelve el número de canciones en el álbum.
     * @return
     */
    public int númeroDeCanciones() {
        return this.cancions.size();
    }

    /**
     * Devuelve la canción que ocupa la posición indicada en el parámetro. Si la posición no es válida devuelve null.
     * @return
     */
    public Cancion dameCanción(int index) {
        if (cancions.size() > index && index >= 0) {
            return cancions.get(index);
        }
        return null;
    }

    /**
     * Añade una canción al final de la secuencia.
     */
    public void añadeCanción(Cancion cancion) {
        this.cancions.add(cancion);
    }

    /**
     * Elimina de la secuencia la canción que ocupa la posición indicada en el parámetro. Si la posición no es válida no hace nada.
     */
    public void quitaCanción(int index) {
        if (cancions.size() > index && index >= 0) {
            cancions.remove(index);
        }
    }

    /**
     * Devuelve la duración total del álbum.
     * @return
     */
    public int duración() {
        int duracion = 0;
        for (Cancion cancion : cancions) {
            duracion += cancion.dameDuración();
        }
        return duracion;
    }

    /**
     * Devuelve una String con un listado numerado empezando en uno (después del número va un cierra paréntesis
     * y un espacio en blanco) de todas las canciones del álbum seguidas por saltos de línea ("\n") y precedidas
     * por el nombre del álbum al que corresponden seguido de dos puntos y un salto de línea (":\n"),
     * (ver ejemplo en main).
     * @return
     */
    public String toString() {
        int counter = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(this.nombre).append(":\n");
        for (Cancion c : cancions) {
            sb.append(counter).append(") ").append(c.toString()).append("\n");
            counter++;
        }
        return sb.toString();
    }

    @Override
    public int compareTo(Album album) {
        return this.dameNombre().compareTo(album.dameNombre());
    }
}
