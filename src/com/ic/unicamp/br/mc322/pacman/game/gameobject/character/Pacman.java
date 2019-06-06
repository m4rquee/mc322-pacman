package com.ic.unicamp.br.mc322.pacman.game.gameobject.character;

import com.ic.unicamp.br.mc322.pacman.game.controller.GameController;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import static com.ic.unicamp.br.mc322.pacman.game.controller.GameController.DOT_SIZE;

public class Pacman extends Character {

    private int life;
    private static final int SIZE = 20;
    private static final int MAX_LIFE = 100;

    public Pacman() {
        super(new Point(20, 20), new ImageIcon("resources/pacmanWithAMouth.png").getImage());
        this.life = MAX_LIFE;
    }

    public Pacman(int life, Image image, Point pos) {
        super(pos, image);
        this.life = life;
    }

    @Override
    public void drawMe(Graphics g) {
        g.drawImage(this.getImage(), this.getPos().getX(), this.getPos().getY(), 20, 20, new GameController());
//        g.drawImage(this.getImage(), this.withFuturePosition().getPos().getX(), this.withFuturePosition().getPos().getY(), 20, 20, new GameController());
    }

    public int getSize() {
        return SIZE;
    }

    public void move() {
        switch (this.getDirection()) {
        case LEFT:
            this.setPos(new Point(this.getPos()
                                      .getX() - 1, this.getPos()
                                                       .getY()));
            break;
        case RIGHT:
            this.setPos(new Point(this.getPos()
                                      .getX() + 1, this.getPos()
                                                       .getY()));
            break;
        case UP:
            this.setPos(new Point(this.getPos()
                                      .getX(), this.getPos()
                                                   .getY() - 1));
            break;
        case DOWN:
            this.setPos(new Point(this.getPos()
                                      .getX(), this.getPos()
                                                   .getY() + 1));
            break;
        }
    }

    public void takeHit(int damage) {
        this.life -= damage;
    }

    public Pacman withFuturePosition() {
        Point futurePosition;
        switch (this.getDirection()) {
        case LEFT:
            futurePosition = new Point(this.pos.getX() - DOT_SIZE, this.getPos()
                                                                       .getY());
            return new Pacman(this.life, this.getImage(), futurePosition);
        case RIGHT:
            futurePosition = new Point(this.pos.getX() + DOT_SIZE, this.getPos()
                                                                       .getY());
            return new Pacman(this.life, this.getImage(), futurePosition);
        case UP:
            futurePosition = new Point(this.pos.getX(), this.getPos()
                                                            .getY() - DOT_SIZE);
            return new Pacman(this.life, this.getImage(), futurePosition);
        case DOWN:
            futurePosition = new Point(this.pos.getX(), this.getPos()
                                                            .getY() + DOT_SIZE);
            return new Pacman(this.life, this.getImage(), futurePosition);
        default:
            return null;
        }
    }
}
