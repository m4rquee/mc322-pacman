package com.ic.unicamp.br.mc322.pacman.game.gameobject;

public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
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
	
	public void setX(int x) {
		this.x = x;	
	}
	
	public void setY(int y) {
		this.y = y;	
	}
	
	public void move(Point vector) {
		this.x += vector.x;
		this.y += vector.y;
	}
}
