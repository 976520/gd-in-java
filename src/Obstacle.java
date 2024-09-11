import java.awt.*;

public class Obstacle {
    private int obstacleXPosition;
    private int obstacleYPosition;
    private int obstacleWidth = 40;
    private int obstacleHeight = 40;

    public Obstacle(int obstacleXPosition, int floorYPosition, int floorHeightValue) {
        this.obstacleXPosition = obstacleXPosition;
        this.obstacleYPosition = floorYPosition - obstacleHeight;
    }

    public void moveObstacle() {
        obstacleXPosition -= 5;
        if (obstacleXPosition < -obstacleWidth) {
            obstacleXPosition = 800;
        }
    }

    public void renderObstacle(Graphics graphics) {
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillPolygon(new int[] {obstacleXPosition, obstacleXPosition + obstacleWidth / 2, obstacleXPosition + obstacleWidth}, new int[] {obstacleYPosition + obstacleHeight, obstacleYPosition, obstacleYPosition + obstacleHeight}, 3);
    }

    public Rectangle getBounds() {
        return new Rectangle(obstacleXPosition, obstacleYPosition, obstacleWidth, obstacleHeight);
    }
}