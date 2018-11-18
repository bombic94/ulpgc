package es.ulpgc.eii.p1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessengerTest {

    Person p1;
    Person p2;
    Person p3;
    Person p4;
    Group g1;
    Group g2;
    Group g3;

    @Before
    public void init(){
        p1 = new Person("David", "123456789");
        p2 = new Person("Petr", "987654321");
        p3 = new Person("Mum", "111222333");
        p4 = new Person("Dad", "444555666");
        g1 = new Group("Family");
        g2 = new Group("Work");
        g3 = new Group("All");
    }


    @Test
    public void testPersonConstructorToString(){
        assertEquals("David: 123456789", p1.toString());
    }

    @Test
    public void testGroupConstructorToString(){
        assertEquals("Family\n", g1.toString());
    }

    @Test
    public void testPersonConstructorGetName(){
        assertEquals("David", p1.getName());
    }

    @Test
    public void testGroupConstructorGetName(){
        assertEquals("Family", g1.getName());
    }

    @Test
    public void testGroupConstructorGetContacts(){
        assertArrayEquals(new Contact[]{}, g1.getContacts());
    }

    @Test
    public void testAddNonExistingMember(){
        assertFalse(g1.isMember(p1.getId()));

        assertTrue(g1.add(p1));
        assertTrue(g1.isMember(p1.getId()));

        assertArrayEquals(new Contact[]{p1}, g1.getContacts());

        assertEquals("Family\n" +
                "David: 123456789\n", g1.toString());
    }

    @Test
    public void testAddExistingMember(){
        assertFalse(g1.isMember(p1.getId()));

        assertTrue(g1.add(p1));
        assertTrue(g1.isMember(p1.getId()));

        assertFalse(g1.add(p1));
        assertTrue(g1.isMember(p1.getId()));

        assertArrayEquals(new Contact[]{p1}, g1.getContacts());

        assertEquals("Family\n" +
                "David: 123456789\n", g1.toString());
    }

    @Test
    public void testAddTwoMembers(){
        assertFalse(g1.isMember(p1.getId()));
        assertFalse(g1.isMember(p2.getId()));

        assertTrue(g1.add(p1));
        assertTrue(g1.add(p2));

        assertTrue(g1.isMember(p1.getId()));
        assertTrue(g1.isMember(p2.getId()));

        assertArrayEquals(new Contact[]{p1, p2}, g1.getContacts());

        assertEquals("Family\n" +
                "David: 123456789\n" +
                "Petr: 987654321\n", g1.toString());
    }

    @Test
    public void testAddGroupToGroup(){
        assertTrue(g1.add(p1));
        assertTrue(g1.add(p2));

        assertTrue(g2.add(g1));
        assertFalse(g2.add(p1));

        assertTrue(g2.isMember(g1.getId()));
        assertTrue(g2.isMember(p1.getId()));
        assertTrue(g2.isMember(p2.getId()));

        assertArrayEquals(new Contact[]{p1, p2}, g1.getContacts());
        assertArrayEquals(new Contact[]{g1}, g2.getContacts());

        assertEquals("Work\n" +
                "Family\n" +
                "David: 123456789\n" +
                "Petr: 987654321\n\n", g2.toString());
    }

    @Test
    public void testAddTwoGroupsToGroup(){
        assertTrue(g1.add(p1));
        assertTrue(g1.add(p2));

        assertTrue(g2.add(p3));
        assertTrue(g2.add(p4));

        assertTrue(g3.add(g1));
        assertTrue(g3.add(g2));

        assertFalse(g3.add(p1));
        assertFalse(g3.add(p3));

        assertTrue(g3.isMember(g1.getId()));
        assertTrue(g3.isMember(p2.getId()));
        assertTrue(g3.isMember(p4.getId()));

        assertArrayEquals(new Contact[]{p1, p2}, g1.getContacts());
        assertArrayEquals(new Contact[]{p3, p4}, g2.getContacts());
        assertArrayEquals(new Contact[]{g1, g2}, g3.getContacts());

        assertEquals("All\n" +
                "Family\n" +
                "David: 123456789\n" +
                "Petr: 987654321\n\n" +
                "Work\n" +
                "Mum: 111222333\n" +
                "Dad: 444555666\n\n", g3.toString());
    }

    @Test
    public void testAddTwoGroupsToGroupInChain(){
        assertTrue(g1.add(p1));
        assertTrue(g1.add(p2));

        assertTrue(g2.add(p3));

        assertTrue(g2.add(g1));
        assertTrue(g3.add(g2));

        assertFalse(g3.add(p1));
        assertFalse(g3.add(p3));

        assertTrue(g3.isMember(g1.getId()));
        assertTrue(g3.isMember(p2.getId()));

        assertArrayEquals(new Contact[]{p1, p2}, g1.getContacts());
        assertArrayEquals(new Contact[]{p3, g1}, g2.getContacts());
        assertArrayEquals(new Contact[]{g2}, g3.getContacts());

        assertEquals("All\n" +
                "Work\n" +
                "Mum: 111222333\n" +
                "Family\n" +
                "David: 123456789\n" +
                "Petr: 987654321\n\n\n", g3.toString());
    }

    @Test
    public void testRemoveNonExistingMember(){
        assertFalse(g1.isMember(p1.getId()));

        assertFalse(g1.remove(p1.getId()));
        assertFalse(g1.isMember(p1.getId()));

        assertArrayEquals(new Contact[]{}, g1.getContacts());

        assertEquals("Family\n", g1.toString());
    }

    @Test
    public void testRemoveExistingMember(){
        assertTrue(g1.add(p1));
        assertTrue(g1.isMember(p1.getId()));

        assertTrue(g1.remove(p1.getId()));
        assertFalse(g1.isMember(p1.getId()));

        assertArrayEquals(new Contact[]{}, g1.getContacts());

        assertEquals("Family\n", g1.toString());
    }

    @Test
    public void testRemoveIndirectMember(){
        assertTrue(g1.add(p1));
        assertTrue(g2.add(g1));

        assertTrue(g2.isMember(p1.getId()));

        assertFalse(g2.remove(p1.getId()));

        assertArrayEquals(new Contact[]{p1}, g1.getContacts());
        assertArrayEquals(new Contact[]{g1}, g2.getContacts());

        assertEquals("Work\n" +
                "Family\n" +
                "David: 123456789\n\n", g2.toString());
    }

    @Test
    public void testRemoveGroup(){
        assertTrue(g1.add(p1));
        assertTrue(g2.add(g1));

        assertTrue(g2.isMember(p1.getId()));

        assertTrue(g2.remove(g1.getId()));

        assertArrayEquals(new Contact[]{}, g2.getContacts());

        assertEquals("Work\n", g2.toString());
    }

    /*********************
     * Person tests
     *********************/
    @Test
    public void testPersonName(){
        Person p = new Person("Bob","123456789");
        assertEquals(p.getName(), "Bob");
    }
    @Test
    public void testPersonToString(){
        Person p = new Person("Bob","123456789");
        assertEquals(p.toString(), "Bob: 123456789");
    }

    /*********************
     * Group tests
     *********************/
    @Test
    public void testGroupName(){
        Group g = new Group("Family");
        assertEquals(g.getName(), "Family");
    }

    @Test
    public void testGroupIsMember(){
        Person pepe = new Person("Pepe","5555380");
        Person juan = new Person("Juan","55541501");
        Person antonio = new Person("Antonio","5556380");
        Group g = new Group("Family");
        Group g2 = new Group("Brothers");
        g.add(pepe);
        g2.add(juan);
        g.add(g2);

        assertTrue(g.isMember(g.getId()));
        assertFalse(g.isMember(antonio.getId()));
        assertTrue(g.isMember(juan.getId()));
    }

    @Test
    public void testGroupAdd(){
        Person pepe = new Person("Pepe","5555380");
        Person juan = new Person("Juan","55541501");
        Person antonio = new Person("Antonio","5556380");
        Group g = new Group("Family");
        Group g2 = new Group("Brothers");

        assertEquals(g.toString(), "Family\n");
        assertTrue(g.add(pepe));
        assertTrue(g2.add(juan));
        assertTrue(g2.add(antonio));
        assertTrue(g.add(g2));
        assertEquals(g.toString(), "Family\nPepe: 5555380\nBrothers\nJuan: 55541501\nAntonio: 5556380\n\n");

        assertFalse(g.add(g));
        assertFalse(g.add(g2));
        assertFalse(g.add(juan));
        assertFalse(g.add(pepe));
        assertEquals(g.toString(), "Family\nPepe: 5555380\nBrothers\nJuan: 55541501\nAntonio: 5556380\n\n");
    }

    @Test
    public void testGroupRemove(){
        Person pepe = new Person("Pepe","5555380");
        Person juan = new Person("Juan","55541501");
        Person antonio = new Person("Antonio","5556380");
        Group g = new Group("Family");
        Group g2 = new Group("Brothers");

        g.add(pepe);
        g2.add(juan);
        g2.add(antonio);
        g.add(g2);

        assertFalse(g.remove(juan.getId()));
        assertFalse(g.remove(g.getId()));
        assertEquals(g.toString(), "Family\nPepe: 5555380\nBrothers\nJuan: 55541501\nAntonio: 5556380\n\n");
        assertTrue(g.remove(pepe.getId()));
        assertEquals(g.toString(), "Family\nBrothers\nJuan: 55541501\nAntonio: 5556380\n\n");
    }

    @Test
    public void testGroupGetContacts(){
        Person pepe = new Person("Pepe","5555380");
        Person juan = new Person("Juan","55541501");
        Person antonio = new Person("Antonio","5556380");
        Group g = new Group("Family");
        Group g2 = new Group("Brothers");
        g.add(pepe);
        g2.add(juan);
        g2.add(antonio);
        g.add(g2);

        Contact[] c = g.getContacts();
        assertArrayEquals(c, new Contact[]{pepe, g2});
        assertArrayEquals(g2.getContacts(), new Contact[]{juan, antonio});
        g.getContacts()[0] = antonio;
        assertArrayEquals(g.getContacts(), new Contact[]{pepe, g2});
        g.remove(g2.getId());
        assertArrayEquals(g.getContacts(), new Contact[]{pepe});
    }

    @Test
    public void testGroupToString(){
        Group g = new Group("Family");
        assertEquals(g.toString(), "Family\n");

        Person pepe = new Person("Pepe","5555380");
        Person juan = new Person("Juan","55541501");
        Person antonio = new Person("Antonio","5556380");
        Group g2 = new Group("Half-Family");
        g.add(pepe);
        g.add(juan);
        g2.add(antonio);
        g.add(g2);
        assertEquals(g.toString(), "Family\nPepe: 5555380\nJuan: 55541501\nHalf-Family\nAntonio: 5556380\n\n");

    }

    @Test
    public void testStupidAulagaToString() {
        Group noFamilia= new Group("no familiares");
        Group amigos = new Group("amigos");
        Group amigas = new Group("amigas");
        Person pepe = new Person("Pepe", "5555380");
        Person antonio = new Person("Antonio","5556380");
        Person ana = new Person("Ana","5557781");
        amigos.add(pepe);
        amigos.add(antonio);
        amigas.add(ana);
        noFamilia.add(amigos);
        noFamilia.add(amigas);
        assertEquals(noFamilia.toString(),"no familiares\namigos\nPepe: 5555380\nAntonio: 5556380\n\namigas\nAna: 5557781\n\n");
    }
    /**
     *Fallo en Group
     *toString() no devuelve el valor esperado. Devuelve:
     *no familiares\namigos\nPepe: 5555380\nAntonio: 5556380\namigas\nAna: 5557781
     *Y debe devolver:
     *no familiares\namigos\nPepe: 5555380\nAntonio: 5556380\n\namigas\nAna: 5557781\n\n
     *add() devuelve verdadero al intentar añadir el mismo contacto
     */
}