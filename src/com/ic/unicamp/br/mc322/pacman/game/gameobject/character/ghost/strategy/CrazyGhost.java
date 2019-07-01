package com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost.strategy;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost.Ghost;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost.GhostType;
import com.ic.unicamp.br.mc322.pacman.game.utilities.Direction;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public abstract class CrazyGhost extends Ghost {

    protected Instant lastTimeChangedDirection;
    protected Instant shouldChangeDirection;

    public CrazyGhost(Point pos, GhostType type) {
        super(pos, type);
        lastTimeChangedDirection = Instant.now();
        shouldChangeDirection = Instant.now().plus(1, ChronoUnit.SECONDS);
    }

    protected void randomDirection() {
        int dir = new Random().nextInt(3);
        setDirection(Direction.values()[dir]);
    }
}
