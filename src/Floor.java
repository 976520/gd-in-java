import java.awt.*;

public class Floor {
    private int floorYPosition;
    private int floorHeightValue;
    private int floorWidth;
    private int xScrollOffset = 0;

    public Floor(int floorYPosition, int floorHeightValue, int floorWidth) {
        this.floorYPosition = floorYPosition;
        this.floorHeightValue = floorHeightValue;
        this.floorWidth = floorWidth;
    }

    public int getYPosition() {
        return floorYPosition;
    }

    public int getHeightValue() {
        return floorHeightValue;
    }

    public void updateScrolling() {
        xScrollOffset -= 5;
        if (xScrollOffset < -floorWidth) {
            xScrollOffset = 0;
        }
    }

    public void renderFloor(Graphics graphics, int cameraXPosition) {
        int startX = xScrollOffset - cameraXPosition % floorWidth;
        for (int x = startX; x < getWidth() + floorWidth; x += floorWidth) {
            graphics.setColor(Color.BLUE);
            graphics.fillRect(x, floorYPosition, floorWidth, floorHeightValue);
        }
    }

    private int getWidth() {
        return floorWidth;
    }
}