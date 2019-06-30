package com.ic.unicamp.br.mc322.pacman.game.gameobject.character;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.GameObject;
import com.ic.unicamp.br.mc322.pacman.game.utilities.Direction;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;

import java.awt.*;
import java.util.Objects;

public abstract class Character extends GameObject {

    private Image image;
    private Point spwanPoint;
    private Direction direction;
    private static final int SIZE = 30;

    public Character(Point pos, Image imageIcon) {
        this.pos = pos;
        this.spwanPoint = pos;
        this.image = imageIcon;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public void respawn() {
        this.pos = spwanPoint;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSize() {
        return SIZE;
    }

    public abstract void drawMe(Graphics g);

    public void move() {
        Direction direction = this.getDirection();
        setNextPosition(direction);
    }

    void setNextPosition(Direction direction) {
        pos = pos.plus(direction.getDelta());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(pos, character.pos) &&
                Objects.equals(image, character.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, image);
    }
}
