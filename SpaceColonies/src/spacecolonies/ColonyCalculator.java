// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mansour Najah (mansourn)
package spacecolonies;

import list.AList;

/**
 * The ColonyCalculator class is in charge of determining whether applicants are
 * eligible for a specific colony or not.
 *
 * @author Mansour Najah (mansourn)
 * @version 04.13.2020
 */
public class ColonyCalculator {

    /**
     * The maximum number of planets.
     */
    public static final int NUM_PLANETS = 3;

    /**
     * The minimum skill level for skills.
     */
    public static final int MIN_SKILL_LEVEL = 1;

    /**
     * The maxiumum skill level for skills.
     */
    public static final int MAX_SKILL_LEVEL = 5;

    private ArrayQueue<Person> applicantQueue;

    private AList<Person> rejectBus;

    private static Planet[] planets;

    /**
     * Creates a new ColonyCalculator class.
     *
     * @param queue       is the queue of applicants
     * @param planetArray is the array of planet colonies
     */
    public ColonyCalculator(ArrayQueue<Person> queue, Planet[] planetArray) {
        if (queue == null) {
            throw new IllegalArgumentException();
        }

        applicantQueue = queue;
        planets = planetArray;
        rejectBus = new AList<Person>();
    }

    /**
     * Determines whether the next person in queue is eligible for
     * their preferred
     * planet, if they don't have a preference or the requirements
     * are not met, they
     * are put in the most available.
     *
     * @param nextPerson the next person in queue to be tested
     * @return the planet that accepts them
     */
    public Planet getPlanetForPerson(Person nextPerson) {

        if (nextPerson == null) {
            return null;
        }

        String planetName = nextPerson.getPlanetName();

        int planetIndex = getPlanetIndex(planetName);

        if (planetIndex != 0) {
            return getPreferredPlanet(nextPerson, planetIndex);
        }

        return getMostAvailablePlanet(nextPerson);
    }

    /**
     * A helper method for getPlanetForPerson that checks to see if the person's
     * preferred planet is available if they have met its requirements.
     *
     * @param nextPerson the person with the preference
     * @param index      refers to the planet they want
     * @return their preference as a planet object
     */
    private Planet getPreferredPlanet(Person nextPerson, int index) {
        if (planets[index].isQualified(nextPerson)
                && !planets[index].isFull()) {
            return planets[index];
        }

        return null;
    }

    /**
     * Returns the most available planet for a person p
     *
     * @param nextPerson the person to be placed in a planet
     * @return the planet that is most available
     */
    private Planet getMostAvailablePlanet(Person nextPerson) {
        Planet availablePlanet = null;

        int mostSpaceAvailable = 0;

        for (int i = 1; i < planets.length; i++) {
            if (planets[i].isQualified(nextPerson)
                    && planets[i].getAvailability() >= mostSpaceAvailable
                    && planets[i].getCapacity() > 0) {

                mostSpaceAvailable = planets[i].getAvailability();
                availablePlanet = planets[i];
            }

        }

        return availablePlanet;
    }

    /**
     * CHecks if the applicant's acceptance was successful.
     *
     * @return true if it was successful, false otherwise
     */
    public boolean accept() {
        boolean accepted = false;

        if (!applicantQueue.isEmpty()) {
            Person person = applicantQueue.getFront();

            Planet prefferedPlanet = getPlanetForPerson(person);

            if (prefferedPlanet != null) {
                prefferedPlanet.addPerson(applicantQueue.getFront());

                applicantQueue.dequeue();

                accepted = true;
            }

        }

        return accepted;
    }

    /**
     * Rejects an applicant by removing them from the queue and
     * adding them to the
     * reject bus.
     */
    public void reject() {
        rejectBus.add(applicantQueue.dequeue());
    }

    /**
     * Gets a specific planet.
     *
     * @param planet is the planet to be returned
     * @return the planet specified
     */
    public Planet planetByNumber(int planet) {
        if (planet > 3) {
            return null;
        }

        return planets[planet];
    }

    /**
     * Return the int representation for the given String (planet name)
     *
     * @param planet being found
     * @return the index of the planet
     */
    public int getPlanetIndex(String planet) {
        if (planet == null) {
            return 0;
        }
        for (int i = 1; i < planets.length; i++) {
            if (planets[i].getName().equals(planet)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Gets the queue of applicants.
     *
     * @return the applicant queue
     */
    public ArrayQueue<Person> getQueue() {
        return applicantQueue;
    }

    /**
     * Gets the array of planets.
     *
     * @return the array of planets
     */
    public static Planet[] getPlanets() {
        return planets;
    }

    /**
     * Gets the list of rejected applicants.
     *
     * @return the reject bus list
     */
    public AList<Person> getRejectBus() {
        return rejectBus;
    }

}
