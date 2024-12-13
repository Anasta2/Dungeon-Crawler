import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class DynamicSprite extends SolidSprite {

    private double speed = 5;  // Default speed
    private double runSpeed = 10; // Speed when running
    private Direction direction = Direction.SOUTH; // Default direction is SOUTH
    private final int spriteSheetNumberOfColumn = 8; // 8 frames per row
    private int timeBetweenFrame = 200; // milliseconds between frames

    // Constructor that takes image, x, y, width, and height
    public DynamicSprite(BufferedImage image, double x, double y, double width, double height) {
        super(image, x, y, width, height);
    }

    // Set the hero's movement speed
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    // Set the hero's running speed (e.g., when pressing shift or ctrl)
    public void setRunSpeed() {
        this.speed = runSpeed;
    }

    // Setter for direction
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // Move method to adjust position based on speed and direction

    public void move() {
        switch (direction) {
            case NORTH -> this.setY(this.getY() - speed);
            case SOUTH -> this.setY(this.getY() + speed);
            case EAST -> this.setX(this.getX() + speed);
            case WEST -> this.setX(this.getX() - speed);
        }
    }

    // Check if movement is possible based on collision detection
    private boolean isMovingPossible(List<Sprite> environment) {
        double newX = this.getX();
        double newY = this.getY();

        switch (direction) {
            case NORTH -> newY -= speed;
            case SOUTH -> newY += speed;
            case EAST -> newX += speed;
            case WEST -> newX -= speed;
        }

        Rectangle2D.Double hitBox = new Rectangle2D.Double(newX, newY, getWidth(), getHeight());

        // Check for collisions with all solid elements in the environment
        for (Sprite element : environment) {
            if (element instanceof SolidSprite && element != this) {
                Rectangle2D.Double elementHitBox = new Rectangle2D.Double(
                        element.getX(), element.getY(), element.getWidth(), element.getHeight()
                );

                // If there's an intersection, movement is not possible
                if (hitBox.intersects(elementHitBox)) {
                    return false;
                }
            }
        }
        return true; // No collisions, movement is possible
    }

    // Public method to move the sprite if no collision is detected
    public void moveIfPossible(List<Sprite> environment) {
        if (isMovingPossible(environment)) {
            move();
        }
    }

    // Draw method to display the current frame based on direction
    @Override
    public void draw(Graphics g) {
        if (getImage() == null) {
            System.out.println("Warning: Hero image is null.");
            return;
        }

        // Each row corresponds to a direction
        int row = switch (direction) {
            case SOUTH -> 0;   // Down
            case EAST -> 3;    // Right
            case NORTH -> 2;   // Up
            case WEST -> 1;    // Left
        };

        // Calculate the index for the current frame in the row
        int index = (int) ((System.currentTimeMillis() / timeBetweenFrame) % spriteSheetNumberOfColumn);

        // Calculate source coordinates in the sprite sheet
        int frameWidth = 48; // width of each frame
        int frameHeight = 50; // height of each frame
        int sx1 = index * frameWidth;
        int sy1 = row * frameHeight;
        int sx2 = sx1 + frameWidth;
        int sy2 = sy1 + frameHeight;

        // Draw the selected frame at the sprite's current position
        g.drawImage(getImage(), (int) getX(), (int) getY(),
                (int) (getX() + frameWidth), (int) (getY() + frameHeight),
                sx1, sy1, sx2, sy2, null);

        System.out.printf("Drawing frame at [%d,%d] to [%d,%d] for direction %s%n",
                sx1, sy1, sx2, sy2, direction);
    }
}
