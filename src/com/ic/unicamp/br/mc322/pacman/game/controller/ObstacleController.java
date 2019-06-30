package com.ic.unicamp.br.mc322.pacman.game.controller;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Obstacle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Ghost;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Pacman;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ObstacleController {
    private List<Obstacle> obstacles;

    ObstacleController() {
        this.obstacles = new LinkedList<>();
    }

    private boolean pontuate;

    public void setPontuate(boolean pontuate) {
        this.pontuate = pontuate;
    }

    public boolean getPontuate() {
        return this.pontuate;
    }

    public void removeObstacle(Obstacle obstacle) {
        obstacles.remove(obstacle);
    }

    public void removeObstacles() {
        obstacles = new LinkedList<>();
    }

    boolean collisionDetected(Character character) {
        for (Obstacle at : obstacles) {
            if (at.collision(character)) {
                if (at.shouldPontuate) {
                    removeObstacle(at);
                    pontuate = true;
                }
                return true;
            }
        }
        return false;
    }

    void drawAllObstacles(Graphics g) {
        for (Obstacle at : obstacles) {
            at.drawMe(g);
        }
    }

    void add(Obstacle obstacle) {
        this.obstacles.add(obstacle);
    }

    void add(List<Obstacle> obstacles) {
        this.obstacles.addAll(obstacles);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObstacleController that = (ObstacleController) o;
        return Objects.equals(obstacles, that.obstacles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(obstacles);
    }

    boolean shouldTakeHit(ArrayList<Ghost> ghosts, Pacman pacman) {
        boolean ret;
        for (Ghost ghost : ghosts) {
            int charX = ghost.getPos().getX();
            int charY = ghost.getPos().getY();
            ret = !(charX + (ghost.getSize()-10) <= pacman.getPos().getX() || charY + (ghost.getSize()-10) <= pacman.getPos().getY()
                    || charX >= pacman.getPos().getX() + (pacman.getSize()-10) || charY >= pacman.getPos().getY() + (pacman.getSize()-10));
            if(ret) {
                return true;
            }
        }
        return false;
    }
}
