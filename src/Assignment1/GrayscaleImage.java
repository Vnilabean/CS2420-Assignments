package Assignment1;


import javax.imageio.ImageIO;
import java.awt.Graphics2D;
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
    private double[][] imageData; // the actual image data


    /**
     * Initialize an image from a 2D array of doubles
     * This constructor creates a copy of the input array
     * @param data initial pixel values
     * @throws IllegalArgumentException if the input array is empty or "jagged" meaning not all rows are the same length
     */
    public GrayscaleImage(double[][] data){
        if(data.length == 0 || data[0].length == 0){
            throw new IllegalArgumentException("Image is empty");
        }

        imageData = new double[data.length][data[0].length];
        for(var row = 0; row < imageData.length; row++){
            if(data[row].length != imageData[row].length){
                throw new IllegalArgumentException("All rows must have the same length");
            }
            for(var col = 0; col < imageData[row].length; col++){
                imageData[row][col] = data[row][col];
            }
        }
    }

    /**
     * Fetches an image from the specified URL and converts it to grayscale
     * Uses the AWT Graphics2D class to do the conversion, so it may add
     * an item to your dock/menu bar as if you're loading a GUI program
     * @param url where to download the image
     * @throws IOException if the image can't be downloaded for some reason
     */
    public GrayscaleImage(URL url) throws IOException {
        var inputImage = ImageIO.read(url);
        //convert input image to grayscale
        //based on (https://stackoverflow.com/questions/6881578/how-to-convert-between-color-models)
        var grayImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d= grayImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, null);
        g2d.dispose();
        imageData = new double[grayImage.getHeight()][grayImage.getWidth()];

        //raster is basically a width x height x 1 3-dimensional array
        var grayRaster = grayImage.getRaster();
        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){
                //getSample parameters are x (our column) and y (our row), so they're "backwards"
                imageData[row][col] = grayRaster.getSampleDouble(col, row, 0);
            }
        }
    }

    public void savePNG(File filename) throws IOException {
        var outputImage = new BufferedImage(imageData[0].length, imageData.length, BufferedImage.TYPE_BYTE_GRAY);
        var raster = outputImage.getRaster();
        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){
                raster.setSample(col, row, 0, imageData[row][col]);
            }
        }
        ImageIO.write(outputImage, "png", filename);
    }

    ///Methods to be filled in by students below

    /**
     * Get the pixel brightness value at the specified coordinates
     * (0,0) is the top left corner of the image, (width -1, height -1) is the bottom right corner
     * @param x horizontal position, increases left to right
     * @param y vertical position, **increases top to bottom**
     * @return the brightness value at the specified coordinates
     * @throws IllegalArgumentException if x, y are not within the image width/height
     */
    public double getPixel(int x, int y){
        if (imageData.length > x && imageData[0].length > y &&y>=0&&x>=0) {
            return imageData[y][x];
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Two images are equal if they have the same size and each corresponding pixel
     * in the two images is exactly equal
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof GrayscaleImage)){
            return false;
        }

        GrayscaleImage otherImage = (GrayscaleImage)other;

        for (int i = 0;i<imageData.length ; i++) {
            for (int j=0;j<imageData[0].length;j++) {
               double item1 = this.getPixel(i,j);
                double item2 = otherImage.getPixel(i,j);
                if (item1 != item2) {
                    return false;
                }

            }
        }
        return true;
    }


    /**
     * Computes the average of all values in image data
     * @return the average of the imageData array
     */
    public double averageBrightness(){
        int count = 0;
        double pixelTotal = 0;
        for (int i = 0;i<imageData.length; i++) {
            for (int j=0;j<imageData[0].length;j++) {
            pixelTotal += this.getPixel(i,j);
            count++;
            }
            }
        return pixelTotal/count;
    }

    /**
     * Return a new GrayScale image where the average new average brightness is 127
     * To do this, uniformly scale each pixel (ie, multiply each imageData entry by the same value)
     * Due to rounding, the new average brightness will not be 127 exactly, but should be very close
     * The original image should not be modified
     * @return a GrayScale image with pixel data uniformly rescaled so that its averageBrightness() is 127
     */
    public GrayscaleImage normalized(){
        GrayscaleImage normalizedSquare = new GrayscaleImage(new double[imageData.length][imageData[0].length]);
        double originalAverage = this.averageBrightness();
        double multiplier = 127/originalAverage;
        for (int i = 0;i<imageData.length; i++) {
            for (int j=0;j<imageData[0].length;j++) {
                normalizedSquare.imageData[j][i] = getPixel(i,j) * multiplier;

            }
        }
        return normalizedSquare;
    }


    /**
     * Returns a new grayscale image that has been "mirrored" across the y-axis
     * In other words, each row of the image should be reversed
     * The original image should be unchanged
     * @return a new GrayscaleImage that is a mirrored version of the this
     */
    public GrayscaleImage mirrored(){
        GrayscaleImage mirroredSquare = new GrayscaleImage(new double[imageData.length][imageData[0].length]);
            int col = 0;
        Stack<Double> temp = new Stack<>();
            for (int j=0;j<imageData.length;j++) {
                for (int i = 0;i< imageData[0].length;i++) {
                    temp.push(getPixel(i,j));
                }
                while(!temp.isEmpty()) {
                    for (int i = 0;i < imageData[0].length;i++) {
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
     * @param startRow
     * @param startCol
     * @param width
     * @param height
     * @return A new GrayscaleImage containing the sub-image in the specified rectangle
     * @throws IllegalArgumentException if the specified rectangle goes outside the bounds of the original image
     */
    public GrayscaleImage cropped(int startRow, int startCol, int width, int height){
        GrayscaleImage croppedSquare = new GrayscaleImage(new double[height][width]);
        if (height > imageData.length || startCol + width > imageData[0].length) {
            throw new IllegalArgumentException();
        }
        int tx = 0;
        int ty = 0;
        for (int r = startCol;r< startCol + height;r++) {

            for (int c = startRow;c< startRow + width;c++) {
                double original = getPixel(r,c);

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
     * @return a new, square, GrayscaleImage
     */
    public GrayscaleImage squarified(){
        GrayscaleImage squaredSquare = null;
        int y = imageData.length;
        int x = imageData[0].length;
//        if (x < y) {
//             squaredSquare = new GrayscaleImage(new double[x][x]);
//        }
//        else {
//             squaredSquare = new GrayscaleImage(new double[y][y]);
//
//        }

        if (x == y) {
            return this;
        }

        if (x > y) {
            int diff = (x-y);
            if (diff%2 == 0) {
                squaredSquare = cropped(1,diff/2,imageData[0].length, imageData.length-1 - (diff/2));
            }
            if (diff%2 == 1) {
               int evenDiff = diff - 1;
                squaredSquare = cropped(0,(evenDiff/2),imageData[0].length - 1 - (diff/2), imageData.length);

                /**
                 * use this mehod above this to fox other methods for y>x and diff%2==0
                 * this is the only method changed from original that is working
                 */




            }
        }
        if (y > x) {
            int diff = (y-x);
            if (diff%2 == 0) {
                squaredSquare = cropped(diff/2,1, imageData[0].length-1-(diff/2), imageData.length );
            }
            if (diff%2 == 1){
                int evenDiff = diff -1;
                squaredSquare = cropped(diff/2,1, imageData[0].length-2-(diff/2), imageData.length );

            }
            
        }
        //STUDENT: FILL ME IN

        return squaredSquare;
    }


}
