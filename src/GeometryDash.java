import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GeometryDash extends JPanel implements ActionListener {
    private Timer timer;
    private Player player;
    private ObstacleManager obstacleManager;
    private boolean isRunning;
    private int attempts;
    private Camera camera;
    private Floor floor;

    public GeometryDash() {
        setFocusable(true);
        setPreferredSize(new Dimension(800, 400));
        initGame();
        timer = new Timer(15, this);
        timer.start();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && isRunning) {
                    player.jump();
                }
            }
        });
    }

    public void initGame() {
        floor = new Floor(300, 100, getWidth());
        player = new Player();
        obstacleManager = new ObstacleManager(floor.getY(), floor.getHeight());
        camera = new Camera(getWidth());
        isRunning = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isRunning) {
            drawObjects(g);
        } else {
            resetGame();
        }
        drawFloor(g);
        drawAttempts(g);
    }

    private void drawObjects(Graphics g) {
        camera.translate(g);

        player.draw(g);
        obstacleManager.draw(g);

        ((Graphics2D) g).translate(camera.getX(), 0);
    }

    private void drawFloor(Graphics g) {
        floor.draw(g, camera.getX());
    }

    private void drawAttempts(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Attempt: " + attempts, 10 - camera.getX(), 30);
    }

    private void resetGame() {
        attempts++;
        initGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            player.update();
            obstacleManager.update();
            floor.update();
            camera.update(player.getX());
            checkCollision();
            repaint();
        }
    }

    private void checkCollision() {
        for (Obstacle obstacle : obstacleManager.getObstacles()) {
            if (obstacle.getBounds().intersects(player.getBounds())) {
                isRunning = false;
                break;
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
