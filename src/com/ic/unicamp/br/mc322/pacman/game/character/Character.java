package com.ic.unicamp.br.mc322.pacman.game.character;

import com.ic.unicamp.br.mc322.pacman.game.utilities.Direction;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;

import java.awt.*;
import java.util.Objects;

public abstract class Character {
    Point pos = new Point(0,0);
    private Image image;
    private Direction direction;
    private int size;

    public Character() {
    }

    public Character(Point pos, Image imageIcon) {
        this.pos = pos;
        this.image = imageIcon;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
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
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public abstract void drawMe(Graphics g);

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
