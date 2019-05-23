package com.ic.unicamp.br.mc322.pacman.game.gameobject;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;
import com.ic.unicamp.br.mc322.pacman.game.utilities.Point;

import java.awt.*;

public class Rectangle extends Obstacle {

	private int width;
	private int height;

	public Rectangle(Point pos, int width, int height) {
		this.pos = pos;
		this.width = width;
		this.height = height;
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
		g.drawRect(pos.getX(), pos.getY(), width, height);
	}

	@Override
	public boolean collision(Character character) {
		int posX = character.getPos().getX();
		int posY = character.getPos().getY();
		return posX + character.getSize() > pos.getX() && posX < pos.getX() + width &&
				posY + character.getSize() > pos.getY() && posY < pos.getY() + height;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Rectangle retangulo = (Rectangle) o;
		return pos.getX() == retangulo.pos.getX() &&
				pos.getY() == retangulo.pos.getY() &&
				width == retangulo.width &&
				height == retangulo.height;
	}
}
