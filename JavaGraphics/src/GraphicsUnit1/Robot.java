package GraphicsUnit1;//(c) A+ Computer Science
//www.apluscompsci.com

//Name -
//Date -
//Class -
//Lab  -

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

class Robot extends Canvas
{
   public Robot()    //constructor method - sets up the class
   {
      setSize(800,600);
      setBackground(Color.WHITE);

      setVisible(true);
   }

   public void paint( Graphics window )
   {
      window.setColor(Color.BLUE);

      window.drawString("GraphicsUnit1.Robot LAB ", 35, 35 );

      head(window);
      upperBody(window);
      lowerBody(window);

      
      //call other methods
      
   }

   public void head( Graphics window )
   {
      window.setColor(Color.GRAY);

      window.fillRect(340, 70, 120, 120);

      window.setColor(Color.YELLOW);
      window.fillOval(342, 90, 30, 30);
      window.fillOval(428, 90, 30, 30);

      window.setColor(Color.BLACK);
      window.fillRect(350, 150, 100, 30);
   }

   public void upperBody( Graphics window )
   {
         window.setColor(Color.DARK_GRAY);
         window.fillRect(300, 190, 200, 300);
		//add more code here
   }

   public void lowerBody( Graphics window )
   {

         window.setColor(Color.DARK_GRAY);
         window.fillRect(350, 490, 100, 200);
		//add more code here

   }
}