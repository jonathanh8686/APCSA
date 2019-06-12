import java.awt.*;

public class Cannon extends Moving implements Locatable {

    int width;
    int height;
    int power = 5;
    int activePowerup = -1;

    public int powerupTime = 0;

    public Cannon(float x, float y, float xs, float ys, int w, int h) {

        super(x, y, xs, ys);
        width = w; height = h;
    }

    public String getActivePowerup()
    {
        if (activePowerup == -1) return "none";
        else return Powerup.POWERUP_TYPES[activePowerup];
    }

    public void setActivePowerup(String ap)
    {
        for (int i = 0; i < Powerup.POWERUP_TYPES.length; i++) {
            if (ap.equals(Powerup.POWERUP_TYPES[i])) activePowerup = i;
        }
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

