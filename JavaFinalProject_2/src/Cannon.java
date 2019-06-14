
import java.util.List;
import java.util.ArrayList;
import java.awt.*;

public class Cannon extends Moving implements Locatable {

    int width;
    int height;
    int power = 5;
    int activePowerup = -1;

    public int powerupTime = 0;

    List<Integer[]> activePowerups = new ArrayList();

    public Cannon(float x, float y, float xs, float ys, int w, int h) {

        super(x, y, xs, ys);
        width = w;
        height = h;
    }

    public String getActivePowerup() {
        if (activePowerup == -1) {
            return "none";
        } else {
            return Powerup.POWERUP_TYPES[activePowerup];
        }
    }

    public List<Integer[]> getActivePowerups() {
        return activePowerups;
    }

    public void addActivePowerup(String ap) {
        boolean set = false;
        for (int i = 0; i < Powerup.POWERUP_TYPES.length; i++) {
            if (ap.equals(Powerup.POWERUP_TYPES[i])) {
                for (Integer[] pu : activePowerups) {
                    if (i == pu[0]) {
                        pu[1] = Powerup.POWERUP_DURATIONS[i];
                        set = true;
                    }
                }
                if (!set) {
                    activePowerups.add(new Integer[]{i, Powerup.POWERUP_DURATIONS[i]});
                }
            }
        }
    }

    public void tickPowerups() {
        for (int i = 0; i < activePowerups.size(); i++) {
            Integer[] pu = activePowerups.get(i);
            pu[1]--;
            if (pu[1] == 0) {
                activePowerups.remove(pu);
            }
        }
    }

    public boolean hasPowerup(String ap) {
        for (int i = 0; i < activePowerups.size(); i++) {
            if (ap.equals(Powerup.POWERUP_TYPES[activePowerups.get(i)[0]])) {
                return true;
            }
        }
        return false;
    }

    public void setActivePowerup(String ap) {
        boolean set = false;
        for (int i = 0; i < Powerup.POWERUP_TYPES.length; i++) {
            if (ap.equals(Powerup.POWERUP_TYPES[i])) {
                activePowerup = i;
                set = true;
            }
        }
        if (!set) {
            activePowerup = -1;
        }
    }

    @Override
    public void move(String d) {
        if (d.equals("RIGHT") && getxPos() + width < BallBlast.WIDTH) {
            setxPos(getxPos() + getxSpeed());
        } else if (d.equals("LEFT") && getxPos() > 0) {
            setxPos(getxPos() - getxSpeed());
        } else if (d.equals("UP")) {
            setyPos(getyPos() - getySpeed());
        } else if (d.equals("DOWN")) {
            setyPos(getyPos() + getySpeed());
        }
    }

    @Override
    public void draw(Graphics window) {
        // TODO: draw cooler cannon
        window.setColor(Color.BLUE);
        window.fillRect((int) getxPos(), (int) getyPos(), width, height);
    }
}
