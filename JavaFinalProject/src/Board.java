import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Board extends Canvas implements KeyListener, Runnable {

    BufferedImage back;
    Cannon player;

    boolean[] keys; // left, right, up, down

    public Board() {
        setBackground(Color.black);

        player = new Cannon(300, 500, 4, 0, 30, 60);
        keys = new boolean[4];

        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        Graphics2D twoDGraph = (Graphics2D) window;

        if (back == null)
            back = (BufferedImage) (createImage(getWidth(), getHeight()));

        Graphics graphToBack = back.createGraphics();
        graphToBack.setColor(Color.BLACK);
        graphToBack.fillRect(0, 0, 800, 600);

        if(keys[0]) player.move("LEFT");
        if(keys[1]) player.move("RIGHT");
        if(keys[2]) player.move("UP");
        if(keys[3]) player.move("DOWN");

        System.out.println(player.getyPos());
        player.draw(graphToBack);

        twoDGraph.drawImage(back, null, 0, 0);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[0] = (e.getKeyCode() == KeyEvent.VK_LEFT);
        keys[1] = (e.getKeyCode() == KeyEvent.VK_RIGHT);
        keys[2] = (e.getKeyCode() == KeyEvent.VK_UP);
        keys[3] = (e.getKeyCode() == KeyEvent.VK_DOWN);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) keys[0] = false;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) keys[1] = false;
        if(e.getKeyCode() == KeyEvent.VK_UP) keys[2] = false;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) keys[3] = false;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
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