// (c) A+ Computer Science
// www.apluscompsci.com
// Name -

import java.awt.*;

public class FancySnowFlake extends AbstractShape {

    public FancySnowFlake(int x, int y, int w, int h) {
        super(x, y, w, h, Color.WHITE, 0, 2);
    }

    public void draw(Graphics window) {
        window.setColor(getColor());
        int x = getXPos();
        int y = getYPos();
        int w = getWidth();
        int h = getHeight();
        int hw = w / 2;
        int hh = h / 2;
        int hhe = (int) Math.ceil(hh * .125);
        int hhq = (int) Math.ceil(hh * .35);
        int hhh = (int) Math.ceil(hh * .7);
        int hwe = (int) Math.ceil(hw * .125);
        int hwq = (int) Math.ceil(hw * .35);
        int hwh = (int) Math.ceil(hw * .7);

        int[] xPoints = {x + hwh, x + hwq, x + hwe, x, x + hwe, x + hwq, x + hwh, x + hw / 2, x + hw, x + w - hw / 2, x + w - hwh, x + w - hwq, x + w - hwe, x + w, x + w - hwe, x + w - hwq, x + w - hwh, x + hw + hwq, x + hw, x + hw - hwq};
        int[] yPoints = {y + hh - hhe, y + hh - hhq, y + hh - hhe / 2, y + hh, y + hh + hhe / 2, y + hh + hhq, y + hh + hhe, y + h - hwq, y + h, y + h - hwq, y + hh + hhe, y + hh + hhq, y + hh + hhe / 2, y + hh, y + hh - hhe / 2, y + hh - hhq, y + hh - hhe / 2, y + hwq, y, y + hwq};
        int nPoints = 20;
        Polygon triangle = new Polygon(xPoints, yPoints, nPoints);
        window.drawPolygon(triangle);
    }

    public void moveAndDraw(Graphics window) {
        //draw(window,Color.BLUE);
        int offset = (int) (Math.random() * 4);
        int rnd = (int) (Math.random() * 2);
        if (rnd == 1) {
            offset = offset * -1;
        }
        setXPos(getXPos() + offset);
        setYPos(getYPos() + getYSpeed());
        draw(window);
    }
}
