package main;

import ball.Ball;
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
        wallObstacles.add(new Rectangle(0, gamePanel.screenHeight + 10, gamePanel.tileSize * gamePanel.maxScreenCol, 10));
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

    public void checkBallCollision(Ball ball) {
        checkBallWallCollision(ball);
        checkBallPaddleCollision(ball);

    }

    private void checkBallWallCollision(Ball ball) {
        Rectangle ballBox = createBallBox(ball);

        // Then we check in the list of obstacles
        for (Rectangle obstacle : wallObstacles) {
            // If the obstacle and the future box intersect
            if (ballBox.intersects(obstacle)) {
                // The collision is active
                ball.ballWallCollision = true;
                return;
            }
        }
        ball.ballWallCollision = false;
    }

    public void checkBallPaddleCollision(Ball ball) {
        Rectangle ballBox = createBallBox(ball);

        for (Paddle paddle : paddles) {
            Rectangle paddleBox = createPaddleBox(paddle);
            if (ballBox.intersects(paddleBox)) {

                ball.ballPaddleCollision = true;
                calculateMiddlePoint(ball, paddle);
                return;
            }
        }
        ball.ballPaddleCollision = false;

    }

    private Rectangle createBallBox(Ball ball) {

        return new Rectangle(
                ball.worldX + ball.solidArea.x - ball.speedX,
                ball.worldY + ball.solidArea.y - ball.speedY,
                ball.solidArea.width,
                ball.solidArea.height);
    }

    private Rectangle createPaddleBox(Paddle paddle) {
        return new Rectangle(
                paddle.worldX + paddle.solidArea.x,
                paddle.worldY + paddle.solidArea.y,
                paddle.solidArea.width,
                paddle.solidArea.height);
    }

    private void calculateMiddlePoint(Ball ball, Paddle paddle) {
        hitPos = (ball.worldY + ball.solidArea.height / 2) - (paddle.worldY + paddle.solidArea.height / 2);
//        System.out.println(hitPos);
    }

    public void draw(Graphics2D g2, Ball ball) {
        g2.setColor(Color.BLUE);
        g2.drawRect(
                ball.worldX + ball.solidArea.x - ball.speedX,
                ball.worldY + ball.solidArea.y - ball.speedY,
                ball.solidArea.width,
                ball.solidArea.height
        );

    }

}
