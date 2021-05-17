// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mansour Najah (mansourn)
package spacecolonies;

/**
 * The skills class contains three integers for certain skills that potential
 * future colonists have, rated 1-5.
 *
 * @author Mansour Najah (mansourn)
 * @version 04.13.2020
 */
public class Skills {

    private int agriculture;
    private int medicine;
    private int technology;

    /**
     * Creates a new Skills() object.
     *
     * @param ag   is the agricultural skill, 1-5.
     * @param med  is the medicine skill, 1-5.
     * @param tech is the technology skill, 1-5.
     */
    public Skills(int ag, int med, int tech) {
        agriculture = ag;
        medicine = med;
        technology = tech;
    }

    /**
     * Gets the agricultural skill.
     *
     * @return the numerical value of agriculture
     */
    public int getAgriculture() {
        return agriculture;
    }

    /**
     * Gets the medicine skill.
     *
     * @return the numerical value of medicine
     */
    public int getMedicine() {
        return medicine;
    }

    /**
     * Gets the technology skill.
     *
     * @return the numerical value of technology
     */
    public int getTechnology() {
        return technology;
    }

    /**
     * Compares to another skills object to see if the current object has lower
     * skills across the board.
     *
     * @param other is the compared skills object.
     * @return true if the current has lower or equal skills across the board,
     *         false otherwise.
     */
    public boolean isBelow(Skills other) {
        return ((this.agriculture <= other.agriculture)
                && (this.medicine <= other.medicine)
                && (this.technology <= other.technology));
    }

    /**
     * The toString() representation of this class.
     *
     * @return should return a string similar to
     *         “Jane Doe A:3 M:2 T:1 Wants: Nars”
     */
    public String toString() {
        StringBuilder strBuild = new StringBuilder();

        strBuild.append("A:");
        strBuild.append(this.agriculture);

        strBuild.append(" ");

        strBuild.append("M:");
        strBuild.append(this.medicine);

        strBuild.append(" ");

        strBuild.append("T:");
        strBuild.append(this.technology);

        return strBuild.toString();
    }

    /**
     * The equals() method to compare skills with another object.
     *
     * @param obj is the object to be compared to
     * @return true if it is compared to itself, or another object with the same
     *         exact skills, false otherwise.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if ((obj == null)) {
            return false;
        }

        if (this.getClass() == obj.getClass()) {
            Skills other = (Skills) obj;

            return ((this.agriculture == other.agriculture)
                    && (this.medicine == other.medicine)
                    && (this.technology == other.technology));
        }

        return false;
    }

}
