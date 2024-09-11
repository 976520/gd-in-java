import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GeometryDash extends JPanel implements ActionListener {
    private Timer gameTimer;
    private Player playerCharacter;
    private ObstacleManager obstacleManagerSystem;
    private boolean isGameRunning;
    private int attemptCount;
    private Camera gameCamera;
    private Floor gameFloor;

    public GeometryDash() {
        setFocusable(true);
        setPreferredSize(new Dimension(800, 400));
        initializeGameElements();
        gameTimer = new Timer(15, this);
        gameTimer.start();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && isGameRunning) {
                    playerCharacter.performJump();
                }
            }
        });
    }

    public void initializeGameElements() {
        gameFloor = new Floor(300, 100, getWidth());
        playerCharacter = new Player();
        obstacleManagerSystem = new ObstacleManager(gameFloor.getYPosition(), gameFloor.getHeightValue());
        gameCamera = new Camera(getWidth());
        isGameRunning = true;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (isGameRunning) {
            renderGameObjects(graphics);
        } else {
            restartGame();
        }
        renderFloor(graphics);
        renderAttemptCount(graphics);
    }

    private void renderGameObjects(Graphics graphics) {
        gameCamera.applyTranslation(graphics);

        playerCharacter.renderPlayer(graphics);
        obstacleManagerSystem.renderObstacles(graphics);

        ((Graphics2D) graphics).translate(gameCamera.getXPosition(), 0);
    }

    private void renderFloor(Graphics graphics) {
        gameFloor.renderFloor(graphics, gameCamera.getXPosition());
    }

    private void renderAttemptCount(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", Font.PLAIN, 24));
        graphics.drawString("Attempt: " + attemptCount, 10 - gameCamera.getXPosition(), 30);
    }

    private void restartGame() {
        attemptCount++;
        initializeGameElements();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameRunning) {
            playerCharacter.updatePlayerPosition();
            obstacleManagerSystem.updateObstacles();
            gameFloor.updateScrolling();
            gameCamera.updateCameraPosition(playerCharacter.getXPosition());
            detectCollisions();
            repaint();
        }
    }

    private void detectCollisions() {
        for (Obstacle obstacle : obstacleManagerSystem.getObstacleList()) {
            if (obstacle.getBounds().intersects(playerCharacter.getBounds())) {
                isGameRunning = false;
                break;
            }
        }
    }

    public static void main(String[] args) {
        JFrame gameWindow = new JFrame("Geometry Dash Game");
        GeometryDash gamePanel = new GeometryDash();
        gameWindow.add(gamePanel);
        gameWindow.pack();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
    }
}
