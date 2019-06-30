package com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost;

import com.ic.unicamp.br.mc322.pacman.game.controller.ObstacleController;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;
import com.ic.unicamp.br.mc322.pacman.game.utilities.Direction;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import static com.ic.unicamp.br.mc322.pacman.game.controller.GameController.DOT_SIZE;

public class Ghost extends Character {

    private GhostType type;
    private Instant lastTimeChangedDirection;
    private Instant shouldChangeDirection;
    private Instant lastTimeSpawned;
    private boolean alreadyEaten;

    public Ghost(Point pos, GhostType type) {
        super(pos, new ImageIcon("resources/Ghost.png").getImage());
        switch (type) {
            case RANDOM:
                this.setImage(new ImageIcon("resources/OrangeGhost.png").getImage());
                break;
            case CHASER:
                this.setImage(new ImageIcon("resources/Ghost.png").getImage());
                break;
            case WIZARD:
                this.setImage(new ImageIcon("resources/BlueGhost.png").getImage());
                break;
            case EVASIVE:
                this.setImage(new ImageIcon("resources/PinkGhost.png").getImage());
                break;
        }

        this.type = type;
        this.setDirection(Direction.RIGHT);
        lastTimeChangedDirection = Instant.now();
        shouldChangeDirection = Instant.now().plus(1, ChronoUnit.SECONDS);
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
        if(Instant.now().compareTo(lastTimeSpawned.plus(1, ChronoUnit.SECONDS)) > 0)
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
        switch (this.getType()) {
            case CHASER:
                if (collidedDirection == null) {
                    if (pos.getY() < this.getPos().getY())
                        this.setDirection(Direction.UP);
                    else if (pos.getX() < this.getPos().getX())
                        this.setDirection(Direction.LEFT);
                    else if (pos.getY() > this.getPos().getY())
                        this.setDirection(Direction.DOWN);
                    else if (pos.getX() > this.getPos().getX())
                        this.setDirection(Direction.RIGHT);
                } else {
                    if (pos.getX() < this.getPos().getX() && !Direction.LEFT.equals(collidedDirection))
                        this.setDirection(Direction.LEFT);
                    else if (pos.getY() < this.getPos().getY() && !Direction.UP.equals(collidedDirection))
                        this.setDirection(Direction.UP);
                    else if (pos.getX() > this.getPos().getX() && !Direction.RIGHT.equals(collidedDirection))
                        this.setDirection(Direction.RIGHT);
                    else if (pos.getY() > this.getPos().getY() && !Direction.DOWN.equals(collidedDirection))
                        this.setDirection(Direction.DOWN);
                }
                break;
            case EVASIVE:
                if (collidedDirection == null) {
                    if (pos.getY() > this.getPos().getY())
                        this.setDirection(Direction.UP);
                    else if (pos.getX() > this.getPos().getX())
                        this.setDirection(Direction.LEFT);
                    else if (pos.getY() <= this.getPos().getY())
                        this.setDirection(Direction.DOWN);
                    else if (pos.getX() <= this.getPos().getX())
                        this.setDirection(Direction.RIGHT);
                } else {
                    if (pos.getX() > this.getPos().getX() && !Direction.LEFT.equals(collidedDirection))
                        this.setDirection(Direction.LEFT);
                    else if (pos.getY() > this.getPos().getY() && !Direction.UP.equals(collidedDirection))
                        this.setDirection(Direction.UP);
                    else if (pos.getX() <= this.getPos().getX() && !Direction.RIGHT.equals(collidedDirection))
                        this.setDirection(Direction.RIGHT);
                    else if (pos.getY() <= this.getPos().getY() && !Direction.DOWN.equals(collidedDirection))
                        this.setDirection(Direction.DOWN);
                }
                break;
            case RANDOM:
                if (lastTimeChangedDirection.compareTo(shouldChangeDirection) < 0) {
                    lastTimeChangedDirection = Instant.now();
                } else {
                    randomDirection();
                    lastTimeChangedDirection = Instant.now();
                    shouldChangeDirection = Instant.now().plus(500, ChronoUnit.MILLIS);
                }
                break;
            case WIZARD:
                this.setDirection(Direction.DOWN);
                // Wizards doesn't need to walk
                break;
        }
    }

    public void setNextPos(ObstacleController obstacleController) {
        if (lastTimeChangedDirection.compareTo(shouldChangeDirection) < 0) {
            lastTimeChangedDirection = Instant.now();
        } else {
            int x = new Random().nextInt(16);
            int y = new Random().nextInt(16);
            this.pos = new Point(x * 30 + 20, y * 30 + 20);

            while (obstacleController.collisionDetected(this)) {
                x = new Random().nextInt(16);
                y = new Random().nextInt(16);
                this.pos = new Point(x * 30 + 20, y * 30 + 20);
            }

            randomDirection();
            lastTimeChangedDirection = Instant.now();
            shouldChangeDirection = Instant.now().plus(2, ChronoUnit.SECONDS);
        }
    }

    private void randomDirection() {
        int dir = new Random().nextInt(3);
        switch (dir) {
            case 0:
                this.setDirection(Direction.RIGHT);
                break;
            case 1:
                this.setDirection(Direction.UP);
                break;
            case 2:
                this.setDirection(Direction.LEFT);
                break;
            case 3:
                this.setDirection(Direction.DOWN);
                break;

        }
    }
}
