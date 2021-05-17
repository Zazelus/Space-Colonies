// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// -- Mansour Najah (mansourn)
package spacecolonies;

import java.awt.Color;
import CS2114.Button;
import CS2114.CircleShape;
import CS2114.Shape;
import CS2114.TextShape;
import CS2114.Window;
import CS2114.WindowSide;
import spacecolonies.ColonyCalculator;

/**
 * The SpaceWindow class handles the gui front-end
 *
 * @author Mansour Najah (mansourn)
 * @version 04.16.2020
 */
public class SpaceWindow {

    private Window window;

    private TextShape applicantInfo;

    private TextShape planet1;
    private TextShape planet2;
    private TextShape planet3;

    private ColonyCalculator colonyCalculator;

    private Button accept;
    private Button reject;

    int circles = ColonyCalculator.NUM_PLANETS;

    public static final int QUEUE_STARTX = 50;


    public static final int QUEUE_STARTY = 100;


    public static final int CIRCLE_SIZE = 50;

    public static final int SQUARE_SIZE = 100;

    public static final int PLANET_STARTX = 100;
    public static final int PLANET_STARTY = 275;


    /**
     * Creates a new SpaceWindow with buttons and text shapes.
     *
     * @param calculator
     *            is the ColonyCalculator object
     * @throws SpaceColonyDataException
     *             if the ColonyCalculator object is null
     */
    public SpaceWindow(ColonyCalculator calculator) throws SpaceColonyDataException {
        colonyCalculator = calculator;

        if (colonyCalculator == null) {
            throw new SpaceColonyDataException(
                "Error: colonyCalculator was null!");
        }

        Planet firstPlanet = calculator.planetByNumber(1);
        Planet secondPlanet = calculator.planetByNumber(2);
        Planet thirdPlanet = calculator.planetByNumber(3);

        window = new Window("Space Colony Placement");

        accept = new Button("ACCEPT");
        reject = new Button("REJECT");

        accept.onClick(this, "clickedAccept");
        reject.onClick(this, "clickedReject");

        window.addButton(accept, WindowSide.SOUTH);
        window.addButton(reject, WindowSide.SOUTH);

        if (calculator.getQueue().isEmpty()) {
            applicantInfo = new TextShape(QUEUE_STARTX, QUEUE_STARTY
                    - CIRCLE_SIZE,
                "");
        }

        else {
            applicantInfo = new TextShape(QUEUE_STARTX, QUEUE_STARTY
                    - CIRCLE_SIZE,
                    calculator.getQueue().getFront().toString());
        }

        planet1 = new TextShape(PLANET_STARTX, PLANET_STARTY, firstPlanet.getName()
            + ", 0/10");

        if (secondPlanet != null) {
            planet2 = new TextShape(PLANET_STARTX + SQUARE_SIZE * 3 / 2,
                PLANET_STARTY, secondPlanet.getName() + ", 0/10");
        }

        if (thirdPlanet != null) {
            planet3 = new TextShape(PLANET_STARTX + SQUARE_SIZE * 3,
                PLANET_STARTY, thirdPlanet.getName() + ", 0/10");
        }

        window.addShape(planet1);
        window.addShape(planet2);
        window.addShape(planet3);

        window.addShape(applicantInfo);

        update();
    }


    /**
     * Updates text shapes to match the queue and capacity,
     * also displays an end message when queue is empty.
     */
    public void update() {
        if (colonyCalculator.getQueue().isEmpty()) {
            endGame();
        }

        else {
            window.removeAllShapes();

            applicantInfo.remove();

            applicantInfo = new TextShape(QUEUE_STARTX, QUEUE_STARTY - CIRCLE_SIZE
                * 3 / 2, colonyCalculator.getQueue().getFront().toString());

            addPlanets();

            window.addShape(applicantInfo);

            populatePlanets();
            populateColonists();
        }
    }


    /**
     * Calls the accept method update to match results of accept, if accept
     * returns false, the button should be disabled.
     *
     * @param acceptButton
     *            the is the accept button
     */
    public void clickedAccept(Button acceptButton) {
        boolean accepted = colonyCalculator.accept();

        if (accepted == true) {
            update();
        }

        else {
            acceptButton.disable();
        }
    }


    /**
     * Calls the reject button and updates the queue with that applicant removed.
     *
     * @param rejectButton
     *            the reject button
     */
    public void clickedReject(Button rejectButton) {
        colonyCalculator.reject();

        update();

        accept.enable();
    }

