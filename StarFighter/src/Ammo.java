//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.*;

public class Ammo extends MovingThing {
    private int speed;

    public Ammo() {
        this(0, 0, 0);
    }

    public Ammo(int x, int y) {
        this(x, y, 5);
    }

    public Ammo(int x, int y, int s) {
        super(x, y);
        speed = s;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public void draw(Graphics window) {
        window.setColor(Color.white);
        window.fillRect(getX(), getY(), getWidth(), getHeight());
    }


    public void move(String direction) {
        setY(getY() - speed);
    }


    public String toString() {
        return "";
    }
}
