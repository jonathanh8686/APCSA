import java.util.List;
import java.util.ArrayList;
import java.awt.*;

public class Bullet extends Moving {

    private List<Ball> ballsHit;
    private int pierceLife;
    
    public Bullet(float x, float y, float xs, float ys) {
        super(x, y, xs, ys);
        ballsHit = new ArrayList();
        pierceLife = 3;
    }
    
    public boolean alreadyHit(Ball b) {
        return ballsHit.contains(b);
    }
    
    public void hitBall(Ball b) {
        ballsHit.add(b);
        pierceLife--;
        if (pierceLife == 0) {
            yPos = -100;
        }
    }

    @Override
    public void move(String d) {
        xPos += xSpeed;
        yPos += ySpeed;
    }

    @Override
    public void draw(Graphics window) {
        window.setColor(Color.WHITE);
        window.fillOval((int) getxPos(), (int) getyPos(), 4, 4);
    }
}
