// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mansour Najah (mansourn)
package spacecolonies;

/**
 * A planet class.
 *
 * @author <Mansour Najah> <mansourn>
 * @version <04.13.2020>
 */
public class Planet implements Comparable<Planet> {

    private String name;

    private Skills minSkills;

    private Person[] population;

    private int populationSize;
    private int capacity;

    /**
     * Creates a new Planet object.
     *
     * @param planetName is the planet's name.
     * @param planetAgri is the minimum agricultural level.
     * @param planetMedi is the minimum medicine level.
     * @param planetTech is the minimum technology level.
     * @param planetCap  is the capacity of the planet.
     */
    public Planet(String planetName,
            int planetAgri, int planetMedi, int planetTech, int planetCap) {
        name = planetName;
        minSkills = new Skills(planetAgri, planetMedi, planetTech);
        capacity = planetCap;
        population = new Person[capacity];
        populationSize = 0;
    }

    /**
     * Sets a name for the planet.
     *
     * @param newName is the new name
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Returns the current name of the planet.
     *
     * @return the current name of the planet
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current minimum skill requirement.
     *
     * @return the minimum requirements
     */
    public Skills getSkills() {
        return minSkills;
    }

    /**
     * Returns the array containing people on the planet.
     *
     * @return the array containing all person objects
     */
    public Person[] getPopulation() {
        return population;
    }

    /**
     * Returns the current amount of the people on the planet.
     *
     * @return the people on the planet.
     */
    public int getPopulationSize() {
        return populationSize;
    }

    /**
     * Returns the population capacity of the planet.
     *
     * @return the population capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns how many slots are left in the population.
     *
     * @return the difference between capacity and current population size.
     */
    public int getAvailability() {
        return capacity - populationSize;
    }

    /**
     * Checks to see if the planet is full or not.
     *
     * @return true if it is full, false otherwise.
     */
    public boolean isFull() {
        return (capacity - populationSize) <= 0;
    }

    /**
     * Adds a new person to the planet if they are
     * qualified and the planet is not full.
     *
     * @param newPerson is the person to be added
     * @return true if the person was added, false otherwise
     */
    public boolean addPerson(Person newPerson) {
        if (!isFull() && isQualified(newPerson)) {
            population[populationSize] = newPerson;

            populationSize++;

            return true;
        }

        return false;
    }

    /**
     * Checks to see if the person is qualified.
     *
     * @param person the person to be judged
     * @return true if they are qualified, false otherwise.
     */
    public boolean isQualified(Person person) {
        return minSkills.isBelow(person.getSkills());
    }

    /**
     * Returns a toString() implementation of the class.
     *
     * @return the string representation.
     */
    public String toString() {
        StringBuilder strBuild = new StringBuilder();

        strBuild.append(name + ", ");
        strBuild.append("population " + populationSize);
        strBuild.append(" (cap: " + capacity + "), ");
        strBuild.append("Requires: A >= " + minSkills.getAgriculture() + ", ");
        strBuild.append("M >= " + minSkills.getMedicine() + ", T >= ");
        strBuild.append(minSkills.getTechnology());

        return strBuild.toString();
    }

    /**
     * Compares this planet to another.
     *
     * @param other is the planet to be compared to.
     * @return 1 if this planet has more available slots. 0 if they have equal
     *         available slots. -1 if this planet has less available slots.
     */
    public int compareTo(Planet other) {
        if (this.getAvailability() > other.getAvailability()) {
            return 1;
        }

        else if (this.getAvailability() == other.getAvailability()) {
            return 0;
        }

        else {
            return -1;
        }
    }

    /**
     * The equals() method to compare skills with another object.
     *
     * @param obj is the object to be compared to
     * @return true if it is compared to itself, or another object with the same
     *         exact name, minimum skills, and capacity, false otherwise.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if ((obj == null)) {
            return false;
        }

        if (this.getClass() == obj.getClass()) {
            Planet other = (Planet) obj;

            return ((this.name.equals(other.name))
                    && (this.getSkills().equals(other.getSkills()))
                    && (this.getCapacity() == other.getCapacity()));
        }

        return false;
    }

}
