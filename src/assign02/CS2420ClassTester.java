package assign02;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for CS2420Class.
 * 
 * @author Erin Parker and ??
 * @version January 20, 2022
 */
public class CS2420ClassTester {

    private CS2420Class emptyClass, verySmallClass, smallClass, largeClass;

    @BeforeEach
    void setUp() throws Exception {
        emptyClass = new CS2420Class();

        verySmallClass = new CS2420Class();
        verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, new EmailAddress("hi", "gmail.com")));
        verySmallClass.addStudent(new CS2420Student("Drew", "Hall", 2323232, new EmailAddress("howdy", "gmail.com")));
        verySmallClass.addStudent(new CS2420Student("Riley", "Nguyen", 4545454, new EmailAddress("hello", "gmail.com")));

        smallClass = new CS2420Class();
        smallClass.addAll("src/assign02/a_small_2420_class.txt");

        largeClass = new CS2420Class();
        for (int i = 0; i < 500; i++) {
            String first = (char) ('A' + i % 26) + "" + (char) ('b' + i % 26);
            String last = (char) ('C' + i % 26) + "" + (char) ('d' + i % 26);
            EmailAddress tempEmail = new EmailAddress(first, last);
            int uNID = 1000000 + i;
            CS2420Student student = new CS2420Student(first, last, uNID, tempEmail);
            largeClass.addStudent(student);
            student.addScore(65 + i % 20, "assignment");
            student.addScore(85, "exam");
            student.addScore(92 + i % 10, "lab");
            student.addScore(43, "lab");
            student.addScore(27 + i % 20, "quiz");
            student.addScore(98, "quiz");

        }
    }

    // Empty CS 2420 class tests --------------------------------------------------------------------------

    @Test
    public void testEmptyLookupUNID() {
        assertNull(emptyClass.lookup(1234567));
    }

    @Test
    public void testEmptyLookupContactInfo() {
        ArrayList<CS2420Student> students = emptyClass.lookup(new EmailAddress("hello", "gmail.com"));
        assertEquals(0, students.size());
    }

    @Test
    public void testEmptyAddScore() {
        // ensure no exceptions thrown
        emptyClass.addScore(1234567, 100, "assignment");
    }

    @Test
    public void testEmptyClassAverage() {
        assertEquals(0, emptyClass.computeClassAverage(), 0);
    }

    @Test
    public void testEmptyContactList() {
        ArrayList<EmailAddress> contactList = emptyClass.getContactList();
        assertEquals(0, contactList.size());
    }

    // Very small CS 2420 class tests --------------------------------------------------------------------

    @Test
    public void testVerySmallLookupUNID() {
        UofUStudent expected = new UofUStudent("Drew", "Hall", 2323232);
        CS2420Student actual = verySmallClass.lookup(2323232);
        assertEquals(expected, actual);
    }

    @Test
    public void testVerySmallLookupContactInfo() {
        UofUStudent expectedStudent = new UofUStudent("Riley", "Nguyen", 4545454);
        ArrayList<CS2420Student> actualStudents = verySmallClass.lookup(new EmailAddress("hello", "gmail.com"));
        assertEquals(1, actualStudents.size());
        assertEquals(expectedStudent, actualStudents.get(0));
    }

    @Test
    public void testVerySmallAddDuplicateStudent() {
        boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101,
                new EmailAddress("hi", "gmail.com")));
        assertFalse(actual);
    }

    @Test
    public void testVerySmallAddNewStudent() {
        boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010100,
                new EmailAddress("hi", "gmail.com")));
        assertTrue(actual);
    }

    @Test
    public void testVerySmallStudentFinalScore0() {
        CS2420Student student = verySmallClass.lookup(2323232);
        student.addScore(86.5, "assignment");
        student.addScore(75, "exam");
        student.addScore(89.2, "quiz");
        assertEquals(0, student.computeFinalScore(), 0);
    }

    @Test
    public void testVerySmallStudentFinalGradeNA() {
        CS2420Student student = verySmallClass.lookup(2323232);
        student.addScore(86.5, "assignment");
        student.addScore(75, "exam");
        student.addScore(100, "lab");
        assertEquals("N/A", student.computeFinalGrade());
    }

    @Test
    public void testVerySmallStudentFinalScore() {
        CS2420Student student = verySmallClass.lookup(2323232);
        student.addScore(86.5, "assignment");
        student.addScore(55, "exam");
        student.addScore(90, "lab");
        student.addScore(89.2, "quiz");
        student.addScore(99, "assignment");
        student.addScore(80, "lab");
        student.addScore(77.7, "quiz");
        assertEquals(55, student.computeFinalScore(), 0.001);
    }

    @Test
    public void testVerySmallStudentFinalGrade() {
        CS2420Student student = verySmallClass.lookup(2323232);
        student.addScore(86.5, "assignment");
        student.addScore(75, "exam");
        student.addScore(90, "lab");
        student.addScore(89.2, "quiz");
        student.addScore(99, "assignment");
        student.addScore(80, "lab");
        student.addScore(77.7, "quiz");
        assertEquals("B", student.computeFinalGrade());
    }

    @Test
    public void testVerySmallStudentComputeScoreTwice() {
        CS2420Student student = verySmallClass.lookup(2323232);
        student.addScore(86.5, "assignment");
        student.addScore(75, "exam");
        student.addScore(90, "lab");
        student.addScore(89.2, "quiz");
        student.addScore(99, "assignment");
        student.addScore(80, "lab");
        student.addScore(77.7, "quiz");
        student.computeFinalScore();

        student.addScore(70, "lab");
        student.addScore(54.5, "exam");

        assertEquals(64.75, student.computeFinalScore(), 0.001);
    }

    @Test
    public void testVerySmallUpdateName() {
        verySmallClass.lookup(1010101).updateName("John", "Doe");
        ArrayList<CS2420Student> students = verySmallClass.lookup(new EmailAddress("hi", "gmail.com"));
        assertEquals("John", students.get(0).getFirstName());
        assertEquals("Doe", students.get(0).getLastName());
    }

    // Small CS 2420 class tests -------------------------------------------------------------------------

    @Test
    public void testSmallLookupContactInfo() {
        UofUStudent expectedStudent1 = new UofUStudent("Kennedy", "Miller", 888888);
        UofUStudent expectedStudent2 = new UofUStudent("Taylor", "Miller", 999999);

        ArrayList<CS2420Student> actualStudents = smallClass.lookup(new EmailAddress("we_love_puppies", "geemail.com"));
        assertEquals(2, actualStudents.size());
        assertTrue(actualStudents.contains(expectedStudent1));
        assertTrue(actualStudents.contains(expectedStudent2));
    }

    @Test
    public void testSmallGetContactList() {
        ArrayList<EmailAddress> actual = smallClass.getContactList();
        assertEquals(9, actual.size());
    }

    @Test
    public void testSmallStudentFinalScore() {
        CS2420Student student = smallClass.lookup(333333);
        assertEquals(95.5345, student.computeFinalScore(), 0.001);
    }

    @Test
    public void testSmallComputeClassAverage() {

        assertEquals(78.356, smallClass.computeClassAverage(), 0.001);
    }


    // Large CS 2420 class tests -------------------------------------------------------------------------


    @Test
    public void testLargeLookupUNID() {
        CS2420Student actual = largeClass.lookup(1000011);
        assertNotNull(actual);
    }

    @Test
    public void testLargeLookupContactInfo() {
        EmailAddress tempMail = new EmailAddress("test", "testDomain.com");
        CS2420Student expectedStudent = new CS2420Student("Riley", "Nguyen", 4545454, tempMail);
        largeClass.addStudent(expectedStudent);

        ArrayList<CS2420Student> actualStudents = largeClass.lookup(new EmailAddress("test", "testDomain.com"));
        assertEquals(1, actualStudents.size());
        assertEquals(expectedStudent, actualStudents.get(0));
    }

    @Test
    public void testLargeAddDuplicateStudent() {
        largeClass.addStudent(new CS2420Student("Jane", "Doe", 1010101,
                new EmailAddress("hi", "gmail.com")));
        boolean actual = largeClass.addStudent(new CS2420Student("Jane", "Doe", 1010101,
                new EmailAddress("hi", "gmail.com")));
        assertFalse(actual);
    }

    @Test
    public void testLargeAddNewStudent() {
        boolean actual = largeClass.addStudent(new CS2420Student("Jane", "Doe", 1010100,
                new EmailAddress("hi", "gmail.com")));
        assertTrue(actual);
    }

    @Test
    public void testLargeStudentFinalScore0() {
        CS2420Student student = largeClass.lookup(1000100);
        assertEquals(73, student.computeFinalScore(), 0);
    }

    @Test
    public void testLargeStudentFinalGradeNA() {
        CS2420Student student = largeClass.lookup(1000055);
        student.clear();
        student.addScore(98, "assignment");
        student.addScore(76, "exam");
        student.addScore(86, "lab");
        assertEquals("N/A", student.computeFinalGrade());
    }

    @Test
    public void testLargeStudentFinalScore() {
        CS2420Student student = largeClass.lookup(1000132);
        assertEquals(78.499, student.computeFinalScore(), 0.001);
    }

    @Test
    public void testLargeStudentFinalGrade() {
        CS2420Student student = largeClass.lookup(1000006);
        assertEquals("C", student.computeFinalGrade());
    }

    @Test
    public void testLargeStudentComputeScoreTwice() {
        CS2420Student student = largeClass.lookup(1000026);
        student.addScore(86.5, "assignment");
        student.addScore(75, "exam");
        student.addScore(90, "lab");
        student.addScore(89.2, "quiz");
        student.addScore(99, "assignment");
        student.addScore(80, "lab");
        student.addScore(77.7, "quiz");
        student.computeFinalScore();

        student.addScore(70, "lab");
        student.addScore(54.5, "exam");
        assertEquals(77.867, student.computeFinalScore(), 0.001);
    }

    @Test
    public void testLargeUpdateName() {
        largeClass.lookup(1000499).updateName("Johnny Depp", "Amber Heard");
        CS2420Student student = largeClass.lookup(1000499);
        assertEquals("Johnny Depp", student.getFirstName());
        assertEquals("Amber Heard", student.getLastName());
    }

}
