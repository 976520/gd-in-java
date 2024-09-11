import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GeometryDash extends JPanel implements ActionListener {
    private Timer timer;
    private Player playerCharacter;
    private ObstacleManager obstacleManagerSystem;
    private boolean isGameRunning;
    private int attempts;
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
                    playerCharacter.jump();
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
        drawFloor(g);
        drawAttempts(g);

        for (YellowJumpRing ring : obstacleManagerSystem.getJumpRingList()) {
            ring.draw(g);
        }
    }


    private void renderGameObjects(Graphics g) {
        cameraSystem.translateCamera(g);

        playerCharacter.render(g);
        obstacleManagerSystem.renderObstacles(g);

        ((Graphics2D) g).translate(cameraSystem.getX(), 0);
    }

    private void drawFloor(Graphics g) {
        floorObject.renderFloor(g, cameraSystem.getX());
    }

    private void drawAttempts(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Attempt: " + attempts, 10 - cameraSystem.getX(), 30);
    }

    private void resetGame() {
        attempts++;
        initializeGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameRunning) {
            playerCharacter.update();
            obstacleManagerSystem.updateObstacles();
            floorObject.updateFloor();
            cameraSystem.updateCamera(playerCharacter.getX());

            for (YellowJumpRing ring : obstacleManagerSystem.getJumpRingList()) {
                ring.update();
            }

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

        for (YellowJumpRing ring : obstacleManagerSystem.getJumpRingList()) {
            if (ring.isPlayerTouchingRing(playerCharacter)) {
                playerCharacter.setBoostedJumpAvailable(true);
                playerCharacter.jump();
                ring.setTouched(true);
            } else {
                ring.setTouched(false);
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
