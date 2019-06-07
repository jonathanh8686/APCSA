
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * to do:
 *
 * Add powerups: * multishot * piercing * freeze. Allow user to add name to
 * scoreboard
 *
 * Cannon fire rate? Bullet power
 *
 */
public class Board extends Canvas implements KeyListener, Runnable {

    //configurable settings
    static final boolean USE_TICK_AS_SCORE = false;
    static final int NUM_SCORES_SAVED = 5;

    int score = 0;
    String prevScore;
    String highScore;
    String playerName = "BAL";
    boolean playerNameSet = true;

    int tick = 0;
    boolean gameGoing = true;

    BufferedImage back;
    Cannon player;

    ArrayList<Ball> balls;
    ArrayList<Bullet> bullets;

    boolean[] moveKeys; // left, right, up, down
    boolean[] funcKeys; // R (restart), S (open scoreboard)

    public Board() {
        setBackground(Color.black);

        player = new Cannon(300, BallBlast.HEIGHT - 100, 4, 0, 30, 60);

        bullets = new ArrayList<Bullet>();
        balls = new ArrayList<Ball>();

        moveKeys = new boolean[4];
        funcKeys = new boolean[2];

        balls.add(new Ball((float) Math.random() * 600 + 100, 100, (float) Math.random() + 0.2f, 0, 100, Color.ORANGE));
        balls.add(new Ball((float) Math.random() * 600 + 100, 100, (float) Math.random() + 0.2f, 0, 100, Color.ORANGE));
        File f1 = new File("scores.local");
        File f2 = new File("highScores.csv");

        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (Exception e) {
            }
        }
        if (!f2.exists()) {
            try {
                f2.createNewFile();
            } catch (Exception e) {
            }
        }

