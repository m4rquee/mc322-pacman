package com.ic.unicamp.br.mc322.pacman.game.gameobject.obstacle;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;

import java.awt.*;

public class Line extends Wall {

    private int width;
    private int height;

    public Line(com.ic.unicamp.br.mc322.pacman.game.gameobject.Point pos, int width, int height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    public Line(com.ic.unicamp.br.mc322.pacman.game.gameobject.Point x1, Point x2) {
        this.pos = x1;
        this.width = x2.getX();
        this.height = x2.getY();
    }

    @Override
    public void drawMe(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawLine(pos.getX(), pos.getY(), width, height);
    }

    @Override
    public boolean collision(Character character) {
        int charY = character.getPos().getY();

        return !(charY + character.getSize() <= pos.getY() || charY >= pos.getY());
    }
}