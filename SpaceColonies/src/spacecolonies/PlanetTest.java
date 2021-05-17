// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mansour Najah (mansourn)
package spacecolonies;

/**
 * Tests for the Planet class.
 *
 * @author <Mansour Najah> <mansourn>
 * @version <04.13.2020>
 */
public class PlanetTest extends student.TestCase {

    private Planet testPlanet;

    private Person personZero;
    private Person personNormal;
    private Person personMax;

    /**
     * Sets up.
     */
    public void setUp() {
        Skills skillsZero;
        Skills skillsNormal;
        Skills skillsMax;

        testPlanet = new Planet("Blank", 1, 1, 1, 10);

        skillsZero = new Skills(0, 0, 0);
        skillsNormal = new Skills(2, 5, 3);
        skillsMax = new Skills(5, 5, 5);

        personZero = new Person("Zero", skillsZero, "Planet 1");
        personNormal = new Person("Normal", skillsNormal, "Planet 2");
        personMax = new Person("Max", skillsMax, "Planet 3");
    }

    /**
     * Tests setName() and getName() methods.
     */
    public void testSetName() {
        assertEquals("Blank", testPlanet.getName());

        testPlanet.setName("Mars");

        assertEquals("Mars", testPlanet.getName());
    }

    /**
     * Tests the getSkills() method.
     */
    public void testGetSkills() {
        assertEquals(1, testPlanet.getSkills().getAgriculture());
        assertEquals(1, testPlanet.getSkills().getMedicine());
        assertEquals(1, testPlanet.getSkills().getTechnology());
    }

    /**
     * Tests the getPopulation() method.
     */
    public void testGetPopulation() {
        Person[] comparPop = new Person[10];

        assertTrue(java.util.Arrays.equals(comparPop,
                testPlanet.getPopulation()));

        comparPop[0] = personMax;
        testPlanet.addPerson(personMax);

        assertTrue(java.util.Arrays.equals(comparPop,
                testPlanet.getPopulation()));

        comparPop[1] = personNormal;
        testPlanet.addPerson(personNormal);

        assertTrue(java.util.Arrays.equals(comparPop,
                testPlanet.getPopulation()));

        comparPop[2] = personZero;

        assertFalse(java.util.Arrays.equals(comparPop,
                testPlanet.getPopulation()));
    }

    /**
     * Tests the getPopulationSize() method.
     */
    public void testGetPopulationSize() {
        assertEquals(0, testPlanet.getPopulationSize());

        testPlanet.addPerson(personMax);

        assertEquals(1, testPlanet.getPopulationSize());

        for (int i = 0; i < 9; i++) {
            testPlanet.addPerson(personMax);
        }

        assertEquals(10, testPlanet.getPopulationSize());
    }

    /**
     * Tests the getCapacity() method.
     */
    public void testGetCapacity() {
        assertEquals(10, testPlanet.getCapacity());

        Planet newPlanet = new Planet("NotSoBlank", 1, 1, 1, 20);

        assertEquals(20, newPlanet.getCapacity());
    }

    /**
     * Tests the getAvailbility() method.
     */
    public void testGetAvailability() {
        assertEquals(10, testPlanet.getAvailability());

        testPlanet.addPerson(personMax);

        assertEquals(9, testPlanet.getAvailability());

        int spaces = 9;

        for (int i = 0; i < 9; i++) {
            testPlanet.addPerson(personMax);
            spaces--;

            assertEquals(spaces, testPlanet.getAvailability());
        }
    }

    /**
     * Tests the isFull() method.
     */
    public void testIsFull() {
        assertFalse(testPlanet.isFull());

        testPlanet.addPerson(personMax);

        assertFalse(testPlanet.isFull());

        for (int i = 0; i < 9; i++) {
            testPlanet.addPerson(personMax);
        }

        assertTrue(testPlanet.isFull());
    }

    /**
     * Tests the addPerson() method.
     */
    public void testAddPerson() {
        assertTrue(testPlanet.addPerson(personNormal));
        assertEquals(1, testPlanet.getPopulationSize());

        assertTrue(testPlanet.addPerson(personMax));
        assertEquals(2, testPlanet.getPopulationSize());

        assertFalse(testPlanet.addPerson(personZero));
        assertEquals(2, testPlanet.getPopulationSize());
    }

    /**
     * Tests the isQualified() method.
     */
    public void testIsQualified() {
        assertTrue(testPlanet.isQualified(personNormal));
        assertTrue(testPlanet.isQualified(personMax));

        assertFalse(testPlanet.isQualified(personZero));
    }

    /**
     * Tests the toString() method.
     */
    public void testToString() {
        String test = "Blank, population 0 (cap: 10), "
                + "Requires: A >= 1" + ", M >= 1, T >= 1";

        assertEquals(test, testPlanet.toString());
    }

    /**
     * Tests the compareTo() method.
     */
    public void testCompareTo() {
        Planet one = new Planet("One", 1, 1, 1, 20);
        Planet two = new Planet("Two", 1, 1, 1, 10);
        Planet three = new Planet("Three", 1, 1, 1, 10);

        testPlanet.addPerson(personNormal);
        three.addPerson(personMax);

        for (int i = 0; i < 20; i++) {
            one.addPerson(personNormal);
        }

        assertEquals(1, testPlanet.compareTo(one));
        assertEquals(-1, testPlanet.compareTo(two));
        assertEquals(0, testPlanet.compareTo(three));
    }

    /**
     * Tests the equals() method.
     */
    public void testEquals() {
        Planet planetBlank = new Planet("Blank", 1, 1, 1, 10);
        Planet planet1 = new Planet("Blank", 1, 2, 1, 10);
        Planet planet2 = new Planet("Planet", 1, 1, 1, 10);
        Planet planet3 = new Planet("Planet", 1, 2, 1, 10);
        Planet planet4 = new Planet("Planet", 1, 2, 1, 7);

        assertTrue(testPlanet.equals(testPlanet));

        boolean bool = testPlanet == null;

        assertFalse(bool);

        assertTrue(testPlanet.equals(planetBlank));

        assertFalse(testPlanet.equals(planet1));
        assertFalse(testPlanet.equals(planet2));
        assertFalse(testPlanet.equals(planet3));
        assertFalse(testPlanet.equals(planet4));
    }

}
