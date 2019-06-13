package com.ic.unicamp.br.mc322.pacman.game.controller;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Circle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Rectangle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Obstacle;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ObstacleController {
    private List<Obstacle> obstacles;

    ObstacleController() {
        this.obstacles = new LinkedList<>();
    }

    public void removeObstacle(Obstacle obstacle) {
        obstacles.remove(obstacle);
    }

    boolean collisionDetected(Character character) {
        for (Obstacle at : obstacles) {
            if (at.collision(character))
                return false;
        }
        return true;
    }

    void drawAllObstacles(Graphics g) {
        for (Obstacle at : obstacles) {
            at.drawMe(g);
        }
    }

    void add(Obstacle obstacle) {
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
