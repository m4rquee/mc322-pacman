package com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost.strategy;

import com.ic.unicamp.br.mc322.pacman.game.controller.ObstacleController;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost.GhostType;

import javax.swing.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Wizard extends CrazyGhost {

    public Wizard(Point pos) {
        super(pos, GhostType.WIZARD);
        this.setImage(new ImageIcon("resources/BlueGhost.png").getImage());
    }

    // Wizards doesn't need to walk

    public void setNextPos(ObstacleController obstacleController) {
        if (lastTimeChangedDirection.compareTo(shouldChangeDirection) < 0) {
            lastTimeChangedDirection = Instant.now();
        } else {
            int x = new java.util.Random().nextInt(16);
            int y = new java.util.Random().nextInt(16);
            this.pos = new Point(x * 30 + 20, y * 30 + 20);

            while (obstacleController.collisionDetected(this)) {
                x = new java.util.Random().nextInt(16);
                y = new Random().nextInt(16);
                this.pos = new Point(x * 30 + 20, y * 30 + 20);
            }

            randomDirection();
            lastTimeChangedDirection = Instant.now();
            shouldChangeDirection = Instant.now().plus(2, ChronoUnit.SECONDS);
        }
    }
}
