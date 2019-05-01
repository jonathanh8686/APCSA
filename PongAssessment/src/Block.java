
import java.awt.Color;
import java.awt.Graphics;

public class Block implements Locatable {

    private int xPos;
    private int yPos;
    private int width;
    private int height;

    private Color color;

    public Block() {

    }

    public Block(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public Block(int x, int y, int w, int h) {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
    }

    //add other Block constructors - x , y , width, height, color
    public Block(int x, int y, int w, int h, Color c) {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        color = c;
    }

    //add the other set methods
    /**
     * @return the xPos
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * @param xPos the xPos to set
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * @return the yPos
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * @param yPos the yPos to set
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param col the color to set
     */
    public void setColor(Color col) {
        color = col;
    }

    public void setPos(int x, int y) {
        setxPos(x);
        setyPos(y);
    }

    public void draw(Graphics window) {
        //uncomment after you write the set and get methods
        window.setColor(color);
        window.fillRect(getxPos(), getyPos(), getWidth(), getHeight());
    }

    public void draw(Graphics window, Color col) {
        window.setColor(col);
        window.fillRect(getxPos(), getyPos(), getWidth(), getHeight());
    }

    public boolean equals(Object obj) {

        Block test = (Block) obj;
        if (this.getColor() != null && test.getColor() != null) {
            if (this.getxPos() == test.getxPos() && this.getyPos() == test.getyPos()
                    && this.getWidth() == test.getWidth() && this.getHeight() == test.getHeight()
                    && this.getColor().equals(test.getColor())) {

                return true;

            }

        } else {
            if (this.getxPos() == test.getxPos() && this.getyPos() == test.getyPos()
                    && this.getWidth() == test.getWidth() && this.getHeight() == test.getHeight()) {

                return true;

            }
        }

        return false;
    }

    //add a toString() method  - x , y , width, height, color
    public String toString() {
        return getxPos() + " " + getyPos() + " " + getWidth() + " " + getHeight() + " " + getColor();
    }

}
