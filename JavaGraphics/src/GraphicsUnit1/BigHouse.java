package GraphicsUnit1;

import java.awt.*;

public class BigHouse extends Canvas {
    public BigHouse()  //constructor - sets up the class
    {
        setSize(800, 600);
        setBackground(Color.WHITE);
        setVisible(true);
    }

    public void paint(Graphics window) {
        //call bigHouse
        bigHouse(window);
    }

    public void bigHouse(Graphics window) {
        window.setColor(Color.BLUE);

        window.drawString("BIG HOUSE ", 50, 50);

        window.setColor(Color.BLUE);
        window.fillRect(200, 200, 400, 400); // body

        window.setColor(Color.RED);
        window.fillPolygon(new int[]{150, 400, 650}, new int[]{200, 100, 200}, 3); // roof

        window.setColor(Color.GRAY);
        window.fillRect( 250, 300, 70, 80); // window
        window.fillRect( 480, 300, 70, 80); // window

        window.setColor(Color.ORANGE);
        window.fillRect(360, 500, 80, 200); // door

    }
}
