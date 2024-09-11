import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class YellowJumpRing {
    private int x, y;
    private int width = 20, height = 20;
    private boolean isTouched;

    public YellowJumpRing(int x, int y) {
        this.x = x;
        this.y = y;
        this.isTouched = false;
    }

    public boolean isPlayerTouchingRing(Player player) {
        return getBounds().intersects(player.getBounds());
    }

    public void draw(Graphics g) {
        if (isTouched) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.ORANGE);
        }
        g.fillOval(x, y, width, height);
    }

    public void update() {
        // ...
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void setTouched(boolean touched) {
        this.isTouched = touched;
    }
}
