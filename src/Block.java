import java.awt.*;
// 보수중입니다
public class Block {
    public int x, y;
    private int width, height;

    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g, int cameraX) {
        g.setColor(Color.BLUE);
        g.fillRect(x - cameraX, y, width, height);
    }
}
