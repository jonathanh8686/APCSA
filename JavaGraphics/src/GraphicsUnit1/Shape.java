package GraphicsUnit1;//(c) A+ Computer Science
//www.apluscompsci.com

//Name -
//Date -
//Class -
//Lab  -

import java.awt.Color;
import java.awt.Graphics;

public class Shape
{
   //instance variables
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private Color color;

   public Shape(int x, int y, int wid, int ht, Color col)
   {
		xPos = x;
		yPos = y;
		width = wid;
		height = ht;
		color = col;
   }


   public void draw(Graphics window)
   {
      window.setColor(color);
      //window.fillRect(xPos, yPos, width, height);
       window.drawOval(xPos, yPos, width + 10, height + 10);
       window.fillRect(xPos + 20, yPos - (yPos/10), width /8, height /8);
       window.fillRect(xPos + 50, yPos - (yPos/10), width/4, height/4);

   }

   //BONUS
   //add in set and get methods for all instance variables

   public String toString()
   {
   	return xPos+" "+yPos+" "+width+" "+height+" "+color;
   }
}