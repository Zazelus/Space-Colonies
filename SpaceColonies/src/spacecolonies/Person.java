// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mansour Najah (mansourn)
package spacecolonies;

/**
 * The person class contains a person's name, skills and preference for a
 * certain planet.
 *
 * @author Mansour Najah (mansourn)
 * @version 04.13.2020
 */
public class Person {

    private String name;
    private String planetPreference;

    private Skills skills;

    /**
     * Creates a new Person() object.
     *
     * @param id     is the person's name.
     * @param agri   is the value of their agricultural skill.
     * @param medi   is the value of their medicine skill.
     * @param tech   is the value of their technology skill.
     * @param planet is their preference of planet.
     */
    public Person(String id, int agri, int medi, int tech, String planet) {
        name = id;
        planetPreference = planet;

        skills = new Skills(agri, medi, tech);
    }

    /**
     * Creates a new Person() object. This constructor takes an a
     * lready existing
     * skills object.
     *
     * @param id     is the person's name.
     * @param ski    is the person's skills.
     * @param planet is their preference of planet.
     */
    public Person(String id, Skills ski, String planet) {
        name = id;
        planetPreference = planet;

        skills = ski;
    }

    /**
     * Gets the person's name.
     *
     * @return the string value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the person's skills.
     *
     * @return the corresponding skills object
     */
    public Skills getSkills() {
        return skills;
    }

    /**
     * Gets the person's planet preference.
     *
     * @return the string value of planetPreference
     */
    public String getPlanetName() {
        return planetPreference;
    }

    /**
     * The toString() representation of this class.
     *
     * @return should return a string similar to
     *     “Jane Doe A:3 M:2 T:1 Wants: Nars”
     */
    public String toString() {
        StringBuilder strBuild = new StringBuilder();

        strBuild.append(name);

        strBuild.append(" ");

        strBuild.append(skills.toString());

        if (planetPreference.length() > 0) {
            strBuild.append(" ");

            strBuild.append("Wants: ");
            strBuild.append(planetPreference);
        }

        else {
            strBuild.insert(0, "No-Planet ");
        }

        return strBuild.toString();
    }

    /**
     * The equals() method to compare skills with another object.
     *
     * @param obj is the object to be compared to
     * @return true if it is compared to itself, or another object with the same
     *         exact name, planet preference and skills, false otherwise.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if ((obj == null)) {
            return false;
        }

        if (this.getClass() == obj.getClass()) {
            Person other = (Person) obj;

            return ((this.name.equals(other.name)) &&
                    (this.planetPreference.equals(other.getPlanetName()))
                    && (this.skills.equals(other.getSkills())));
        }

        return false;
    }

}
