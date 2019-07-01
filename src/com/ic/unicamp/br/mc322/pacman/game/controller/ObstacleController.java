package com.ic.unicamp.br.mc322.pacman.game.controller;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.obstacle.Collectable;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.obstacle.Obstacle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost.Ghost;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Pacman;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.obstacle.Wall;
import com.ic.unicamp.br.mc322.pacman.game.utilities.Tuple;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ObstacleController {

    private List<Wall> walls;
    private List<Collectable> collectables;

    ObstacleController() {
        this.walls = new LinkedList<>();
        this.collectables = new LinkedList<>();
    }

    private void removeObstacle(Wall wall) {
        this.walls.remove(wall);
    }

    private void removeObstacle(Collectable collectable) {
        this.collectables.remove(collectable);
    }

    void removeObstacles() {
        this.walls = new LinkedList<>();
        this.collectables = new LinkedList<>();
    }

    public boolean collisionDetected(Character character) {
        for (Wall at : this.walls)
            if (at.collision(character))
                return true;

        return false;
    }

    public Collectable collectPoint(Pacman pacman) {
        for (Collectable at : collectables)
            if (at.collision(pacman)) {
                removeObstacle(at);
                at.givePoints(pacman);
                return at;
            }
        return null;
    }

    void drawAllObstacles(Graphics g) {
        for (Obstacle at : this.walls) {
            at.drawMe(g);
        }

        for (Obstacle at : this.collectables) {
            at.drawMe(g);
        }
    }

    void add(Wall wall) {
        this.walls.add(wall);
    }

    void add(Collectable collectable) {
        this.collectables.add(collectable);
    }

    void add(Tuple<List<Wall>, List<Collectable>> obstacles) {
        this.walls.addAll(obstacles.getA());
        this.collectables.addAll(obstacles.getB());
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

    boolean collectedAllPoints() {
        return this.collectables.size() == 0;
    }
}
