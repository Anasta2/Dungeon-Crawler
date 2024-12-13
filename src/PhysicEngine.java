import java.util.ArrayList;
import java.util.List;

public class PhysicEngine implements Engine {

    // List to hold sprites that can move
    private List<DynamicSprite> movingSpriteList = new ArrayList<>();

    // List to hold solid elements (environment)
    private List<SolidSprite> environment = new ArrayList<>();

    // Method to add a sprite to the moving list
    public void addToMovingSpriteList(DynamicSprite sprite) {
        movingSpriteList.add(sprite);
    }

    // Setter for the environment list
    public void setEnvironment(List<SolidSprite> environment) {
        this.environment = environment;
    }

    // Update method to move each sprite in the movingSpriteList if possible
    @Override
    public void update() {
        for (DynamicSprite sprite : movingSpriteList) {
            sprite.moveIfPossible(new ArrayList<>(environment));
        }
    }
}
