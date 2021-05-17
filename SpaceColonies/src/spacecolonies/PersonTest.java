// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mansour Najah (mansourn)
package spacecolonies;

/**
 * Tests for the Person class.
 *
 * @author <Mansour Najah> <mansourn>
 * @version <04.13.2020>
 */
public class PersonTest extends student.TestCase {

    private Person personZero;
    private Person personNormal;
    private Person personMax;

    /**
     * Instantiates several skills and person objects to be
     * used in all tests.
     */
    public void setUp() {
        Skills skillsZero;
        Skills skillsNormal;
        Skills skillsMax;

        skillsZero = new Skills(0, 0, 0);
        skillsNormal = new Skills(2, 5, 3);
        skillsMax = new Skills(5, 5, 5);

        personZero = new Person("Zero", skillsZero, "Planet 1");
        personNormal = new Person("Normal", skillsNormal, "Planet 2");
        personMax = new Person("Max", skillsMax, "Planet 3");
    }

    /**
     * Tests to see if the method getName() is working as intended.
     */
    public void testGetName() {
        assertEquals("Zero", personZero.getName());
        assertEquals("Normal", personNormal.getName());
        assertEquals("Max", personMax.getName());
    }

    /**
     * Tests to see if the method getSkills() is working as intended.
     */
    public void testGetSkills() {
        assertEquals("A:0 M:0 T:0", personZero.getSkills().toString());
        assertEquals("A:2 M:5 T:3", personNormal.getSkills().toString());
        assertEquals("A:5 M:5 T:5", personMax.getSkills().toString());
    }

    /**
     * Tests to see if the method getPlanetName() is working as intended.
     */
    public void testGetPlanetName() {
        assertEquals("Planet 1", personZero.getPlanetName());
        assertEquals("Planet 2", personNormal.getPlanetName());
        assertEquals("Planet 3", personMax.getPlanetName());
    }

    /**
     * Tests to see if the method toString() is working as intended.
     */
    public void testToString() {
        assertEquals("Zero A:0 M:0 T:0 Wants: Planet 1",
                personZero.toString());
        assertEquals("Normal A:2 M:5 T:3 Wants: Planet 2",
                personNormal.toString());
        assertEquals("Max A:5 M:5 T:5 Wants: Planet 3",
                personMax.toString());

        Person peter = new Person("Peter", 3, 3, 3, "");

        assertEquals("No-Planet Peter A:3 M:3 T:3", peter.toString());
    }

    /**
     * Tests to see if the method equals() is working as intended.
     */
    public void testEquals() {
        assertTrue(personZero.equals(personZero));
        assertTrue(personNormal.equals(personNormal));
        assertTrue(personMax.equals(personMax));

        assertFalse(personZero.equals(personNormal));
        assertFalse(personZero.equals(personMax));
        assertFalse(personNormal.equals(personMax));

        Person sam = new Person("Normal", 2, 5, 3, "Planet 2");
        Person rob = new Person("Rob", 2, 5, 3, "Planet 1");
        Person tom = new Person("Tom", 2, 5, 3, "");

        assertTrue(personNormal.equals(sam));

        assertFalse(personNormal.equals(rob));
        assertFalse(personNormal.equals(tom));
        assertFalse(personNormal.equals(new Object()));
    }

}
