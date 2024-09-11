import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObstacleManager {
    private List<Spike> obstacles;
    private int floorY;
    private int floorHeight;

    public ObstacleManager(int floorY, int floorHeight) {
        this.floorY = floorY;
        this.floorHeight = floorHeight;
        obstacles = new ArrayList<>();
        initializeObstacles(); // Initialize obstacles in the constructor
    }

    private void initializeObstacles() {
        obstacles.add(new Spike(900, floorY, floorHeight));
        obstacles.add(new Spike(1400, floorY, floorHeight));
        obstacles.add(new Spike(1440, floorY, floorHeight));
        obstacles.add(new Spike(1480, floorY, floorHeight));
        obstacles.add(new Spike(2400, floorY, floorHeight));
        obstacles.add(new Spike(2440, floorY, floorHeight));
        obstacles.add(new Spike(2480, floorY, floorHeight));
    }

    public void updateObstacles() {
        for (Spike obstacle : obstacles) {
            obstacle.update();
        }
    }

    public void renderObstacles(Graphics g) {
        for (Spike obstacle : obstacles) {
            obstacle.draw(g);
        }
    }

    public List<Spike> getObstacles() {
        return obstacles;
    }
}
