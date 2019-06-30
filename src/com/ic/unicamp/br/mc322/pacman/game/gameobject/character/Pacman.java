package com.ic.unicamp.br.mc322.pacman.game.gameobject.character;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.utilities.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.ic.unicamp.br.mc322.pacman.game.controller.GameController.DOT_SIZE;

public class Pacman extends Character {

    private int life;
    private int points;
    private Map<Direction, Image> images;
    private static final int SIZE = 30;
    public static final Point DEFAULT_START_POINT = new Point(20, 20);

    public Pacman() {
        super(DEFAULT_START_POINT, new ImageIcon("resources/pacmanWithAMouth.png").getImage());
        initImages();
        points = 0;
        life = 5;
        setDirection(Direction.RIGHT);
    }

    public Pacman(Image image, Point pos) {
        super(pos, image);
    }

    public void pontuate(int points) {
        this.points += points;
    }

    public int getPoints() {
        return points;
    }

    public int getLife() {
        return this.life;
    }

    private void initImages() {
        images = new HashMap<>();
        images.put(Direction.DOWN, new ImageIcon("resources/pacmanWithAMouthDown.png").getImage());
        images.put(Direction.UP, new ImageIcon("resources/pacmanWithAMouthUp.png").getImage());
        images.put(Direction.LEFT, new ImageIcon("resources/pacmanWithAMouthLeft.png").getImage());
        images.put(Direction.RIGHT, new ImageIcon("resources/pacmanWithAMouth.png").getImage());
    }

    @Override
    public void drawMe(Graphics g) {
        g.setColor(Color.white);
        g.drawString("Vidas: " + getLife(), 100, 18);
        g.drawImage(this.getImage(), this.getPos().getX(), this.getPos().getY(), super.getSize(), super.getSize(), (Image img, int infoflags, int x, int y, int width, int height) -> false);
    }

    public int getSize() {
        return SIZE;
    }

    public void move() {
        Direction direction = this.getDirection();
        this.setImage(this.images.get(direction));

        super.setNextPosition(direction);
    }

    public void takeHit() {
        this.life -= 1;
        this.setPos(DEFAULT_START_POINT);
    }

    public Pacman withFuturePosition() {
        Point futurePosition;
        switch (this.getDirection()) {
            case LEFT:
                futurePosition = new Point(this.pos.getX() - DOT_SIZE, this.getPos()
                        .getY());
                return new Pacman(this.getImage(), futurePosition);
            case RIGHT:
                futurePosition = new Point(this.pos.getX() + DOT_SIZE, this.getPos()
                        .getY());
                return new Pacman(this.getImage(), futurePosition);
            case UP:
                futurePosition = new Point(this.pos.getX(), this.getPos()
                        .getY() - DOT_SIZE);
                return new Pacman(this.getImage(), futurePosition);
            case DOWN:
                futurePosition = new Point(this.pos.getX(), this.getPos()
                        .getY() + DOT_SIZE);
                return new Pacman(this.getImage(), futurePosition);
            default:
                return null;
        }
    }

    public void setLife(int life) {
        this.life = life;
    }
}
