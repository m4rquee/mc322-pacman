package com.ic.unicamp.br.mc322.pacman.game.gameobject;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;

import java.awt.*;

public class Rectangle extends Obstacle {

	private Point point;
	private int width;
	private int height;

	public Rectangle(Point point, int width, int height) {
		this.point = point;
		this.width = width;
		this.height = height;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void drawMe(Graphics g) {
		g.drawRect(point.getX(), point.getY(), width, height);
	}

	@Override
	public boolean collision(Character character) {
		int posX = character.getPos().getX();
		int posY = character.getPos().getY();
		return posX + character.getSize() > point.getX() && posX < point.getX() + width &&
				posY + character.getSize() > point.getY() && posY < point.getY() + height;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Rectangle retangulo = (Rectangle) o;
		return point.getX() == retangulo.point.getX() &&
				point.getY() == retangulo.point.getY() &&
				width == retangulo.width &&
				height == retangulo.height;
	}
}
