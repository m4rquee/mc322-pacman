package com.ic.unicamp.br.mc322.pacman.game.gameobject.obstacle;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;

import java.awt.*;

public class Circle extends Collectable {

    private int width;
    private int height;
    private static final int DEFAULT_SIZE = 15;
    private Color color;

    public Circle(Point pos, int pontuation) {
        super(pontuation);
        this.pos = pos;
        this.width = DEFAULT_SIZE;
        this.height = DEFAULT_SIZE;
        this.color = Color.YELLOW;
    }

    public Circle(Point pos, Color color, int size, int pontuation) {
        this(pos, pontuation);
        this.width = size;
        this.height = size;
        this.color = color;
    }

    @Override
    public void drawMe(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.pos.getX(), this.pos.getY(), width, height);
    }

    @Override
    public boolean collision(Character character) {
        int charX = character.getPos().getX();
        int charY = character.getPos().getY();

        if (!(charX + character.getSize() <= pos.getX() || charY + character.getSize() <= pos.getY() || charX >= pos.getX() + width
                || charY >= pos.getY() + height)) {
            this.color = Color.BLACK;
            return true;
        }
        return false;
    }
}
