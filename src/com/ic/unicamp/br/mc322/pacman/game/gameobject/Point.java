package com.ic.unicamp.br.mc322.pacman.game.gameobject;

public class Point { // Immutable class
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int k) {
        this.x = k;
        this.y = k;
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Point plus(Point vector) {
        return new Point(this.x + vector.x, this.y + vector.y);
    }

    public Point plus(int k) {
        return new Point(this.x + k, this.y + k);
    }

    public Point times(int scalar) {
        return new Point(this.x * scalar, this.y * scalar);
    }
}
