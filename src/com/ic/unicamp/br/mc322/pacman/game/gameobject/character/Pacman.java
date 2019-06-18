package com.ic.unicamp.br.mc322.pacman.game.gameobject.character;

import com.ic.unicamp.br.mc322.pacman.game.controller.GameController;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.utilities.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import static com.ic.unicamp.br.mc322.pacman.game.controller.GameController.DOT_SIZE;

public class Pacman extends Character {

    private int life;
    private static final int SIZE = 30;
    private static final int MAX_LIFE = 100;
    private Map<Direction, Image> images;
    private static final Point DEFAULT_START_POINT = new Point(18, 20);
    private int points = 0;

    public Pacman() {
        super(DEFAULT_START_POINT, new ImageIcon("resources/pacmanWithAMouth.png").getImage());
        this.life = MAX_LIFE;
        initImages();
    }

    public Pacman(int life, Image image, Point pos) {
        super(pos, image);
        this.life = life;
        initImages();
    }

    public void pontuate(int points) {
        this.points += points;
    }

    public int getPoints() {
        return points;
    }

    private void initImages() {
        images = new HashMap<>();
        images.put(Direction.DOWN, new ImageIcon("resources/pacmanWithAMouthDown.png").getImage());
        images.put(Direction.UP,new ImageIcon("resources/pacmanWithAMouthUp.png").getImage());
        images.put(Direction.LEFT,new ImageIcon("resources/pacmanWithAMouthLeft.png").getImage());
        images.put(Direction.RIGHT,new ImageIcon("resources/pacmanWithAMouth.png").getImage());
    }

    @Override
    public void drawMe(Graphics g) {
        g.drawImage(this.getImage(), this.getPos().getX(), this.getPos().getY(), SIZE, SIZE, new GameController());
    }

    public int getSize() {
        return SIZE;
    }

    public void move() {
        Direction direction = this.getDirection();
        this.setImage(this.images.get(direction));

        switch (direction) {
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
