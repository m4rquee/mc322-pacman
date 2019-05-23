package com.ic.unicamp.br.mc322.pacman.game.gameobject;

import com.ic.unicamp.br.mc322.pacman.game.utilities.Point;

import java.awt.*;

public abstract class GameObject {
	protected Point pos;

	public abstract void drawMe(Graphics g);

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}
}
