import java.awt.*;

public abstract class Controllable implements Locatable{

    int xPos;
    int yPos;

    int xSpeed;
    int ySpeed;

    public Controllable(int x, int y, int xs, int ys) {
        xPos = x;
        yPos = y;

        xSpeed = xs;
        ySpeed = ys;
    }

    public Controllable(int x, int y) {
        xPos = x;
        yPos = y;

        xSpeed = 5;
        ySpeed = 5;
    }
    public void setxSpeed(int xs) {
        xSpeed = xs;
    }

    public void setySpeed(int ys) {
        ySpeed = ys;
    }

    public void setxPos(int x){
        xPos = x;
    }

    public void setyPos(int y){
        yPos = y;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getxSpeed() { return xSpeed; }

    public int getySpeed() {
        return ySpeed;
    }

    public abstract void move(String d);
    public abstract void draw(Graphics window);
}