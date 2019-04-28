//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde {
    private List<Alien> aliens;

    public AlienHorde(int size) {
        aliens = new ArrayList<Alien>();
    }

    public void add(Alien al) {
        aliens.add(al);
    }

    public void drawEmAll(Graphics window) {
        for (int i = 0; i < aliens.size(); i++)
            aliens.get(i).draw(window);
    }

    public void moveEmAll() {
        for (int i = 0; i < aliens.size(); i++)
            aliens.get(i).move("");
    }

    public void removeDeadOnes(List<Ammo> shots) {
        for(int i = 0; i < shots.size(); i++)
        {
            for(int j = aliens.size() - 1; j >= 0; j--)
            {
                if(aliens.get(j).isColliding(shots.get(i))){
                    aliens.remove(j);
                }
            }
        }
    }

    public String toString() {
        return "";
    }
}
