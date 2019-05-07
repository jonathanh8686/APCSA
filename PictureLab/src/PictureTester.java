/**
 * This class contains class (static) methods
 * that will help you test the Picture class
 * methods.  Uncomment the methods and the code
 * in the main to test.
 *
 * @author Barbara Ericson
 */
public class PictureTester {
    /**
     * Method to test zeroBlue
     */
    public static void testZeroBlue() {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

    /**
     * Method to test mirrorVertical
     */
    public static void testMirrorVertical() {
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        caterpillar.mirrorVertical();
        caterpillar.explore();
    }

    /**
     * Method to test mirrorTemple
     */
    public static void testMirrorTemple() {
        Picture temple = new Picture("temple.jpg");
        temple.explore();
        temple.mirrorTemple();
        temple.explore();
    }

    /**
     * Method to test the collage method
     */
    public static void testCollage() {
        Picture canvas = new Picture("640x480.jpg");
        canvas.createCollage();
        canvas.explore();
    }

    /**
     * Method to test edgeDetection
     */
    public static void testEdgeDetection() {
        Picture swan = new Picture("swan.jpg");
        swan.edgeDetection(10);
        swan.explore();
    }

    /**
     * Method to test keepOnlyBlue
     */
    public static void testKeepOnlyBlue() {
        Picture beach = new Picture("beach.jpg");
        beach.keepOnlyBlue();
        beach.explore();
    }

    /**
     * Method to test negate
     */
    public static void testNegate() {
        Picture beach = new Picture("beach.jpg");
        beach.negate();
        beach.explore();
    }

    /**
     * Method to test grayscale
     */
    public static void testGrayscale() {
        Picture beach = new Picture("beach.jpg");
        beach.grayscale();
        beach.explore();
    }

    /**
     * Method to test fixUnderwater
     */
    public static void testFixUnderwater() {
        Picture water = new Picture("water.jpg");
        water.fixUnderwater();
        water.explore();
    }

    /**
     * Method to test mirrorVerticalRightToLeft
     */
    public static void testMirrorVerticalRightToLeft() {
        Picture beach = new Picture("beach.jpg");
        beach.mirrorVerticalRightToLeft();
        beach.explore();
    }

    /**
     * Method to test mirrorHorizontal
     */
    public static void testMirrorHorizontal() {
        Picture beach = new Picture("beach.jpg");
        beach.mirrorHorizontal();
        beach.explore();
    }

    /**
     * Method to test mirrorHorizontalBotToTop
     */
    public static void testMirrorHorizontalBotToTop() {
        Picture beach = new Picture("beach.jpg");
        beach.mirrorHorizontalBotToTop();
        beach.explore();
    }

    /**
     * Method to test mirrorDiagonal
     */
    public static void testMirrorDiagonal() {
        Picture beach = new Picture("beach.jpg");
        beach.mirrorDiagonal();
        beach.explore();
    }

    /**
     * Method to test mirrorArms
     */
    public static void testMirrorArms() {
        Picture snowman = new Picture("snowman.jpg");
        snowman.mirrorArms();
        snowman.explore();
    }

    /**
     * Method to test mirrorArms
     */
    public static void testMirrorGull() {
        Picture gull = new Picture("seagull.jpg");
        gull.mirrorGull();
        gull.explore();
    }

    /**
     * Method copy section on image
     */
    public static void testCopy() {
        Picture gull = new Picture("seagull.jpg");
        gull.copy(new Picture("beach.jpg"), 30, 50, 100, 200);
        gull.explore();
    }

    public static void testMyCollage() {
        Picture water = new Picture("water.jpg");
        water.myCollage();
        water.explore();
    }

    public static void testBlur(int x, int y, int w, int h, int n) {
        Picture redMoto = new Picture("redMotorcycle.jpg");
        for (int i = 0; i < n; i++) {
            redMoto.blur(x, y, w, h);
        }
        redMoto.explore();
    }

    /**
     * Main method for testing.  Every class can have a main
     * method in Java
     */
    public static void main(String[] args) {
        // uncomment a call here to run a test
        // and comment out the ones you don't want
        // to run

        testBlur(184, 158, 35, 30, 5);

        //testMyCollage();
        //testMirrorGull();
        //testCopy();
        //testMirrorVerticalRightToLeft();
        //testMirrorHorizontal();
        //testMirrorHorizontalBotToTop();
        //testZeroBlue();
        //testKeepOnlyBlue();
        //testKeepOnlyRed();
        //testKeepOnlyGreen();
        //testNegate();
        //testGrayscale();
        //testFixUnderwater();
        //testMirrorVertical();
        //testMirrorTemple();
        //testMirrorArms();
        //testMirrorGull();
        //testMirrorDiagonal();
        //testCollage();
        //testCopy();
        //testEdgeDetection();
        //testEdgeDetection2();
        //testChromakey();
        //testEncodeAndDecode();
        //testGetCountRedOverValue(250);
        //testSetRedToHalfValueInTopHalf();
        //testClearBlueOverValue(200);
        //testGetAverageForColumn(0);
    }
}