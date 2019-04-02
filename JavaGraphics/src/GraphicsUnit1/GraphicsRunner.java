package GraphicsUnit1;
import javax.swing.JFrame;

public class GraphicsRunner extends JFrame
{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	public GraphicsRunner()
	{
		super("Graphics Runner");

		setSize(WIDTH,HEIGHT);

		//getContentPane().add(new SmileyFace());
		getContentPane().add(new ShapePanel());

		//add other classes to run them 
		//GraphicsUnit1.BigHouse, GraphicsUnit1.Robot, or GraphicsUnit1.ShapePanel

		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main( String args[] )
	{
		GraphicsRunner run = new GraphicsRunner();
	}
}