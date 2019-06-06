import javax.swing.JFrame;
import java.awt.Component;

public class BallBlast extends JFrame
{
    protected static final int WIDTH = 1200;
    protected static final int HEIGHT = 720;

    public BallBlast()
    {
        super("Ball Blast");
        setSize(WIDTH, HEIGHT);

        Board gameBoard = new Board();
        gameBoard.setFocusable(true);

        getContentPane().add(gameBoard);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main( String args[] )
    {
        BallBlast run = new BallBlast();
    }
}