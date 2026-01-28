package entity.modificador;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class Modifier extends Entity {

    public Modifier(GamePanel gamePanel) {
        super(gamePanel, Color.YELLOW);
        createOriginalRandomPosition();
        createOriginalRandomSpeed();
    }

    private void createOriginalRandomPosition() {
        Random random = new Random();
        int size = gamePanel.tileSize / 3;

        worldY = (random.nextInt(4, 10) * gamePanel.tileSize) - size / 2;
        worldX = gamePanel.screenWidth / 2 - size / 2;

        solidArea = new Rectangle(0, 0, gamePanel.tileSize / 3, gamePanel.tileSize / 3);
    }

    private void createOriginalRandomSpeed() {
        Random random = new Random();

        int speedDirectionX = random.nextInt(2), speedDirectionY = random.nextInt(2);
        int randomSpeedX = random.nextInt(5, 10), randomSpeedY = random.nextInt(1, 2);

        speedX = speedDirectionX == 1 ? randomSpeedX : randomSpeedX * -1;
        speedY = speedDirectionY == 1 ? randomSpeedY : randomSpeedY * -1;
        System.out.println(speedX + " " + speedY);
    }

    @Override
    protected void collisionReaction() {

    }
}
