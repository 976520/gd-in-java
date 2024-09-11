import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObstacleManager {
    private List<Obstacle> obstacleList;
    private int floorYPosition;
    private int floorHeightValue;

    public ObstacleManager(int floorYPosition, int floorHeightValue) {
        this.floorYPosition = floorYPosition;
        this.floorHeightValue = floorHeightValue;
        obstacleList = new ArrayList<>();
        setupObstaclePositions();
    }

    private void setupObstaclePositions() {
        obstacleList.add(new Obstacle(900, floorYPosition, floorHeightValue));
        obstacleList.add(new Obstacle(1400, floorYPosition, floorHeightValue));
        obstacleList.add(new Obstacle(1440, floorYPosition, floorHeightValue));
        obstacleList.add(new Obstacle(1480, floorYPosition, floorHeightValue));
    }

    public void updateObstacles() {
        for (Obstacle obstacle : obstacleList) {
            obstacle.moveObstacle();
        }
    }

    public void renderObstacles(Graphics graphics) {
        for (Obstacle obstacle : obstacleList) {
            obstacle.renderObstacle(graphics);
        }
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }
}