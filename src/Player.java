import java.awt.Graphics;  // Graphics 클래스를 import
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

public class Player {
    private int x, y;
    private int width = 30, height = 30;
    private int yVelocity;
    private boolean jumping;
    private boolean boostedJumpAvailable;  // Yellow Jump Ring 점프 가능 여부
    private double rotation;
    private final double ROTATION_SPEED = 90;
    private final int MOVE_SPEED = 4;

    private final int NORMAL_JUMP_VELOCITY = -15;
    private final int BOOSTED_JUMP_VELOCITY = -25;  // Yellow Jump Ring 점프 높이

    public Player() {
        this.x = 100;
        this.y = 300 - height;
        this.yVelocity = 0;
        this.jumping = false;
        this.boostedJumpAvailable = false;
        this.rotation = 0;
    }

    public void updatePlayerPosition() {
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

    public void performNormalJump() {
        if (!jumping) {
            yVelocity = NORMAL_JUMP_VELOCITY;
            jumping = true;
            rotation = -90;
        }
    }

    public void performBoostedJump() {
        if (!jumping && boostedJumpAvailable) {  // Yellow Jump Ring에 닿은 상태에서만 가능
            yVelocity = BOOSTED_JUMP_VELOCITY;
            jumping = true;
            boostedJumpAvailable = false;  // 한 번 사용 후 비활성화
            rotation = -90;
        }
    }

    public void setBoostedJumpAvailable(boolean available) {
        this.boostedJumpAvailable = available;
    }

    // 이 메서드를 추가하여 Yellow Jump Ring 점프 가능 여부를 반환
    public boolean isBoostedJumpAvailable() {
        return boostedJumpAvailable;
    }

    public void renderPlayer(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        g2d.rotate(Math.toRadians(rotation), x + width / 2, y + height / 2);
        g2d.fillRect(x, y, width, height);
        g2d.rotate(-Math.toRadians(rotation), x + width / 2, y + height / 2);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getXPosition() {
        return x;
    }
}
