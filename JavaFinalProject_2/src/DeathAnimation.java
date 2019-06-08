
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yaod5171
 */
public class DeathAnimation {

    
    final int MAX_LIFE = 100;

    private int x, y;
    private int radius;
    private Color color;
    protected int life;

    List<int[]> squares;
    private int count = 14;
    private int size = 7;

    public DeathAnimation(int x, int y, int distance, Color Color) {
        this.x = x;
        this.y = y;
        radius = distance;
        color = setAlpha(Color, 255);
        life = MAX_LIFE;
        initCode();
    }

    private void initCode() {
        count = (int)(Math.sqrt(radius));
        size = (int)(Math.sqrt(radius));
        squares = new ArrayList();
        for (int i = 0; i < count; i++) {
            squares.add(new int[]{x, y, (int) (Math.random() * 360)});
        }
    }

    private static Color setAlpha(Color color, int alpha) {
        if (0 >= alpha) {
            alpha = 1;
        }
        if (alpha >= 256) {
            alpha = 255;
        }
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    public void drawSquare(int x, int y, Graphics window) {
        window.setColor(color);
        window.fillRect(x - size / 2, y - size / 2, size, size);
    }

    public void draw(Graphics window) {
        for (int[] sq : squares) {
            drawSquare(sq[0], sq[1], window);
        }
    }

    public void update() {
        color = setAlpha(color, (int)((double)(life) / MAX_LIFE * 255));
        double dist = Math.acos((double)life / MAX_LIFE) * radius;
        for (int[] sq : squares) {
            sq[0] = (int)(dist * Math.cos(sq[2])) + x;
            sq[1] = (int)(dist * Math.sin(sq[2])) + y;
        }
        life--;
    }
}
