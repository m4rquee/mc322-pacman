package com.ic.unicamp.br.mc322.pacman.game.controller;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.obstacle.Obstacle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost.Ghost;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Pacman;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ObstacleController {

    private List<Obstacle> obstacles;
    private Obstacle pontuate;

    ObstacleController() {
        this.obstacles = new LinkedList<>();
    }

    void setPontuate(Obstacle pontuate) {
        this.pontuate = pontuate;
    }

    Obstacle getPontuate() {
        return this.pontuate;
    }

    private void removeObstacle(Obstacle obstacle) {
        obstacles.remove(obstacle);
    }

    void removeObstacles() {
        obstacles = new LinkedList<>();
    }

    public boolean collisionDetected(Character character) {
        for (Obstacle at : obstacles) {
            if (at.collision(character)) {
                if (at.shouldPontuate) {
                    setPontuate(at);
                    removeObstacle(at);
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

    Ghost hitGhost(ArrayList<Ghost> ghosts, Pacman pacman) {
        boolean ret;
        for (Ghost ghost : ghosts) {
            int charX = ghost.getPos().getX();
            int charY = ghost.getPos().getY();
            ret = !(charX + (ghost.getSize() - 10) <= pacman.getPos().getX() || charY + (ghost.getSize() - 10) <= pacman.getPos().getY()
                    || charX >= pacman.getPos().getX() + (pacman.getSize() - 10) || charY >= pacman.getPos().getY() + (pacman.getSize() - 10));

            if (ret)
                return ghost;
        }
        return null;
    }

    boolean endedLevel() {
        int pointsInTheMap = 0;
        for(Obstacle obstacle : obstacles) {
            pointsInTheMap += obstacle.getPontuation();
        }
        return pointsInTheMap == 0;
    }
}
