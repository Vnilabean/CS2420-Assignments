package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrayscaleImageTestBugReport {
    private GrayscaleImage smallSquare;
    private GrayscaleImage smallWide;
    private GrayscaleImage medSquare;

    @BeforeEach
    void setUp() {
        smallSquare = new GrayscaleImage(new double[][]{{1, 2}, {3, 4}});
        smallWide = new GrayscaleImage(new double[][]{{1, 2, 3}, {4, 5, 6}});
        medSquare = new GrayscaleImage(new double[][]
                {
                        {1, 2, 3, 4, 5, 6},
                        {7, 8, 9, 10, 11, 12},
                        {13, 14, 15, 16, 17, 18},
                        {19, 20, 21, 22, 23, 24},
                        {25, 26, 27, 28, 29, 30},
                        {31, 32, 33, 34, 35, 36}
                }
        );

    }

    @Test
    void equalsOnMismatchedArrays() {
        assertFalse(medSquare.equals(smallWide));
    }



    @Test
    void cropWide() {


        assertEquals(smallWide.cropped(1, 1, 2, 1).getPixel(0, 0), 5);
    }


    @Test
    void mirroredVeryTallEven() {
        var medtall = new GrayscaleImage(new double[][]
                {
                        {1, 2},
                        {3, 4},
                        {5, 6},
                        {7, 8},
                        {9, 10},
                        {11, 12},
                        {13, 14},
                        {15, 16}


                }
        );

        assertEquals(medtall.mirrored().getPixel(0, 0), 2);
    }
    @Test
    void normalizedRect() {
        assertEquals( smallWide.normalized().getPixel(0,0),36.28,0.01);

    }
    @Test
    void testDifSize() {
        assertFalse(smallWide.equals(smallSquare));
    }
    @Test
    void averageBrightnessNotSquare() {

        assertEquals(smallWide.averageBrightness(),3.5);
    }
    @Test
    void outOfBoundsCropped(){
        try {
            smallWide.cropped(0,0,-4,-4);


        } catch (IllegalArgumentException e) {
            // passes if this is caught
        }
    }
    @Test
    void NonSquareMirrored() {
        assertEquals(smallWide.mirrored().getPixel(0,0),3);
    }









}