package main;

import entity.Entity;
import paddles.Paddle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CollisionChecker {

    List<Rectangle> wallObstacles = new ArrayList<>();
    Paddle[] paddles;
    public int hitPos;
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel, Paddle[] paddles) {
        this.gamePanel = gamePanel;
        this.paddles = paddles;
        calculateWallObstacles();
    }

    public void calculateWallObstacles() {
        // Top wall and bottom wall
        wallObstacles.add(new Rectangle(0, -10, gamePanel.tileSize * gamePanel.maxScreenCol, 10));
        wallObstacles.add(new Rectangle(0, gamePanel.screenHeight, gamePanel.tileSize * gamePanel.maxScreenCol, 10));
    }

    /**
     * Checks for paddle collision
     *
     * @param paddle Paddle that is going to be checked for collision
     */
    public void checkPaddleCollision(Paddle paddle) {

        // Creates a box in the position of the paddle hitbox
        Rectangle futureBox = new Rectangle(
                paddle.worldX + paddle.solidArea.x,
                paddle.worldY + paddle.solidArea.y,
                paddle.solidArea.width,
                paddle.solidArea.height
        );

        calculateFutureBox(paddle.direction, paddle.speed, futureBox);

        // Then we check in the list of obstacles
        for (Rectangle obstacle : wallObstacles) {
            // If the obstacle and the future box intersect
            if (futureBox.intersects(obstacle)) {
                // The collision is active
                paddle.collision = true;
                return;
            }
        }
        paddle.collision = false;
    }

    private void calculateFutureBox(String direction, int speed, Rectangle futureBox) {
        // In function of the direction we calculate the future box
        switch (direction) {
            case "up":
                futureBox.y -= speed;
                break;
            case "down":
                futureBox.y += speed;
                break;
        }
    }

    public void checkBallCollision(Entity entity) {
        checkBallYWallCollision(entity);
        checkBallXWallCollision(entity);
        checkBallPaddleCollision(entity);
    }

    private void checkBallYWallCollision(Entity entity) {
        Rectangle ballBox = createBallBox(entity);

        // Then we check in the list of obstacles
        for (Rectangle obstacle : wallObstacles) {
            // If the obstacle and the future box intersect
            if (ballBox.intersects(obstacle)) {
                // The collision is active
                entity.entityYWallCollision = true;
                return;
            }
        }
        entity.entityYWallCollision = false;
    }

    private void checkBallXWallCollision(Entity entity) {
        if (entity.worldX < 0 || entity.worldX > gamePanel.screenWidth)
            entity.entityXWallCollision = true;
    }

    public void checkBallPaddleCollision(Entity entity) {
        Rectangle ballBox = createBallBox(entity);

        for (Paddle paddle : paddles) {
            Rectangle paddleBox = createPaddleBox(paddle);
            if (ballBox.intersects(paddleBox)) {
                entity.entityPaddleCollision = true;
                calculateHitPoint(entity, paddle);
                return;
            }
        }
        entity.entityPaddleCollision = false;

    }

    private Rectangle createBallBox(Entity entity) {

        return new Rectangle(
                entity.worldX + entity.solidArea.x - entity.speedX,
                entity.worldY + entity.solidArea.y - entity.speedY,
                entity.solidArea.width,
                entity.solidArea.height);
    }

    private Rectangle createPaddleBox(Paddle paddle) {
        return new Rectangle(
                paddle.worldX + paddle.solidArea.x,
                paddle.worldY + paddle.solidArea.y,
                paddle.solidArea.width,
                paddle.solidArea.height);
    }

    private void calculateHitPoint(Entity entity, Paddle paddle) {
        hitPos = (entity.worldY + entity.solidArea.height / 2) - (paddle.worldY + paddle.solidArea.height / 2);
    }

    public void draw(Graphics2D g2, Entity entity) {
        g2.setColor(Color.BLUE);
        g2.drawRect(
                entity.worldX + entity.solidArea.x - entity.speedX,
                entity.worldY + entity.solidArea.y - entity.speedY,
                entity.solidArea.width,
                entity.solidArea.height
        );
    }

}
