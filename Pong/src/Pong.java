//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import static java.lang.Character.toUpperCase;

public class Pong extends Canvas implements KeyListener, Runnable {
    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private boolean[] keys;
    private BufferedImage back;


    public Pong() {
        ball = new Ball(350,250);
        leftPaddle = new Paddle(40, 200, 15, 75, 5);
        rightPaddle = new Paddle(730, 200, 15, 75, 5);

        keys = new boolean[4];

        setBackground(Color.WHITE);
        setVisible(true);

        new Thread(this).start();
        addKeyListener(this);        //starts the key thread to log key strokes
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


        ball.moveAndDraw(graphToBack);
        leftPaddle.draw(graphToBack);
        rightPaddle.draw(graphToBack);


        //see if ball hits left wall or right wall
        if (!(ball.getX() >= 10 && ball.getX() <= 780)) {
            ball.setXSpeed(0);
            ball.setYSpeed(0);
        }

        if(!(ball.getY() >= 10 && ball.getY() <= 550)){
            ball.setYSpeed(-ball.getYSpeed());
        }

        if (ball.didCollideLeft(leftPaddle) && (ball.didCollideTop(leftPaddle) || ball.didCollideBottom(leftPaddle))) {
            if (ball.getX() <= leftPaddle.getX() + leftPaddle.getWidth())
                ball.setYSpeed(-ball.getYSpeed());
            else
                ball.setXSpeed(-ball.getXSpeed());

        }

        if (ball.didCollideRight(rightPaddle) && (ball.didCollideTop(rightPaddle) || ball.didCollideBottom(rightPaddle))) {
            if (ball.getX() >= rightPaddle.getX() + rightPaddle.getWidth())
                ball.setYSpeed(-ball.getYSpeed());
            else
                ball.setXSpeed(-ball.getXSpeed());

        }
        //see if the paddles need to be moved
        if (keys[0]) {
            leftPaddle.moveUpAndDraw(graphToBack);
        }

        if (keys[1]) {
            leftPaddle.moveDownAndDraw(graphToBack);
        }

        if (keys[2]) {
            rightPaddle.moveUpAndDraw(graphToBack);
        }

        if (keys[3]) {
            rightPaddle.moveDownAndDraw(graphToBack);
        }

        twoDGraph.drawImage(back, null, 0, 0);
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