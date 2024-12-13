import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite implements Displayable {

    private BufferedImage image;
    private double x, y;
    private double width, height;

    // Constructor that initializes the five attributes
    public Sprite(BufferedImage image, double x, double y, double width, double height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        // Draw the image at the specified x and y position
        g.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
    }

    // Getters for width, height, x, and y
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // New Setters for x and y
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
