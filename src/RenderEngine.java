import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;

public class RenderEngine extends JPanel {
    private ArrayList<Displayable> renderList = new ArrayList<>();

    public void addToRenderList(Displayable displayable) {
        renderList.add(displayable);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Displayable displayable : renderList) {
            displayable.draw(g);
        }
    }

    public void update() {
        repaint();
    }
}
