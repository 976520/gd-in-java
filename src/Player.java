import java.awt.*;

public class Player {
    private int playerXPosition;
    private int playerYPosition;
    private int playerWidth = 30;
    private int playerHeight = 30;
    private int verticalVelocity;
    private boolean isJumping;
    private double rotationAngle;
    private final double ROTATION_SPEED = 90;
    private final int MOVE_SPEED = 4;

    public Player() {
        this.playerXPosition = 100;
        this.playerYPosition = 300 - playerHeight;
        this.verticalVelocity = 0;
        this.isJumping = false;
        this.rotationAngle = 0;
    }

    public void updatePlayerPosition() {
        playerXPosition += MOVE_SPEED;

        if (isJumping) {
            verticalVelocity += 1;
            playerYPosition += verticalVelocity;
            rotationAngle += ROTATION_SPEED / 10.0;

            if (playerYPosition >= 300 - playerHeight) {
                playerYPosition = 300 - playerHeight;
                isJumping = false;
                verticalVelocity = 0;
                rotationAngle = 0;
            }
        }
    }

    public void performJump() {
        if (!isJumping) {
            verticalVelocity = -15;
            isJumping = true;
            rotationAngle = -90;
        }
    }

    public void renderPlayer(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.GREEN);
        graphics2D.rotate(Math.toRadians(rotationAngle), playerXPosition + playerWidth / 2, playerYPosition + playerHeight / 2);
        graphics2D.fillRect(playerXPosition, playerYPosition, playerWidth, playerHeight);
        graphics2D.rotate(-Math.toRadians(rotationAngle), playerXPosition + playerWidth / 2, playerYPosition + playerHeight / 2);
    }

    public Rectangle getBounds() {
        return new Rectangle(playerXPosition, playerYPosition, playerWidth, playerHeight);
    }

    public int getXPosition() {
        return playerXPosition;
    }
}