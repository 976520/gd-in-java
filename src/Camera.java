import java.awt.*;

public class Camera {
    private int x;
    private int width;

    public Camera(int width) {
        this.x = 0;
        this.width = width;
    }

    public void update(int playerX) {
        x = playerX - width / 2;
    }

    public void translate(Graphics g) {
        ((Graphics2D) g).translate(-x, 0);
    }

    public int getX() {
        return x;
    }
}
