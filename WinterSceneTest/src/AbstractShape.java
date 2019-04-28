// (c) A+ Computer Science
// www.apluscompsci.com
// Name -

import java.awt.*;

public abstract class AbstractShape {

    private int xPos;
    private int yPos;

    private int width;
    private int height;

    private Color color;

    private int xSpeed;
    private int ySpeed;

    public AbstractShape(int x, int y, int wid, int ht) {
        xPos = x;
        yPos = y;
        width = wid;
        height = ht;
        color = Color.WHITE;
        xSpeed = 0;
        ySpeed = 0;
    }

    public AbstractShape(int x, int y, int wid, int ht, Color col) {
        xPos = x;
        yPos = y;
        width = wid;
        height = ht;
        color = col;
        xSpeed = 0;
        ySpeed = 0;
    }

    public AbstractShape(int x, int y, int wid, int ht, Color col, int xSpd, int ySpd) {
        xPos = x;
        yPos = y;
        width = wid;
        height = ht;
        color = col;
        xSpeed = xSpd;
        ySpeed = ySpd;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(int ys) {
        ySpeed = ys;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(int xs) {
        xSpeed = xs;
    }

    public int getXPos() {
        return xPos;
    }

    //add in set and get methods for pos and speed
    public void setXPos(int xp) {
        xPos = xp;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yp) {
        yPos = yp;
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract void draw(Graphics window);

    public abstract void moveAndDraw(Graphics window);

    public String toString() {
        return xPos + " " + yPos + " " + width + " " + height + " " + color + " " + xSpeed + " " + ySpeed;
    }
}
