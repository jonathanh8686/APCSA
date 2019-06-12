
import java.awt.*;

public abstract class Physicsable implements Locatable, Collidable {

    float xPos;
    float yPos;

    float xSpeed;
    float ySpeed;

    public Physicsable(float x, float y, float xs, float ys) {
        xPos = x;
        yPos = y;

        xSpeed = xs;
        ySpeed = ys;
    }

    public void applyGravity() {
        ySpeed += 0.018;
    }

    public abstract void move();

    @Override
    public float getyPos() {
        return yPos;
    }

    @Override
    public void setyPos(float y) {
        yPos = y;
    }

    @Override
    public float getxPos() {
        return xPos;
    }

    @Override
    public void setxPos(float x) {
        xPos = x;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xs) {
        xSpeed = xs;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setySpeed(float ys) {
        ySpeed = ys;
    }

    public boolean isColliding(float x2, float y2, int r1, int r2) {
        return Math.sqrt((xPos - x2) * (xPos - x2) + (yPos - y2) * (yPos - y2)) <= (r1 + r2);
    }

    public abstract void draw(Graphics window);
}
