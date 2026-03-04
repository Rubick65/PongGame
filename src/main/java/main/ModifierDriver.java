package main;

import entity.modifiers.GoodModifiers.BallVelocityUp;
import entity.modifiers.GoodModifiers.PaddleDirectionChange;
import entity.modifiers.GoodModifiers.PaddleVelocityUp;
import entity.modifiers.Modifier;

import java.awt.*;
import java.util.Random;

public class ModifierDriver {

    // UNIQUE CLASS VARIABLES
    private final GamePanel gamePanel;
    private int modifierCounter = 0, startUpdateCounter = 0;
    private Modifier currentModifier;
    private boolean activeModifier = false;
    private int randomSleepTime;
    private final Random random = new Random();

    // CONSTANTS
    private final int TIME_BEFORE_UPDATE = 50;
    private final int MINI_TIME = 6, MAXI_TIME = 10;
    private final int SLEEP_TIME_MODIFIER = 100;


    public ModifierDriver(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.randomSleepTime = random.nextInt(MINI_TIME, MAXI_TIME) * SLEEP_TIME_MODIFIER;
    }

    public void updateModifier() {
        if (updateModifierCondition())
            currentModifier.update();
    }

    private boolean updateModifierCondition() {
        return activeModifier && (startUpdateCounter >= TIME_BEFORE_UPDATE);
    }

    public void draw(Graphics2D g2) {

        if (currentModifier == null || currentModifier.entityXWallCollision)
            selectRandomModifier();

        if (activeModifier) {
            startUpdateCounter++;
            currentModifier.draw(g2);
        }

        if (newModifierCondition()) {
            currentModifier = null;
            activeModifier = false;
            startUpdateCounter = 0;
        }
    }

    private boolean newModifierCondition() {
        return currentModifier != null && (currentModifier.entityPaddleCollision || currentModifier.entityXWallCollision);
    }

    private void selectRandomModifier() {
        modifierCounter++;

        if (modifierCounter == randomSleepTime) {
            int randomModifierIndex = random.nextInt(3);
            randomSleepTime = random.nextInt(MINI_TIME, MAXI_TIME) * SLEEP_TIME_MODIFIER;

            currentModifier = randomBeneficialModifierSelection(randomModifierIndex);

            activeModifier = true;
            modifierCounter = 0;
        }
    }

    private Modifier randomBeneficialModifierSelection(int randomModifierIndex) {
        Modifier selectedModifier;

        switch (randomModifierIndex) {
            case 0 -> selectedModifier = new BallVelocityUp(gamePanel);
            case 1 -> selectedModifier = new PaddleVelocityUp(gamePanel);
            case 2 -> selectedModifier = new PaddleDirectionChange(gamePanel);
            default -> selectedModifier = null;
        }

        return selectedModifier;
    }

    public void restartModifierPosition() {
        currentModifier = null;
        activeModifier = false;
    }
}
