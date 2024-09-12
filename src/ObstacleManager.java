import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObstacleManager {
    private List<Spike> spike;
    private List<FlatSpike> flatSpike;
    private int floorY;
    private int floorHeight;

    public ObstacleManager(int floorY, int floorHeight) {
        this.floorY = floorY;
        this.floorHeight = floorHeight;
        spike = new ArrayList<>();
        flatSpike = new ArrayList<>();
        initializeObstacles();
    }

    private void initializeObstacles() {
        spike.add(new Spike(900, floorY, floorHeight));

        spike.add(new Spike(1400, floorY, floorHeight));
        spike.add(new Spike(1440, floorY, floorHeight));
        //flatSpike.add(new FlatSpike(1480, floorY, floorHeight));
        spike.add(new Spike(2400, floorY, floorHeight));
        spike.add(new Spike(2440, floorY, floorHeight));
        spike.add(new Spike(2480, floorY, floorHeight));
    }

    public void updateObstacles() {
        for (Spike obstacle : spike) {
            obstacle.update();
        }
        for (FlatSpike obstacle : flatSpike) {
            obstacle.update();
        }
    }

    public void renderObstacles(Graphics g) {
        for (Spike obstacle : spike) {
            obstacle.draw(g);
        }
        for (FlatSpike obstacle : flatSpike) {
            obstacle.update();
        }
    }

    public List<Spike> getSpike() {
        return spike;
    }

    public List<FlatSpike> getFlatSpike() {
        return flatSpike;
    }
}
