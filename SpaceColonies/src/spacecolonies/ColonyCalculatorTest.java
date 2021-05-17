// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mansour Najah (mansourn)
package spacecolonies;

/**
 * Tests the ColonyCalculator class.
 *
 * @author Mansour Najah (mansourn)
 * @version 04.16.2020
 */
public class ColonyCalculatorTest extends student.TestCase {

    private ColonyCalculator colonyCalc;

    private ArrayQueue<Person> applicantQueue;

    private Planet[] planets;

    /**
     * Instantiates the ColonyCalculator, ArrayQueue and Planet[] objects for
     * testing.
     */
    public void setUp() {
        planets = new Planet[4];

        planets[1] = new Planet("Planet1", 1, 1, 1, 10);
        planets[2] = new Planet("Planet2", 4, 4, 4, 9);
        planets[3] = new Planet("Planet3", 1, 2, 1, 18);

        applicantQueue = new ArrayQueue<Person>();

        applicantQueue.enqueue(new Person("Normal1", 3, 3, 3, "Planet1"));
        applicantQueue.enqueue(new Person("Normal2", 3, 3, 3, "Planet2"));
        applicantQueue.enqueue(new Person("Normal3", 3, 3, 3, ""));
        applicantQueue.enqueue(new Person("Normal4", 1, 1, 1, ""));
        applicantQueue.enqueue(new Person("Normal5", 5, 5, 5, ""));

        colonyCalc = new ColonyCalculator(applicantQueue, planets);
    }

    /**
     * Tests the ColonyCalculator constructor when queue is null.
     */
    @SuppressWarnings("unused")
    public void testColonyCalcNullParam() {
        ColonyCalculator nullColony = null;

        Exception thrown = null;

        try {
            nullColony = new ColonyCalculator(null, planets);
        }
        catch (Exception exception) {
            thrown = exception;
        }

        assertNotNull(thrown);

        assertTrue(thrown instanceof IllegalArgumentException);
    }

    /**
     * Tests to see if the method getQueue() is working as intended.
     */
    public void testGetQueue() {
        assertEquals(11, colonyCalc.getQueue().getLength());
    }

    /**
     * Tests to see if the method getPlanets() is working as intended.
     */
    public void testGetPlanets() {
        assertEquals(4, ColonyCalculator.getPlanets().length);
    }

    /**
     * Tests to see if the method getPlanetForPerson() is working as intended.
     */
    public void testGetPlanetForPerson() {
        assertEquals(planets[1],
                colonyCalc.getPlanetForPerson(applicantQueue.getFront()));

        applicantQueue.dequeue();

        assertNull(colonyCalc.getPlanetForPerson(applicantQueue.getFront()));

        // Null person case

        applicantQueue.dequeue();
        applicantQueue.dequeue();

        Person nullPerson = null;

        assertNull(colonyCalc.getPlanetForPerson(nullPerson));
    }

    /**
     * Tests to see if the method getPlanetForPerson() is working as intended.
     *
     * This test is for the case when there is no planet preference and all
     * planets are full.
     */
    public void testGetPlanetForPersonNoPrefFullPlanets() {
        Planet[] planetsTest = new Planet[4];

        planetsTest[1] = new Planet("Planet1", 1, 1, 1, 0);
        planetsTest[2] = new Planet("Planet2", 2, 2, 2, 0);
        planetsTest[3] = new Planet("Planet3", 3, 3, 3, 0);

        ArrayQueue<Person> testQueue = new ArrayQueue<Person>();

        testQueue.enqueue(new Person("person", 3, 3, 3, ""));

        ColonyCalculator testCalc = new ColonyCalculator(testQueue,
            planetsTest);

        assertNull(testCalc.getPlanetForPerson(testQueue.getFront()));

    }

    /**
     * Tests to see if the method getPlanetForPerson() is working as intended.
     *
     * This test is for the case when a person has no planet preference.
     */
    public void testGetPlanetForPersonNoPref() {
        for (int i = 0; i < 4; i++) {
            colonyCalc.getQueue().dequeue();
        }

        assertEquals(colonyCalc.getPlanetForPerson(applicantQueue.getFront()),
                planets[3]);
    }

    /**
     * Tests to see if the method accept() is working as intended.
     */
    public void testAccept() {
        assertTrue(colonyCalc.accept());

        assertFalse(colonyCalc.accept());
        assertFalse(colonyCalc.accept());
        assertFalse(colonyCalc.accept());
        assertFalse(colonyCalc.accept());

        colonyCalc.getQueue().clear();

        assertFalse(colonyCalc.accept());
    }

    /**
     * Tests to see if the method reject() is working as intended.
     */
    public void testReject() {
        colonyCalc.reject();

        assertEquals(1, colonyCalc.getRejectBus().getLength());
    }

    /**
     * Tests to see if the method planetByNumber() is working as intended.
     */
    public void testPlanetByNumber() {
        assertEquals(planets[1], colonyCalc.planetByNumber(1));
        assertEquals(planets[2], colonyCalc.planetByNumber(2));
        assertEquals(planets[3], colonyCalc.planetByNumber(3));

        assertNull(colonyCalc.planetByNumber(9));
    }

    /**
     * Tests to see if the method getPlanetIndex() is working as intended.
     */
    public void testGetPlanetIndex() {
        assertEquals(2, colonyCalc.getPlanetIndex("Planet2"));
        assertEquals(0, colonyCalc.getPlanetIndex("Planet 0"));

        String nullString = null;

        assertEquals(0, colonyCalc.getPlanetIndex(nullString));
    }

    /**
     * Tests to see if the method getPreferredPlanet() is working as intended.
     */
    public void testGetPreferredPlanet() {
        assertEquals(planets[1],
                colonyCalc.getPlanetForPerson(applicantQueue.dequeue()));

        planets[2] = new Planet("Planet2", 0, 0, 0, 0);

        assertNull(colonyCalc.getPlanetForPerson(applicantQueue.dequeue()));

        colonyCalc.getQueue().dequeue();
        colonyCalc.getQueue().dequeue();
        colonyCalc.getQueue().dequeue();

        applicantQueue.enqueue(new Person("Random1", 0, 0, 0, "Planet1"));
        assertNull(colonyCalc.getPlanetForPerson(applicantQueue.dequeue()));

        applicantQueue.enqueue(new Person("Random2", 1, 1, 1, "Planet2"));
        assertNull(colonyCalc.getPlanetForPerson(applicantQueue.dequeue()));
    }

    /**
     * Tests to see if the method getPlanetForPerson() is working as intended.
     *
     * This case is for when all planets have the same availability and
     * criteria.
     */
    public void testManyPlanetsSameCapacity() {
        planets[1] = new Planet("Planet1", 1, 1, 1, 10);
        planets[2] = new Planet("Planet2", 1, 1, 1, 10);
        planets[3] = new Planet("Planet3", 1, 1, 1, 10);

        planets[1].addPerson(new Person("Random", 1, 1, 1, ""));
        planets[2].addPerson(new Person("Random", 1, 1, 1, ""));
        planets[3].addPerson(new Person("Random", 1, 1, 1, ""));

        colonyCalc.getQueue().clear();

        applicantQueue.enqueue(new Person("Max", 5, 5, 5, ""));

        assertEquals(planets[3],
                colonyCalc.getPlanetForPerson(applicantQueue.dequeue()));

    }

}
