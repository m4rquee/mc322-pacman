package com.ic.unicamp.br.mc322.pacman.game.gameobject;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;

import java.awt.*;

public class Circle extends Obstacle {

    private int width;
    private int height;
    private static final int DEFAULT_SIZE = 10;

    public Circle(Point pos, int width, int height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    public Circle(Point pos) {
        this.pos = pos;
        this.width = DEFAULT_SIZE;
        this.height = DEFAULT_SIZE;
    }

    @Override
    public void drawMe(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(this.pos.getX(), this.pos.getY(), width, height);
    }

    @Override
    public boolean collision(Character character) {
        int charX = character.getPos().getX();
        int charY = character.getPos().getY();

        return !(charX + character.getSize() <= pos.getX() || charY + character.getSize() <= pos.getY() || charX >= pos.getX() + width
                || charY >= pos.getY() + height);
    }
}
