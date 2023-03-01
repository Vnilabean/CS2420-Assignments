package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class WebBrowserTest {
    WebBrowser test;
    URL google, amazon, testSite, twitter, youtube;
    SinglyLinkedList<URL> history;
    @BeforeEach
    void setUp() throws MalformedURLException {
        google = new URL("https://google.com");
        testSite = new URL("https://testsite.com");
        amazon = new URL("https://amazon.com");
        twitter = new URL("https://twitter.com");
        youtube = new URL("https://youtube.com");
        test = new WebBrowser();
    }

    @Test
    void visit() {
        test.visit(amazon);
        history = test.history();
        System.out.println(Arrays.toString(test.history().toArray()));
        assertEquals("https://amazon.com", history.getFirst().toString());
    }

    @Test
    void back() {
        test.visit(google);
        test.visit(amazon);
        test.back();
       history = test.history();
       assertEquals(history.getFirst().toString(),"https://google.com");
    }
    @Test
    void backException() {
        test.visit(google);
        test.visit(amazon);
        test.back();
        try {
            test.back();
        } catch (NoSuchElementException e){
            // passes if reached
        }
    }

    @Test
    void forward() {
        test.visit(google);
        test.visit(amazon);
        test.back();
        test.forward();
        history = test.history();
        assertEquals(history.getFirst().toString(),"https://amazon.com");
    }

    @Test
    void forwardException() {
        test.visit(google);
        test.visit(amazon);
        try {
            test.forward();
        } catch (NoSuchElementException e) {
            // passes if reached
        }
    }

    @Test
    void history() {
        test.visit(amazon);
        test.visit(google);
        test.visit(testSite);
        test.visit(twitter);
        System.out.println(Arrays.toString(test.history().toArray()));
    }
}