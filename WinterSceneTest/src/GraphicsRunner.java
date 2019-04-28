import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicsRunner extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public GraphicsRunner() {
        super("THE WINTER SCENE PROJECT");
        setSize(WIDTH, HEIGHT);

        WinterScenePanel winterPanel = new WinterScenePanel();
        winterPanel.setFocusable(true);
        getContentPane().add(winterPanel);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        GraphicsRunner run = new GraphicsRunner();
    }
}
