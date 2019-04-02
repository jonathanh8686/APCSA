//(c) A+ Computer Science
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
	private int xSpeed;
	private int ySpeed;

   /*
    *The constructor is used to initialize all instance variables.
    *The constructor makes the object.
    */
   public Shape(int x, int y, int wid, int ht, Color col, int xSpd, int ySpd)
   {
       xPos = x;
       yPos = y;
       width = wid;
       height = ht;
       color = col;
       xSpeed = xSpd;
       ySpeed = ySpd;
   }
      /*
    *The draw method draws the shape on the screen.
    */

   public void draw(Graphics window)
   {
      window.setColor(color);
      window.fillRect(xPos, yPos, width, height);

       window.setColor(color);
       //window.fillRect(xPos, yPos, width, height);
       window.drawOval(xPos, yPos, width + 10, height + 10);
       window.fillRect(xPos + 20, yPos - (yPos/10), width /8, height /8);
       window.fillRect(xPos + 50, yPos - (yPos/10), width/4, height/4);

   }

   /*
    *This draw method will be used to erase the shape.
    */
   public void draw(Graphics window, Color col)
   {
      window.setColor(col);
      window.fillRect(xPos, yPos, width, height);
   }

   public void moveAndDraw(Graphics window)
   {
    	setX(xPos + xSpeed);
    	setY(yPos + ySpeed);
    	draw(window);
   }

   //add in set and get methods for xPos, yPos, xSpeed, and ySpeed
   public int getX()
   {
       return xPos;
   }

   public void setX( int x )
   {
       xPos = x;
   }

   public int getY()
   {
       return yPos;
   }

   public void setY(int y)
   {
       yPos = y;
   }

   public int getxSpeed()
   {
       return xSpeed;
   }

   public void setxSpeed(int xspd)
   {
       xSpeed = xspd;
   }

   public int getySpeed()
   {
       return ySpeed;
   }

   public void setySpeed(int yspd)
   {
       ySpeed = yspd;
   }

   public String toString()
   {
   	return xPos+" "+yPos+" "+width+" "+height+" "+color+" "+xSpeed+" "+ySpeed;
   }
}