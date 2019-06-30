package com.ic.unicamp.br.mc322.pacman.game.utilities;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;

public enum Direction {
    LEFT(-1, 0), RIGHT(1, 0), UP(0, -1), DOWN(0, 1);

    private final Point delta;

    Direction(int dx, int dy) {
        delta = new Point(dx, dy);
    }

    public Point getDelta() {
        return delta;
    }
}
