package com.ic.unicamp.br.mc322.pacman.game.gameobject.character;

import com.ic.unicamp.br.mc322.pacman.game.controller.GameController;
import com.ic.unicamp.br.mc322.pacman.game.utilities.Point;

import javax.swing.*;
import java.awt.*;

import static com.ic.unicamp.br.mc322.pacman.game.controller.GameController.DOT_SIZE;

public class Pacman extends Character {

	private int life;
	private static final int SIZE = 10;
	private static final int MAX_LIFE = 100;
//	private Direction direction;
//	private bool isOnPowerUp;

	public Pacman() {
		super(new Point(0, 0), new ImageIcon("resources/greenPacman.png").getImage());
		this.life = MAX_LIFE;
	}

	public Pacman(int life, Image image, Point pos) {
		super(pos, image);
		this.life = life;
	}

	public Pacman(int l, Point p, char up, char down, char left, char right) {
		this.life = l;
		this.pos = p;
//		this.isOnPowerUp = false;
//		this.symbols = new char[4] {up, down, left, right};
	}

	@Override
	public void drawMe(Graphics g) {
		g.drawImage(this.getImage(), this.getPos().getX(), this.getPos().getY(), new GameController());
	}


	public int getSize() {
		return SIZE;
	}

	public void move() {
		this.pos.move(this.getDirection().deltaVector());
	}

	public void moveTo(Point pos) {
		this.pos = pos;
	}

	public void takeHit(int damage) {
		this.life -= damage;
	}

	public void resetLife() {
		this.life = MAX_LIFE;
	}

	public Pacman withFuturePosition() {
		Point futurePosition = this.pos.translate(this.getDirection().deltaVector().times(DOT_SIZE));
		return new Pacman(this.life, this.getImage(), futurePosition);
	}
}
