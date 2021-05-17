// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// -- Mansour Najah (mansourn)
package spacecolonies;

import java.io.FileNotFoundException;
import bsh.ParseException;
import spacecolonies.ColonyReader;

/**
 * The ProjectRunner class runs the application and parses file input.
 *
 * @author Mansour Najah (mansourn)
 * @version 04.13.2020
 */
public class ProjectRunner {

    @SuppressWarnings("unused")
    private static ColonyReader colonyReader;

    /**
     * Main method to execute the program
     *
     * @param args can be the files for input.
     * @throws ParseException           handles a parse exception
     * @throws FileNotFoundException    handles an exception if a file is not found
     * @throws                          java.text.ParseException if the program
     *                                  can't parse correctly
     * @throws SpaceColonyDataException if there is a problem with the data of space
     *                                  colonies
     */
    public static void main(String[] args)
            throws FileNotFoundException, ParseException,
            SpaceColonyDataException, java.text.ParseException {
        if (args.length == 2) {
            colonyReader = new ColonyReader(args[0], args[1]);
        }

        colonyReader = new ColonyReader("input.txt", "planets.txt");
    }
}
