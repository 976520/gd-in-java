import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GeometryDash extends JPanel implements ActionListener {
    private Timer timer;
    private Player playerCharacter;
    private ObstacleManager obstacleManagerSystem;
    private boolean isGameRunning;
    private int numberOfAttempts;
    private Camera cameraSystem;
    private Floor floorObject;

    public GeometryDash() {
        setFocusable(true);
        setPreferredSize(new Dimension(800, 400));
        initializeGame();
        timer = new Timer(15, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && isGameRunning) {
                    if (playerCharacter.isBoostedJumpAvailable()) {
                        playerCharacter.performBoostedJump();
                    } else {
                        playerCharacter.performNormalJump();
                    }
                }
            }
        });
    }

    public void initializeGame() {
        floorObject = new Floor(300, 100, getWidth());
        playerCharacter = new Player();
        obstacleManagerSystem = new ObstacleManager(floorObject.getFloorY(), floorObject.getFloorHeight());
        cameraSystem = new Camera(getWidth());
        isGameRunning = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isGameRunning) {
            renderGameObjects(g);
        } else {
            resetGame();
        }
        renderFloor(g);
        renderAttempts(g);
    }

    private void renderGameObjects(Graphics g) {
        cameraSystem.translateCamera(g);

        playerCharacter.renderPlayer(g);
        obstacleManagerSystem.renderObstacles(g);

        ((Graphics2D) g).translate(cameraSystem.getX(), 0);
    }

    private void renderFloor(Graphics g) {
        floorObject.renderFloor(g, cameraSystem.getX());
    }

    private void renderAttempts(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Attempt: " + numberOfAttempts, 10 - cameraSystem.getX(), 30);
    }

    private void resetGame() {
        numberOfAttempts++;
        initializeGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameRunning) {
            playerCharacter.updatePlayerPosition();
            obstacleManagerSystem.updateObstacles();
            floorObject.updateFloor();
            cameraSystem.updateCamera(playerCharacter.getXPosition());
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

        for (YellowJumpRing jumpRing : obstacleManagerSystem.getJumpRingList()) {
            if (jumpRing.isPlayerTouchingRing(playerCharacter)) {
                playerCharacter.setBoostedJumpAvailable(true);
            } else {
                playerCharacter.setBoostedJumpAvailable(false);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Geometry Dash");
        GeometryDash game = new GeometryDash();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
