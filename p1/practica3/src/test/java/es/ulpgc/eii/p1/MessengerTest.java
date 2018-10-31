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
        assertEquals("Family", g1.toString());
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
                "David: 123456789", g1.toString());
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
                "David: 123456789", g1.toString());
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
                "Petr: 987654321", g1.toString());
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
                "Petr: 987654321", g2.toString());
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
                "Petr: 987654321\n" +
                "Work\n" +
                "Mum: 111222333\n" +
                "Dad: 444555666", g3.toString());
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
                "Petr: 987654321", g3.toString());
    }

    @Test
    public void testRemoveNonExistingMember(){
        assertFalse(g1.isMember(p1.getId()));

        assertFalse(g1.remove(p1.getId()));
        assertFalse(g1.isMember(p1.getId()));

        assertArrayEquals(new Contact[]{}, g1.getContacts());

        assertEquals("Family", g1.toString());
    }

    @Test
    public void testRemoveExistingMember(){
        assertTrue(g1.add(p1));
        assertTrue(g1.isMember(p1.getId()));

        assertTrue(g1.remove(p1.getId()));
        assertFalse(g1.isMember(p1.getId()));

        assertArrayEquals(new Contact[]{}, g1.getContacts());

        assertEquals("Family", g1.toString());
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
                "David: 123456789", g2.toString());
    }

    @Test
    public void testRemoveGroup(){
        assertTrue(g1.add(p1));
        assertTrue(g2.add(g1));

        assertTrue(g2.isMember(p1.getId()));

        assertTrue(g2.remove(g1.getId()));

        assertArrayEquals(new Contact[]{}, g2.getContacts());

        assertEquals("Work", g2.toString());
    }
}

