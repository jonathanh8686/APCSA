
import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Block implements Collidable {

    private int xSpeed;
    private int ySpeed;

    public Ball() {
        super(200, 200);
        xSpeed = 3;
        ySpeed = 1;
    }

    public Ball(int x, int y) {
        super(x, y);
        xSpeed = 3;
        ySpeed = 1;
    }

    public Ball(int x, int y, int wid, int ht) {
        super(x, y, wid, ht);
        xSpeed = 3;
        ySpeed = 1;
    }

    public Ball(int x, int y, int wid, int ht, Color col) {
        super(x, y, wid, ht, col);
        xSpeed = 3;
        ySpeed = 1;
    }

    public Ball(int x, int y, int wid, int ht, int xSpd, int ySpd) {
        super(x, y, wid, ht);
        xSpeed = xSpd;
        ySpeed = ySpd;
    }

    public Ball(int x, int y, int wid, int ht, Color col, int xSpd, int ySpd) {
        super(x, y, wid, ht, col);
        xSpeed = xSpd;
        ySpeed = ySpd;
    }

    public void setXSpeed(int xSpd) {
        xSpeed = xSpd;
    }

    public void setYSpeed(int ySpd) {
        ySpeed = ySpd;
    }

    public void moveAndDraw(Graphics window) {
        //draws over the old ball with a white ball
        draw(window, Color.white);

        //draw(window,Color.red); //draws over the old ball with whichever color you want
        //in case you want to have a trail
        setxPos(getxPos() + xSpeed);
        setyPos(getyPos() + ySpeed);

        draw(window);   //draws the Ball
    }

    public boolean didCollideLeft(Object obj) {
        Block other = (Block) obj;
        return getxPos() <= other.getxPos() + other.getWidth() + Math.abs(getXSpeed());
    }

    public boolean didCollideRight(Object obj) {
        Block other = (Block) obj;
        return getxPos() + getWidth() >= other.getxPos() - Math.abs(getXSpeed());
    }

    public boolean didCollideTop(Object obj) {
        Block other = (Block) obj;
        return getyPos() >= other.getyPos() && getyPos() <= other.getyPos() + getHeight();
    }

    public boolean didCollideBottom(Object obj) {
        Block other = (Block) obj;
        return getyPos() + getHeight() >= other.getyPos() && getyPos() + getHeight() <= other.getyPos() + other.getHeight();
    }

    public boolean equals(Object obj) {
        Ball other = (Ball) obj;
        if (super.equals(other) && getXSpeed() == other.getXSpeed() && getYSpeed() == other.getYSpeed()) {
            return true;
        }
        return false;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public String toString() {
        return super.toString() + " " + xSpeed + " " + ySpeed;
    }
}
