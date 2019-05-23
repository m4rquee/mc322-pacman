package com.ic.unicamp.br.mc322.pacman.game.utilities;

public enum Direction {

	LEFT(new Point(-1, 0)), RIGHT(new Point(1, 0)),
	UP(new Point(0, -1)), DOWN(new Point(0, 1));

	private final Point delta;

	Direction(Point delta) {
		this.delta = delta;
	}

	public Point deltaVector() {
		return this.delta;
	}
}
