package spacecolonies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import bsh.ParseException;

public class ColonyReader {

    private Planet[] planets;

    private ArrayQueue<Person> queue;

    @SuppressWarnings("unused")
    private SpaceWindow spaceWindow;

    private ColonyCalculator colonyCalculator;

    public ColonyReader(String applicantFileName, String planetFileName)
            throws FileNotFoundException, ParseException,
            SpaceColonyDataException {
        queue = readQueueFile(applicantFileName);
        planets = readPlanetFile(planetFileName);

        // Instantiate New SpaceWIndow and ColonyCalculator
        colonyCalculator = new ColonyCalculator(queue, planets);

        spaceWindow = new SpaceWindow(colonyCalculator);
    }

    public Planet[] getPlanets() {
        return planets;
    }

    public ArrayQueue<Person> getPeople() {
        return queue;
    }

    private Planet[] readPlanetFile(String planetFile)
            throws FileNotFoundException, ParseException,
            SpaceColonyDataException {
        Planet[] planetArray = new Planet[4];

        @SuppressWarnings("resource")
        Scanner file = new Scanner(new File(planetFile));

        int index = 1;

        while (file.hasNextLine() && index < 4) {
            String line = file.nextLine();
            String[] lineSplit = line.split(", *");

            if (lineSplit.length != 5) {
                throw new ParseException("Does not contain 5 seperated values.");
            }

            else {
                if (isInSkillRange(Integer.parseInt(lineSplit[1]),
                        Integer.parseInt(lineSplit[2]),
                        Integer.parseInt(lineSplit[3]))) {

                    planetArray[index] = new Planet(lineSplit[0],
                            Integer.parseInt(lineSplit[1]),
                            Integer.parseInt(lineSplit[2]),
                            Integer.parseInt(lineSplit[3]),
                            Integer.parseInt(lineSplit[4]));
                    index++;
                }

                else {
                    throw new SpaceColonyDataException("Skills for planet are not between 1 and 5.");
                }
            }

        }

        file.close();
        if (index < 3) {
            throw new SpaceColonyDataException("less than 3 planets");
        }

        return planetArray;
    }

    @SuppressWarnings("resource")
    private ArrayQueue<Person> readQueueFile(String applicantFile)
            throws FileNotFoundException, SpaceColonyDataException {
        ArrayQueue<Person> queue = new ArrayQueue<Person>();

        Scanner file = new Scanner(new File(applicantFile));

        while (file.hasNextLine()) {
            String line = file.nextLine();
            String fourthElement = "";

            String[] lineSplit = line.split(", *");

            if (lineSplit.length > 4) {
                fourthElement = lineSplit[4];

            }

            if (!isInSkillRange(Integer.parseInt(lineSplit[1]),
                    Integer.parseInt(lineSplit[2]),
                    Integer.parseInt(lineSplit[3]))) {

                throw new SpaceColonyDataException("Skills are not between 1 and 5.");
            }

            else {
                queue.enqueue(new Person(lineSplit[0],
                        Integer.parseInt(lineSplit[1]),
                        Integer.parseInt(lineSplit[2]),
                        Integer.parseInt(lineSplit[3]), fourthElement));
            }
        }

        return queue;
    }

    private boolean isInSkillRange(int num1, int num2, int num3) {
        boolean numFirst = (num1 >= ColonyCalculator.MIN_SKILL_LEVEL)
                && (num1 <= ColonyCalculator.MAX_SKILL_LEVEL);
        boolean numSecond = (num2 >= ColonyCalculator.MIN_SKILL_LEVEL)
                && (num2 <= ColonyCalculator.MAX_SKILL_LEVEL);
        boolean numThird = (num3 >= ColonyCalculator.MIN_SKILL_LEVEL)
                && (num3 <= ColonyCalculator.MAX_SKILL_LEVEL);

        return numFirst && numSecond && numThird;
    }

}
