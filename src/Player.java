import java.awt.*;

public class Player {
    private int x, y;
    private int width = 30, height = 30;
    private int yVelocity;
    private boolean isJumping;
    private boolean isBoostedJumpAvailable;
    private double rotation;
    private final double ROTATION_SPEED = 90;
    private final int NORMAL_JUMP_SPEED = -15;
    private final int BOOSTED_JUMP_SPEED = -25;
    private final int MOVE_SPEED = 4;

    public Player() {
        this.x = 100;
        this.y = 300 - height;
        this.yVelocity = 0;
        this.isJumping = false;
        this.isBoostedJumpAvailable = false;
        this.rotation = 0;
    }

    public void update() {
        x += MOVE_SPEED;

        if (isJumping) {
            yVelocity += 1;
            y += yVelocity;
            rotation += ROTATION_SPEED / 10.0;

            if (y >= 300 - height) {
                y = 300 - height;
                isJumping = false;
                yVelocity = 0;
                rotation = 0;
            }
        }
    }

    public void jump() {
        if (!isJumping) {
            if (isBoostedJumpAvailable) {
                yVelocity = BOOSTED_JUMP_SPEED;
                isBoostedJumpAvailable = false;
            } else {
                yVelocity = NORMAL_JUMP_SPEED;
            }
            isJumping = true;
            rotation = -90;
        }
    }

    public void setBoostedJumpAvailable(boolean available) {
        this.isBoostedJumpAvailable = available;
    }

    public boolean isBoostedJumpAvailable() {
        return isBoostedJumpAvailable;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        g2d.rotate(Math.toRadians(rotation), x + width / 2, y + height / 2);
        g2d.fillRect(x, y, width, height);
        g2d.rotate(-Math.toRadians(rotation), x + width / 2, y + height / 2);
    }
}
