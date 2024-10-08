import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FlatSpike {
    private int x, y;
    public int width = 40, height = 40;

    public FlatSpike(int x, int floorY, int floorHeight) {
        this.x = x;
        this.y = floorY - height;

    }

    public void update() {
        x -= 5;
        if (x < -width) {
            x = 800;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillPolygon(new int[] {x, x + width / 2, x + width}, new int[] {y + height, y, y + height}, 3);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
