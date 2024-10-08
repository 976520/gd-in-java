import java.awt.Color;
import java.awt.Graphics;

public class Floor {
    private int y;
    private int height;
    private int width;
    private int xOffset = -300;

    public Floor(int y, int height, int width) {
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public int getFloorY() {
        return y;
    }

    public int getFloorHeight() {
        return height;
    }

    public void updateFloor() {
        xOffset -= 5;
        if (xOffset < -width) {
            xOffset = 0;
        }
    }

    public void renderFloor(Graphics g, int cameraX) {
        int startX = xOffset - cameraX % width;
        for (int x = startX; x < getWidth() + width; x += width) {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
        }
    }

    private int getWidth() {
        return width;
    }
}
