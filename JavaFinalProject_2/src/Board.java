import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Board extends Canvas implements KeyListener, Runnable {

    int tick = 0;
    boolean gameGoing = true;

    BufferedImage back;
    Cannon player;

    ArrayList<Ball> balls;
    ArrayList<Bullet> bullets;

    boolean[] keys; // left, right, up, down

    public Board() {
        setBackground(Color.black);

        player = new Cannon(300, 500, 4, 0, 30, 60);

        bullets = new ArrayList<Bullet>();
        balls = new ArrayList<Ball>();

        keys = new boolean[4];

        balls.add(new Ball((float) Math.random() * 600 + 100, (float) Math.random() * 100 + 100, (float) Math.random() + 0.2f, 0, 100, Color.ORANGE));
        balls.add(new Ball((float) Math.random() * 600 + 100, (float) Math.random() * 100 + 100, (float) Math.random() + 0.2f, 0, 100, Color.ORANGE));


        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {

        if (!gameGoing)
            return;

        tick++;
        Graphics2D twoDGraph = (Graphics2D) window;

        if (back == null)
            back = (BufferedImage) (createImage(getWidth(), getHeight()));

        Graphics graphToBack = back.createGraphics();
        graphToBack.setColor(Color.BLACK);
        graphToBack.fillRect(0, 0, 800, 600);

        try {
            Scanner sc = new Scanner(new File("scores.txt"));
            graphToBack.setColor(Color.CYAN);
            int ti = 0;
            while (sc.hasNext()) {
                String s = sc.nextLine();
                System.out.println("Previous Score:" + s);
                graphToBack.drawString("Previous Score:" + s, 50, ++ti * 100);
            }
        }
        catch (Exception e)
        {

        }

        for (int i = bullets.size() - 1; i >= 0; i--) {
            if (bullets.get(i).getyPos() < 0)
                bullets.remove(i);

            bullets.get(i).move("");
            bullets.get(i).draw(graphToBack);
        }

        if (keys[0]) player.move("LEFT");
        if (keys[1]) player.move("RIGHT");
        if (keys[2]) player.move("UP");
        if (keys[3]) player.move("DOWN");

        if (tick % 15 == 0)
            bullets.add(new Bullet(player.getxPos() + player.width / 2, player.getyPos(), 0, -10));

        player.draw(graphToBack);

        for (int i = balls.size() - 1; i >= 0; i--) {
            Ball cball = balls.get(i);

            if (cball.isColliding(player.xPos + player.width / 2, player.yPos + player.height / 2, cball.getRadius(), 10) == true) {
                gameGoing = false;

                try {
                    FileWriter fileWriter = new FileWriter("scores.txt");
                    fileWriter.append(Integer.toString(tick) + "\n" );
                    fileWriter.close();
                } catch (Exception e) {

                }

                return;
            }

            for (int j = bullets.size() - 1; j >= 0; j--) {
                if (cball.isColliding(bullets.get(j).xPos, bullets.get(j).yPos, cball.getRadius(), 2)) {
                    bullets.remove(j);
                    cball.setSize(balls.get(i).size - 5);
                }
            }

            if (cball.getRadius() <= 100 && cball.getColor() == Color.GREEN) {
                balls.add(new Ball(cball.xPos, cball.yPos, cball.xSpeed, -Math.abs(cball.ySpeed), cball.getRadius() - 25, Color.RED));
                balls.add(new Ball(cball.xPos, cball.yPos, -cball.xSpeed, -Math.abs(cball.ySpeed), cball.getRadius() - 25, Color.RED));
                balls.remove(i);
                continue;
            }

            if (cball.getRadius() <= 50 && cball.getColor() == Color.RED) {
                balls.add(new Ball(cball.xPos, cball.yPos, cball.xSpeed, -Math.abs(cball.ySpeed), cball.getRadius() - 10, Color.ORANGE));
                balls.add(new Ball(cball.xPos, cball.yPos, -cball.xSpeed, -Math.abs(cball.ySpeed), cball.getRadius() - 10, Color.ORANGE));
                balls.remove(i);
                continue;
            }

            if (cball.getRadius() <= 25 && cball.getColor() == Color.ORANGE) {
                balls.add(new Ball(cball.xPos, cball.yPos, cball.xSpeed, -Math.abs(cball.ySpeed), cball.getRadius() / 2, Color.CYAN));
                balls.add(new Ball(cball.xPos, cball.yPos, -cball.xSpeed, -Math.abs(cball.ySpeed), cball.getRadius() / 2, Color.CYAN));
                balls.remove(i);
                continue;
            } else if (cball.getRadius() <= 10 && cball.getColor() == Color.cyan) {
                // ball destroyed
                balls.remove(i);

                spawnBall();
            }

            cball.applyGravity();
            cball.move();
            cball.draw(graphToBack);
        }

        twoDGraph.drawImage(back, null, 0, 0);
    }

    public void spawnBall() {
        balls.add(new Ball((float) Math.random() * 600 + 100, (float) Math.random() * 100 + 100, (float) Math.random() + 0.2f, 0, 125, Color.GREEN));
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
        if (e.getKeyCode() == KeyEvent.VK_LEFT) keys[0] = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) keys[1] = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) keys[2] = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) keys[3] = false;

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