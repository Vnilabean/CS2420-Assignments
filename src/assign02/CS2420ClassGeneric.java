package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class CS2420ClassGeneric<Type> {

    /**
     * Creates an empty CS 2420 class.
     */
    public CS2420ClassGeneric() {

        studentList = new ArrayList<CS2420Student>();
    }

    private ArrayList<CS2420Student> studentList;
    /**
     * Creates a student from the given first name, last name, and uNID.
     *
     * @param firstName
     * @param lastName
     * @param uNID
     */


    /**
     * Returns the list of CS 2420 students in this class, sorted by uNID in ascending order.
     */
    public ArrayList<CS2420StudentGeneric<Type>> getOrderedByUNID() {
        ArrayList<CS2420StudentGeneric<Type>> studentListCopy = new ArrayList<CS2420StudentGeneric<Type>>();
        for(CS2420StudentGeneric<Type> student : studentListCopy)
            studentListCopy.add(student);

        sort(studentListCopy, new OrderByUNID());

        return studentListCopy;
    }

    /**
     * Returns the list of CS 2420 students in this class, sorted by last name in lexicographical order.
     * Breaks ties in last names using first names (lexicographical order).
     * Breaks ties in first names using uNIDs (ascending order).
     */
    public ArrayList<CS2420StudentGeneric<Type>> getOrderedByName() {
        // FILL IN — do not return null
        return null;
    }

    /**
     * Returns the list of CS 2420 students in this class with a final score of at least cutoffScore,
     * sorted by final score in descending order.
     * Breaks ties in final scores using uNIDs (ascending order).
     *
     * @param cutoffScore - value that a student's final score must be at or above to be included
     *                      in the returned list
     */
    public ArrayList<CS2420StudentGeneric<Type>> getOrderedByScore(double cutoffScore) {
        // FILL IN — do not return null
        return null;
    }

    /**
     * Performs a SELECTION SORT on the input ArrayList.
     *
     * 1. Finds the smallest item in the list.
     * 2. Swaps the smallest item with the first item in the list.
     * 3. Reconsiders the list be the remaining unsorted portion (second item to Nth item) and
     *    repeats steps 1, 2, and 3.
     */
    private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
        for(int i = 0; i < list.size() - 1; i++) {
            int j, minIndex;
            for(j = i + 1, minIndex = i; j < list.size(); j++)
                if(c.compare(list.get(j), list.get(minIndex)) < 0)
                    minIndex = j;
            ListType temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }



    /**
     * Comparator that defines an ordering among CS 2420 students using their uNIDs.
     * uNIDs are guaranteed to be unique, making a tie-breaker unnecessary.
     */
    protected class OrderByUNID implements Comparator<CS2420StudentGeneric<Type>> {

        /**
         * Returns a negative value if lhs (left-hand side) is smaller than rhs (right-hand side).
         * Returns a positive value if lhs is larger than rhs.
         * Returns 0 if lhs and rhs are equal.
         */
        public int compare(CS2420StudentGeneric<Type> lhs, CS2420StudentGeneric<Type> rhs) {
            return lhs.getUNID() - rhs.getUNID();
        }
    }

    /**
     * Comparator that defines an ordering among CS 2420 students using their names.
     * Compares by last name, then first name (if last names are the same), then uNID
     * (if both names are the same).  uNIDs are guaranteed to be unique.
     */
    protected class OrderByName implements Comparator<CS2420StudentGeneric<Type>> {
        @Override
        public int compare(CS2420StudentGeneric<Type> o1, CS2420StudentGeneric<Type> o2) {
            return 0;
        }
        // FILL IN
    }





    /**
     * Adds the given student to the collection of students in CS 2420, avoiding duplicates.
     *
     * @param student - student to be added to this CS 2420 class
     * @return true if the student was added,
     *         false if the student was not added because they already exist in the collection
     */
    public boolean addStudent(CS2420Student student) {
        if (studentList.contains(student))
            return false;
        else
            return true;
    }

    /**
     * Retrieves the CS 2420 student with the given uNID.
     *
     * @param uNID - uNID of student to be retrieved
     * @return the CS 2420 student with the given uNID, or null if no such student exists in the collection
     */
    public CS2420Student lookup(int uNID) {
        for(CS2420Student s: studentList)
            if (s.getUNID() == uNID)
                return s;
        return null;
    }

    /**
     * Retrieves the CS 2420 student(s) with the given contact information.
     *
     * @param contactInfo - contact information of student(s) to be retrieved
     * @return a list of the CS 2420 student(s) with the given contact information (in any order),
     * 	     or an empty list if no such students exist in the collection
     */
    public ArrayList<CS2420Student> lookup(EmailAddress contactInfo) {
        var tmp = new ArrayList<CS2420Student>();
        for(CS2420Student s: studentList)
            if (s.getContactInfo() == contactInfo)
                tmp.add(s);
        return tmp;
    }

    /**
     * Adds an assignment, exam, lab, or quiz score for the CS 2420 student with the given uNID.
     *
     * NOTE: If the category string is not one of "assignment", "exam", "lab", or "quiz", or
     *       if no student with the uNID exists in the collection, then this method has no effect.
     *
     * @param uNID - uNID of student whose score is to be added
     * @param score - the score to be added
     * @param category - the category in which to add the score
     */
    public void addScore(int uNID, double score, String category) {
        CS2420Student addStudentScore = lookup(uNID);
        addStudentScore.addScore(score, category);
    }

    /**
     * Computes the average score of all CS 2420 students in the collection.
     *
     * @return the average score, or 0 if there are no students in the collection
     */
    public double computeClassAverage() {
        if(studentList.size() == 0)
            return 0;
        var averageScore = 0.0;
        for (CS2420Student s : studentList)
            averageScore += s.computeFinalScore();
        return averageScore /= studentList.size();
    }

    /**
     * Creates a list of contact information for all CS 2420 students in the collection.
     *
     * @return the duplicate-free list of contact information, in any order
     */
    public ArrayList<EmailAddress> getContactList() {
        var tmp = new ArrayList<EmailAddress>(studentList.size());
        for (CS2420Student s : studentList)
            if(!tmp.contains(s.getContactInfo()))
                tmp.add(s.getContactInfo());
        return tmp;
    }


}
