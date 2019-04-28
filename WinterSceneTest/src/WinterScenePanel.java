// (c) A+ Computer Science
// www.apluscompsci.com
// Name -

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class WinterScenePanel extends JPanel implements Runnable, KeyListener {

    boolean wHold = false;
    private List<AbstractShape> shapes;
    private AbstractShape sMan;

    public WinterScenePanel() {
        setVisible(true);
        shapes = new ArrayList<AbstractShape>();
        for (int i = 0; i < 50; i++) {
            int y = (int) (Math.random() * 600);
            int s = (int) (Math.random() * 30) + 20;

            shapes.add(new SimpleSnowFlake(i * 14, y, s, s));
            shapes.add(new FancySnowFlake(i * 14, y, s, s));
        }
        sMan = new SnowMan(500, 350, 200, 150);
        new Thread(this).start();
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        window.setColor(Color.BLUE);
        window.fillRect(0, 0, getWidth(), getHeight());
        window.setColor(Color.WHITE);
        window.drawRect(20, 20, getWidth() - 40, getHeight() - 40);
        window.setFont(new Font("TAHOMA", Font.BOLD, 18));
        window.drawString("MAKE A WINTER SCENE!", 40, 40);

        sMan.draw(window);
        for (AbstractShape sh : shapes) {
            sh.moveAndDraw(window);
            if (sh.getYPos() >= getHeight()) {
                sh.setYPos(0);
                if (sh instanceof StormySnowFlake) {
                    sh.setXPos((int) (Math.random() * 600));
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W)
            for (int i = 0; i < shapes.size(); i++)
                shapes.set(i, new StormySnowFlake(shapes.get(i).getXPos(), shapes.get(i).getYPos(), shapes.get(i).getWidth(), shapes.get(i).getHeight()));
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W)
            wHold = false;
    }

    public void run() {
        try {
            while (true) {
                this.addKeyListener(this);
                Thread.sleep(35);
                repaint();
            }
        } catch (Exception e) {
        }
    }
}
