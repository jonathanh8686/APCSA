
import java.awt.*;

public class Powerup extends Physicsable {

    public static final String[] POWERUP_TYPES = {"freeze", "firert", "pierce"};
    public static final int[] POWERUP_DURATIONS = {500, 1000, 1000};
    public String powerType = ""; // freeze, firert, ghost

    public Powerup(float x, float y, float xs, float ys, String pt) {
        super(x, y, xs, ys);
        powerType = pt;
    }

    public Powerup(float x, float y, float xs, float ys) {
        super(x, y, xs, ys);
        powerType = POWERUP_TYPES[(int) (Math.random() * POWERUP_TYPES.length)];
    }

    public void move() {
        xPos += xSpeed;
        yPos += ySpeed;
        if (yPos <= 0) {
            ySpeed = -ySpeed;
        }
        if (yPos + 2 * getRadius() + 20 >= BallBlast.HEIGHT) {
            ySpeed = -(float) Math.random() * 3 - 2;
        }
        if (xPos <= 0 || xPos + 2 * getRadius() >= BallBlast.WIDTH) {
            xSpeed = -xSpeed;
        }
    }

    @Override
    public int getRadius() {
        return 5;
    }

    @Override
    public void draw(Graphics window) {
        //TODO: draw different images for each powerup
        if (powerType == "freeze") {
            window.setColor(Color.blue);
        } else if (powerType == "firert") {
            window.setColor(Color.red);
        } else if (powerType == "pierce") {
            window.setColor(Color.white);
        } else {
            window.setColor(Color.white);
        }

        window.drawOval((int) xPos, (int) yPos, getRadius() * 2, getRadius() * 2);
        window.drawString(powerType, (int) xPos, (int) yPos);
    }
}
