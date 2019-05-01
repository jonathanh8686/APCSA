import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.*;
import static java.lang.Character.toUpperCase;

public class Breakout extends Canvas implements KeyListener, Runnable {

    private Ball ball;
    private Paddle paddle;
    private boolean[] keys;
    private BufferedImage back;
    private int score;

    private ArrayList<Block> bricks;

    public Breakout() {
        // print info
        System.out.println("Jonathan Hsieh\t Computer Number: 16");
        System.out.println("Period 1");
        System.out.println("4/29/2019");

        //set up all variables related to the game
        ball = new Ball(400, 300, 10, 10, Color.blue, 2, 1);
        paddle = new Paddle(700, 50, 10, 100, Color.red, 3);
        keys = new boolean[2];
        score = 0;

        bricks = new ArrayList<Block>();

        //testBrick = new Block(300, 300, 30, 100, Color.green);

        final int numCol = 4;
        final int numRow = 5;
        for(int i = 0; i < numCol; i++) // column
        {
            for(int j = 0; j < numRow; j++) // row
            {
                bricks.add(new Block(10 + 50 * i, 10 + 110*j, 30, 100, Color.GREEN));
            }
        }

        setBackground(Color.WHITE);
        setVisible(true);

        new Thread(this).start();
        addKeyListener(this);        //starts the key thread to log key strokes
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        Graphics2D twoDGraph = (Graphics2D) window;

        if (back == null) {
            back = (BufferedImage) (createImage(getWidth(), getHeight()));
        }

        Graphics graphToBack = back.createGraphics();
        graphToBack.setColor(Color.WHITE);
        graphToBack.fillRect(0, 0, 800, 600);
        graphToBack.setColor(Color.red);

        ball.moveAndDraw(graphToBack);
        paddle.draw(graphToBack);

        if (ball.getxPos() >= 780) {
            ball.setXSpeed(0);
            ball.setYSpeed(0);

            score = 0;

            try {
                Thread.sleep(950);
            } catch (Exception e) {

            }

            ball.draw(graphToBack, Color.WHITE);
            ball.setxPos((int) (Math.random() * 50) + 400);
            ball.setyPos((int) (Math.random() * 50) + 300);
            int whoot = (int) (Math.random() * 2);
            if (whoot == 0) {
                ball.setXSpeed(2);
                ball.setYSpeed(1);
            } else {
                ball.setXSpeed(-2);
                ball.setYSpeed(1);
            }
        }

        graphToBack.setColor(Color.WHITE);
        graphToBack.fillRect(440, 520, 80, 80);

        graphToBack.setColor(Color.red);

        graphToBack.drawString("score = " + score, 400, 560);

        for(int i = 0; i < bricks.size(); i++)
            bricks.get(i).draw(graphToBack);

        //see if ball hits top wall or bottom wall
        if (!(ball.getyPos() >= 20 && ball.getyPos() <= 550)) {
            ball.setYSpeed(-ball.getYSpeed());
        }

        // ball hits left wall
        if(!(ball.getxPos() + ball.getWidth() / 2 >= 0))
            ball.setXSpeed(-ball.getXSpeed());

        if(isColliding(ball, paddle))
            ball.setXSpeed(-ball.getXSpeed());

        // ball hits bricks

        checkBrickCollisions();


        //see if the paddles need to be moved
        if (keys[0])
            paddle.moveUpAndDraw(graphToBack);
        if (keys[1])
            paddle.moveDownAndDraw(graphToBack);

        twoDGraph.drawImage(back, null, 0, 0);

    }

    public void checkBrickCollisions()
    {
        for(int i = bricks.size() - 1; i >= 0; i--)
        {
            if(isColliding(ball, bricks.get(i)))
            {
                bricks.remove(i);
                score++;
                ball.setXSpeed(-ball.getXSpeed());
            }
        }
    }

    public boolean isColliding(Ball a, Block b)
    {
        // check if ball hits paddle
        if(a.getxPos() <= b.getxPos() + b.getWidth() / 2 && a.getxPos() >= b.getxPos() - a.getWidth() / 2) // x bound
        {
            if(a.getyPos() > b.getyPos() && a.getyPos() < b.getyPos() + b.getHeight()) { // y bound
                //collision
                return true;
            }
        }
        return false;
    }

    public void keyPressed(KeyEvent e) {
        switch (toUpperCase(e.getKeyChar())) {
            case 'W':
                keys[0] = true;
                break;
            case 'S':
                keys[1] = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (toUpperCase(e.getKeyChar())) {
            case 'W':
                keys[0] = false;
                break;
            case 'S':
                keys[1] = false;
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
