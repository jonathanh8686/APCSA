import java.awt.*;

public abstract class Moving implements Locatable{

    float xPos;
    float yPos;

    float xSpeed;
    float ySpeed;

    public Moving(float x, float y, float xs, float ys) {
        xPos = x;
        yPos = y;

        xSpeed = xs;
        ySpeed = ys;
    }

    public Moving(float x, float y) {
        xPos = x;
        yPos = y;

        xSpeed = 5;
        ySpeed = 5;
    }
    public void setxSpeed(float xs) {
        xSpeed = xs;
    }

    public void setySpeed(float ys) {
        ySpeed = ys;
    }

    public void setxPos(float x){
        xPos = x;
    }

    public void setyPos(float y){
        yPos = y;
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public float getxSpeed() { return xSpeed; }

    public float getySpeed() {
        return ySpeed;
    }

    public abstract void move(String d);
    public abstract void draw(Graphics window);
}