        try {
            Scanner sc = new Scanner(f1);
            prevScore = sc.nextLine();

        } catch (Exception e) {
            prevScore = "0";
        }
        try {
            Scanner sc = new Scanner(f2);
            highScore = sc.nextLine().split(",")[1];
        } catch (Exception e) {

            highScore = "0";
        }
        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);
    }

    public void reset() {

        bullets = new ArrayList<Bullet>();
        balls = new ArrayList<Ball>();

        moveKeys = new boolean[4];
        funcKeys = new boolean[2];

        gameGoing = true;
        score = 0;
        tick = 0;

        player = new Cannon(300, BallBlast.HEIGHT - 100, 4, 0, 30, 60);

        balls.add(new Ball((float) Math.random() * 600 + 100, 100, (float) Math.random() + 0.2f, 0, 100, Color.ORANGE));
        balls.add(new Ball((float) Math.random() * 600 + 100, 100, (float) Math.random() + 0.2f, 0, 100, Color.ORANGE));

        File f1 = new File("scores.local");
        File f2 = new File("highScores.csv");

        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (Exception e) {
            }
        }
        if (!f2.exists()) {
            try {
                f2.createNewFile();
            } catch (Exception e) {
            }
        }
        try {
            Scanner sc = new Scanner(f1);
            prevScore = sc.nextLine();

        } catch (Exception e) {
            prevScore = "0";
        }
        try {
            Scanner sc = new Scanner(f2);
            highScore = sc.nextLine().split(",")[1];
        } catch (Exception e) {

            highScore = "0";
        }
    }

    /**
     * Code to run once when the game is ending
     *
     * @param window the graphics window
     */
    public void gameEnd(Graphics window) {
        try {
            FileWriter fileWriter = new FileWriter("scores.local");
            fileWriter.append(Integer.toString(score) + "\n");
            fileWriter.close();
        } catch (Exception e) {

        }

        try { //score format: name,score,time
            Scanner highScores = new Scanner(new File("highScores.csv"));
            List<String> scoreData = new ArrayList();
            List<Integer> scores = new ArrayList();
            for (int n = 0; n < NUM_SCORES_SAVED; n++) {
                scoreData.add(highScores.nextLine());
                scores.add(Integer.parseInt(scoreData.get(n).split(",")[1]));
            }
            scores.add(-1); //out of bounds errors
            int n = 0;
            while (score < scores.get(n)) {
                n++;
            }
            if (n != 5) {
                new SetName(this, scoreData, n).setVisible(true);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void finishScoreBoard(List<String> scoreData, int n, String playerName) {
        try {
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            scoreData.add(n, playerName + "," + score + "," + df.format(new Date()));

            FileWriter fileWriter = new FileWriter("highScores.csv");
            for (String s : scoreData.subList(0, NUM_SCORES_SAVED)) {
                fileWriter.append(s + "\n");
            }
            fileWriter.close();
        } catch (Exception e) {
        }
        
        new Scoreboard().setVisible(true);
    }

    /**
     * Code to run after the game ends
     *
     * @param window the graphics window
     */
    public void gameOver(Graphics window) {
        if (funcKeys[0]) {
            funcKeys[0] = false;
            reset();
        }
        if (funcKeys[1]) {
            new Scoreboard().setVisible(true);
            funcKeys[1] = false;
        }
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        if (USE_TICK_AS_SCORE) {
            score = tick;
        }
        if (!gameGoing) {
            gameOver(window);
        } else {
            tick++;
            Graphics2D twoDGraph = (Graphics2D) window;

            if (back == null) {
                back = (BufferedImage) (createImage(BallBlast.WIDTH, BallBlast.HEIGHT));
            }

            Graphics graphToBack = back.createGraphics();
            graphToBack.setColor(Color.BLACK);
            graphToBack.fillRect(0, 0, BallBlast.WIDTH, BallBlast.HEIGHT);

            for (int i = bullets.size() - 1; i >= 0; i--) {
                if (bullets.get(i).getyPos() < 0) {
                    bullets.remove(i);
                }

                bullets.get(i).move("");
                bullets.get(i).draw(graphToBack);
            }

            if (moveKeys[0]) {
                player.move("LEFT");
            }
            if (moveKeys[1]) {
                player.move("RIGHT");
            }
            if (moveKeys[2]) {
                player.move("UP");
            }
            if (moveKeys[3]) {
                player.move("DOWN");
            }

            if (tick % 15 == 0) {
                bullets.add(new Bullet(player.getxPos() + player.width / 2, player.getyPos(), 0, -10));
            }

            player.draw(graphToBack);

            for (int i = balls.size() - 1; i >= 0; i--) {
                Ball cball = balls.get(i);

                if (cball.isColliding(player.xPos + player.width / 2, player.yPos + player.height / 2, cball.getRadius(), 10) == true) {
                    gameGoing = false;
                    gameEnd(window);

                    return;
                }

                for (int j = bullets.size() - 1; j >= 0; j--) {
                    if (cball.isColliding(bullets.get(j).xPos, bullets.get(j).yPos, cball.getRadius(), 2)) {
                        bullets.remove(j);
                        cball.setSize(balls.get(i).size - 5);
                        if (!USE_TICK_AS_SCORE) {
                            score += 5;
                        }
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

            graphToBack.setColor(Color.CYAN);
            graphToBack.drawString("Previous Score: " + prevScore, 50, 100);
            graphToBack.drawString("High Score: " + highScore, 50, 120);
            graphToBack.drawString("Current Score: " + score, 50, 200);

            twoDGraph.drawImage(back, null, 0, 0);
        }
    }

    public void spawnBall() {
        balls.add(new Ball((float) Math.random() * 600 + 100, 100, (float) Math.random() + 0.2f, 0, 125, Color.GREEN));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        moveKeys[0] = (e.getKeyCode() == KeyEvent.VK_LEFT);
        moveKeys[1] = (e.getKeyCode() == KeyEvent.VK_RIGHT);
        moveKeys[2] = (e.getKeyCode() == KeyEvent.VK_UP);
        moveKeys[3] = (e.getKeyCode() == KeyEvent.VK_DOWN);
        funcKeys[0] = (e.getKeyCode() == 'R');
        funcKeys[1] = (e.getKeyCode() == 'S');
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moveKeys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveKeys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moveKeys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moveKeys[3] = false;
        }
        if (e.getKeyCode() == 'R') {
            funcKeys[0] = false;
        }
        if (e.getKeyCode() == 'S') {
            funcKeys[1] = false;
        }

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
