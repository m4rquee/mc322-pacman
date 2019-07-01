package com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost.strategy;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost.GhostType;
import com.ic.unicamp.br.mc322.pacman.game.utilities.Direction;

import javax.swing.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Random extends CrazyGhost {

    public Random(Point pos) {
        super(pos, GhostType.RANDOM);
        this.setImage(new ImageIcon("resources/OrangeGhost.png").getImage());
    }

    @Override
    public void setNextDirection(Point pos, Direction collidedDirection) {
        if (lastTimeChangedDirection.compareTo(shouldChangeDirection) < 0) {
            lastTimeChangedDirection = Instant.now();
        } else {
            randomDirection();
            lastTimeChangedDirection = Instant.now();
            shouldChangeDirection = Instant.now().plus(500, ChronoUnit.MILLIS);
        }
    }
}
