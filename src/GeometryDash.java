import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GeometryDash extends JPanel implements ActionListener {
    private static final int TIMER_DELAY = 15;
    private Timer gameTimer;
    private Player player;
    private ObstacleManager obstacleManager;
    private Camera camera;
    private Floor floor;
    private List<YellowJumpRing> jumpRings;
    private boolean isRunning;
    private int attemptCount;

    public GeometryDash() {
        setFocusable(true);
        setPreferredSize(new Dimension(800, 400));
        initializeGame();
        gameTimer = new Timer(TIMER_DELAY, this);
        gameTimer.start();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && isRunning) {
                    player.jump();
                }
            }
        });
    }

    private void initializeGame() {
        floor = new Floor(300, 100, getWidth());
        player = new Player();
        obstacleManager = new ObstacleManager(floor.getFloorY(), floor.getFloorHeight());
        camera = new Camera(getWidth());
        jumpRings = new ArrayList<>();
        isRunning = true;
        initializeJumpRings();
    }

    private void initializeJumpRings() {
        jumpRings.add(new YellowJumpRing(500, 250));
        jumpRings.add(new YellowJumpRing(700, 250));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isRunning) {
            renderGameObjects(g);
        } else {
            resetGame();
        }
        drawFloor(g);
        drawAttempts(g);
        drawJumpRings(g);
    }

    private void renderGameObjects(Graphics g) {
        camera.translateCamera(g);
        player.render(g);
        obstacleManager.renderObstacles(g);
        ((Graphics2D) g).translate(camera.getX(), 0);
    }

    private void drawFloor(Graphics g) {
        floor.renderFloor(g, camera.getX());
    }

    private void drawAttempts(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Attempt: " + attemptCount, 10 - camera.getX(), 30);
    }

    private void drawJumpRings(Graphics g) {
        for (YellowJumpRing ring : jumpRings) {
            ring.draw(g);
        }
    }

    private void resetGame() {
        attemptCount++;
        initializeGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            updateGameObjects();
            detectCollisions();
            repaint();
        }
    }

    private void updateGameObjects() {
        player.update();
        obstacleManager.updateObstacles();
        floor.updateFloor();
        camera.updateCamera(player.getX());

        for (YellowJumpRing ring : jumpRings) {
            ring.update();
        }
    }

    private void detectCollisions() {
        if (isPlayerCollidingWithObstacle()) {
            isRunning = false;
            return;
        }
        handleJumpRingCollisions();
    }

    private boolean isPlayerCollidingWithObstacle() {
        for (Spike obstacle : obstacleManager.getSpike()) {
            if (obstacle.getBounds().intersects(player.getBounds())) {
                return true;
            }
        }
        return false;
    }

    private void handleJumpRingCollisions() {
        for (YellowJumpRing ring : jumpRings) {
            if (ring.isPlayerTouchingRing(player)) {
                player.getBounds();
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
