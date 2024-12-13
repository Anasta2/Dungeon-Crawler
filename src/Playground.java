import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Playground {
    private ArrayList<Sprite> environment = new ArrayList<>();

    // Constructor that loads the playground based on a map file
    public Playground(String pathName) {
        try {
            // Load images with specified paths
            final BufferedImage imageTree = ImageIO.read(new File("C:\\Users\\Dell\\Pictures\\tree.png"));
            final BufferedImage imageGrass = ImageIO.read(new File("C:\\Users\\Dell\\Pictures\\grass.png"));
            final BufferedImage imageRock = ImageIO.read(new File("C:\\Users\\Dell\\Pictures\\rock.png"));
            final BufferedImage imageTrap = ImageIO.read(new File("C:\\Users\\Dell\\Pictures\\trap.png"));

            final int imageTreeWidth = imageTree.getWidth();
            final int imageTreeHeight = imageTree.getHeight();
            final int imageGrassWidth = imageGrass.getWidth();
            final int imageGrassHeight = imageGrass.getHeight();
            final int imageRockWidth = imageRock.getWidth();
            final int imageRockHeight = imageRock.getHeight();

            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line = bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;

            while (line != null) {
                for (byte element : line.getBytes(StandardCharsets.UTF_8)) {
                    switch (element) {
                        case 'T' -> environment.add(new SolidSprite(
                                imageTree,  // Ensure BufferedImage comes first
                                columnNumber * imageTreeWidth,
                                lineNumber * imageTreeHeight,
                                imageTreeWidth, imageTreeHeight
                        ));
                        case ' ' -> environment.add(new Sprite(
                                imageGrass,  // Ensure BufferedImage comes first
                                columnNumber * imageGrassWidth,
                                lineNumber * imageGrassHeight,
                                imageGrassWidth, imageGrassHeight
                        ));
                        case 'R' -> environment.add(new SolidSprite(
                                imageRock,  // Ensure BufferedImage comes first
                                columnNumber * imageRockWidth,
                                lineNumber * imageRockHeight,
                                imageRockWidth, imageRockHeight
                        ));
                    }
                    columnNumber++;
                }
                columnNumber = 0;
                lineNumber++;
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get only the solid sprites from the environment
    public List<SolidSprite> getSolidSpriteList() {
        List<SolidSprite> solidSpriteList = new ArrayList<>();
        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite) solidSpriteList.add((SolidSprite) sprite);
        }
        return solidSpriteList;
    }

    // Method to get all sprites as displayable items
    public ArrayList<Displayable> getSpriteList() {
        ArrayList<Displayable> displayableList = new ArrayList<>();
        for (Sprite sprite : environment) {
            displayableList.add(sprite);
        }
        return displayableList;
    }
}
