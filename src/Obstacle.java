import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Obstacle {
    private int x, y;
    private int width = 40, height = 40;

    public Obstacle(int x, int floorY, int floorHeight) {
        this.x = x;
        this.y = floorY - height;  // Obstacle의 y 위치 설정
    }

    public void update() {
        x -= 5;  // 장애물이 왼쪽으로 이동
        if (x < -width) {
            x = 800;  // 화면 밖으로 나간 장애물의 위치를 재설정
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
