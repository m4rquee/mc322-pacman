package com.ic.unicamp.br.mc322.pacman.game.controller;

import com.ic.unicamp.br.mc322.pacman.game.character.Character;
import com.ic.unicamp.br.mc322.pacman.game.gameObject.Obstacle;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ObstacleController {
    private List<Obstacle> obstacles;

    public ObstacleController() {
        this.obstacles = new LinkedList<>();
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public boolean collisionDetected(Character character) {
        for (Obstacle at : obstacles) {
            if (at.collision(character)) {
                return true;
            }
        }
        return false;
    }

    public void drawAllObstacles(Graphics g) {
        for (Obstacle at : obstacles) {
            at.drawMe(g);
        }
    }

    public void add (Obstacle obstacle) {
        this.obstacles.add(obstacle);
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
