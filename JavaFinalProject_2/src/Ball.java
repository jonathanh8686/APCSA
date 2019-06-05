import java.awt.*;

public class Ball extends Physicsable {

    int size;
    int health;

    Color col;

    public Ball(float x, float y, float xs, float ys, int r, Color c) {
        super(x, y, xs, ys);
        size = r;
        col = c;
    }

    public void setSize(int ns)
    {
        size = ns;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public int getRadius() {
        return size;
    }

    public Color getColor() {return col; }

    public void move()
    {
        xPos += xSpeed;
        yPos += ySpeed;
        if(yPos <= 0)  ySpeed = -ySpeed;
        if(yPos + 2*getRadius() + 20 >= BallBlast.HEIGHT) ySpeed = -(float)Math.random() * 3 - 2;
        if(xPos <= 0 || xPos + 2*getRadius() >= BallBlast.WIDTH) xSpeed = -xSpeed;
    }

    @Override
    public void draw(Graphics window) {
        window.setColor(col);
        window.fillOval((int)xPos, (int)yPos, size * 2, size * 2);
        window.drawString(Integer.toString(size), (int)xPos, (int)yPos);
    }

    @Override
    public boolean isColliding(float x2, float y2, int r1, int r2) {
        float centX = xPos + getRadius();
        float centY = yPos + getRadius();
        return Math.sqrt((centX-x2)*(centX-x2) + (centY-y2)*(centY-y2)) <= (r1 + r2);
    }
}
