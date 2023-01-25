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
        john.addScore(100, "exams");
        john.addScore(100, "labs");
        john.addScore(100, "assignments");
        john.addScore(100, "quizzes");
        assertEquals(john.computeFinalScore(), 100);
        john.clear();
        john.addScore(50, "exams");
        john.addScore(50, "labs");
        john.addScore(50, "assignments");
        john.addScore(50, "quizzes");
        assertEquals(john.computeFinalScore(), 50);
        john.clear();
        john.addScore(80, "exams");
        john.addScore(70, "labs");
        john.addScore(90, "assignments");
        john.addScore(60, "quizzes");
        assertEquals(john.computeFinalScore(), 81);
        john.clear();
        john.addScore(80, "exams");
        john.addScore(75, "exams");
        john.addScore(70, "labs");
        john.addScore(90, "assignments");
        john.addScore(30, "assignments");
        john.addScore(60, "quizzes");
        assertEquals(john.computeFinalScore(), 68);
        john.clear();
    }

    @Test
    void testComputeFinalScoreExam() {
        john.addScore(64, "exams");
        john.addScore(100, "labs");
        john.addScore(100, "assignments");
        john.addScore(100, "quizzes");
        assertEquals(john.computeFinalScore(), 64);

    }

    @Test
    void testComputeFinalGrade() {
        john.clear();
        john.addScore(100, "exams");
        john.addScore(100, "labs");
        john.addScore(100, "assignments");
        john.addScore(100, "quizzes");
        assertEquals("A", john.computeFinalGrade());
        john.clear();
        john.addScore(80, "exams");
        john.addScore(70, "labs");
        john.addScore(90, "assignments");
        john.addScore(60, "quizzes");
        assertEquals("B-", john.computeFinalGrade());
        john.clear();
        john.addScore(50, "exams");
        john.addScore(50, "labs");
        john.addScore(50, "assignments");
        john.addScore(50, "quizzes");
        assertEquals("E", john.computeFinalGrade());
    }
}
