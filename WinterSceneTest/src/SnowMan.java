// (c) A+ Computer Science
// www.apluscompsci.com
// Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class SnowMan extends AbstractShape {

    public SnowMan(int x, int y, int w, int h) {
        super(x, y, w, h, Color.WHITE, 0, 0);
    }

    public void draw(Graphics window) {
        window.setColor(getColor());
        window.fillOval(getXPos() + getWidth() / 3, getYPos(), getWidth() / 3, getHeight() / 4);
        window.fillOval(getXPos() + getWidth() / 4, getYPos() + getHeight() / 5, getWidth() / 2, getHeight() / 2);
        window.fillOval(getXPos(), getYPos() + getHeight() / 2, getWidth(), getHeight());
    }

    public void moveAndDraw(Graphics window) {
        draw(window);
    }
}
