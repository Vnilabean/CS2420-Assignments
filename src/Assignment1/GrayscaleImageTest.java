package Assignment1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.sasl.SaslServer;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GrayscaleImageTest {

    private GrayscaleImage smallSquare;
    private GrayscaleImage smallWide;
    private GrayscaleImage medSquare;

    @BeforeEach
    void setUp() {
        smallSquare = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        smallWide = new GrayscaleImage(new double[][]{{1,2,3},{4,5,6}});
        medSquare = new GrayscaleImage(new double[][]
                {
                        {1,2,3,4,5,6},
                        {7,8,9,10,11,12},
                        {13,14,15,16,17,18},
                        {19,20,21,22,23,24},
                        {25,26,27,28,29,30},
                        {31,32,33,34,35,36}
                }
        );

    }

    @Test
    void testGetPixel(){
        assertEquals(smallSquare.getPixel(0,0), 1);
        assertEquals(smallSquare.getPixel(1,0), 2);
        assertEquals(smallSquare.getPixel(0,1), 3);
        assertEquals(smallSquare.getPixel(1,1), 4);

    }
    @Test
    void testGetMedPixel() {
        assertEquals(medSquare.getPixel(0,0),1);
        assertEquals(medSquare.getPixel(1,3),20);
        assertEquals(medSquare.getPixel(2,5),33);
        assertEquals(medSquare.getPixel(5,5),36);
    }

    @Test
    void outOfBoundsGet() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            medSquare.getPixel(-1,-1);
        });
        assertEquals("java.lang.IllegalArgumentException", exception.toString());
    }




    @Test
    void testEquals() {
        assertEquals(smallSquare, smallSquare);
        var equivalent = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        assertEquals(smallSquare, equivalent);
        assertEquals(medSquare,medSquare);
    }
    @Test
    void testEqualsFalse() {
        assertNotEquals(smallSquare,medSquare);
        assertNotEquals(smallSquare,smallWide);
        var close = new GrayscaleImage(new double[][]{{1,2,4},{4,5,6}});
        assertNotEquals(smallSquare,close);
    }


    @Test
    void averageBrightness() {
        assertEquals(smallSquare.averageBrightness(), 2.5, 2.5*.001);
        var bigZero = new GrayscaleImage(new double[1000][1000]);
        assertEquals(bigZero.averageBrightness(), 0);
    }
    @Test
    void averageBrightnessMedium() {
        assertEquals(medSquare.averageBrightness(),18.5,18.5*.001);
    }

    @Test
    void normalized() {
        var smallNorm = smallSquare.normalized();
        assertEquals(smallNorm.averageBrightness(), 127, 127*.001);
        var scale = 127/2.5;
        var expectedNorm = new GrayscaleImage(new double[][]{{scale, 2*scale},{3*scale, 4*scale}});
        for(var row = 0; row < 2; row++){
            for(var col = 0; col < 2; col++){
                assertEquals(smallNorm.getPixel(col, row), expectedNorm.getPixel(col, row),
                        expectedNorm.getPixel(col, row)*.001,
                        "pixel at row: " + row + " col: " + col + " incorrect");
            }
        }
    }

    @Test
    void normalizedMed() {
        var medNorm = medSquare.normalized();
        assertEquals(medNorm.getPixel(3,5),medNorm.getPixel(2,5),10);
    }
    @Test
    void normalizedTest() {
        var medNorm =medSquare.normalized();
        assertEquals(medNorm.averageBrightness(),127,1);
    }

    @Test
    void mirrored() {
        var expected = new GrayscaleImage(new double[][]{{2,1},{4,3}});
        System.out.println(smallSquare.mirrored().getPixel(1,1));
        assertEquals(smallSquare.mirrored(), expected);
    }

    @Test
    void cropped() {
        var cropped = smallSquare.cropped(1,1,1,1);

        assertEquals(cropped, new GrayscaleImage(new double[][]{{4}}));
    }

    @Test
    void squarified() {
        var squared = smallWide.squarified();
        var expected = new GrayscaleImage(new double[][]{{1,2},{4,5}});
        assertEquals(squared, expected);
    }
}