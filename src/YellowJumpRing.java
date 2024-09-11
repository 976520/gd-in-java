import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class YellowJumpRing {
    private int x, y;
    private int width = 30, height = 10;
    private boolean isTouched;

    public YellowJumpRing(int x, int floorY) {
        this.x = x;
        this.y = floorY - height - 20;  // 링의 위치 설정
        this.isTouched = false;
    }

    public void update() {
        // 필요에 따라 업데이트 로직 추가
    }

    public void draw(Graphics g) {
        if (isTouched) {
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, width, height);
        }
    }

    public boolean isPlayerTouchingRing(Player player) {
        Rectangle ringBounds = new Rectangle(x, y, width, height);
        return ringBounds.intersects(player.getBounds());
    }

    public void setTouched(boolean touched) {
        this.isTouched = touched;
    }
}
