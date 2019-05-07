import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A class that represents a picture.  This class inherits from
 * SimplePicture and allows the student to add functionality to
 * the Picture class.
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments
     */
    public Picture() {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor
         */
        super();
    }

    /**
     * Constructor that takes a file name and creates the picture
     *
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName) {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     *
     * @param height the height of the desired picture
     * @param width  the width of the desired picture
     */
    public Picture(int height, int width) {
        // let the parent class handle this width and height
        super(width, height);
    }

    /**
     * Constructor that takes a picture and creates a
     * copy of that picture
     *
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture) {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     *
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image) {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /* Main method for testing - each class in Java can have a main
     * method
     */
    public static void main(String[] args) {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

    /**
     * Method to return a string with information about this picture.
     *
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString() {
        String output = "Picture, filename " + getFileName() +
                " height " + getHeight()
                + " width " + getWidth();
        return output;

    }

    /**
     * Method to set the blue to 0
     */
    public void zeroBlue() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setBlue(0);
            }
        }
    }

    /**
     * Method to set all non-blue values to 0
     */
    public void keepOnlyBlue() {
        Pixel[][] pixels = this.getPixels2D();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                pixels[i][j].setGreen(0);
                pixels[i][j].setRed(0);
            }
        }
    }

    /**
     * Method to negate all the colors in a picture
     */
    public void negate() {
        Pixel[][] pixels = this.getPixels2D();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                pixels[i][j].setRed(255 - pixels[i][j].getRed());
                pixels[i][j].setGreen(255 - pixels[i][j].getGreen());
                pixels[i][j].setRed(255 - pixels[i][j].getBlue());
            }
        }
    }

    /**
     * Method to convert images to only grayscale
     */
    public void grayscale() {
        Pixel[][] pixels = this.getPixels2D();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int avg = Math.floorDiv(pixels[i][j].getRed() + pixels[i][j].getGreen() + pixels[i][j].getBlue(), 3);
                pixels[i][j].setRed(avg);
                pixels[i][j].setGreen(avg);
                pixels[i][j].setBlue(avg);
            }
        }
    }

    /**
     * Method to clean up underwater images
     */
    public void fixUnderwater() {
        Pixel[][] pixels = this.getPixels2D();

        int averageBlueValue = 0;
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                averageBlueValue += pixels[i][j].getBlue();
            }
        }
        averageBlueValue = Math.floorDiv(averageBlueValue, pixels.length * pixels[0].length);

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                if (pixels[i][j].getBlue() > averageBlueValue + 15) {
                    pixels[i][j].setGreen(pixels[i][j].getBlue());
                    pixels[i][j].setRed(pixels[i][j].getBlue());
                    pixels[i][j].setBlue(averageBlueValue - 100);
                }
            }
        }
    }

    /**
     * Method that mirrors the picture around a
     * vertical mirror in the center of the picture
     * from left to right
     */
    public void mirrorVertical() {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < width / 2; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /**
     * Method that mirrors the picture around a
     * vertical mirror in the center of the picture
     * from left to right
     */
    public void blur(int x, int y, int w, int h) {
        Pixel[][] pixels = this.getPixels2D();

        final int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
        final int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
        for(int i = x; i < x + w; i++)
        {
            for(int j = y; j < y + h; j++)
            {
                int[] sum = new int[3];
                for(int k = 0; k < dx.length; k++)
                {
                    sum[0] += pixels[i + dx[k]][j + dy[k]].getRed();
                    sum[1] += pixels[i + dx[k]][j + dy[k]].getGreen();
                    sum[2] += pixels[i + dx[k]][j + dy[k]].getBlue();
                }
                pixels[i][j].setRed(sum[0] / dx.length);
                pixels[i][j].setGreen(sum[1] / dx.length);
                pixels[i][j].setBlue(sum[2] / dx.length);
            }

        }
    }


    /**
     * Mirrors the image starting from the right
     */
    public void mirrorVerticalRightToLeft() {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = width / 2; col < width; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /**
     * Method to mirror the images horizontally
     */
    public void mirrorHorizontal() {
        Pixel[][] pixels = this.getPixels2D();
        for (int i = 0; i < pixels.length / 2; i++) {
            // rows
            for (int j = 0; j < pixels[i].length; j++) {
                pixels[i][j].setColor(pixels[pixels.length - i - 1][j].getColor());
            }
        }
    }

    /**
     * Method to mirror the images horizontally Bottom to Top
     */
    public void mirrorHorizontalBotToTop() {
        Pixel[][] pixels = this.getPixels2D();
        for (int i = 0; i < pixels.length / 2; i++) {
            // rows
            for (int j = 0; j < pixels[i].length; j++) {
                pixels[pixels.length - i - 1][j].setColor(pixels[i][j].getColor());
            }
        }
    }

    /**
     * Method to mirror the images diagonally
     */
    public void mirrorDiagonal() {
        Pixel[][] pixels = this.getPixels2D();
        int s = Math.min(pixels.length, pixels[0].length);

        for (int i = 0; i < s; i++)
            for (int j = i; j < s; j++)
                pixels[i][j].setColor(pixels[j][i].getColor());
    }

    /**
     * Method to mirror the snowman's arms
     */
    public void mirrorArms() {
        Pixel[][] pixels = this.getPixels2D();
        int s = Math.min(pixels.length, pixels[0].length);

        for (int i = 160; i < 160 + 30; i++)
            for (int j = 95; j <= 300; j++)
                pixels[i + 2 * (190 - i)][j].setColor(pixels[i][j].getColor());
    }

    /**
     * Method to mirror the seagull
     */
    public void mirrorGull() {
        Pixel[][] pixels = this.getPixels2D();
        int s = Math.min(pixels.length, pixels[0].length);

        for (int i = 233; i < 330; i++)
            for (int j = 233; j <= 339; j++)
                pixels[i][j + 2 * (340 - j)].setColor(pixels[i][j].getColor());
    }

    /**
     * Mirror just part of a picture of a temple
     */
    public void mirrorTemple() {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++) {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++) {

                leftPixel = pixels[row][col];
                rightPixel = pixels[row]
                        [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /**
     * copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     *
     * @param fromPic  the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic,
                     int startRow, int startCol) {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow;
             fromRow < fromPixels.length &&
                     toRow < toPixels.length;
             fromRow++, toRow++) {
            for (int fromCol = 0, toCol = startCol;
                 fromCol < fromPixels[0].length &&
                         toCol < toPixels[0].length;
                 fromCol++, toCol++) {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }
    }

    /**
     * copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     *
     * @param fromPic  the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     * @param endRow   the end row to copy from
     * @param endCol   the end col to copy from
     */
    public void copy(Picture fromPic,
                     int startRow, int startCol, int endRow, int endCol) {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow;
             fromRow < endRow &&
                     toRow < endRow + startRow;
             fromRow++, toRow++) {
            for (int fromCol = 0, toCol = startCol;
                 fromCol < endCol &&
                         toCol < endCol + startCol;
                 fromCol++, toCol++) {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }
    }

    /**
     * Method to create a collage of several pictures
     */
    public void createCollage() {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1, 0, 0);
        this.copy(flower2, 100, 0);
        this.copy(flower1, 200, 0);
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy(flowerNoBlue, 300, 0);
        this.copy(flower1, 400, 0);
        this.copy(flower2, 500, 0);
        this.mirrorVertical();
        this.write("collage.jpg");
    }

    public void myCollage() {
        Picture beach = new Picture("beach.jpg");
        Picture seagull = new Picture("seagull.jpg");
        this.copy(beach, 30, 0, 100, 100);
        this.copy(seagull, 100, 0, 300, 300);
        this.copy(beach, 200, 0);
        Picture flowerNoBlue = new Picture("temple.jpg");
        flowerNoBlue.zeroBlue();
        flowerNoBlue.mirrorHorizontalBotToTop();
        this.copy(flowerNoBlue, 200, 0);
        this.copy(beach, 300, 0);
        this.copy(seagull, 400, 0);
        this.write("collage.jpg");
    }

    /**
     * Method to show large changes in color
     *
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist) {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0;
                 col < pixels[0].length - 1; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col + 1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) >
                        edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }

        for(int col = 0; col < pixels[0].length; col++) {
            for(int row = 0; row < pixels.length - 1; row++)
            {
                Pixel top = pixels[row][col];
                Pixel bot = pixels[row + 1][col];
                if(bot.colorDistance(top.getColor()) > edgeDist)
                    top.setColor(Color.black);
                else
                    top.setColor(Color.white);
            }
        }
    }

} // this } is the end of class Picture, put all new methods before this
