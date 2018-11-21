package es.ulpgc.eii.p1;

import java.util.*;
import java.util.stream.Collectors;

public class Biblioteca {

    private Set<Album> albums = new TreeSet<>();

    /**
     * Añade un álbum a la biblioteca. Si ya existe uno con el mismo nombre lo sustituye por el nuevo.
     */
    public void añadeÁlbum(Album album) {
        eliminaÁlbum(album.dameNombre());
        albums.add(album);
    }

    /**
     * Devuelve un álbum a partir de su nombre. Si no existe devuelve null.
     * @return
     */
    public Album dameÁlbum(String nombre) {
        for (Album a : albums) {
            if (a.dameNombre().equals(nombre)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Elimina un álbum a partir de su nombre. Si no existe no hace nada.
     * @param nombre
     */
    public void eliminaÁlbum(String nombre) {
        Album a = dameÁlbum(nombre);
        if (a != null) {
            albums.remove(a);
        }
    }

    /**
     * Devuelve el conjunto de las canciones que se repiten (que tienen el mismo nombre)
     * más de una vez en toda la biblioteca.
     * @return
     */
    public Set<Cancion> dameCancionesRepetidas() {
        List<Cancion> allSongs = new ArrayList<>();
        Set<Cancion> result = new HashSet<>();

        for (Album a : albums) {
            for (int i = 0; i < a.númeroDeCanciones(); i++) {
                Cancion cancion = a.dameCanción(i);
                allSongs.add(cancion);
            }
        }

        for (Cancion cancion : allSongs) {
            for (Cancion cancion2 : allSongs) {
                if (!cancion.equals(cancion2) && cancion.dameTítulo().equals(cancion2.dameTítulo())) {
                    result.add(cancion);
                }
            }
        }

        return result;
    }

    /**
     * Devuelve una lista con los intérpretes ordenados desde el que más canciones interpreta hasta el que
     * menos (en caso de que interpreten el mismo número de canciones siguen el orden alfabético)
     * @return
     */
    public List<String> dameIntérpretes() {
        List<Interpret> interprets = new ArrayList<>();
        boolean found;
        for (Album a : albums) {
            for (int i = 0; i < a.númeroDeCanciones(); i++) {
                found = false;
                Cancion cancion = a.dameCanción(i);
                String name = cancion.dameIntérprete();
                for (Interpret interpret : interprets) {
                    if (interpret.getName().equals(name)) {
                        interpret.incrementCount();
                        found = true;
                    }
                }
                if (!found) {
                    interprets.add(new Interpret(name));
                }
            }
        }
        interprets.sort(Interpret::compareTo);
        List<String> result = new ArrayList<>();
        for (Interpret interpret : interprets) {
            result.add(interpret.getName());
        }
        return result;
    }

    /**
     * Devuelve una String con la concatenación de todos los álbumes de la biblioteca seguidos
     * por una línea en blanco, (ver ejemplo en main).
     * @return
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Album a : albums) {
            sb.append(a.toString()).append("\n");
        }
        return sb.toString();
    }

    private class Interpret implements Comparable<Interpret> {

        private String name;
        private int count;

        public Interpret(String name) {
            this.name = name;
            count = 1;
        }

        public String getName() {
            return name;
        }

        public int getCount() {
            return count;
        }

        @Override
        public int compareTo(Interpret interpret) {
            if (this.count > interpret.count) {
                return -1;
            } else if (this.count < interpret.count) {
                return 1;
            } else {
                return this.name.compareTo(interpret.name);
            }
        }

        public void incrementCount() {
            this.count++;
        }
    }
}
