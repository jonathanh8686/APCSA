import java.awt.*;

public class Paddle extends Block {
    //instance variables
    private int speed;

    public Paddle() {
        super(10, 10);
        speed = 5;
    }

    public Paddle(int x, int y) {
        super(x, y);
        speed = 5;
    }

    public Paddle(int x, int y, int spd) {
        super(x, y);
        speed = spd;
    }

    public Paddle(int x, int y, int width, int height, int spd) {
        super(x, y, width, height);
        speed = spd;
    }

    public Paddle(int x, int y, int width, int height, Color col, int spd) {
        super(x, y, width, height, col);
        speed = spd;
    }


    public void moveUpAndDraw(Graphics window) {
        draw(window, Color.WHITE);
        setY(getY() - speed);
        draw(window);

    }

    public void moveDownAndDraw(Graphics window) {
        draw(window, Color.WHITE);
        setY(getY() + speed);
        draw(window);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int spd) {
        speed = spd;
    }


    public String toString() {
        return super.toString() + "\tSpeed: " + speed;

    }
}