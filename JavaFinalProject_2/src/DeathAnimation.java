
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
    final int NUM_SQUARES = 50;
    final int SQUARE_SIZE = 10;
    
    int x, y;
    int dist;
    Color color;
    
    List<int[]> squares;
    
    public DeathAnimation(int x, int y, int distance, Color Color) {
        this.x = x;
        this.y = y;
        dist = distance;
        color = Color;
        initCode();
    }
    
    private void initCode() {
        squares = new ArrayList();
        for (int i = 0; i < NUM_SQUARES; i++) {
            squares.add(new int[] {x, y, (int)(Math.random()*360)});
        }
    }
    
    public void drawSquare(int x, int y, Graphics window) {
        window.setColor(color);
        window.fillRect(x - SQUARE_SIZE/2, y - SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
    }
}
