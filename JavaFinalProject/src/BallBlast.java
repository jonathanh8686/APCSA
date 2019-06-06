import javax.swing.JFrame;
import java.awt.Component;

public class BallBlast extends JFrame
{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public BallBlast()
    {
        super("Ball Blast");
        setSize(WIDTH, HEIGHT);

        Board gameBoard = new Board();
        gameBoard.setFocusable(true);

        getContentPane().add(gameBoard);

        setVisible(true);
    }

    public static void main( String args[] )
    {
        BallBlast run = new BallBlast();
    }
}