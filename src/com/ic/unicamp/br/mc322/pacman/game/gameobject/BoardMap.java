package com.ic.unicamp.br.mc322.pacman.game.gameobject;

import com.ic.unicamp.br.mc322.pacman.game.utilities.Point;

import java.util.*;

public class BoardMap {

	private Point spawnPoint;
	private Point ghostsPoint;
	private Obstacle[][] obstacles;

	public BoardMap(int w, int h, Point spawnPoint, Point ghostsPoint) {
		this.spawnPoint = spawnPoint;
		this.ghostsPoint = ghostsPoint;
		this.obstacles = new Obstacle[w][h];
	}

	public boolean empty(Point pos) {
		return this.obstacles[pos.getX()][pos.getY()] == null;
	}

	public void addObstacle(Obstacle obstacle) {

	}
}
