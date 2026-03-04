package entity.modifiers;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.util.Random;

public abstract class Modifier extends Entity {

    public Modifier(GamePanel gamePanel, Color color) {
        super(gamePanel, color);
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
        int randomSpeedX = random.nextInt(6, 8), randomSpeedY = random.nextInt(0, 5);

        speedX = speedDirectionX == 1 ? randomSpeedX : randomSpeedX * -1;
        speedY = speedDirectionY == 1 ? randomSpeedY : randomSpeedY * -1;
    }

    @Override
    protected void collisionReaction() {
        if (entityPaddleCollision)
            modifierReaction();
    }

    abstract protected void modifierReaction();

}
