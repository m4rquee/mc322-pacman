package com.ic.unicamp.br.mc322.pacman.game.gameobject;

import com.ic.unicamp.br.mc322.pacman.game.character.Character;

import java.awt.*;
import java.util.Objects;

public class Rectangle extends Obstacle {
    private int x;
    private int y;
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void drawMe(Graphics g) {
        g.drawRect(x, y, width, height);
    }

    @Override
    public boolean collision(Character character) {
        int posX = character.getPos().getX();
        int posY = character.getPos().getY();
        return posX + character.getSize() > x && posX < x + width && posY + character.getSize() > y && posY < y + height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle retangulo = (Rectangle) o;
        return x == retangulo.x &&
                y == retangulo.y &&
                width == retangulo.width &&
                height == retangulo.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height);
    }
}
