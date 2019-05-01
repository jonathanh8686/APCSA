
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Component;

public class TheGame extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    // name
    // blocks right side
    // score increases
    // blocks disappear
    // ball restarts

    public TheGame() {
        super("PONG");
        setSize(WIDTH, HEIGHT);

        //Pong game = new Pong();
        Breakout game = new Breakout();

        ((Component) game).setFocusable(true);
        getContentPane().add(game);

        setVisible(true);
    }

    public static void main(String args[]) {
        TheGame run = new TheGame();
    }
}
