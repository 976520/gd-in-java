import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObstacleManager {
    private List<Obstacle> obstacles;
    private int floorY;
    private int floorHeight;

    public ObstacleManager(int floorY, int floorHeight) {
        this.floorY = floorY;
        this.floorHeight = floorHeight;
        obstacles = new ArrayList<>();
        initializeObstacles(); // Initialize obstacles in the constructor
    }

    private void initializeObstacles() {
        obstacles.add(new Obstacle(900, floorY, floorHeight));
        obstacles.add(new Obstacle(1400, floorY, floorHeight));
        obstacles.add(new Obstacle(1440, floorY, floorHeight));
        obstacles.add(new Obstacle(1480, floorY, floorHeight));
    }

    public void updateObstacles() {
        for (Obstacle obstacle : obstacles) {
            obstacle.update();
        }
    }

    public void renderObstacles(Graphics g) {
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }
}
