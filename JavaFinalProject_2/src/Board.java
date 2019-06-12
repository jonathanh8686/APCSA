
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
 * Add powerups: * multishot * piercing * freeze. 
 *
 * Cannon fire rate? Bullet power
 *
 */
public class Board extends Canvas implements KeyListener, Runnable {

    //configurable settings
    static final boolean USE_TICK_AS_SCORE = false;
    static final int NUM_SCORES_SAVED = 5;
    static final int POWERUP_TICKS = 5000;

    int score = 0;
    String prevScore;
    String highScore;
    String playerName = "";
    boolean playerNameSet = true;

    int tick = 0;
    boolean gameGoing = true;

    BufferedImage back;
    Cannon player;

    ArrayList<Ball> balls;
    ArrayList<Bullet> bullets;
    ArrayList<DeathAnimation> deathAnimations;
    ArrayList<Powerup> powerups;

    boolean[] moveKeys; // left, right, up, down
    boolean[] funcKeys; // R (restart), S (open scoreboard)

    public Board() {
        setBackground(Color.black);
        reset();
        this.addKeyListener(this);
        new Thread(this).start();

        spawnPowerup();

        setVisible(true);
    }

    public void reset() {
        bullets = new ArrayList<>();
        balls = new ArrayList<>();
        powerups = new ArrayList<>();
        deathAnimations = new ArrayList<>();

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
                FileWriter fw = new FileWriter(f2);
                fw.append("NAN,0,19691201\nNAN,0,19691201\nNAN,0,19691201\nNAN,0,19691201\nNAN,0,19691201");
                fw.close();
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
            //write the previous score
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
        //called by SetName.java. Finishes the process started in gameEnd().
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
        //game over text
        window.setColor(Color.RED);
        window.drawString("GAME OVER", BallBlast.WIDTH / 2 - 50, 200);
        window.drawString("Press R to restart", 50, BallBlast.HEIGHT - 70);
        window.drawString("Press S to view the scoreboard", BallBlast.WIDTH - 300, BallBlast.HEIGHT - 70);

        //kill balls that hit the ground
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
            if (b.yPos + 2 * b.getRadius() + 20 >= BallBlast.HEIGHT) {
                deathAnimations.add(new DeathAnimation((int) (b.xPos), (int) (b.yPos), b.size * 2, b.col));
                balls.remove(b);
            }
        }

        //if [R], reset
        if (funcKeys[0]) {
            funcKeys[0] = false;
            reset();
        }
        //if [S], scoreboard
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
        
        //graphToBack magic
        if (back == null) {
            back = (BufferedImage) (createImage(BallBlast.WIDTH, BallBlast.HEIGHT));
        }
        Graphics graphToBack = back.createGraphics();
        graphToBack.setColor(Color.BLACK);
        graphToBack.fillRect(0, 0, BallBlast.WIDTH, BallBlast.HEIGHT);
        Graphics2D twoDGraph = (Graphics2D) window;

        //bullet updating
        for (int i = bullets.size() - 1; i >= 0; i--) {
            bullets.get(i).move("");
            
            if (bullets.get(i).getyPos() < 0) {
                bullets.remove(i);
            }

            bullets.get(i).draw(graphToBack);
        }
        
        //ball updating and splitting
        for (int i = balls.size() - 1; i >= 0; i--) {
            Ball cball = balls.get(i);

            if (cball.getRadius() <= 100 && cball.getColor() == Color.GREEN) {
                deathAnimations.add(new DeathAnimation((int) cball.xPos, (int) cball.yPos, cball.size, cball.col));
                balls.add(new Ball(cball.xPos, cball.yPos, cball.xSpeed, -Math.abs(cball.ySpeed), cball.getRadius() - 25, Color.RED));
                balls.add(new Ball(cball.xPos, cball.yPos, -cball.xSpeed, -Math.abs(cball.ySpeed), cball.getRadius() - 25, Color.RED));
                balls.remove(i);
                spawnPowerup();
                continue;
            }

            if (cball.getRadius() <= 50 && cball.getColor() == Color.RED) {
                deathAnimations.add(new DeathAnimation((int) cball.xPos, (int) cball.yPos, cball.size, cball.col));
                balls.add(new Ball(cball.xPos, cball.yPos, cball.xSpeed, -Math.abs(cball.ySpeed), cball.getRadius() - 10, Color.ORANGE));
                balls.add(new Ball(cball.xPos, cball.yPos, -cball.xSpeed, -Math.abs(cball.ySpeed), cball.getRadius() - 10, Color.ORANGE));
                balls.remove(i);
                continue;
            }

            if (cball.getRadius() <= 25 && cball.getColor() == Color.ORANGE) {
                deathAnimations.add(new DeathAnimation((int) cball.xPos, (int) cball.yPos, cball.size, cball.col));
                balls.add(new Ball(cball.xPos, cball.yPos, cball.xSpeed, -Math.abs(cball.ySpeed), cball.getRadius() / 2, Color.CYAN));
                balls.add(new Ball(cball.xPos, cball.yPos, -cball.xSpeed, -Math.abs(cball.ySpeed), cball.getRadius() / 2, Color.CYAN));
                balls.remove(i);
                continue;
            } else if (cball.getRadius() <= 10 && cball.getColor() == Color.cyan) {
                // ball destroyed
                deathAnimations.add(new DeathAnimation((int) cball.xPos, (int) cball.yPos, 2 * cball.size, cball.col));
                balls.remove(i);

                spawnBall();
            }


            if(!player.getActivePowerup().equals("freeze")) { // don't move them if freeze is active
                cball.applyGravity();
                cball.move();
            }
            cball.draw(graphToBack);
        }

