//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.*;

public class Block implements Locatable {
    private int xPos;
    private int yPos;
    private int width;
    private int height;

    private Color color;

    public Block() {
        xPos = 0;
        yPos = 0;
        width = 50;
        height = 50;
        color = Color.BLACK;

    }

    public Block(int x, int y) {
        xPos = x;
        yPos = y;
        width = 10;
        height = 10;
        color = Color.BLACK;
    }

    public Block(int x, int y, int w, int h) {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        color = Color.BLACK;
    }


    public Block(int x, int y, int w, int h, Color c) {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        color = c;
    }

    public void setPos(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color col) {
        color = col;
    }

    public void draw(Graphics window) {
        window.setColor(color);
        window.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public void draw(Graphics window, Color col) {
        window.setColor(col);
        window.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public boolean equals(Object obj) {
        Block tb = (Block) obj;
        return getX() == tb.getX() && getY() == tb.getY() && getWidth() == tb.getWidth() && getHeight() == tb.getHeight() && getColor() == tb.getColor();
    }

    public int getX() {
        return xPos;
    }

    public void setX(int x) {
        xPos = x;
    }

    public int getY() {
        return yPos;
    }

    public void setY(int y) {
        yPos = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String toString() {
        String rtn = "Pos: (" + getX() + ", " + getY() + ")\t\tWidth: " + getWidth() + "\tHeight: " + getHeight() + " Color: " + getColor();
        return rtn;
    }
}