package com.ic.unicamp.br.mc322.pacman.game.gameobject.character;

import com.ic.unicamp.br.mc322.pacman.game.controller.ObstacleController;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
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

    public Ghost(Point pos, GhostType type) {
        super(pos, new ImageIcon("resources/Ghost.png").getImage());
        this.type = type;
        this.setDirection(Direction.RIGHT);
        lastTimeChangedDirection = Instant.now();
        shouldChangeDirection = Instant.now().plus(2, ChronoUnit.SECONDS);
    }

    public GhostType getType() {
        return type;
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
