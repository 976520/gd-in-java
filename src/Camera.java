import java.awt.*;

public class Camera {
    private int cameraXPosition;
    private int cameraViewportWidth;

    public Camera(int cameraViewportWidth) {
        this.cameraXPosition = 0;
        this.cameraViewportWidth = cameraViewportWidth;
    }

    public void updateCameraPosition(int playerCharacterXPosition) {
        cameraXPosition = playerCharacterXPosition - cameraViewportWidth / 2;
    }

    public void applyTranslation(Graphics graphics) {
        ((Graphics2D) graphics).translate(-cameraXPosition, 0);
    }

    public int getXPosition() {
        return cameraXPosition;
    }
}