package com.ic.unicamp.br.mc322.pacman.game.gameobject.character;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;

import javax.swing.*;
import java.awt.*;

import static com.ic.unicamp.br.mc322.pacman.game.controller.GameController.DOT_SIZE;

public class Ghost extends Character {

    private int life = 1;
    private static final Point DEFAULT_START_POINT = new Point(530, 530);
    private GhostType type;

    public Ghost(GhostType type) {
        super(DEFAULT_START_POINT, new ImageIcon("resources/Ghost.png").getImage());
        this.type = type;
    }

    private Ghost(Point pos, GhostType type) {
        super(pos, new ImageIcon("resources/Ghost.png").getImage());
        this.type = type;
    }

    public int getLife() {
        return this.life;
    }

    public GhostType getType() {
        return type;
    }

    @Override
    public void drawMe(Graphics g) {
        g.setColor(Color.white);
        g.drawString("Vidas: " + getLife(), 100, 18);
        g.drawImage(this.getImage(), this.getPos().getX(), this.getPos().getY(), super.getSize(), super.getSize(), (Image img, int infoflags, int x, int y, int width, int height) -> false);
    }

    public int getSize() {
        return super.getSize();
    }

    public void takeHit(int damage) {
        this.life -= 1;
    }

    public Ghost withFuturePosition() {
        Point futurePosition;
        switch (this.getDirection()) {
            case LEFT:
                futurePosition = new Point(this.pos.getX() - DOT_SIZE, this.getPos()
                        .getY());
                return new Ghost(futurePosition, getType());
            case RIGHT:
                futurePosition = new Point(this.pos.getX() + DOT_SIZE, this.getPos()
                        .getY());
                return new Ghost(futurePosition, getType());
            case UP:
                futurePosition = new Point(this.pos.getX(), this.getPos()
                        .getY() - DOT_SIZE);
                return new Ghost(futurePosition, getType());
            case DOWN:
                futurePosition = new Point(this.pos.getX(), this.getPos()
                        .getY() + DOT_SIZE);
                return new Ghost(futurePosition, getType());
            default:
                return null;
        }
    }
}
