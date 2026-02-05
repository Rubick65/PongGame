package main;

import entity.modificador.BallVelocityUp;
import entity.modificador.Modifier;

import java.awt.*;
import java.util.Random;

public class ModifierDriver {

    // UNIQUE CLASS VARIABLES
    private GamePanel gamePanel;
    private Modifier[] goodModifiers = new Modifier[3];
    private int modifierCounter = 0;
    private Modifier currentModifier;
    private boolean selectedModifier = false;
    private boolean activeModifier = false;
    private int randomSleepTime;
    private Random random = new Random();

    // CONSTANTS
    private final int MINI_TIME = 3, MAXI_TIME = 7;
    private final int SLEEP_TIME_MODIFIER = 100;

    public ModifierDriver(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        addModifiers();
        this.randomSleepTime = random.nextInt(MINI_TIME, MAXI_TIME);
    }

    private void addModifiers() {
        try {
            goodModifiers[0] = new BallVelocityUp(gamePanel);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("No more modifiers can be added");
        }
    }

    public void draw(Graphics2D g2) {

        if (!selectedModifier)
            selectRandomModifier();

        if (activeModifier)
            currentModifier.draw(g2);

        if (currentModifier != null && currentModifier.ballPaddleCollision)
            selectedModifier = true;

    }

    private void selectRandomModifier() {
        modifierCounter++;
        if (modifierCounter == randomSleepTime * SLEEP_TIME_MODIFIER) {
            int randomModifierIndex = random.nextInt(goodModifiers.length);
            randomSleepTime = random.nextInt(MINI_TIME, MAXI_TIME);

            currentModifier = goodModifiers[randomModifierIndex];

            activeModifier = true;
            selectedModifier = true;
            modifierCounter = 0;
        }
    }
}
