import java.awt.*;

public class Cannon extends Moving implements Locatable {

    int width;
    int height;

    public Cannon(float x, float y, float xs, float ys, int w, int h) {

        super(x, y, xs, ys);
        System.out.println(y);
        width = w; height = h;
    }

    @Override
    public void move(String d) {
        if(d.equals("RIGHT")){
            setxPos(getxPos() + getxSpeed());
        }
        else if(d.equals("LEFT")){
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
