package assign09;

import java.text.DecimalFormat;

/**
 * This class provides a simple representation for a University of Utah student.
 * Object's hashCode method is overridden with a correct hash function for this
 * object, but this one does a good/decent job of distributing students in a hash
 * table.
 *
 * @author Erin Parker and Philippe Gonzalez and Conner Francis
 * @version April 3, 2023
 */
public class StudentGoodHash {
    private int uid;
    private String firstName;
    private String lastName;

    /**
     * Creates a new student with the specified uid, firstName, and lastName.
     *
     * @param uid
     * @param firstName
     * @param lastName
     */
    public StudentGoodHash(int uid, String firstName, String lastName) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * @return the UID for this student object
     */
    public int getUid() {
        return this.uid;
    }

    /**
     * @return the first name for this student object
     */

    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @return the last name for this student object
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * @return true if this student and 'other' have the same UID, first name, and last name; false otherwise
     */
    public boolean equals(Object other) {
        // change to StudentMediumHash and StudentGoodHash for two new classes
        if(!(other instanceof StudentBadHash))
            return false;

        StudentGoodHash rhs = (StudentGoodHash) other;

        return this.uid == rhs.uid && this.firstName.equals(rhs.firstName) && this.lastName.equals(rhs.lastName);
    }

    /**
     * @return a textual representation of this student
     */
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("0000000");
        return firstName + " " + lastName + " (u" + formatter.format(uid) + ")";
    }
    /**
     * turn this object into an int value
     * @return first name hashcode + the last name hashcode * uid
     */
    public int hashCode() {
        return firstName.hashCode() + lastName.hashCode() * uid;
    }
}
