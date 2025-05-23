package assign01;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Stack;


/**
 * Represents a grayscale (black and white) image as a 2D array of "pixel" brightnesses
 * 255 is "white" 127 is "gray" 0 is "black" with intermediate values in between
 * Author: Ben Jones and Philippe Gonzalez
 */
public class GrayscaleImage {
    private final double[][] imageData;


    /**
     * Initialize an image from a 2D array of doubles
     * This constructor creates a copy of the input array
     *
     * @param data initial pixel values
     * @throws IllegalArgumentException if the input array is empty or "jagged" meaning not all rows are the same length
     */
    public GrayscaleImage(double[][] data) {
        if (data.length == 0 || data[0].length == 0) {
            throw new IllegalArgumentException("Image is empty");
        }

        imageData = new double[data.length][data[0].length];
        for (var row = 0; row < imageData.length; row++) {
            if (data[row].length != imageData[row].length) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
            System.arraycopy(data[row], 0, imageData[row], 0, imageData[row].length);
        }
    }

    /**
     * Fetches an image from the specified URL and converts it to grayscale
     * Uses the AWT Graphics2D class to do the conversion, so it may add
     * an item to your dock/menu bar as if you're loading a GUI program
     *
     * @param url where to download the image
     * @throws IOException if the image can't be downloaded for some reason
     */
    public GrayscaleImage(URL url) throws IOException {
        var inputImage = ImageIO.read(url);
        //convert input image to grayscale
        //based on (https://stackoverflow.com/questions/6881578/how-to-convert-between-color-models)
        var grayImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = grayImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, null);
        g2d.dispose();
        imageData = new double[grayImage.getHeight()][grayImage.getWidth()];

