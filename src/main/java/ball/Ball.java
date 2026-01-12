package ball;

import main.GamePanel;

import java.awt.*;

public class Ball {
    GamePanel gamePanel;


    public int worldX, worldY;
    int originX, originY, originalSpeed;
    public int speedX, speedY;
    public boolean ballWallCollision;
    public boolean ballPaddleCollision;

    public Rectangle solidArea;

    public Ball(GamePanel gamePanel) {
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

        solidArea = new Rectangle(0, 0, gamePanel.tileSize / 3, gamePanel.tileSize / 3);
    }

    private void ballFirstSide() {
        int randomNumber = (int) (Math.random() * 2);

        if (randomNumber == 0)
            speedX = 10;
        else {
            speedX = -10;
        }
    }

    public void update() {
        ballMovement();
    }


    private void ballMovement() {
        worldX -= speedX;
        worldY -= speedY;
        checkBallCollision();
    }

    private void checkBallCollision() {
        ballWallCollision = false;

        gamePanel.collisionChecker.checkBallCollision(this);
        gamePanel.collisionChecker.checkBallPaddleCollision(this);

        reactToWallCollision();
        reactToBallPaddleCollision();
    }

    private void reactToWallCollision() {
        if (ballWallCollision){
            speedY = -speedY;
            gamePanel.soundEffect.play(0);
        }

    }

    private void reactToBallPaddleCollision() {
        if (ballPaddleCollision) {
            speedY = -((gamePanel.collisionChecker.hitPos / 3)) ;
            speedX = -speedX;
            gamePanel.soundEffect.play(0);
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillOval(worldX, worldY, gamePanel.tileSize / 3, gamePanel.tileSize / 3);

        g2.setColor(Color.red);
        g2.drawRect(worldX + solidArea.x, worldY + solidArea.y, solidArea.width, solidArea.height);
    }

    public void restarBallOriginalPosition() {
        worldX = originX;
        worldY = originY;
        ballFirstSide();
        speedY = 0;
    }
}
