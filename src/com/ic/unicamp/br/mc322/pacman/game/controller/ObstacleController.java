package com.ic.unicamp.br.mc322.pacman.game.controller;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Obstacle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;

import java.awt.*;
import java.util.Collection;
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
        obstacles.removeAll(new LinkedList<Obstacle>());
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
}
