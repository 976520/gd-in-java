import java.awt.*;

public class Player {
    private int x, y;
    private int width = 30, height = 30;
    private int yVelocity;
    private boolean jumping;
    private double rotation;
    private final double ROTATION_SPEED = 90;
    private final int MOVE_SPEED = 4;

    public Player() {
        this.x = 100;
        this.y = 300 - height;
        this.yVelocity = 0;
        this.jumping = false;
        this.rotation = 0;
    }

    public void update() {
        x += MOVE_SPEED;

        if (jumping) {
            yVelocity += 1;
            y += yVelocity;
            rotation += ROTATION_SPEED / 10.0;

            if (y >= 300 - height) {
                y = 300 - height;
                jumping = false;
                yVelocity = 0;
                rotation = 0;
            }
        }
    }

    public void jump() {
        if (!jumping) {
            yVelocity = -15;
            jumping = true;
            rotation = -90;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        g2d.rotate(Math.toRadians(rotation), x + width / 2, y + height / 2);
        g2d.fillRect(x, y, width, height);
        g2d.rotate(-Math.toRadians(rotation), x + width / 2, y + height / 2);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }
}
