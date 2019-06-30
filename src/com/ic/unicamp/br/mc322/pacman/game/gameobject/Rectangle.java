package com.ic.unicamp.br.mc322.pacman.game.gameobject;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;

import java.awt.*;

public class Rectangle extends Obstacle {

    private int width;
    private int height;
    public static final int DEFAULT_SIZE = 30;

    public Rectangle(Point pos, int width, int height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.shouldPontuate = false;
    }

    public Rectangle(Point pos) {
        this.pos = pos;
        this.width = DEFAULT_SIZE;
        this.height = DEFAULT_SIZE;
    }

    @Override
    public void drawMe(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect(pos.getX(), pos.getY(), width, height);
    }

    @Override
    public boolean collision(Character character) {
        int charX = character.getPos().getX();
        int charY = character.getPos().getY();

        return !(charX + character.getSize() <= pos.getX() || charY + character.getSize() <= pos.getY() || charX >= pos.getX() + width
                || charY >= pos.getY() + height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Rectangle retangulo = (Rectangle) o;
        return pos.getX() == retangulo.pos.getX() && pos.getY() == retangulo.pos.getY() && width == retangulo.width && height == retangulo.height;
    }
}