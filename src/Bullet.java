import java.awt.*;

public class Bullet extends Moving{

    public Bullet(float x, float y, float xs, float ys) {
        super(x, y, xs, ys);
    }

    @Override
    public void move(String d) {
        xPos += xSpeed;
        yPos += ySpeed;
    }

    @Override
    public void draw(Graphics window) {
        window.setColor(Color.WHITE);
        window.fillOval((int)getxPos(), (int)getyPos(), 4, 4);
    }
}
