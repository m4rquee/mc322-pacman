package com.ic.unicamp.br.mc322.pacman.game.gameobject.character;

import com.ic.unicamp.br.mc322.pacman.game.controller.GameController;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;

import javax.swing.*;
import java.awt.*;

import static com.ic.unicamp.br.mc322.pacman.game.controller.GameController.DOT_SIZE;

public class Pacman extends Character{

	private int life;
	private static final int SIZE = 10;
	private static final int MAX_LIFE = 100;
//	private Direction direction;
//	private bool isOnPowerUp;

	public Pacman() {
		super(new Point(0, 0),  new ImageIcon("src/resources/greenPacman.png").getImage());
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
		switch (this.getDirection()) {
			case LEFT:
				this.setPos(new Point(this.getPos().getX() - 1, this.getPos().getY()));
				break;
			case RIGHT:
				this.setPos(new Point(this.getPos().getX() + 1, this.getPos().getY()));
				break;
			case UP:
				this.setPos(new Point(this.getPos().getX(), this.getPos().getY() - 1));
				break;
			case DOWN:
				this.setPos(new Point(this.getPos().getX(), this.getPos().getY() + 1));
				break;
		}
	}

	public void takeHit(int damage) {
		this.life -= damage;
	}

	public void resetLife() {
		this.life = MAX_LIFE;
	}

	public Pacman withFuturePosition(){
		Point futurePosition;
		switch (this.getDirection()) {
			case LEFT:
				futurePosition = new Point(this.pos.getX() - DOT_SIZE, this.getPos().getY());
				return new Pacman(this.life, this.getImage(), futurePosition);
			case RIGHT:
				futurePosition = new Point(this.pos.getX() + DOT_SIZE, this.getPos().getY());
				return new Pacman(this.life, this.getImage(), futurePosition);
			case UP:
				futurePosition = new Point(this.pos.getX(), this.getPos().getY() - DOT_SIZE);
				return new Pacman(this.life, this.getImage(), futurePosition);
			case DOWN:
				futurePosition = new Point(this.pos.getX(), this.getPos().getY() + DOT_SIZE);
				return new Pacman(this.life, this.getImage(), futurePosition);
			default:
				return null;
		}
	}
}
