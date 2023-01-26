package assign02;

/**
 * This Class creates a student and modifies and computes their scores on assignment, exam, lab,
 * and quiz. This gives a letter grade along with their final scores.
 *
 * @author Conner Francis and Philippe Gonzalez
 * @version January 21, 2023
 */
public class CS2420Student extends UofUStudent{
    private EmailAddress studentContactInformation;
    private double assignment = 0.0;
    private double exams = 0.0;
    private int examsCount = 0;
    private double labs = 0.0;
    private int labsCount = 0;
    private double quizzes = 0.0;
    private int quizzesCount = 0;
    /**
     * Creates a student from the given first name, last name, and uNID.
     *
     * @param firstName
     * @param lastName
     * @param uNID
     * @param email
     */
    public CS2420Student(String firstName, String lastName, int uNID, EmailAddress email) {
        super(firstName, lastName, uNID);
        studentContactInformation = email;
    }

    public EmailAddress getContactInfo() {
        return studentContactInformation;
    }


    /**
     * adds to the score when given the assignment, exam, lab, quiz name and the amount to increase
     * it by.
     * @param score
     * @param category
     */
    public void addScore(double score, String category){
        switch (category) {
            case "assignment" -> assignment += score;
            case "exams" -> exams += score;
            case "labs" -> labs += score;
            case "quizzes" -> quizzes += score;
        }
    }

    /**
     * This takes a final score that from the percentages of assignment 40%, exam 40%, lab 10%, and
     * quiz 10%. if a student has below a 65% exam average, their exam average is their final
     * course score. If a student does not have at least one score in each category return 0.0.
     * @return total score
     */
    public double computeFinalScore(){
        double finalExam = (exam/examCount);
        if (finalExam < 65){
            return finalExam;
        }
        if (assignment != 0.0 && exams != 0.0 && labs != 0.0 && quizzes != 0.0 ) {
            return 40/assignment + 40/exams + 10/labs + 10/ quizzes;

        }
        return 0.0;
    }


    /**
     * This method computes a letter grade for the finale score. An A is between 100 and 93%, An A- is between 93 and 90
     * , An B+ is between 90 and 87, An B is between 87 and 83, An B- is between 83 and 80, An C+ is between 80 and 77,
     * An C is between 77 and 73, C- is between 73 and 70, An D+ is between 70 and 67, An D is between 67 and 63,
     * An D- is between 63 and 60, An E is anything below 60. If a student does not have at least one score in each
     * category return N/A.
     * @return final grade percentage
     */
    public String computeFinalGrade(){
        if (assignment != 0.0 && exams != 0.0 && labs != 0.0 && quizzes != 0.0 ) {
        double finalGrade = computeFinalScore();
       if (finalGrade >= 93 && finalGrade <= 100){return "A";};
        if (finalGrade >= 90 && finalGrade < 93){return "A-";};
        if (finalGrade >= 87 && finalGrade < 90){return "B-";};
        if (finalGrade >= 83 && finalGrade < 87){return "B";};
        if (finalGrade >= 80 && finalGrade < 83){return "B-";};
        if (finalGrade >= 77 && finalGrade < 80){return "C+";};
        if (finalGrade >= 73 && finalGrade < 77){return "C";};
        if (finalGrade >= 70 && finalGrade < 73){return "C-";};
        if (finalGrade >= 67 && finalGrade < 70){return "D+";};
        if (finalGrade >= 63 && finalGrade < 67){return "D";};
        if (finalGrade >= 60 && finalGrade < 63){return "D-";};
        if (finalGrade < 60){return "E";};

    }
        return "N/A";
    }

    /**
     * used for testing by clearing values assigned to a student
     */
    public void clear() {
        assignment = 0.0;
        assignCount = 0;
        quiz = 0.0;
        quizCount = 0;
        lab = 0.0;
        labCount = 0;
        exam = 0.0;
        examCount = 0;
    }
    /**
     * getter method for testing values of scores.
     * @param category score category in which you want to access value of
     * @return the score of the assignment or -1 if input is not correct
     */
    public double getScore(String category){
        return switch (category) {
            case "assignment" -> assignment;
            case "exam" -> exam;
            case "lab" -> lab;
            case "quiz" -> quiz;
            default -> -1;
        };
    }

}
