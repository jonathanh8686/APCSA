// (c) A+ Computer Science
// www.apluscompsci.com
// Name -

import java.awt.*;
import java.util.Random;

public class StormySnowFlake extends AbstractShape {

    public StormySnowFlake(int x, int y, int w, int h) {
        super(x, y, w, h, Color.WHITE, 20, 20);
    }

    public void draw(Graphics window) {
        Random rng = new Random();

        window.setColor(getColor());

        window.drawArc(getXPos(), getYPos(), getWidth(), getHeight(), rng.nextInt(180), rng.nextInt(180) + 90);
    }

    public void moveAndDraw(Graphics window) {
        window.setColor(Color.BLACK);
        setXPos(getXPos() + getXSpeed());
        setYPos(getYPos() + getYSpeed());
        draw(window);
    }
}
