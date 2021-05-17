// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mansour Najah (mansourn)
package spacecolonies;

/**
 * Tests for the Skills class.
 *
 * @author <Mansour Najah> <mansourn>
 * @version <04.13.2020>
 */
public class SkillsTest extends student.TestCase {

    private Skills skillsZero;
    private Skills skillsNormal;
    private Skills skillsMax;

    /**
     * Instantiates several skills objects to be used in all tests.
     */
    public void setUp() {
        skillsZero = new Skills(0, 0, 0);
        skillsNormal = new Skills(2, 5, 3);
        skillsMax = new Skills(5, 5, 5);
    }

    /**
     * Tests to see if the getAgriculture() method is implemented properly.
     */
    public void testGetAgriculture() {
        assertEquals(skillsZero.getAgriculture(), 0);
        assertEquals(skillsNormal.getAgriculture(), 2);
        assertEquals(skillsMax.getAgriculture(), 5);
    }

    /**
     * Tests to see if the getMedicine() method is implemented properly.
     */
    public void testGetMedicine() {
        assertEquals(skillsZero.getMedicine(), 0);
        assertEquals(skillsNormal.getMedicine(), 5);
        assertEquals(skillsMax.getMedicine(), 5);
    }

    /**
     * Tests to see if the getTechnology() method is implemented properly.
     */
    public void testGetTechnology() {
        assertEquals(skillsZero.getTechnology(), 0);
        assertEquals(skillsNormal.getTechnology(), 3);
        assertEquals(skillsMax.getTechnology(), 5);
    }

    /**
     * Tests to see if the isBelow() method is implemented properly.
     */
    public void testIsBelow() {
        assertTrue(skillsZero.isBelow(skillsNormal));
        assertTrue(skillsNormal.isBelow(skillsMax));

        assertFalse(skillsNormal.isBelow(skillsZero));
        assertFalse(skillsMax.isBelow(skillsZero));
        assertFalse(skillsMax.isBelow(skillsNormal));
    }

    /**
     * Tests to see if the toString() method is implemented properly.
     */
    public void testToString() {
        assertEquals("A:0 M:0 T:0", skillsZero.toString());
        assertEquals("A:2 M:5 T:3", skillsNormal.toString());
        assertEquals("A:5 M:5 T:5", skillsMax.toString());
    }

    /**
     * Tests to see if the equals() method is implemented properly.
     */
    public void testEquals() {
        assertTrue(skillsNormal.equals(skillsNormal));

        assertFalse(skillsZero.equals(skillsNormal));
        assertFalse(skillsZero.equals(skillsMax));
        assertFalse(skillsNormal.equals(skillsMax));

        Skills skillsNormal2 = new Skills(2, 5, 3);

        assertTrue(skillsNormal2.equals(skillsNormal));

        assertFalse(skillsNormal.equals(new Object()));

        Skills nullSkills = null;

        assertFalse(skillsNormal.equals(nullSkills));
    }

}
