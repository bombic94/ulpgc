package es.ulpgc.eii.p1;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class BibliotecaTest {

    Biblioteca b;
    Album a1, a2, a3, a4;
    Cancion c1, c2, c3, c4, c5, c6, c7;

    @Before
    public void init() {
        b = new Biblioteca();
        a1 = new Album("album1");
        a2 = new Album("album2");
        a3 = new Album("album3");
        a4 = new Album("album4");
        c1 = new Cancion("song1","i1",90);
        c2 = new Cancion("song2","i1",90);
        c3 = new Cancion("song3","i2",120);
        c4 = new Cancion("song1","i2",120);
        c5 = new Cancion("song3","i2",120);
        c6 = new Cancion("song4","i3",150);
        c7 = new Cancion("song1","i3",150);
    }

    @Test
    public void testCancion() {
        assertEquals("song1", c1.dameTítulo());
        assertEquals("i1", c1.dameIntérprete());
        assertEquals(90, c1.dameDuración());
        assertEquals("[Título:song1 intérprete:i1 duración:90]", c1.toString());
    }

    @Test
    public void testAlbumBasic() {
        assertEquals("album1", a1.dameNombre());
        assertEquals(0, a1.númeroDeCanciones());
    }

    @Test
    public void testAlbumDameCancion() {
        assertNull(a1.dameCanción(0));
        a1.añadeCanción(c1);
        assertEquals(c1, a1.dameCanción(0));
        assertNull(a1.dameCanción(1));
        a1.añadeCanción(c2);
        a1.añadeCanción(c3);
        assertEquals(c2, a1.dameCanción(1));
        assertEquals(c3, a1.dameCanción(2));
        a1.quitaCanción(0);
        assertEquals(c2, a1.dameCanción(0));
        assertEquals(c3, a1.dameCanción(1));
        assertNull(a1.dameCanción(2));
    }

    @Test
    public void testAlbumNumberoCanciones() {
        assertEquals(0, a1.númeroDeCanciones());
        a1.añadeCanción(c1);
        assertEquals(1, a1.númeroDeCanciones());
        a1.añadeCanción(c2);
        a1.añadeCanción(c3);
        assertEquals(3, a1.númeroDeCanciones());
        a1.quitaCanción(0);
        assertEquals(2, a1.númeroDeCanciones());
    }

    @Test
    public void testAlbumDuracion() {
        assertEquals(0, a1.duración());
        a1.añadeCanción(c1);
        assertEquals(90, a1.duración());
        a1.añadeCanción(c2);
        a1.añadeCanción(c3);
        assertEquals(300, a1.duración());
        a1.quitaCanción(0);
        assertEquals(210, a1.duración());
    }

    @Test
    public void testAlbumToString() {
        assertEquals("album1:\n", a1.toString());
        a1.añadeCanción(c1);
        assertEquals("album1:\n1) [Título:song1 intérprete:i1 duración:90]\n", a1.toString());
        a1.añadeCanción(c2);
        a1.añadeCanción(c3);
        assertEquals("album1:\n1) [Título:song1 intérprete:i1 duración:90]\n2) [Título:song2 intérprete:i1 duración:90]\n3) [Título:song3 intérprete:i2 duración:120]\n", a1.toString());
        a1.quitaCanción(0);
        assertEquals("album1:\n1) [Título:song2 intérprete:i1 duración:90]\n2) [Título:song3 intérprete:i2 duración:120]\n", a1.toString());
    }

    @Test
    public void testBibliotecaEmpty() {
        assertNull(b.dameÁlbum("album1"));
        assertEquals("", b.toString());
        assertEquals(0, b.dameCancionesRepetidas().size());
        assertEquals(0, b.dameIntérpretes().size());
    }

    @Test
    public void testBibliotecaDameAlbum() {
        assertNull(b.dameÁlbum("album1"));
        b.añadeÁlbum(a1);
        assertEquals(a1, b.dameÁlbum("album1"));
        b.añadeÁlbum(a1);
        assertEquals(a1, b.dameÁlbum("album1"));
        b.eliminaÁlbum("album1");
        assertNull(b.dameÁlbum("album1"));
        b.eliminaÁlbum("album1");
        assertNull(b.dameÁlbum("album1"));
    }

    @Test
    public void testBibliotecaToString() {
        assertEquals("", b.toString());
        b.añadeÁlbum(a1);
        assertEquals("album1:\n\n", b.toString());
        a1.añadeCanción(c1);
        assertEquals("album1:\n1) [Título:song1 intérprete:i1 duración:90]\n\n", b.toString());
        b.añadeÁlbum(a2);
        assertEquals("album1:\n1) [Título:song1 intérprete:i1 duración:90]\n\nalbum2:\n\n", b.toString());
        a2.añadeCanción(c1);
        a2.añadeCanción(c1);
        assertEquals("album1:\n1) [Título:song1 intérprete:i1 duración:90]\n\nalbum2:\n1) [Título:song1 intérprete:i1 duración:90]\n2) [Título:song1 intérprete:i1 duración:90]\n\n", b.toString());
        a2.quitaCanción(0);
        a1.quitaCanción(0);
        assertEquals("album1:\n\nalbum2:\n1) [Título:song1 intérprete:i1 duración:90]\n\n", b.toString());
        b.eliminaÁlbum("album1");
        assertEquals("album2:\n1) [Título:song1 intérprete:i1 duración:90]\n\n", b.toString());
    }

    @Test
    public void testBibliotecaCancionesRepetidas() {
        assertEquals(0, b.dameCancionesRepetidas().size());
        a1.añadeCanción(c1);
        a1.añadeCanción(c2);
        a1.añadeCanción(c3);
        a2.añadeCanción(c4);
        a2.añadeCanción(c5);
        a3.añadeCanción(c6);
        a3.añadeCanción(c7);
        b.añadeÁlbum(a1);
        b.añadeÁlbum(a2);
        b.añadeÁlbum(a3);
        assertEquals(5, b.dameCancionesRepetidas().size());
        assertTrue(b.dameCancionesRepetidas().contains(c1));
        assertTrue(b.dameCancionesRepetidas().contains(c3));
        assertTrue(b.dameCancionesRepetidas().contains(c4));
        assertTrue(b.dameCancionesRepetidas().contains(c5));
        assertTrue(b.dameCancionesRepetidas().contains(c7));
        assertFalse(b.dameCancionesRepetidas().contains(c2));
        assertFalse(b.dameCancionesRepetidas().contains(c6));
    }

    @Test
    public void testBibliotecaInterpretes() {
        assertEquals(0, b.dameIntérpretes().size());
        a1.añadeCanción(c1);
        a1.añadeCanción(c2);
        a1.añadeCanción(c3);
        a2.añadeCanción(c4);
        a2.añadeCanción(c5);
        a3.añadeCanción(c6);
        a3.añadeCanción(c7);
        b.añadeÁlbum(a1);
        b.añadeÁlbum(a2);
        b.añadeÁlbum(a3);
        assertEquals(3, b.dameIntérpretes().size());
        assertEquals("i2", b.dameIntérpretes().get(0));
        assertEquals("i1", b.dameIntérpretes().get(1));
        assertEquals("i3", b.dameIntérpretes().get(2));
    }
}
