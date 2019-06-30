package com.ic.unicamp.br.mc322.pacman.game.gameobject;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Pacman;

import java.awt.*;

public class Circle extends Obstacle {

    private int width;
    private int height;
    private static final int DEFAULT_SIZE = 15;
    private Color color = Color.YELLOW;

    public Circle(Point pos, int width, int height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.shouldPontuate = true;
    }

    public Circle(Point pos) {
        this.pos = pos;
        this.width = DEFAULT_SIZE;
        this.height = DEFAULT_SIZE;
        this.shouldPontuate = true;
    }

    @Override
    public void drawMe(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.pos.getX(), this.pos.getY(), width, height);
    }

    @Override
    public boolean collision(Character character) {
        if(!(character instanceof Pacman)) {
            return false;
        }
        int charX = character.getPos().getX();
        int charY = character.getPos().getY();

        if(!(charX + character.getSize() <= pos.getX() || charY + character.getSize() <= pos.getY() || charX >= pos.getX() + width
                || charY >= pos.getY() + height)) {
            this.color = Color.BLACK;
            return true;
        }
        return false;
    }
}
