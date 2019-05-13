package com.ic.unicamp.br.mc322.pacman.game.controller;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Ghost;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Pacman;
import com.ic.unicamp.br.mc322.pacman.game.utilities.Direction;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameController extends BoardController implements ActionListener {

	public static final int DOT_SIZE = 1;
	private static final int DELAY = 15;

	private Pacman pacman = new Pacman();
	private ArrayList<Ghost> ghosts = new ArrayList<>();

	private boolean inGame = true;

	private Timer timer;

	public GameController() {
		super.initBoard();
		initGame();
	}

	private void initGame() {
		timer = new Timer(DELAY, this);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		doDrawing(g);
	}

	public void doDrawing(Graphics g) {
		if (inGame) {
			super.doDrawing(g, pacman);
		} else {
			super.gameOver(g);
		}
	}

	private void move() {
		if (KeyController.leftDirection) {
			pacman.setDirection(Direction.LEFT);
		} else if (KeyController.rightDirection) {
			pacman.setDirection(Direction.RIGHT);
		} else if (KeyController.upDirection) {
			pacman.setDirection(Direction.UP);
		} else if (KeyController.downDirection) {
			pacman.setDirection(Direction.DOWN);
		}

		if (!obstacleController.collisionDetected(pacman.withFuturePosition())) {
			pacman.move();
		}
	}

	private void checkBoardLimitDiyingArea() {
		Point ini = pacman.getPos();

		if (ini.getY() + pacman.getSize()/2 >= B_HEIGHT) {
			inGame = false;
		}

		if (ini.getY() + pacman.getSize()/2 < 0) {
			inGame = false;
		}

		if (ini.getX() + pacman.getSize()/2 >= B_WIDTH) {
			inGame = false;
		}

		if (ini.getX() + pacman.getSize() / 2 < 0) {
			inGame = false;
		}

		if (!inGame) {
			timer.stop();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (inGame) {
			checkBoardLimitDiyingArea();
			move();
		}
		repaint();
	}
}
