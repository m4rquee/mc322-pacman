package com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;
import com.ic.unicamp.br.mc322.pacman.game.utilities.Direction;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static com.ic.unicamp.br.mc322.pacman.game.controller.GameController.DOT_SIZE;

public class Ghost extends Character {

    private GhostType type;
    private Instant lastTimeSpawned;
    private boolean alreadyEaten;

    public Ghost(Point pos, GhostType type) {
        super(pos, new ImageIcon("resources/Ghost.png").getImage());
        this.type = type;
        this.setDirection(Direction.RIGHT);
        lastTimeSpawned = Instant.now();
        alreadyEaten = false;
    }

    public GhostType getType() {
        return type;
    }

    public void setAlreadyEaten(boolean alreadyEaten) {
        this.alreadyEaten = alreadyEaten;
    }

    public boolean wasAlreadyEaten() {
        return alreadyEaten;
    }

    public void respawn() {
        lastTimeSpawned = Instant.now();
        super.respawn();
    }

    public void move() {
        if (Instant.now().compareTo(lastTimeSpawned.plus(1, ChronoUnit.SECONDS)) > 0)
            super.move();
    }

    @Override
    public void drawMe(Graphics g) {
        g.drawImage(this.getImage(), this.getPos().getX(), this.getPos().getY(), super.getSize(), super.getSize(), (Image img, int infoflags, int x, int y, int width, int height) -> false);
    }

    public int getSize() {
        return super.getSize();
    }

    public Ghost withFuturePosition() {
        return new Ghost(this.getDirection().getDelta().times(DOT_SIZE).plus(pos), type);
    }

    public void setNextDirection(Point pos, Direction collidedDirection) {
        this.setDirection(Direction.DOWN);
    }
}
