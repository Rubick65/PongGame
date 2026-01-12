package paddles;

import keyHandler.KeyHandler;
import main.GamePanel;

import java.awt.*;

public class Paddle {

    // These variables indicate the actual game window or panel and the key's used in the game
    GamePanel gamePanel;
    KeyHandler keyHandler;

    // Position and speed of the entity
    public int worldX, worldY;
    private int originalX, originalY;
    public int playerCounter = 0;

    public int speed;
    boolean player;

    // Direction that the paddle is moving
    public String direction;

    // Indicates the area of the hitbox
    public Rectangle solidArea;

    // Detector de colisiones
    public boolean collision;


    public Paddle(GamePanel gamePanel, KeyHandler keyHandler, int worldX, boolean player) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.direction = "down";
        this.worldX = worldX;
        this.player = player;
        solidArea = new Rectangle(0, 0, gamePanel.tileSize / 3, gamePanel.tileSize * 2);
        setDefaultValues();
    }

    public void setDefaultValues() {
        int size = gamePanel.tileSize * 2;
        worldY = (gamePanel.screenHeight / 2) - size / 2;
        originalX = worldX;
        originalY = worldY;
        speed = 7;
    }

    public void update() {

        if (player)
            playerOneMovement();
        else
            playerTwoMovement();

    }

    private void playerOneMovement() {
        if (keyHandler.wPressed || keyHandler.sPressed) {
            if (keyHandler.wPressed) {
                direction = "up";
            } else if (keyHandler.sPressed) {
                direction = "down";
            }

            checkCollision();

        }

    }

    private void playerTwoMovement() {
        if (keyHandler.upPressed || keyHandler.downPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
            } else {
                direction = "down";
            }
            checkCollision();
        }

    }

    private void checkCollision() {
        collision = false;
        gamePanel.collisionChecker.checkPaddleCollision(this);

        if (!collision) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                default:
                    break;
            }

        }

    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(worldX, worldY, gamePanel.tileSize / 3, gamePanel.tileSize * 2);

        // We set the color of  rectangle that indicates the collision of our character
        g2.setColor(Color.red);
        // Then we drawPlayerCounters that rectangle to see it in the screen
        g2.drawRect(worldX + solidArea.x, worldY + solidArea.y, solidArea.width, solidArea.height);
    }

    public void restartPaddleOriginalPositions() {
        worldX = originalX;
        worldY = originalY;
    }

}
