import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    JFrame displayZoneFrame;
    RenderEngine renderEngine;
    PhysicEngine physicEngine;
    GameEngine gameEngine;

    // Timer for the countdown
    private int remainingTime = 10; // 10 seconds countdown
    private JLabel timerLabel;  // Label to display the timer

    public Main() throws Exception {
        // Setup display frame
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400, 600);
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize RenderEngine, PhysicEngine, and GameEngine
        renderEngine = new RenderEngine();
        physicEngine = new PhysicEngine();
        System.out.println("Initialized RenderEngine and PhysicEngine");

        // Initialize Playground with the path to the map file
        Playground playground = new Playground("C:\\Users\\Dell\\Pictures\\level1.txt");
        System.out.println("Loaded Playground environment");

        // Integrate Playground's lists into RenderEngine and PhysicEngine first
        for (Displayable sprite : playground.getSpriteList()) {
            renderEngine.addToRenderList(sprite);
        }
        physicEngine.setEnvironment(playground.getSolidSpriteList());
        System.out.println("Added Playground sprites to RenderEngine and PhysicEngine");

        // Load the hero sprite sheet image and create the hero (DynamicSprite)
        BufferedImage heroImage = ImageIO.read(new File("C:\\Users\\Dell\\Pictures\\heroTileSheetlowres.png"));
        if (heroImage != null) {
            System.out.println("Loaded hero image");
        } else {
            System.out.println("Failed to load hero image");
        }
        DynamicSprite hero = new DynamicSprite(heroImage, 100, 300, 48, 50);

        // Initialize GameEngine with the hero and add it as a KeyListener
        gameEngine = new GameEngine(hero);
        displayZoneFrame.addKeyListener(gameEngine);
        System.out.println("Initialized GameEngine and added KeyListener");

        // Add the hero to RenderEngine and PhysicEngine last so it appears on top
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        System.out.println("Added hero to RenderEngine and PhysicEngine");

        // Set up timers for each engine to update regularly
        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(50, (time) -> physicEngine.update());

        // Start the timers to initiate game loop updates
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();
        System.out.println("Timers started for rendering and updating engines");

        // Add RenderEngine to the display frame and make it visible
        displayZoneFrame.getContentPane().add(renderEngine);

        // Add the Timer Label at the top of the game panel
        timerLabel = new JLabel("Time Remaining: " + remainingTime, SwingConstants.CENTER);
        timerLabel.setFont(new Font("Serif", Font.BOLD, 20));
        displayZoneFrame.getContentPane().add(timerLabel, BorderLayout.NORTH);

        // Start a countdown timer that updates every second
        Timer countdownTimer = new Timer(1000, e -> updateTimer());
        countdownTimer.start();
        System.out.println("Countdown timer started");

        displayZoneFrame.setVisible(true);
        displayZoneFrame.repaint(); // Ensure initial repaint
        System.out.println("Display frame setup complete and visible");
    }

    // Update the timer and check for game over
    private void updateTimer() {
        remainingTime--;
        timerLabel.setText("Time Remaining: " + remainingTime); // Update the label with the remaining time
        if (remainingTime <= 0) {
            gameOver(); // Call game over when timer reaches 0
        }
    }

    // Show the game over screen when the timer hits zero
    private void gameOver() {
        JOptionPane.showMessageDialog(displayZoneFrame, "Game Over! Time's up.");
        System.exit(0); // Exit the game when time is up (or you can implement a restart logic)
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}
