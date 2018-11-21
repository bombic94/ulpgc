package es.ulpgc.eii.p1;

public class UsaBiblioteca{
    public static void main(String[] args){
        Cancion canción1 = new Cancion("song1","i1",90);
        Cancion canción2 = new Cancion("song2","i1",90);
        Cancion canción3 = new Cancion("song3","i2",120);
        Cancion canción4 = new Cancion("song1","i2",120);
        Cancion canción5 = new Cancion("song3","i2",120);
        Cancion canción6 = new Cancion("song4","i3",150);
        Cancion canción7 = new Cancion("song1","i3",150);

// DESCOMENTAR Y USAR CUANDO ESTÉN IMPLEMENTADOS
        Album álbum1 = new Album("Álbum 1");
        álbum1.añadeCanción(canción1);
        álbum1.añadeCanción(canción2);
        álbum1.añadeCanción(canción3);

        Album álbum2 = new Album("Álbum 2");
        álbum2.añadeCanción(canción4);
        álbum2.añadeCanción(canción5);
        álbum2.añadeCanción(canción6);
        álbum2.añadeCanción(canción7);

        Biblioteca miBiblioteca = new Biblioteca();
        miBiblioteca.añadeÁlbum(álbum1);
        miBiblioteca.añadeÁlbum(álbum2);
        System.out.println(miBiblioteca);

// Resultado mostrado en consola:
// Álbum 1:
// 1) [Título:song1 intérprete:i1 duración:90]
// 2) [Título:song2 intérprete:i1 duración:90]
// 3) [Título:song3 intérprete:i2 duración:120]

// Álbum 2:
// 1) [Título:song1 intérprete:i2 duración:120]
// 2) [Título:song3 intérprete:i2 duración:120]
// 3) [Título:song4 intérprete:i3 duración:150]
// 4) [Título:song1 intérprete:i3 duración:150]

        //DESCOMENTAR para ejecutar la clase de pruebas con JUnit
        //org.junit.runner.JUnitCore.main("BibliotecaTest");
    }
}

