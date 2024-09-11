import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class ObstacleManager {
    private List<Obstacle> obstacles;
    private List<YellowJumpRing> jumpRings;
    private int floorY;
    private int floorHeight;

    public ObstacleManager(int floorY, int floorHeight) {
        this.floorY = floorY;
        this.floorHeight = floorHeight;
        obstacles = new ArrayList<>();
        jumpRings = new ArrayList<>();
        initializeObstacles();
        initializeJumpRings();
    }

    private void initializeObstacles() {
        obstacles.add(new Obstacle(900, floorY, floorHeight));
        obstacles.add(new Obstacle(1400, floorY, floorHeight));
        obstacles.add(new Obstacle(1440, floorY, floorHeight));
        obstacles.add(new Obstacle(1480, floorY, floorHeight));
    }

    private void initializeJumpRings() {
        jumpRings.add(new YellowJumpRing(1100, floorY));
    }

    public void updateObstacles() {
        for (Obstacle obstacle : obstacles) {
            obstacle.update();
        }
        for (YellowJumpRing ring : jumpRings) {
            ring.update();
        }
    }

    public void renderObstacles(Graphics g) {
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
        for (YellowJumpRing ring : jumpRings) {
            ring.draw(g);
        }
    }

    public List<Obstacle> getObstacleList() {
        return obstacles;
    }

    public List<YellowJumpRing> getJumpRingList() {
        return jumpRings;
    }
}
