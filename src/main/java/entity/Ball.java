package entity;

import main.GamePanel;

import java.awt.*;

public class Ball extends Entity {

    int originX, originY, originalSpeed;
    private final int BALL_SPEED_UP = 2;

    public Ball(GamePanel gamePanel) {
        super(gamePanel, Color.white);
        this.gamePanel = gamePanel;
        setInitialConfiguration();
    }

    private void setInitialConfiguration() {
        int size = gamePanel.tileSize / 3;
        worldX = gamePanel.screenWidth / 2 - size / 2;
        worldY = gamePanel.screenHeight / 2 - size / 2;

        ballFirstSide();
        speedY = 0;

        originX = worldX;
        originY = worldY;
        originalSpeed = speedX;

    }

    private void ballFirstSide() {
        int randomNumber = (int) (Math.random() * 2);
        speedX = randomNumber == 0 ? 10 : -10;
    }


    @Override
    protected void collisionReaction() {
        if (entityYWallCollision || entityPaddleCollision)
            gamePanel.soundEffect.play(0);

        // Reacts to the paddle collision
        reactToBallPaddleCollision();
    }

    private void reactToBallPaddleCollision() {
        if (entityPaddleCollision) {
            speedY = -((gamePanel.collisionChecker.hitPos / 3));
            speedX = -speedX;
        }
    }

    public void restarBallOriginalPosition() {
        worldX = originX;
        worldY = originY;
        ballFirstSide();
        speedY = 0;
    }

    public void speedUp() {
        speedX += speedX > 0 ? BALL_SPEED_UP : -BALL_SPEED_UP;
        System.out.println("speedXUp: " + speedX);
    }
}
