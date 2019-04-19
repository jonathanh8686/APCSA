import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.lang.Character.toUpperCase;

public class PaddleTestTwo extends Canvas implements KeyListener, Runnable {
    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private boolean[] keys;

    public PaddleTestTwo() {
        ball = new Ball();

        leftPaddle = new Paddle(100, 200, 50, 100, 5);
        rightPaddle = new Paddle(400, 200, 50, 100, 5);

        keys = new boolean[]{false, false, false, false, false};

        setBackground(Color.WHITE);
        setVisible(true);

        this.addKeyListener(this);
        new Thread(this).start();

    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        ball.moveAndDraw(window);
        leftPaddle.draw(window);

        if (!(ball.getX() >= 10 && ball.getX() <= 550)) {
            ball.setXSpeed(-ball.getXSpeed());
        }

        if (!(ball.getY() >= 10 && ball.getY() <= 450)) {
            ball.setYSpeed(-ball.getYSpeed());
        }

        if (keys[0] == true) {
            leftPaddle.moveUpAndDraw(window);
        }
        if (keys[1] == true) {
            leftPaddle.moveDownAndDraw(window);
        }
        if (keys[2] == true) {
            rightPaddle.moveUpAndDraw(window);
        }
        if (keys[3] == true) {
            rightPaddle.moveDownAndDraw(window);
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (toUpperCase(e.getKeyChar())) {
            case 'W':
                keys[0] = true;
                break;
            case 'Z':
                keys[1] = true;
                break;
            case 'I':
                keys[2] = true;
                break;
            case 'M':
                keys[3] = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (toUpperCase(e.getKeyChar())) {
            case 'W':
                keys[0] = false;
                break;
            case 'Z':
                keys[1] = false;
                break;
            case 'I':
                keys[2] = false;
                break;
            case 'M':
                keys[3] = false;
                break;
        }
    }

    public void keyTyped(KeyEvent e) {
        //no code needed here
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(8);
                repaint();
            }
        } catch (Exception e) {
        }
    }
}