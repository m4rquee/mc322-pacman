package com.ic.unicamp.br.mc322.pacman.game.gameobject.character;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.utilities.Direction;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import static com.ic.unicamp.br.mc322.pacman.game.controller.GameController.DOT_SIZE;

public class Pacman extends Character {

    private int life;
    private int points;
    private boolean hasPowerUp;
    private Map<Direction, Image> images;
    private static final int SIZE = 30;
    private static final int INITIAL_X = 20;
    private static final int INITIAL_Y = 20;
    private Instant shouldFinishPowerUp;

    public Pacman() {
        super(new Point(INITIAL_X, INITIAL_Y), new ImageIcon("resources/pacmanWithAMouth.png").getImage());
        initImages();
        points = 0;
        life = 3;
        hasPowerUp = false;
        setDirection(Direction.RIGHT);
        shouldFinishPowerUp = Instant.now().minus(1, ChronoUnit.SECONDS);
    }

    private Pacman(Point pos) {
        super(pos, null);
    }

    public boolean hasPowerUp() {
        return hasPowerUp;
    }

    public void setPowerUp(boolean bool) {
        if(bool) {
            shouldFinishPowerUp = Instant.now().plus(5, ChronoUnit.SECONDS);
        }
        this.hasPowerUp = bool;
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
        g.drawString(hasPowerUp ? "POWER UP!" : "", 200, 18);
        g.drawImage(this.getImage(), this.getPos().getX(), this.getPos().getY(), super.getSize(), super.getSize(), (Image img, int infoflags, int x, int y, int width, int height) -> false);
    }

    public int getSize() {
        return SIZE;
    }

    public void move() {
        if(Instant.now().compareTo(this.shouldFinishPowerUp) > 0) {
            this.hasPowerUp = false;
        }
        Direction direction = this.getDirection();
        this.setImage(this.images.get(direction));

        super.setNextPosition(direction);
    }

    public void takeHit() {
        this.life -= 1;
        this.hasPowerUp = false;
        this.respawn();
    }

    public void respawn() {
        super.respawn();
        this.setDirection(Direction.RIGHT);
    }

    public Pacman withFuturePosition() {
        return new Pacman(this.getDirection().getDelta().times(DOT_SIZE).plus(pos));
    }

    public void addLife() {
        this.life++;
    }
}
