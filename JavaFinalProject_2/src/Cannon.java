import java.awt.*;

public class Cannon extends Moving implements Locatable {

    int width;
    int height;
    int power = 5;
    String activePowerup = "none";

    public int powerupTime = 0;

    public Cannon(float x, float y, float xs, float ys, int w, int h) {

        super(x, y, xs, ys);
        width = w; height = h;
    }

    public String getActivePowerup()
    {
        return activePowerup;
    }

    public void setActivePowerup(String ap)
    {
        activePowerup = ap;
    }

    @Override
    public void move(String d) {
        if(d.equals("RIGHT") && getxPos() + width < BallBlast.WIDTH){
            setxPos(getxPos() + getxSpeed());
        }
        else if(d.equals("LEFT") && getxPos() > 0){
            setxPos(getxPos() - getxSpeed());
        }
        else if(d.equals("UP")){
            setyPos(getyPos() - getySpeed());
        }
        else if(d.equals("DOWN")){
            setyPos(getyPos() + getySpeed());
        }
    }

    @Override
    public void draw(Graphics window) {
        // TODO: draw cooler cannon
        window.setColor(Color.BLUE);
        window.fillRect((int)getxPos(), (int)getyPos(), width, height);
    }
}

