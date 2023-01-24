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
         testEmail = new EmailAddress("test","testsite.com");
        john = new CS2420Student("john", "doe",123456789,testEmail);


    }

    @Test
    void testGetContactInfo() {
        assertEquals(john.getContactInfo(),testEmail);
    }
    @Test
    void testAddingScore() {

    }


}
