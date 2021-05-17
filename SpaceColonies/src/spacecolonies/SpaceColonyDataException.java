// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mansour Najah (mansourn)
package spacecolonies;

/**
 * Handles exceptions related to data and stuff.
 *
 * @author Mansour Najah <mansourn>
 * @version <04.13.2020>
 */
public class SpaceColonyDataException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Exceptions related to parsing data.
     *
     * @param e is the error message
     */
    public SpaceColonyDataException(String e) {
        super(e);
    }

}
