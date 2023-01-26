package assign02;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CS2420StudentTester {
    private CS2420Class emptyClass, verySmallClass, smallClass;
    private CS2420Student john;
    private EmailAddress testEmail;

    @BeforeEach
    void setUp() throws Exception {
        testEmail = new EmailAddress("test", "testsite.com");
        john = new CS2420Student("john", "doe", 123456789, testEmail);


    }

    @Test
    void testGetContactInfo() {
        assertEquals(john.getContactInfo(), testEmail);
    }

    @Test
    void testAddingScore() {

    }

    @Test
    void testComputeFinalScore() {
        john.clear();
        john.addScore(100, "exam");
        john.addScore(100, "lab");
        john.addScore(100, "assignment");
        john.addScore(100, "quiz");
        assertEquals(john.computeFinalScore(), 100);
        john.clear();
        john.addScore(50, "exam");
        john.addScore(50, "lab");
        john.addScore(50, "assignment");
        john.addScore(50, "quiz");
        assertEquals(john.computeFinalScore(), 50);
        john.clear();
        john.addScore(80, "exam");
        john.addScore(70, "lab");
        john.addScore(90, "assignment");
        john.addScore(60, "quiz");
        assertEquals(john.computeFinalScore(), 81);
        john.clear();
        john.addScore(80, "exam");
        john.addScore(75, "exam");
        john.addScore(70, "lab");
        john.addScore(90, "assignment");
        john.addScore(30, "assignment");
        john.addScore(60, "quiz");
        assertEquals(john.computeFinalScore(), 68);
        john.clear();
    }

    @Test
    void testComputeFinalScoreExam() {
        john.clear();
        john.addScore(64, "exam");
        john.addScore(100, "lab");
        john.addScore(100, "assignment");
        john.addScore(100, "quiz");
        assertEquals(john.computeFinalScore(), 64);

    }

    @Test
    void testComputeFinalGrade() {
        john.clear();
        john.addScore(100, "exam");
        john.addScore(100, "lab");
        john.addScore(100, "assignment");
        john.addScore(100, "quiz");
        assertEquals("A", john.computeFinalGrade());
        john.clear();
        john.addScore(80, "exam");
        john.addScore(70, "lab");
        john.addScore(90, "assignment");
        john.addScore(60, "quiz");
        assertEquals("B-", john.computeFinalGrade());
        john.clear();
        john.addScore(50, "exam");
        john.addScore(50, "lab");
        john.addScore(50, "assignment");
        john.addScore(50, "quiz");
        assertEquals("E", john.computeFinalGrade());
    }
}
