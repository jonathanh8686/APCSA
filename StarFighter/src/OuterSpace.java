//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class OuterSpace extends Canvas implements KeyListener, Runnable {
    private Ship ship;

    private AlienHorde horde;
    private Bullets shots;

    private boolean[] keys;
    private BufferedImage back;

    public OuterSpace() {
        setBackground(Color.black);

        keys = new boolean[5];

        ship = new Ship();
        horde = new AlienHorde(10);

        for (int i = 0; i < 10; i++) {
            horde.add(new Alien(100 + i * 60, 200, 50, 50, 3));
        }

        shots = new Bullets();

        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        //set up the double buffering to make the game animation nice and smooth
        Graphics2D twoDGraph = (Graphics2D) window;

        //take a snap shop of the current screen and same it as an image
        //that is the exact same width and height as the current screen
        if (back == null)
            back = (BufferedImage) (createImage(getWidth(), getHeight()));

        //create a graphics reference to the back ground image
        //we will draw all changes on the background image
        Graphics graphToBack = back.createGraphics();

        graphToBack.setColor(Color.BLUE);
        graphToBack.drawString("StarFighter ", 25, 50);
        graphToBack.setColor(Color.BLACK);
        graphToBack.fillRect(0, 0, 800, 600);

        if (keys[0])
            ship.move("LEFT");

        if (keys[1])
            ship.move("RIGHT");

        if (keys[2])
            ship.move("UP");

        if (keys[3])
            ship.move("DOWN");

        if (keys[4]) {
            shots.add(new Ammo(ship.getX() + ship.getWidth() / 2 - 10, ship.getY()));
            keys[4] = false;
        }

        horde.removeDeadOnes(shots.getList());
        horde.moveEmAll();
        horde.drawEmAll(graphToBack);
        shots.moveEmAll();
        shots.cleanEmUp();
        shots.drawEmAll(graphToBack);
        ship.draw(graphToBack);

        twoDGraph.drawImage(back, null, 0, 0);
    }


    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = true;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = false;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {
        //no code needed here
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(5);
                repaint();
            }
        } catch (Exception e) {
        }
    }
}

