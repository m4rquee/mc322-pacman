package com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost.strategy;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost.Ghost;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost.GhostType;
import com.ic.unicamp.br.mc322.pacman.game.utilities.Direction;

import javax.swing.*;

public class Chaser extends Ghost {

    public Chaser(Point pos) {
        super(pos, GhostType.CHASER);
        this.setImage(new ImageIcon("resources/Ghost.png").getImage());
    }

    @Override
    public void setNextDirection(Point pos, Direction collidedDirection) {
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
    }
}