        //raster is basically a width x height x 1 3-dimensional array
        var grayRaster = grayImage.getRaster();
        for (var row = 0; row < imageData.length; row++) {
            for (var col = 0; col < imageData[0].length; col++) {
                //getSample parameters are x (our column) and y (our row), so they're "backwards"
                imageData[row][col] = grayRaster.getSampleDouble(col, row, 0);
            }
        }
    }

    public void savePNG(File filename) throws IOException {
        var outputImage = new BufferedImage(imageData[0].length, imageData.length, BufferedImage.TYPE_BYTE_GRAY);
        var raster = outputImage.getRaster();
        for (var row = 0; row < imageData.length; row++) {
            for (var col = 0; col < imageData[0].length; col++) {
                raster.setSample(col, row, 0, imageData[row][col]);
            }
        }
        ImageIO.write(outputImage, "png", filename);
    }

    /**
     * Get the pixel brightness value at the specified coordinates
     * (0,0) is the top left corner of the image, (width -1, height -1) is the bottom right corner
     *
     * @param x horizontal position, increases left to right
     * @param y vertical position, **increases top to bottom**
     * @return the brightness value at the specified coordinates
     * @throws IllegalArgumentException if x, y are not within the image width/height
     */
    public double getPixel(int x, int y) {
        if (imageData.length > y && imageData[0].length > x && y >= 0 && x >= 0) {
            return imageData[y][x];
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Two images are equal if they have the same size and each corresponding pixel
     * in the two images is exactly equal
     *
     * @param other other image to compare with this
     * @return true if each pixel is exactly the same as each other
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof GrayscaleImage otherImage)) {
            return false;
        }
        if (this.imageData.length != otherImage.imageData.length || this.imageData[0].length != otherImage.imageData[0].length) {
            return false;
        }

        for (int i = 0; i < imageData.length; i++) {
            for (int j = 0; j < imageData[0].length; j++) {
                double item1 = this.getPixel(i, j);
                double item2 = otherImage.getPixel(i, j);
                if (item1 != item2) {
                    return false;
                }

            }
        }
        return true;
    }


    /**
     * Computes the average of all values in image data
     *
     * @return the average of the imageData array
     */
    public double averageBrightness() {
        int count = 0;
        double pixelTotal = 0;
        for (int i = 0; i < imageData.length; i++) {
            for (int j = 0; j < imageData[0].length; j++) {
                pixelTotal += this.getPixel(j, i);
                count++;
            }
        }
        return pixelTotal / count;
    }

    /**
     * Return a new GrayScale image where the average new average brightness is 127
     * To do this, uniformly scale each pixel (ie, multiply each imageData entry by the same value)
     * Due to rounding, the new average brightness will not be 127 exactly, but should be very close
     * The original image should not be modified
     *
     * @return a GrayScale image with pixel data uniformly rescaled so that its averageBrightness() is 127
     */
    public GrayscaleImage normalized() {
        GrayscaleImage normalizedSquare = new GrayscaleImage(new double[imageData.length][imageData[0].length]);
        double originalAverage = this.averageBrightness();
        double multiplier = 127 / originalAverage;
        for (int i = 0; i < imageData.length; i++) {
            for (int j = 0; j < imageData[0].length; j++) {
                normalizedSquare.imageData[i][j] = getPixel(j, i) * multiplier;

            }
        }
        return normalizedSquare;
    }


    /**
     * Returns a new grayscale image that has been "mirrored" across the y-axis
     * In other words, each row of the image should be reversed
     * The original image should be unchanged
     *
     * @return a new GrayscaleImage that is a mirrored version of the this
     */
    public GrayscaleImage mirrored() {
        GrayscaleImage mirroredSquare = new GrayscaleImage(new double[imageData.length][imageData[0].length]);
        Stack<Double> temp = new Stack<>();
        for (int j = 0; j < imageData.length; j++) {
            for (int i = 0; i < imageData[0].length; i++) {
                temp.push(getPixel(i, j));
            }
            while (!temp.isEmpty()) {
                for (int i = 0; i < imageData[0].length; i++) {
                    mirroredSquare.imageData[j][i] = temp.pop();
                }
            }
        }


        return mirroredSquare;
    }

    /**
     * Returns a new GrayscaleImage of size width x height, containing the part of `this`
     * from startRow -> startRow + height, startCol -> startCol + width
     * The original image should be unmodified
     *
     * @param startRow starting row 0 based
     * @param startCol starting column 0 based
     * @param width    how many items from the starting col to include
     * @param height   how many items from the starting row to include
     * @return A new GrayscaleImage containing the sub-image in the specified rectangle
     * @throws IllegalArgumentException if the specified rectangle goes outside the bounds of the original image
     */
    public GrayscaleImage cropped(int startRow, int startCol, int width, int height) {

        if (startRow + height > imageData.length || startCol + width > imageData[0].length||startRow + height<0||startCol + width<0) {
            throw new IllegalArgumentException();
        }
        GrayscaleImage croppedSquare = new GrayscaleImage(new double[height][width]);
        int tx = 0;
        int ty = 0;
        for (int c = startCol; c < startCol + width; c++) {

            for (int r = startRow; r < startRow + height; r++) {
                double original = getPixel(c, r);

                croppedSquare.imageData[tx][ty] = original;
                tx++;
            }
            ty++;
            tx = 0;

        }
        return croppedSquare;
    }

    /**
     * Returns a new "centered" square image (new width == new height)
     * For example, if the width is 20 pixels greater than the height,
     * this should return a height x height image, with 10 pixels removed from the left and right
     * edges of the image
     * If the number of pixels to be removed is odd, remove 1 fewer pixel from the left or top part
     * (note this convention should be SIMPLER/EASIER to implement than the alternative)
     * The original image should not be changed
     *
     * @return a new, square, GrayscaleImage
     */
    public GrayscaleImage squarified() {
        GrayscaleImage squaredSquare = null;
        int y = imageData.length;
        int x = imageData[0].length;

        if (x == y) {
            return this;
        }

        if (x > y) {
            int diff = (x - y);
            if (x % 2 == 0) {
                squaredSquare = cropped(0, (diff / 2), y, y);
            }
            if (x % 2 == 1) {
                int evenDiff = diff - 1;
                squaredSquare = cropped(0, (evenDiff / 2), y, y);
            }
        }
        if (y > x) {
            int diff = (y - x);
            if (y % 2 == 0) {
                squaredSquare = cropped(diff / 2, 0, x, x);
            }
            if (y % 2 == 1) {
                int evenDiff = diff - 1;
                squaredSquare = cropped(evenDiff / 2, 0, x, x);
            }

        }

        return squaredSquare;
    }
}
