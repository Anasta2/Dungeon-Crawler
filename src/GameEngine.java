import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {

    private final DynamicSprite hero;  // Reference to the hero sprite
    private boolean isRunning = false;  // Flag to track if the hero is running

    // Constructor
    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Handle movement direction
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;

            // Press Shift or Ctrl to run
            case KeyEvent.VK_SHIFT:  // Run when Shift is pressed
            case KeyEvent.VK_CONTROL:  // Run when Ctrl is pressed
                isRunning = true;
                hero.setRunSpeed();  // Set hero's speed to running
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Stop running when the key is released
        if (e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyCode() == KeyEvent.VK_CONTROL) {
            isRunning = false;
            hero.setSpeed(5);  // Reset to walking speed
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used, but required by KeyListener
    }

    @Override
    public void update() {
        // This can be expanded for game logic updates
        // For example, you could add collision detection or additional behavior here
    }
}

