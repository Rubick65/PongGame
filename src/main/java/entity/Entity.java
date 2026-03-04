package entity;

import main.GamePanel;

import java.awt.*;

public abstract class Entity {

    public GamePanel gamePanel;

    public Color color;
    public int worldX, worldY;
    public int speedX, speedY;
    public Rectangle solidArea;
    public boolean entityYWallCollision, entityXWallCollision;
    public boolean entityPaddleCollision;

    public Entity(GamePanel gamePanel, Color color) {
        this.gamePanel = gamePanel;
        this.color = color;
        solidArea = new Rectangle(0, 0, gamePanel.tileSize / 3, gamePanel.tileSize / 3);
    }

    protected void checkBallCollision() {
        entityYWallCollision = false;
        entityXWallCollision = false;
        entityPaddleCollision = false;

        gamePanel.collisionChecker.checkBallCollision(this);

        collisionReaction();
        reactToWallCollision();
    }

    protected void reactToWallCollision() {
        if (entityYWallCollision)
            speedY = -speedY;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillOval(worldX, worldY, gamePanel.tileSize / 3, gamePanel.tileSize / 3);

        g2.setColor(Color.red);
        g2.drawRect(worldX + solidArea.x, worldY + solidArea.y, solidArea.width, solidArea.height);
    }

    public void update() {
        movement();
        checkBallCollision();
    }

    private void movement() {
        worldX -= speedX;
        worldY -= speedY;
    }

    protected abstract void collisionReaction();
}