        //update death animations
        for (int i = 0; i < deathAnimations.size(); i++) {
            DeathAnimation anim = deathAnimations.get(i);
            anim.update();
            if (anim.life == 0) {
                deathAnimations.remove(anim);
            } else {
                anim.draw(graphToBack);
            }
        }
        
        //print scores
        graphToBack.setColor(Color.CYAN);
        graphToBack.drawString("Previous Score: " + prevScore, 50, 100);
        graphToBack.drawString("High Score: " + highScore, 50, 120);
        graphToBack.drawString("Current Score: " + score, 50, 200);

        if (!gameGoing) {
            gameOver(graphToBack);
        } else {    
            //shoot
            tick++;

            if(!player.getActivePowerup().equals("none"))
            {
                graphToBack.drawString(player.getActivePowerup() + ":  " + Integer.toString(POWERUP_TICKS - player.powerupTime), 50, 300);
                if(player.powerupTime++ > POWERUP_TICKS)
                {
                    player.setActivePowerup("none");
                    player.powerupTime = 0;
                }
            }


            if(!player.getActivePowerup().equals("firert")) {
                if (tick % 15 == 0) {
                    bullets.add(new Bullet(player.getxPos() + player.width / 2, player.getyPos(), 0, -10));
                }
            }
            else
            {
                if(tick % 7 == 0)
                {
                    bullets.add(new Bullet(player.getxPos() + player.width / 2, player.getyPos(), 0, -10));
                }
            }

            for(int i = powerups.size() - 1; i >= 0; i--)
            {
                powerups.get(i).applyGravity();
                powerups.get(i).draw(graphToBack);
                powerups.get(i).move();

                if(powerups.get(i).isColliding(player.xPos + player.width / 2, player.yPos + player.height / 2, powerups.get(i).getRadius(), 20) == true)
                {
                    System.out.println("collision");
                    player.setActivePowerup(powerups.get(i).powerType);
                    player.powerupTime = 0;
                    powerups.remove(i);
                }
            }

            //player move
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

            player.draw(graphToBack);

            for (Ball cball : balls) {
                //check if ball hit the player
                if (cball.isColliding(player.xPos + player.width / 2, player.yPos + player.height / 2, cball.getRadius(), 10) == true) {
                    gameGoing = false;
                    deathAnimations.add(new DeathAnimation((int) player.xPos, (int) player.yPos, 100, Color.BLUE));
                    gameEnd(window);

                    return;
                }

                //check if ball hit a bullet
                for (int j = bullets.size() - 1; j >= 0; j--) {
                    if (cball.isColliding(bullets.get(j).xPos, bullets.get(j).yPos, cball.getRadius(), 2)) {
                        bullets.remove(j);

                        if(player.getActivePowerup().equals("pierce"))
                            player.power = 10; // do extra damage if pierce is active
                        else
                            player.power = 5;
                        
                        cball.setSize(cball.size - player.power);
                        if (!USE_TICK_AS_SCORE) {
                            score += 5;
                        }
                    }
                }
            }

        }

        twoDGraph.drawImage(back, null, 0, 0);
    }

    public void spawnPowerup() {
        powerups.add(new Powerup((float)Math.random() * 600 + 10, 0.0f, (float)Math.random() + 0.2f, 0.0f));
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
