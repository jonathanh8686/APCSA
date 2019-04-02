import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapePanel extends JPanel
{
	public ShapePanel()
	{
		setBackground(Color.WHITE);
		setVisible(true);
	}

	public void update(Graphics window)
	{
		paint(window);
	}

	/*
	 *All of your test code should be placed in paint.
	 */
	public void paint(Graphics window)
	{
		//instantiate a Shape
		//tell your shape to draw
		Shape shape = new Shape(200, 300, 100, 100, Color.GREEN, 5, 5);
		shape.draw(window);
		Shape shape2 = new Shape(80, 500, 100, 100, Color.RED, 1, 5);
		shape2.draw(window);
		Shape shape3 = new Shape(400, 200, 100, 50, Color.BLUE, 5, 1);
		shape3.draw(window);

		//instantiate a Shape
		//tell your shape to draw

		//instantiate a Shape
		//tell your shape to draw
	}
}