    /**
     * Ends the game when the queue is empty.
     */
    public void endGame() {
        window.removeAllShapes();

        window.removeButton(accept, WindowSide.SOUTH);
        window.removeButton(reject, WindowSide.SOUTH);

        TextShape done = new TextShape(window.getWidth() / 3, window
            .getHeight() / 3, "All applicants processed.");

        window.addShape(done);
    }

    /**
     * Adds all planets and sets their text for all their attributes.
     */
    public void addPlanets() {
        Planet firstPlanet = colonyCalculator.planetByNumber(1);
        Planet secondPlanet = colonyCalculator.planetByNumber(2);
        Planet thirdPlanet = colonyCalculator.planetByNumber(3);

        planet1.setText(firstPlanet.getName() + ", "
            + firstPlanet.getPopulationSize() + "/"
            + firstPlanet.getCapacity());

        TextShape perm = new TextShape(PLANET_STARTX, PLANET_STARTY + 15,
            firstPlanet.getSkills().toString());

        if (secondPlanet != null) {
            planet2.setText(secondPlanet.getName() + ", "
                + secondPlanet.getPopulationSize()
                + "/" + secondPlanet.getCapacity());

            TextShape perma2 = new TextShape(PLANET_STARTX + SQUARE_SIZE * 3
                / 2, PLANET_STARTY + 15, secondPlanet.getSkills().toString());

            window.addShape(perma2);
        }

        if (thirdPlanet != null) {
            planet3.setText(thirdPlanet.getName() + ", " + thirdPlanet.getPopulationSize()
                + "/" + thirdPlanet.getCapacity());

            TextShape perma3 = new TextShape(PLANET_STARTX + SQUARE_SIZE
                * 3, PLANET_STARTY + 15, thirdPlanet.getSkills().toString());

            window.addShape(perma3);
        }

        window.addShape(planet1);
        window.addShape(planet2);
        window.addShape(planet3);

        window.addShape(perm);
    }

    /**
     * Renders the shape for each planet.
     */
    public void populatePlanets() {
        for (int i = 1; i < ColonyCalculator.NUM_PLANETS + 1; i++) {
            Shape planetshape = new Shape(PLANET_STARTX + SQUARE_SIZE * 3
                / 2 * (i - 1), PLANET_STARTY - SQUARE_SIZE, SQUARE_SIZE,
                SQUARE_SIZE);

            Shape fillShape = planetshape;

            Planet curr = colonyCalculator.planetByNumber(i);

            if (curr != null) {
                int newSS = SQUARE_SIZE * curr.getAvailability() / curr
                    .getCapacity();
                fillShape = new Shape(PLANET_STARTX + SQUARE_SIZE * 3 / 2
                    * (i - 1), PLANET_STARTY - SQUARE_SIZE, SQUARE_SIZE, newSS);
                window.moveToFront(fillShape);
            }

            planetshape.setForegroundColor(Color.BLACK);
            fillShape.setForegroundColor(Color.BLACK);
            switch (i) {
                case 1:
                    planetshape.setBackgroundColor(Color.YELLOW);
                    fillShape.setBackgroundColor(Color.ORANGE);
                    break;
                case 2:
                    planetshape.setBackgroundColor(Color.CYAN);
                    fillShape.setBackgroundColor(Color.BLUE);
                    break;
                case 3:
                    planetshape.setBackgroundColor(Color.MAGENTA);
                    fillShape.setBackgroundColor(Color.RED);
                    break;
            }
            window.addShape(planetshape);
            window.addShape(fillShape);
            window.moveToBack(planetshape);
        }
    }

    /**
     * Renders the shape for each applicant.
     */
    public void populateColonists() {
        ArrayQueue<Person> tempQueue = null;

        if (!colonyCalculator.getQueue().isEmpty()) {
            tempQueue = colonyCalculator.getQueue();
        }

        Object[] copy = tempQueue.toArray();

        for (int i = 0; i < tempQueue.getSize(); i++) {
            CircleShape shape = new CircleShape(QUEUE_STARTX + CIRCLE_SIZE
                * i, QUEUE_STARTY, CIRCLE_SIZE);
            String plan = ((Person)copy[i]).getPlanetName();

            int personPlanet = colonyCalculator.getPlanetIndex(plan);

            shape.setBackgroundColor(Color.BLACK);

            switch (personPlanet) {
                case 1:
                    shape.setForegroundColor(Color.YELLOW);
                    break;
                case 2:
                    shape.setForegroundColor(Color.CYAN);
                    break;
                case 3:
                    shape.setForegroundColor(Color.MAGENTA);
                    break;

            }

            window.addShape(shape);
        }
    }

}
